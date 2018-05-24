/**
 * @author Helen 
 * @date 2018年4月28日  
 */
package jinengxia_WebUI.websiteTest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.BaseData;
import common.BaseWindows;
import common.mysql_conn;
import jinengxia_WebUI.website_pages.TIndex_page;
import jinengxia_WebUI.website_pages.TStudentTeamManager_page;
import jinengxia_WebUI.website_pages.index_page;
/**
 * 描述：教务平台－班级管理业务测试，这里需要等待课时管理结束后再管理班级，因为班级必须是阶段开始才能管理，而课时必须是阶段未开始才能导入
 */
@Test(groups="TStudentTeamManagerTest",dependsOnGroups="TClassManagerTest")
public class TStudentTeamManagerTest {
	loginTest loginTest = new loginTest();
	WebDriver driver;
	BaseData bdata = new BaseData();
	mysql_conn mConn = new mysql_conn();
	BaseData baseData = new BaseData();
	BaseWindows windows = new BaseWindows();
	index_page index_page;//前台主页
	TIndex_page tIndex_page;//老师工作台主页
	TStudentTeamManager_page teamManager_page;//班级管理页面
	String course_id;//课程ID
	String schedule;//班期ID

	@Test(description="随机分组")
	public void autoCreateTeam(){
		driver = loginTest.get_driver("helen_student01", "123456");
		index_page = PageFactory.initElements(driver, index_page.class);
		index_page.click_teacherSYSLink(driver);// 点击“教务工作台”
		windows.changeWindow(driver);// 窗口切换到教务工作台
		tIndex_page = PageFactory.initElements(driver, TIndex_page.class);
		tIndex_page.click_courseAtLast();// 点击最后一个技能班
		//设置第一个班期的第一个阶段开始结束时间
		course_id=baseData.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);
		mConn.updateData("UPDATE course_stage SET start_time=UNIX_TIMESTAMP('"+bdata.getTimeByMonthsAndDays(0, -3)+"') where course_id="+course_id+" ORDER BY id ASC LIMIT 1");
		tIndex_page.click_firstSchedule();// 点击第一个班期（列表最后）
		teamManager_page = PageFactory.initElements(driver, TStudentTeamManager_page.class);
		teamManager_page.click_classManagerLink();//班级管理链接
		//随机分组
		teamManager_page.click_autoCreateTeamBTN();
		teamManager_page.sendkeys_maxTeamNO("2");//设置每组最大人数
		teamManager_page.click_submitAutoCreateTeam();//开始分组按钮
		schedule = baseData.getTargetList(driver.getCurrentUrl(), "(\\d+)(\\d+)").get(1);
		String maxNO =mConn.getData("SELECT COUNT(*) as stuCount from course_schedule_group_has_student where course_schedule_id="+schedule+" GROUP BY course_schedule_group_id ORDER BY stuCount DESC LIMIT 1", "stuCount").get(0);
		assertEquals(maxNO, "2");//判断最大组人数是不是2
		driver.quit();
	}
}
