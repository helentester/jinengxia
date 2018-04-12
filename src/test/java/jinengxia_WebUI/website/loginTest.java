package jinengxia_WebUI.website;

import org.testng.annotations.Test;

import jinengxia_WebUI.website_pages.index_page;
import jinengxia_WebUI.website_pages.login_page;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class loginTest {
	
	public WebDriver get_driver() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://dev.jinengxia.com/");
		driver.manage().window().maximize();
		// 登录操作
		login_page login_page = PageFactory.initElements(driver, login_page.class);
		index_page index_page = PageFactory.initElements(driver, index_page.class);
		index_page.click_loginBTN();
		login_page.sendkeys_username("lanna100");
		login_page.sendkes_password("123456");
		login_page.click_btn();
		assertTrue(index_page.get_myImg().isDisplayed());
		return driver;
	}

	@Test
	public void login() {
		 WebDriver driver=this.get_driver();
		 index_page index_page = PageFactory.initElements(driver, index_page.class);
		 assertTrue(index_page.get_myImg().isDisplayed());
		 driver.quit();
	}

	@Test
	public void loginOut() {
		WebDriver driver = this.get_driver();
		// 退出登录操作
		index_page index_page = PageFactory.initElements(driver, index_page.class);
		Actions actions = new Actions(driver);
		actions.moveToElement(index_page.get_myImg()).perform();
		index_page.click_loginOut();
		assertTrue(index_page.loginBTN_displayed());
		driver.quit();
	}


}
