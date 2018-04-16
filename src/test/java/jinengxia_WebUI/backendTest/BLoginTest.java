/**
 * @author Helen 
 * @date 2018年4月16日  
 */
package jinengxia_WebUI.backendTest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import common.BaseWindows;
import jinengxia_WebUI.backend_pages.BIndex_page;
import jinengxia_WebUI.backend_pages.BLogin_page;
/**
 * 描述：后台登录测试
 */
public class BLoginTest {
	
	private WebDriver login() {
		BaseWindows windows = new BaseWindows();
		WebDriver driver = windows.get_driver("https://backend.dev.jinengxia.com");
		BLogin_page bLogin_page = PageFactory.initElements(driver, BLogin_page.class);
		bLogin_page.sendkes_username("xsteach");
		bLogin_page.sendkeys_password("xsteach");
		bLogin_page.click_loginBTN();
		BIndex_page index_page = PageFactory.initElements(driver, BIndex_page.class);
		assertEquals(index_page.get_h1Text(), "技能侠教务工作平台");
		return driver;
	}

  @Test(description="后台登录")
  public void BLogin() {
	  WebDriver driver=this.login();
	  driver.quit();
  }
  

}
