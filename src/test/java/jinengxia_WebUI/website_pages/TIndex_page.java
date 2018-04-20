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
 * 描述：教务工作台首页
 */
public class TIndex_page extends BasePage{

	public TIndex_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="(html/body/div[1]/aside/ul/li)[last()]/a")
	private WebElement courseAtLast;//最后一个技能班
	public void click_courseAtLast() {
		this.click(courseAtLast);
	}
	
	@FindBy(xpath="(html/body/div[1]/section/dl/dd)[last()]/a")
	private WebElement firstSchedule;//第一个班期（排在列表最后）
	public void click_firstSchedule() {
		this.click(firstSchedule);
	}
	
	@FindBy(xpath="html/body/div[1]/section/main/div/ul/li[1]/div/p[2]/a[1]")
	private WebElement classLink;//第一个阶段的课时管理链接
	public void click_classLink() {
		this.click(classLink);
	}

}
