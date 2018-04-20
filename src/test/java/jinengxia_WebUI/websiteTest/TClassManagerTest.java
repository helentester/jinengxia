package jinengxia_WebUI.websiteTest;

import org.testng.annotations.Test;

import common.BaseData;
import common.BaseWindows;
import common.mysql_conn;
import jinengxia_WebUI.website_pages.TClass_page;
import jinengxia_WebUI.website_pages.TIndex_page;
import jinengxia_WebUI.website_pages.index_page;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

public class TClassManagerTest {
	loginTest loginTest = new loginTest();
	WebDriver driver = loginTest.get_driver("helen_student01","123456");
	BaseData bdata = new BaseData();
	mysql_conn mConn = new mysql_conn();
	BaseWindows windows = new BaseWindows();
	index_page index_page = PageFactory.initElements(driver, index_page.class);
	TIndex_page tIndex_page=PageFactory.initElements(driver, TIndex_page.class);
	TClass_page class_page = PageFactory.initElements(driver, TClass_page.class);

	@BeforeClass
	public void beforeClass() {
		index_page.click_teacherSYSLink(driver);//点击“教务工作台”
		windows.changeWindow(driver);//窗口切换到教务工作台
		tIndex_page.click_courseAtLast();//点击最后一个技能班
		tIndex_page.click_firstSchedule();//点击第一个班期（列表最后）
		tIndex_page.click_classLink();//第一个阶段的课时管理链接
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	@Test(description="导入课时列表",enabled=false)
	public void inputClassList() throws IOException {
		class_page.sendkeys_inputFile(bdata.getFilePath("data/classList.xlsx"));//不知何故，这里不能用相对路径，必须用绝对路径，所以要转换一下取得绝对路径才能上传上功
		String stageId=bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);
		String periodCount=mConn.getData("SELECT COUNT(*)as periodCount FROM stage_period where stage_id="+stageId, "periodCount");
		assertTrue(Integer.parseInt(periodCount)==8);
	}
	
	@Test(description="编辑阅读课内容")
	public void readMassageEdit() throws IOException {
		class_page.click_clearCheck();//清空已构选的内容
		class_page.click_checkRead();//构选“阅读”多选框
		class_page.click_searchBTN();//查询按钮
		class_page.click_editBTN();//内容管理按钮
		class_page.sendkeys_editor(driver,"阅读课呀");//输入内容
		class_page.sendkeys_inputMassageFile(bdata.getFilePath("data/测试用导入.rar"));//导入rar文件
		class_page.sendkeys_inputMassageFile(bdata.getFilePath("data/env.tar"));//导入tar文件
		class_page.sendkeys_inputMassageFile(bdata.getFilePath("data/interface_demo.zip"));//导入zip文件
		class_page.click_submitBTN();//保存
	}
}
