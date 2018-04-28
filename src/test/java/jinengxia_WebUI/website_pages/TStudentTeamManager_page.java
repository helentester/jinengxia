/**
 * @author Helen 
 * @date 2018年4月28日  
 */
package jinengxia_WebUI.website_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：教务平台－班级管理页面
 */
public class TStudentTeamManager_page extends BasePage{

	public TStudentTeamManager_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="html/body/div[1]/section/main/div/ul/li[1]/div/p[2]/a[4]")
	private WebElement classManagerLink;//班级管理链接
	public void click_classManagerLink() {
		this.click(classManagerLink);
	}
	
	@FindBy(linkText="一键随机分组")
	private WebElement autoCreateTeamBTN;//“一键随机分组”按钮
	public void click_autoCreateTeamBTN() {
		this.click(autoCreateTeamBTN);
	}
	
	@FindBy(className="form-control")
	private WebElement maxTeamNO;//随权分组－每组最大人数
	public void sendkeys_maxTeamNO(String s) {
		this.sendkeys(maxTeamNO, s);
	}
	
	@FindBy(xpath=".//*[@id='app']/div/section/div[2]/div[2]/button")
	private WebElement submitAutoCreateTeam;//随机分组－开始分组
	public void click_submitAutoCreateTeam() {
		this.click(submitAutoCreateTeam);
	}

}
