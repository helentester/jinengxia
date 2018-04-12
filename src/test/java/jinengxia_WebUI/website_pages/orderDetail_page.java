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
 * 描述：下订单页面http://dev.jinengxia.com/order/default/index?id=2
 */
public class orderDetail_page extends BasePage{

	public orderDetail_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id="order-confirm-contract")
	private WebElement ConfirmOrderForm;//协议多选框
	public void click_ConfirmOrderForm() {
		this.click(ConfirmOrderForm);//构选协议框
	}
	
	@FindBy(id="order-submit-button")
	private WebElement orderSubmitBTN;//提交订单按钮
	public void click_orderSubmitBTN() {
		this.click(orderSubmitBTN);//点击提交订单按钮
	}
	
	@FindBy(className="red")
	private WebElement coursePrice;//课程价格
	public String get_coursePrice() {
		return this.findMyElement(coursePrice).getText();//获取课程价格
	}

}
