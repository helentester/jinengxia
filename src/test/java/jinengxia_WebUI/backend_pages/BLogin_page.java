/**
 * @author Helen 
 * @date 2018年4月16日  
 */
package jinengxia_WebUI.backend_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：后台登录页面
 */
public class BLogin_page extends BasePage {

	public BLogin_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "loginform-username")
	private WebElement username;// 用户名

	public void sendkes_username(String s) {
		this.sendkeys(username, s);// 输入用户名
	}

	@FindBy(id = "loginform-password")
	private WebElement password;// 密码

	public void sendkeys_password(String s) {
		this.sendkeys(password, s);
	}

	@FindBy(tagName = "button")
	private WebElement loginBTN;// 登录按钮

	public void click_loginBTN() {
		this.click(loginBTN);
	}

}
