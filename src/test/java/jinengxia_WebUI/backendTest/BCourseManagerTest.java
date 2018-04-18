/**
 * @author Helen 
 * @date 2018年4月18日  
 */
package jinengxia_WebUI.backendTest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import common.BaseData;
import common.mysql_conn;
import jinengxia_WebUI.backend_pages.BCourseManager_page;
import jinengxia_WebUI.backend_pages.BIndex_page;

/**
 * 描述：后台技能班管理测试
 */
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
	
	@Test(description = "新增技能班")
	public void addCourse() {
		//首页
		index_page.click_CourseManager();// 点击技能班管理链接
		//技能班列表页面
		courseManager_page.click_createCourseBTN();// 点击“添加技能班”按钮
		//编辑技能班页面
		String courseName = bdata.getRandomName("技能班");
		courseManager_page.sendkeys_courseName(courseName);// 技能班名称
		courseManager_page.select_courseStage("2");// 阶段数
		courseManager_page.click_SubmitBTN();// 保存
		assertEquals(courseManager_page.get_courseNameAtList(), courseName);
	}
	
	@Test(description="给技能班添加老师",dependsOnMethods="addCourse")
	public void addTeacher() {
		//技能班列表页面
		index_page.click_CourseManager();
		courseManager_page.click_teacherLink();//点击老师管理
		//技能班的老师列表页面
		courseManager_page.click_addTeachBTN();//点击添加老师按钮
		//技能班添加老师页面
		courseManager_page.sendkeys_inputTeacherName("helen老师");//输入老师名称查询老师
		courseManager_page.click_selectTeachName();//选择第一个老师
		courseManager_page.click_SubmitBTN();//保存
		assertEquals(courseManager_page.get_teachNameAtCourse(), "helen老师");
	}
	
	@Test(description="给技能班设置评分维度",dependsOnMethods="addCourse")
	public void addCourseScore() {
		//技能班列表页面
		index_page.click_CourseManager();
		courseManager_page.click_ScoreLink();//点击“评分维度管理”
		
		/**添加第一个维度**/
		//评分维度列表页面
		courseManager_page.click_addScoreBTN();//添加评分维度按钮
		//评分维度编辑页面
		courseManager_page.sendkeys_scoreName("灵感");//维度名称
		courseManager_page.select_scoreStars("10");//星数
		courseManager_page.sendkeys_score("100");//分数
		courseManager_page.click_SubmitBTN();//保存
		assertEquals(courseManager_page.get_ScoreName1AtList(), "灵感");
		
		/*添加第二个维度（默认值）*/
		//评分维度列表页面
		courseManager_page.click_addScoreBTN();
		//评分维度编辑页面
		courseManager_page.sendkeys_scoreName("创新");//维度名称
		courseManager_page.click_SubmitBTN();//保存
		assertEquals(courseManager_page.get_ScoreName2AtList(), "创新");
		
		/*设置通关分值*/
		courseManager_page.sendKeys_passScore("66");//通关分值
		courseManager_page.click_passScoreBTN();//点击“设置通关分值”按钮
		String courseId = bdata.getTargetWord(driver.getCurrentUrl(), "\\d+");//通过正则，从URL获取当前技能班的id
		String passScore = mConn.getData("SELECT pass_score from course where id="+courseId, "pass_score");
		assertEquals(passScore, "66");//从数据库中读取已保存在技能班中的通关分值来判断是否设置成功
	}
}
