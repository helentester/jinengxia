/**
 * @author Helen 
 * @date 2018年4月26日  
 */
package jinengxia_WebUI.website_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：工作台－批改作业页面
 */
public class TCorrectTask_page extends BasePage{

	public TCorrectTask_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="html/body/div[1]/section/main/div[1]/div/div[2]/div[1]/ul/li[1]/span[2]/i[10]")
	private WebElement start1;//第一个评分维度满星10个
	public void click_start1() {
		this.click(start1);
	}
	
	@FindBy(xpath="html/body/div[1]/section/main/div[1]/div/div[2]/div[1]/ul/li[1]/span[3]")
	private WebElement start1Score;//第一个评分维度的评分
	public String get_start1Score() {
		return this.findMyElement(start1Score).getText();
	}
	
	@FindBy(xpath="html/body/div[1]/section/main/div[1]/div/div[2]/div[1]/ul/li[2]/span[2]/i[5]")
	private WebElement start2;//第二个评分维度满星5个
	public void click_start2() {
		this.click(start2);
	}
	
	@FindBy(xpath="html/body/div[1]/section/main/div[1]/div/div[2]/div[1]/ul/li[2]/span[3]")
	private WebElement start2Score;//第二个评分维度的评分
	public String get_start2Score() {
		return this.findMyElement(start2Score).getText();
	}
	
	@FindBy(xpath="html/body/div[1]/section/main/div[1]/div/div[2]/div[1]/div/span")
	private WebElement score;//总得分
	public String get_score() {
		return this.findMyElement(score).getText();
	}
	
	@FindBy(className="current-comment-text")
	private WebElement teacherNote;//老师评语
	public void sendkeys_teacherNote(String s) {
		this.sendkeys(teacherNote, s);
	}
	
	@FindBy(className="btn-correcting")
	private WebElement submitBTN;//确认批改按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}

}
