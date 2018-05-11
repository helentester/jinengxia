/**
 * @author Helen 
 * @date 2018年4月20日  
 */
package jinengxia_WebUI.website_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：教务工作台－课时管理页面
 */
public class TClass_page extends BasePage{

	public TClass_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(linkText="一键复制课时")
	private WebElement copyClassBTN;//一键复制课时按钮
	public void click_copyClassBTN() {
		this.click(copyClassBTN);
	}
	
	@FindBy(xpath=".//*[@id='app']/div[3]/h2/button")
	private WebElement copyBTN;//复制按钮
	public void click_copyBTN() {
		this.click(copyBTN);
	}
	
	@FindBy(xpath=".//*[@id='app']/div[1]/section/h2/div/div[2]/input")
	private WebElement inputFile;//导入文件的输入框（课时导入和内容管理中的导入都用同样的对象）
	public void sendkeys_inputFile(String s) {
		this.sendkeys(inputFile, s);//输入导入文件的路径
		//this.findMyElement(inputFile).sendKeys(Keys.ENTER);//回车
	}
	
	/************查询版块***************/
	@FindBy(xpath=".//*[@id='app']/div[1]/section/div/div[1]/form/div[1]/label[2]")
	private WebElement checkAll;//多选框：全选
	public void click_clearCheck() {
		if(this.findMyElement(checkAll).isSelected()) {//因为整个功能中我只是想清空已选内容，所以。……。。……。……。……
			this.click(checkAll);
		}
		else {
			this.click(checkAll);
			this.click(checkAll);
		}
	}
	
	@FindBy(xpath=".//*[@id='app']/div[1]/section/div/div[1]/form/div[1]/label[3]")
	private WebElement checkVideo;//多选框：录播
	public void click_checkVideo() {
		if(this.findMyElement(checkVideo).isSelected()) {
			//什么都不做
		}
		else {
			this.click(checkVideo);
		}
		
	}
	
	@FindBy(xpath=".//*[@id='app']/div[1]/section/div/div[1]/form/div[1]/label[4]")
	private WebElement checkLiveVideo;//多选框：直播
	public void click_checkLiveVideo() {
		if(this.findMyElement(checkLiveVideo).isSelected()) {
			//什么都不做
		}
		else {
			this.click(checkLiveVideo);
		}
	}
	
	@FindBy(xpath=".//*[@id='app']/div[1]/section/div/div[1]/form/div[1]/label[5]")
	private WebElement checkRead;//多选框：阅读
	public void click_checkRead() {
		if(this.findMyElement(checkRead).isSelected()) {
			//什么都不做
		}
		else {
			this.click(checkRead);
		}
	}
	
	@FindBy(xpath=".//*[@id='app']/div[1]/section/div/div[1]/form/div[1]/label[6]")
	private WebElement checkWork;//多选框：作业
	public void click_checkWork() {
		if(this.findMyElement(checkWork).isSelected()) {
			//什么都不做
		}
		else {
			this.click(checkWork);
		}
	}
	
	@FindBy(name="id_or_name")
	private WebElement searchWords;//查询关键词：课时ID或课时名称
	public void sendkeys_searchWords(String s) {
		this.sendkeys(searchWords, s);
	}
	
	@FindBy(className="btn-success-not")
	private WebElement searchBTN;//查询按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}

	@FindBy(xpath=".//*[@id='app']/div[1]/section/div/div[2]/table/tbody/tr[1]/td[7]/a[1]")
	private WebElement editBTN;//列表第一个内容管理按钮
	public void click_editBTN() {
		this.click(editBTN);
	}
	
	/***************内容管理页面*****************/
	@FindBy(xpath=".//*[@id='jnx-editor-video']")
	private WebElement editor;//导读内容
	public void sendkeys_editor(WebDriver driver,String h1) {
		String jString = "arguments[0].innerHTML = '<h1>"+h1+"</h1><br><table width=\"560\" border=\"1\"><tbody><tr ><td >日期</td><td >修订说明</td><td >修改人</td></tr><tr><td >2018-03-05</td><td >初稿</td><td >刘大为</td></tr><tr><td >2018-03-05</td><td >完成版交付</td><td >大为刘</td></tr></tbody></table><br>'";
		this.JS(driver, editor, jString);//通过JS插入
	    //jsExecutor.executeScript("arguments[0].innerHTML = '<h1>Selenium Test </h1>I love Selenium <br> this article Post By Selenium WebDriver<br><h2>Create By Young</h2>'", myElement);
	}
	
					
	@FindBy(xpath=".//*[@id='app']/div[4]/div/div/div[3]/div/div/div[2]/input")
	private WebElement inputMassageFile;//内容管理页面导入文件
	public void sendkeys_inputMassageFile(String s) {
		this.sendkeys(inputMassageFile, s);
	}

	@FindBy(className="btn-submit")
	private WebElement submitBTN;//保存按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}
	
	@FindBy(id="jnx-period_video_url")
	private WebElement videoURL;//视频链接
	public void sendkeys_videoURL(String s) {
		this.sendkeys(videoURL, s);
	}
}
