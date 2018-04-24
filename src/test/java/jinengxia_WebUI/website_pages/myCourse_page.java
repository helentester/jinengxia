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
	

}
