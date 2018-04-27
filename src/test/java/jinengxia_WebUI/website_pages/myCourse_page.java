/**
 * @author Helen 
 * @date 2018年4月11日  
 */
package jinengxia_WebUI.website_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：我的技能班页面http://dev.jinengxia.com/user/class/my-course
 */
public class myCourse_page extends BasePage{

	public myCourse_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="html/body/div[1]/div[1]/div/div/div/p[2]")
	private WebElement studentUID;//学员的UID
	public String get_studentUID() {
		return this.findMyElement(studentUID).getText();
	}
	
	@FindBy(linkText="我的订单")
	private WebElement myOrderLink;//我的订单链接
	public void click_myOrderLink() {
		this.click(myOrderLink);//点击我的订单链接
	}
	
	@FindBy(xpath="(html/body/div[1]/div[2]/div[2]/ul/li)[last()]/div[2]/a")
	private WebElement myCourseLink;//“我的技能班”列表中最后一个班
	public String get_myCourseLink() {
		return this.findMyElement(myCourseLink).getAttribute("href");//获取链接
	}
	public void click_myCourseLink() {
		this.click(myCourseLink);
	}
	
	/*************技能班学习页面****************/
	@FindBy(xpath=".//*[@id='app']/div[1]/ul/li[1]/h3")
	private WebElement firstStageLink;//左则列表第一个阶段名
	public void click_firstStageLink() {
		this.click(firstStageLink);
	}
	
	@FindBy(xpath=".//*[@id='app']/div[1]/ul/li[1]/ul")
	private WebElement firstStageUL;//第一个阶段列表
	public String get_firstStageID() {
		return this.findMyElement(firstStageUL).getAttribute("data-stage");//获取第一个阶段的ID
	}
	
	@FindBy(xpath=".//*[@id='app']/div[1]/ul/li[2]/ul")
	private WebElement secondStageLink;//第二个阶段列表
	public void click_secondStageLink() {
		this.click(secondStageLink);
	}
	
	/**************作业界面*********************/
	@FindBy(className="edui-body-container")
	private WebElement taskEditor;//作业编辑器
	public void sendkeys_taskEditor(WebDriver driver,String h1) {
		String jString = "arguments[0].innerHTML = '<h1>"+h1+"</h1><br><table width=\"560\" border=\"1\"><tbody><tr ><td >内容标题</td><td >内容详情</td><td >完成情况</td></tr><tr><td >PS入门</td><td >软件安装.....</td><td >完成</td></tr><tr><td >第一个图层</td><td >图层....</td><td >掌握80%</td></tr></tbody></table><br>'";
		this.JS(driver, taskEditor, jString);//通过JS插入
	}
	
	@FindBy(className="webuploader-element-invisible")
	private WebElement taskFile;//附件
	public void sendkeys_taskFile(String s) {
		this.sendkeys(taskFile, s);
	}
	
	@FindBy(xpath=".//*[@id='app']/div[2]/div[2]/div[3]/div/div[1]/h2/a")
	private WebElement submitBTN;//打开作业提交的：提交作业按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}
//	public boolean get_submitBTN() {
//		return this.findMyElement(submitBTN).isEnabled();//返回提交按钮可操作状态
//	}
	
	@FindBy(xpath=".//*[@id='app']/div[4]/div[2]/div[2]/a")
	private WebElement submitTaskBTN;//提交作业内容按钮
	public void click_submitTaskBTN() {
		this.click(submitTaskBTN);
	}

	@FindBy(xpath=".//*[@id='app']/div[2]/div[2]/div[3]/div/div[3]/div/ul/li/div[1]/span[2]")
	private WebElement taskType;//作业状态
	public String get_taskType() {
		return this.findMyElement(taskType).getText();
	}

}
