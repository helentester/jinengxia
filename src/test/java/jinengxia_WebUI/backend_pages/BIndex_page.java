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
public class BIndex_page extends BasePage {

	public BIndex_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(className = "clearfix")
	private WebElement h1;// 页头内容

	public String get_h1Text() {
		return this.findMyElement(h1).getText();// 获取页头内容
	}

	@FindBy(linkText = "技能班管理")
	private WebElement courseManager;// 技能班管理链接

	public void click_CourseManager() {
		this.click(courseManager);
	}

}
