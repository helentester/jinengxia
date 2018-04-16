/**
 * @author Helen 
 * @date 2018年4月16日  
 */
package jinengxia_WebUI.backend_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * 描述：后台首页
 */
public class BIndex_page extends BasePage{

	public BIndex_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(tagName="h1")
	private WebElement h1;//页头内容
	public String get_h1Text() {
		return this.findMyElement(h1).getText();//获取页头内容
	}
	

}
