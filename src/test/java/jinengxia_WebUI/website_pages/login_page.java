/**
 * @author Helen 
 * @date 2018年4月9日  
 */
package jinengxia_WebUI.website_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：
 */
public class login_page extends BasePage {

	public login_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "loginform-username")
	private WebElement username;// 用户名
	public void sendkeys_username(String s) {
		this.sendkeys(username, s);
	}

	@FindBy(id = "loginform-password")
	private WebElement password;// 密码
	public void sendkes_password(String s) {
		this.sendkeys(password, s);
	}

	@FindBy(xpath = ".//*[@id='w0']/div[4]/button")
	private WebElement btn;//登录按钮
	public void click_btn() {
		this.click(btn);
	}

}
