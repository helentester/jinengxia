/**
 * @author Helen 
 * @date 2018年4月9日  
 */
package jinengxia_WebUI.website_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：网站首页http://dev.jinengxia.com
 */
public class index_page extends BasePage {

	public index_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = ".//*[@id='header']/div/ul/li[2]/a[2]")
	private WebElement loginBTN;// 登录按
	public void click_loginBTN() {
		this.click(loginBTN);// 点击登录按钮
	}	
	public Boolean loginBTN_displayed() {
		return this.findMyElement(loginBTN).isDisplayed();// 判断登录按钮是否存在
	}

	@FindBy(xpath = ".//*[@id='header']/div/ul/li[3]/a/img")
	private WebElement myImg;// 用户头像
	public WebElement get_myImg() {
		return this.findMyElement(myImg);// 获取用户头像
	}
	public void click_myImg() {
		this.click(myImg);//点击用户头像
	}
	
	@FindBy(linkText="教务工作台")
	private WebElement teacherSYSLink;//教务工作台链接
	public void click_teacherSYSLink(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(this.get_myImg()).perform();
		this.click(teacherSYSLink);
	}

	@FindBy(linkText = "查看课程详情")
	private WebElement courseLink;// 课程详情链接
	public void click_courseLink() {
		this.click(courseLink);// 点击“课程详情”页面
	}

	@FindBy(linkText = "退出")
	private WebElement loginOut;// 退出按钮
	public void click_loginOut(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(this.get_myImg()).perform();
		this.click(loginOut);// 点击退出按钮
	}

}
