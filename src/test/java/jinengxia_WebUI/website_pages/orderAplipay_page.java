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
 * 描述：支付宝支付页面
 */
public class orderAplipay_page extends BasePage{

	public orderAplipay_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath=".//*[@id='order']/div[1]/div[2]/span[1]")
	private WebElement orderNB;//订单号
	public String get_orderNB() {
		return this.findMyElement(orderNB).getText();//获取订单号
	}
	
	@FindBy(xpath=".//*[@id='order']/div[1]/div[2]/span[2]")
	private WebElement payee;//收款方
	public String get_paree() {
		return this.findMyElement(payee).getText();//获取收款方
	}
	
	@FindBy(className="qrcode-header-money")
	private WebElement money;//金额
	public String get_money() {
		return this.findMyElement(money).getText();//获取需付金额
	}

}
