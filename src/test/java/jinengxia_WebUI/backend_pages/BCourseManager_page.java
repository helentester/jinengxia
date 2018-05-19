/**
 * @author Helen 
 * @date 2018年4月18日  
 */
package jinengxia_WebUI.backend_pages;

import java.sql.Time;

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
	public void click_studentListLink() {
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
		this.selectValue(courseStage, "2","ByValue");
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
	@FindBy(className="select2-search__field")
	private WebElement inputTeacherName;//老师名称输入框
	public void sendkeys_inputTeacherName(String s) {
		this.sendkeys(inputTeacherName, s);
	}
	
	@FindBy(xpath=".//*[@id='select2-coursehasteacherform-teacher_id-results']/li[1]")//这里被坑了，li的后面记得加下标
	private WebElement selectTeachName;//查询到的第一个老师
	public void click_selectTeachName() {
		this.click(selectTeachName);//选择
	}
	
	@FindBy(xpath=".//*[@id='w0']/div[2]/div/button[1]")
	private WebElement submitTeacherBTN;
	public void click_submitTeacherBTN() {
		this.click(submitTeacherBTN);
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
		this.selectValue(scoreStars, s,"ByValue");
	}
	
	@FindBy(id="coursescoreform-score")
	private WebElement score;//分数
	public void sendkeys_score(String s) {
		this.sendkeys(score, s);
	}
	
	/************班期列表页面***************/
	@FindBy(linkText="添加班期")
	private WebElement addScheduleBTN;//添加班期按钮
	public void click_addScheduleBTN() {
		this.click(addScheduleBTN);
	}
	
	@FindBy(xpath=".//*[@id='w0']/table/tbody/tr/td[6]/a[2]")
	private WebElement stageLink;//阶段管理链接
	public void click_stageLink() {
		this.click(stageLink);
	}
	
	/************阶段管理列表页面******************/
	@FindBy(linkText="添加阶段")
	private WebElement addStageBTN;//添加阶段按钮
	public void click_addStageBTN() {
		this.click(addStageBTN);
	}
	
	@FindBy(id="coursestageform-course_schedule_id")
	private WebElement scheduleAtStageList;//查询条件：班期
	public void select_scheduleAtStageList(int selectIndex) {
		this.selectValue(scheduleAtStageList, Integer.toString(selectIndex), "ByIndex");
	}
	
	@FindBy(className="btn-search")
	private WebElement searchBTN;//查询按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}
	
	/************阶段编辑页面**********************/
	@FindBy(id="coursestageform-start_time")
	private WebElement stageStart;//阶段开始时间
	public void sendkeys_stageStart(String s) {
		this.sendkeys(stageStart, s);
	}
	
	@FindBy(id="coursestageform-end_time")
	private WebElement stageEnd;//阶段结束时间
	public void sendkeys_stageEnd(String s) {
		this.sendkeys(stageEnd, s);
	}
	
	/*****************学员管理列表页面*****************/
	@FindBy(linkText="添加学员")
	private WebElement addStudentBTN;//添加学员按钮
	public void click_addStudentBTN() {
		this.click(addStudentBTN);
	}
	
	@FindBy(xpath=".//*[@id='w0']/table/tbody/tr[1]/td[4]")
	private WebElement studentSchedule;//列表第一行的班期名称
	public String get_studentSchedule() {
		return this.findMyElement(studentSchedule).getText();
	}
	
	/*************添加学员页面*************/
	@FindBy(id="courseschedulehasstudentform-student_id")
	private WebElement studentID;//学员ID输入框
	public void sendkeys_studentID(String s) {
		this.sendkeys(studentID, s);
	}
	
	@FindBy(id="courseschedulehasstudentform-course_schedule_id")
	private WebElement scheduleAtStudent;//班期选择
	public void select_scheduleAtStudent(String s) {
		this.selectValue(scheduleAtStudent, s, "ByIndex");
	}
}
