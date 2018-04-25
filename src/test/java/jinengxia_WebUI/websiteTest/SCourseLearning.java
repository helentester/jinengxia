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
	index_page index_page = PageFactory.initElements(driver, index_page.class);//前台首页
	myCourse_page course_page= PageFactory.initElements(driver, myCourse_page.class);//技能班学习面
	String studentUID;//学员UID
	String schedule_id;//班期ID
	
	@Test(description="提交作业")
	public void submitTask() throws IOException {
		//登录前台
		driver = loginTest.get_driver("du002", "123456");
		index_page.click_myCourseLink();// 点击我的技能班
		//切换到我的技能班页面
		windows.changeWindow(driver);
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
	
	@Test(description="老师批改作业",enabled=false)
	public void correctTask() {
		driver = loginTest.get_driver("helen_student01", "123456");
		driver.get("https://dev.jinengxia.com/edu/period/task-view?id=164&period_id=1102");
		
		
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
