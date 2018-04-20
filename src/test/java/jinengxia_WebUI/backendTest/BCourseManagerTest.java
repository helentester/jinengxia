/**
 * @author Helen 
 * @date 2018年4月18日  
 */
package jinengxia_WebUI.backendTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import common.BaseData;
import common.mysql_conn;
import jinengxia_WebUI.backend_pages.BCourseManager_page;
import jinengxia_WebUI.backend_pages.BIndex_page;

/**
 * 描述：后台技能班管理测试
 */
@Test(groups="BCourseManagerTest")
public class BCourseManagerTest {
	WebDriver driver;
	BaseData bdata = new BaseData();
	mysql_conn mConn = new mysql_conn();
	BIndex_page index_page;
	BCourseManager_page courseManager_page;

	@BeforeClass
	public void openBrowser() {
		BLoginTest loginTest = new BLoginTest();
		driver = loginTest.login();
		index_page = PageFactory.initElements(driver, BIndex_page.class);
		courseManager_page = PageFactory.initElements(driver, BCourseManager_page.class);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	@Test(description = "新增技能班")
	public void addCourse() {
		// 首页
		index_page.click_CourseManager();// 点击技能班管理链接
		// 技能班列表页面
		courseManager_page.click_createCourseBTN();// 点击“添加技能班”按钮
		// 编辑技能班页面
		String courseName = bdata.getRandomName("技能班");
		courseManager_page.sendkeys_courseName(courseName);// 技能班名称
		courseManager_page.select_courseStage("2");// 阶段数
		courseManager_page.click_SubmitBTN();// 保存
		assertEquals(courseManager_page.get_courseNameAtList(), courseName);
	}

	@Test(description = "给技能班添加老师", dependsOnMethods = "addCourse")
	public void addTeacher() throws InterruptedException {
		// 技能班列表页面
		index_page.click_CourseManager();
		courseManager_page.click_teacherLink();// 点击老师管理
		// 技能班的老师列表页面
		courseManager_page.click_addTeachBTN();// 点击添加老师按钮
		// 技能班添加老师页面
		courseManager_page.sendkeys_inputTeacherName("helen老师");// 输入老师名称查询老师
		Thread.sleep(1000);// 这里需要等待下面的控件加载完成
		courseManager_page.click_selectTeachName();// 选择第一个老师
		courseManager_page.click_SubmitBTN();// 保存
		assertEquals(courseManager_page.get_teachNameAtCourse(), "helen老师");
	}

	@Test(description = "给技能班设置评分维度", dependsOnMethods = "addCourse")
	public void addCourseScore() {
		// 技能班列表页面
		index_page.click_CourseManager();
		courseManager_page.click_ScoreLink();// 点击“评分维度管理”

		/** 添加第一个维度 **/
		// 评分维度列表页面
		courseManager_page.click_addScoreBTN();// 添加评分维度按钮
		// 评分维度编辑页面
		courseManager_page.sendkeys_scoreName("灵感");// 维度名称
		courseManager_page.select_scoreStars("10");// 星数
		courseManager_page.sendkeys_score("100");// 分数
		courseManager_page.click_SubmitBTN();// 保存
		assertEquals(courseManager_page.get_ScoreName1AtList(), "灵感");

		/* 添加第二个维度（默认值） */
		// 评分维度列表页面
		courseManager_page.click_addScoreBTN();
		// 评分维度编辑页面
		courseManager_page.sendkeys_scoreName("创新");// 维度名称
		courseManager_page.click_SubmitBTN();// 保存
		assertEquals(courseManager_page.get_ScoreName2AtList(), "创新");

		/* 设置通关分值 */
		courseManager_page.sendKeys_passScore("66");// 通关分值
		courseManager_page.click_passScoreBTN();// 点击“设置通关分值”按钮
		String courseId = bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);// 通过正则，从URL获取当前技能班的id
		String passScore = mConn.getData("SELECT pass_score from course where id=" + courseId, "pass_score");
		assertEquals(passScore, "66");// 从数据库中读取已保存在技能班中的通关分值来判断是否设置成功
	}

	@Test(description = "给技能班设置班期",dependsOnMethods="addCourse")
	public void addSchedule() throws InterruptedException {
		// 技能班列表页面
		index_page.click_CourseManager();
		courseManager_page.click_scheduleLink();//班期管理链接
		
		//班期列表和编辑页面，添加两个班期
		for(int g=0;g<2;g++) {
			courseManager_page.click_addScheduleBTN();//添加班期按钮
			courseManager_page.click_SubmitBTN();//所有采用默认值，直接保存即可
		}
		String courseId = bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);//获取当前技能班id
		String ScheduleCount = mConn.getData("SELECT COUNT(*)as ScheduleCount FROM course_schedule WHERE course_id="+courseId, "ScheduleCount");
		assertTrue(Integer.parseInt(ScheduleCount)==2);//判断是否有两个班期

		//阶段管理和编辑页面，添加两个阶段
		courseManager_page.click_stageLink();//点击“阶段管理”链接
		for(int i=0;i<2;i++) {//为两个查询添加阶段
			//阶段列表页面
			courseManager_page.select_scheduleAtStageList(i);//班期关键词
			courseManager_page.click_searchBTN();//查询按钮
			Thread.sleep(1000);
			for (int h = 0; h < 2; h++) {
				this.addStage(h);
			}
			String ScheduleId=bdata.getTargetList(driver.getCurrentUrl(), "(\\d+)(\\d+)").get(1);//从URL中获取当前班期的ID
			String stageCount = mConn.getData("SELECT COUNT(*)as stageCount FROM course_stage WHERE course_schedule_id=56", "stageCount");
			assertTrue(Integer.parseInt(stageCount)==2);//判断是否有两个阶段
		}
		
		
	}
	
	@Test(description="给技能班添加学员",dependsOnMethods="addCourse")
	public void addStudents() {
		index_page.click_CourseManager();//进入技能班管理页面
		courseManager_page.click_studentListLink();//点击学员管理列表
		//学员列表页面
		courseManager_page.click_addStudentBTN();//点击添加学员按钮
		courseManager_page.sendkeys_studentID("9938924\n" + 
				"9938894\n" + 
				"9938895\n" + 
				"9938896\n"+
				"9938897\n"+
				"9938898\n"+
				"9938899\n"+
				"9938900");//输入学员ID列表
		courseManager_page.select_scheduleAtStudent("1");//选择班期
		courseManager_page.click_SubmitBTN();//保存
		String course_id = bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);
		String studentCount = mConn.getData("SELECT COUNT(*) as studentCount FROM course_schedule_has_student WHERE course_id="+course_id, "studentCount");
		assertTrue(Integer.parseInt(studentCount)==8);
	}
	
	/*添加阶段*/
	private void addStage(int months) {
		courseManager_page.click_addStageBTN();//添加阶段按钮
		//阶段编辑页面
		courseManager_page.sendkeys_stageStart(bdata.getTimeByMonthsAndDays(months,1));//阶段开始时间
		courseManager_page.sendkeys_stageEnd(bdata.getTimeByMonthsAndDays(months+1,0));//阶段结束时间
		courseManager_page.click_SubmitBTN();//保存
	}
}
