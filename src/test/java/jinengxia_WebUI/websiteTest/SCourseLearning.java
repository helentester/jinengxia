/**
 * @author Helen 
 * @date 2018年4月24日  
 */
package jinengxia_WebUI.websiteTest;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.html.Image;

import common.BaseData;
import common.mysql_conn;
import jinengxia_WebUI.website_pages.index_page;
import jinengxia_WebUI.website_pages.myCourse_page;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

/**
 * 描述：技能班学习
 */
public class SCourseLearning {
	loginTest loginTest = new loginTest();
	mysql_conn mConn = new mysql_conn();
	BaseData baseData = new BaseData();
	WebDriver driver;
	index_page index_page;
	myCourse_page Course_page;
	

	@Test
	public void f() {
	}

	@BeforeClass(description="进入我的技能班列表，并设置阶段开始时间")
	public void beforeClass() {
		driver = loginTest.get_driver("du001", "123456");
		index_page = PageFactory.initElements(driver, index_page.class);
		Course_page = PageFactory.initElements(driver, myCourse_page.class);
		index_page.click_myCourseLink();//点击我的技能班
		String schedule_id = baseData.getTargetList(Course_page.get_myCourseLink(), "\\d+").get(0);//获取班期ID
		mConn.updateData("");//设计阶段的开始时间
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
