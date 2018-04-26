/**
 * @author Helen 
 * @date 2018年4月24日  
 */
package jinengxia_WebUI.websiteTest;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.html.Image;

import common.BaseData;
import common.BaseWindows;
import common.mysql_conn;
import jinengxia_WebUI.website_pages.TCorrectTask_page;
import jinengxia_WebUI.website_pages.TIndex_page;
import jinengxia_WebUI.website_pages.course_page;
import jinengxia_WebUI.website_pages.index_page;
import jinengxia_WebUI.website_pages.myCourse_page;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

/**
 * 描述：技能班提交作业和批改作业
 */
public class SCourseLearning {
	loginTest loginTest = new loginTest();
	mysql_conn mConn = new mysql_conn();
	BaseData baseData = new BaseData();
	BaseWindows windows = new BaseWindows();
	WebDriver driver;
	index_page index_page;//前台首页
	myCourse_page course_page;//技能班学习面
	TCorrectTask_page correctTask_page;//老师批改作业页面
	String studentUID;//学员UID
	String schedule_id;//班期ID
	String period_id;//课时ID
	
	@Test(description="提交作业",enabled=false)
	public void submitTask() throws IOException {
		//登录前台
		driver = loginTest.get_driver("du002", "123456");
		index_page = PageFactory.initElements(driver, index_page.class);//前台首页
		index_page.click_myCourseLink();// 点击我的技能班
		//切换到我的技能班页面
		windows.changeWindow(driver);
		course_page= PageFactory.initElements(driver, myCourse_page.class);
		studentUID = baseData.getTargetList(course_page.get_studentUID(), "\\d+").get(0);//获取当前学员UID
		schedule_id = baseData.getTargetList(course_page.get_myCourseLink(), "\\d+").get(0);// 获取班期ID
		// 设计阶段的开始结束时间，使得当前班期是正在学习状态
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
		course_page.sendkeys_taskEditor(driver, "学员UID＝"+studentUID+"的作业");
		course_page.sendkeys_taskFile(baseData.getFilePath("testFile/测试用导入.rar"));
		course_page.sendkeys_taskFile(baseData.getFilePath("testFile/env.tar"));
		course_page.sendkeys_taskFile(baseData.getFilePath("testFile/interface_demo.zip"));
		course_page.click_submitBTN();
		//assertEquals(false, course_page.get_submitBTN());//判断提交按钮此时应当是不可点击的
		assertEquals("待批改", course_page.get_taskType());
		driver.quit();
	}
	
	@Test(description="老师批改作业")
	public void correctTask() {
		driver = loginTest.get_driver("helen_student01", "123456");
		period_id="1102";
		String userTaskID = mConn.getData("SELECT id from user_period_task where period_id="+period_id+" and `status` in(2,3) ORDER BY id DESC LIMIT 1;", "id").get(0);//学员作业ID
		//直接进入批改作业页面，批改第一个作业
		driver.get("https://dev.jinengxia.com/edu/period/task-view?id="+userTaskID+"&period_id="+period_id);
		driver.navigate().refresh();
		correctTask_page = PageFactory.initElements(driver, TCorrectTask_page.class);//批改作业页面
		correctTask_page.click_start1();
		correctTask_page.click_start2();
		//获取分类
		int score1 = Integer.parseInt(baseData.getTargetList(correctTask_page.get_start1Score(), "\\d+").get(0)) ;//获取第一个评分
		int score2 = Integer.parseInt(baseData.getTargetList(correctTask_page.get_start2Score(), "\\d+").get(0)) ;//获取第二个评分
		int score = Integer.parseInt(correctTask_page.get_score());//总得分
		assertEquals(score, score1+score2);//判断所有得分是不是总分之和
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}