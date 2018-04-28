/**
 * @author Helen 
 * @date 2018年4月28日  
 */
package jinengxia_WebUI.websiteTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BaseData;
import common.BaseWindows;
import common.mysql_conn;
import jinengxia_WebUI.website_pages.TIndex_page;
import jinengxia_WebUI.website_pages.TStudentTeamManager_page;
import jinengxia_WebUI.website_pages.index_page;
/**
 * 描述：教务平台－班级管理业务测试
 */
public class TStudentTeamManagerTest {
	loginTest loginTest = new loginTest();
	WebDriver driver = loginTest.get_driver("helen_student01", "123456");
	BaseData bdata = new BaseData();
	mysql_conn mConn = new mysql_conn();
	BaseWindows windows = new BaseWindows();
	index_page index_page = PageFactory.initElements(driver, index_page.class);
	TIndex_page tIndex_page = PageFactory.initElements(driver, TIndex_page.class);
	TStudentTeamManager_page teamManager_page;//班级管理页面
	
	@BeforeClass(description="进入教务平台，班级管理页面")
	public void interEdu() {
		index_page.click_teacherSYSLink(driver);// 点击“教务工作台”
		windows.changeWindow(driver);// 窗口切换到教务工作台
		tIndex_page.click_courseAtLast();// 点击最后一个技能班
		tIndex_page.click_firstSchedule();// 点击第一个班期（列表最后）
		teamManager_page = PageFactory.initElements(driver, TStudentTeamManager_page.class);
		teamManager_page.click_classManagerLink();//班级管理链接
		
	}

	@Test(description="随机分组")
	public void autoCreateTeam() {
		teamManager_page.click_autoCreateTeamBTN();
	}
}
