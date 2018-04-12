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
 * 描述：确订订单页面http://dev.jinengxia.com/trade/default/index?id=JNX-180411-281518
 */
public class orderPay_page extends BasePage{

	public orderPay_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(className="mr30")
	private WebElement orderNB;//订单编号
	public String get_orderNB() {
		return this.findMyElement(orderNB).getText();//获取订单编号
	}
	
	@FindBy(className="red")
	private WebElement orderPay;//应付金额
	public String get_orderPay() {
		return this.findMyElement(orderPay).getText();//获取应付金额
	}
	
	@FindBy(className="icon-alipay")
	private WebElement aplipay;//支付方式：支付宝
	public void click_aplipay() {
		this.click(aplipay);//选中支付宝支付方式
	}
	
	@FindBy(className="btn-primary")
	private WebElement primaryBTN;//立即支付按钮
	public void click_primaryBTN() {
		this.click(primaryBTN);//点击“立即支付”
	}
}
