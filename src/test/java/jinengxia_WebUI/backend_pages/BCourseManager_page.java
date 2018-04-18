/**
 * @author Helen 
 * @date 2018年4月18日  
 */
package jinengxia_WebUI.backend_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：技能班管理页面
 */
public class BCourseManager_page extends BasePage{

	public BCourseManager_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*********技能班列表页面*************/
	@FindBy(linkText="添加技能班")
	private WebElement createCourseBTN;//添加技能班按钮
	public void click_createCourseBTN() {
		this.click(createCourseBTN);
	}
	
	@FindBy(xpath=".//*[@id='w0']/table/tbody/tr[1]/td[2]")
	private WebElement courseNameAtList;//列表第一个技能班的名称
	public String get_courseNameAtList() {
		return this.findMyElement(courseNameAtList).getText();//返回技能班名称
	}
	
	@FindBy(xpath=".//*[@id='w0']/table/tbody/tr[1]/td[4]/a[1]")
	private WebElement teacherLink;//技能班列表，排在第一位的技能班的“老师管理”链接
	public void click_teacherLink() {
		this.click(teacherLink);
	}
	
	@FindBy(xpath=".//*[@id='w0']/table/tbody/tr[1]/td[4]/a[2]")
	private WebElement ScoreLink;//列表第一个技能班的－评分维度管理
	public void click_ScoreLink() {
		this.click(ScoreLink);
	}
	
	@FindBy(xpath=".//*[@id='w0']/table/tbody/tr[1]/td[4]/a[3]")
	private WebElement scheduleLink;//列表第一个技能班的：班期管理
	public void click_scheduleLink() {
		this.click(scheduleLink);
	}
	
	@FindBy(xpath=".//*[@id='w0']/table/tbody/tr[1]/td[4]/a[4]")
	private WebElement studentListLink;//列表第一个技能班的：学员管理
	public void click_() {
		this.click(studentListLink);
	}
	
	/*************技能班编辑页面**********************/
	@FindBy(id="courseform-name")
	private WebElement courseName;//技能班名称
	public void sendkeys_courseName(String s) {
		this.sendkeys(courseName, s);
	}
	
	@FindBy(id="courseform-stage")
	private WebElement courseStage;//阶段数
	public void select_courseStage(String s) {
		this.selectValue(courseStage, "2");
	}
	
	@FindBy(className="btn-success")
	private WebElement SubmitBTN;//保存按钮
	public void click_SubmitBTN() {
		this.click(SubmitBTN);
	}
	
	/************技能班的老师管理页面*****************/
	@FindBy(linkText="添加老师")
	private WebElement addTeachBTN;//添加老师按钮
	public void click_addTeachBTN() {
		this.click(addTeachBTN);
	}
	
	@FindBy(xpath=".//*[@id='w0']/table/tbody/tr/td[2]")
	private WebElement teachNameAtCourse;//列表中第一个老师的名称
	public String get_teachNameAtCourse() {
		return this.findMyElement(teachNameAtCourse).getText();
	}
	
	/***********技能班的老师编辑页面***************/
	@FindBy(tagName="input")
	private WebElement inputTeacherName;//老师名称输入框
	public void sendkeys_inputTeacherName(String s) {
		this.sendkeys(inputTeacherName, s);
	}
	
	@FindBy(tagName=".//*[@id='w0']/div[1]/div/span[2]/span[1]/span/ul/li[1]")
	private WebElement selectTeachName;//查询到的第一个老师
	public void click_selectTeachName() {
		this.click(selectTeachName);//选择
	}
	
	/***********评分维度管理页面****************/
	@FindBy(linkText="添加评分纬度")
	private WebElement addScoreBTN;//添加评分维度按钮
	public void click_addScoreBTN() {
		this.click(addScoreBTN);
	}
	
	@FindBy(xpath=".//*[@id='w1']/table/tbody/tr[1]/td[2]")
	private WebElement ScoreName1AtList;//列表第一个维度名称
	public String get_ScoreName1AtList() {
		return this.findMyElement(ScoreName1AtList).getText();
	}
	
	@FindBy(xpath=".//*[@id='w1']/table/tbody/tr[2]/td[2]")
	private WebElement ScoreName2AtList;//列表第二个维度名称
	public String get_ScoreName2AtList() {
		return this.findMyElement(ScoreName2AtList).getText();
	}
	
	@FindBy(id="pass_score")
	private WebElement passScore;//通关分值
	public void sendKeys_passScore(String s) {
		this.sendkeys(passScore, s);
	}
	
	@FindBy(className="btn-primary")
	private WebElement passScoreBTN;//设置通关分值按钮
	public void click_passScoreBTN() {
		this.click(passScoreBTN);
	}
	
	/*********评分维度编辑页面*****************/
	@FindBy(id="coursescoreform-name")
	private WebElement scoreName;//评分维度名称
	public void sendkeys_scoreName(String s) {
		this.sendkeys(scoreName, s);
	}
	
	@FindBy(id="coursescoreform-stars")
	private WebElement scoreStars;//星数
	public void select_scoreStars(String s) {
		this.selectValue(scoreStars, s);
	}
	
	@FindBy(id="coursescoreform-score")
	private WebElement score;//分数
	public void sendkeys_score(String s) {
		this.sendkeys(score, s);
	}
}
