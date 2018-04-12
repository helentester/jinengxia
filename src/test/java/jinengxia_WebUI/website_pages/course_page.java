/**
 * @author Helen 
 * @date 2018年4月10日  
 */
package jinengxia_WebUI.website_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：课程详情页面http://dev.jinengxia.com/course/landing-page
 */
public class course_page extends BasePage{

	public course_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(className="btn")
	private WebElement buyBTN;//立即购买按钮
	public void click_buyBTN() {
		this.click(buyBTN);//点击立即购买
	}

}
