/**
 * @author Helen 
 * @date 2018年4月24日  
 */
package jinengxia_WebUI.websiteTest;

import org.testng.annotations.Test;

import common.BaseData;
import common.BaseWindows;
import common.mysql_conn;
import jinengxia_WebUI.website_pages.TCorrectTask_page;
import jinengxia_WebUI.website_pages.TIndex_page;
import jinengxia_WebUI.website_pages.index_page;
import jinengxia_WebUI.website_pages.myCourse_page;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

/**
 * 描述：技能班提交作业和批改作业
 */
@Test(groups="SCourseLearning",description="学员提交作业，老师批改作业",dependsOnGroups="TClassManagerTest")
public class SCourseLearning {
	loginTest loginTest = new loginTest();
	mysql_conn mConn = new mysql_conn();
	BaseData baseData = new BaseData();
	BaseWindows windows = new BaseWindows();
	WebDriver driver;
	index_page index_page;//前台首页
	myCourse_page course_page;//技能班学习面
	TIndex_page tIndex_page;//教务工作台首页
	TCorrectTask_page correctTask_page;//老师批改作业页面
	String course_id;//课程ID
	String studentUID;//学员UID
	String schedule_id;//班期ID
	String period_id;//课时ID
	
	@AfterClass
	public void driverQuit() {
		driver.quit();
	}
	
	@Test(description="多个学员提交作业")
	public void submitTaskByList() throws IOException {
		List<String> studentList=new ArrayList<String>();
		studentList.add("du001");
		studentList.add("du002");
		studentList.add("du003");
		studentList.add("du004");
		studentList.add("du005");
		studentList.add("du006");
		studentList.add("du007");
		for(int i=0;i<studentList.size();i++) {
			//submitTask(studentList.get(i));
			//登录前台
			driver = loginTest.get_driver(studentList.get(i), "123456");
			index_page = PageFactory.initElements(driver, index_page.class);//前台首页
			index_page.click_myCourseLink();// 点击我的技能班
			//切换到我的技能班页面
			windows.changeWindow(driver);
			course_page= PageFactory.initElements(driver, myCourse_page.class);
			studentUID = baseData.getTargetList(course_page.get_studentUID(), "\\d+").get(0);//获取当前学员UID
			schedule_id = baseData.getTargetList(course_page.get_myCourseLink(), "\\d+").get(0);// 获取班期ID
			// 设置阶段的开始结束时间，使得当前班期是正在学习状态
			mConn.updateData("UPDATE course_stage SET start_time=UNIX_TIMESTAMP('" + baseData.getTimeByMonthsAndDays(0, -1)
					+ "'),end_time=UNIX_TIMESTAMP('" + baseData.getTimeByMonthsAndDays(1, 0)
					+ "') where course_schedule_id=" + schedule_id + " ORDER BY id ASC LIMIT 1;");
			course_page.click_myCourseLink();//点击最后一个班期
			//技能班学习首页
			course_page.click_firstStageLink();
			//获取阶段ID和课时ID，直接打开作业页面
			String stage_id = course_page.get_firstStageID();
			String period_id = mConn.getData("SELECT id from stage_period where stage_id="+stage_id+" and type=3 and id not in (SELECT period_id from user_period_task where user_id="+studentUID+" and `status` in(3,4,5)) ORDER BY id ASC LIMIT 1;", "id").get(0);
			driver.get("https://dev.jinengxia.com/user/class/index?schedule_id="+schedule_id+"#"+period_id);//直接进入第一个可提交的作业课时
			driver.navigate().refresh();//需要刷新网页才可以真的打开作业页面
			course_page.click_submitBTN();//打开提交作业窗口
			course_page.sendkeys_taskEditor(driver, "学员UID＝"+studentUID+"的作业");
			course_page.sendkeys_taskFile(baseData.getFilePath("testFile/测试用导入.rar"));
			course_page.sendkeys_taskFile(baseData.getFilePath("testFile/env.tar"));
			course_page.sendkeys_taskFile(baseData.getFilePath("testFile/interface_demo.zip"));
			course_page.click_submitTaskBTN();//提交作业
			assertEquals("待批改", course_page.get_taskType());
			driver.quit();
		}
		
	}
	
	@Test(description="老师批改作业",dependsOnMethods="submitTaskByList")
	public void correctTask() {
		driver = loginTest.get_driver("helen_student01", "123456");
		index_page = PageFactory.initElements(driver, index_page.class);//前台首页
		index_page.click_teacherSYSLink(driver);// 点击“教务工作台”
		windows.changeWindow(driver);// 窗口切换到教务工作台
		tIndex_page = PageFactory.initElements(driver, TIndex_page.class);
		tIndex_page.click_courseAtLast();// 点击最后一个技能班
		tIndex_page.click_firstSchedule();// 点击第一个班期（列表最后）
		schedule_id = baseData.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);//班期ID
		List<String> userTaskID = mConn.getData("SELECT id from user_period_task where period_id in(SELECT id from stage_period WHERE schedule_id="+schedule_id+" and type=3)  and `status` in(2,3);", "id");//学员作业ID
		//直接进入批改作业页面，批改作业
		for (int i = 0; i < userTaskID.size(); i++) {
			period_id=mConn.getData("SELECT period_id from user_period_task WHERE id="+userTaskID.get(i), "period_id").get(0);
			driver.get("https://dev.jinengxia.com/edu/period/task-view?id="+userTaskID.get(i)+"&period_id="+period_id);
			driver.navigate().refresh();
			correctTask_page = PageFactory.initElements(driver, TCorrectTask_page.class);//批改作业页面
			//给第一个评分维度设置一个随机的星级
			int startNO = baseData.getRandomInt(10, 5);
			if(startNO==5) {
				correctTask_page.click_start5();
			}
			else if(startNO==6){
				correctTask_page.click_start6();
			}
			else if(startNO==7){
				correctTask_page.click_start7();
			}
			else if(startNO==8){
				correctTask_page.click_start8();
			}
			else if(startNO==9){
				correctTask_page.click_start9();
			}
			else {
				correctTask_page.click_start1();//设置第一个维度的星级：满星
			}
			//给第二个维度设置满星
			correctTask_page.click_SecondStart5();//设置第二个维度的星级：满星
			correctTask_page.sendkeys_teacherNote("好流B的作品，不用上课了，直接毕业吧！");//输入老师评语
			//获取分数
			int score1 = Integer.parseInt(baseData.getTargetList(correctTask_page.get_start1Score(), "\\d+").get(0)) ;//获取第一个评分
			int score2 = Integer.parseInt(baseData.getTargetList(correctTask_page.get_start2Score(), "\\d+").get(0)) ;//获取第二个评分
			int score = Integer.parseInt(correctTask_page.get_score());//总得分
			//确认批改
			correctTask_page.click_submitBTN();
			//数据库获取数据
			int pass_score = Integer.parseInt(mConn.getData("SELECT pass_score from course where id=(SELECT course_id from course_schedule where id=(SELECT schedule_id from stage_period where id="+period_id+"))", "pass_score").get(0));
			int taskStatu = Integer.parseInt(mConn.getData("SELECT status from user_period_task where id="+userTaskID.get(i), "status").get(0));//数据库中作业的状态
			assertEquals(score, score1+score2);//判断所有得分是不是总分之和
			if(score>=pass_score) {
				assertEquals(taskStatu, 5);//判断作业的状态是否已批改：5＝通过
			}
			else {
				assertEquals(taskStatu, 6);//判断作业的状态是否已批改：6＝不通过
			}
			
		}
			
		driver.quit();
	}
	


}
