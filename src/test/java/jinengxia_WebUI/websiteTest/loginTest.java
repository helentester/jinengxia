package jinengxia_WebUI.websiteTest;

import org.testng.annotations.Test;

import common.BaseWindows;
import jinengxia_WebUI.website_pages.index_page;
import jinengxia_WebUI.website_pages.login_page;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class loginTest {
	
	public WebDriver get_driver(String username,String password) {
		BaseWindows windows = new BaseWindows();
		WebDriver driver = windows.get_driver("http://dev.jinengxia.com/");
		// 登录操作
		login_page login_page = PageFactory.initElements(driver, login_page.class);
		index_page index_page = PageFactory.initElements(driver, index_page.class);
		index_page.click_loginBTN();
		login_page.sendkeys_username(username);
		login_page.sendkes_password(password);
		login_page.click_btn();
		assertTrue(index_page.get_myImg().isDisplayed());
		return driver;
	}

	@Test(groups= {"login"},description="前台官网登录")
	public void login() {
		 WebDriver driver=this.get_driver("lanna100","123456");
		 index_page index_page = PageFactory.initElements(driver, index_page.class);
		 assertTrue(index_page.get_myImg().isDisplayed());
		 driver.quit();
	}

	@Test(groups= {"login"},description="前台官网退出登录")
	public void loginOut() {
		WebDriver driver = this.get_driver("lanna100","123456");
		// 退出登录操作
		index_page index_page = PageFactory.initElements(driver, index_page.class);
		index_page.click_loginOut(driver);
		assertTrue(index_page.loginBTN_displayed());
		driver.quit();
	}


}
