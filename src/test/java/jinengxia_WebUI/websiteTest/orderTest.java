/**
 * @author Helen 
 * @date 2018年4月10日  
 */
package jinengxia_WebUI.websiteTest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseWindows;
import common.mysql_conn;
import jinengxia_WebUI.website_pages.course_page;
import jinengxia_WebUI.website_pages.index_page;
import jinengxia_WebUI.website_pages.orderAplipay_page;
import jinengxia_WebUI.website_pages.orderDetail_page;
import jinengxia_WebUI.website_pages.orderPay_page;

/**
 * 描述：官网前台订单操作
 */
public class orderTest {

	@Test(description="官网下订单操作")
	public void buy_aplipay() {
		loginTest loginTest = new loginTest();
		WebDriver driver= loginTest.get_driver("lanna100","123456");
		//官网首页
		index_page index_page = PageFactory.initElements(driver, index_page.class);
		index_page.click_courseLink();// 点击课程链接按钮
		//课程详情页面
		course_page course_page = PageFactory.initElements(driver, course_page.class);
		BaseWindows windows = new BaseWindows();
		windows.changeWindow(driver);// 因为详情页面是另起标签页打开，所以要切换窗口
		course_page.click_buyBTN();// 点击立即购买按钮
		//下订单页面
		orderDetail_page orderDetail_page = PageFactory.initElements(driver, orderDetail_page.class);
		String coursePrice = orderDetail_page.get_coursePrice();//获取课程价格
		orderDetail_page.click_ConfirmOrderForm();// 构选购买协议
		orderDetail_page.click_orderSubmitBTN();// 点击“提交订单”按钮
		//确认订单页面
		orderPay_page orderPay_page = PageFactory.initElements(driver, orderPay_page.class);
		String orderNB = orderPay_page.get_orderNB();//获取订单的应付金额
		assertEquals(coursePrice, orderPay_page.get_orderPay());//比较下订单页面的“课程价格”是否与订单确认页的“应付金额”一至
		orderPay_page.click_aplipay();//选择支付宝支付方式
		orderPay_page.click_primaryBTN();//点击“立即支付”
		//支付宝支付页面
		orderAplipay_page orderAplipay_page = PageFactory.initElements(driver, orderAplipay_page.class);
		assertEquals("在线购买技能班: "+orderNB, orderAplipay_page.get_orderNB());//判断订单号是否正确
		assertEquals("收款方：广州邢帅教育科技有限公司", orderAplipay_page.get_paree());//判断付款方是否正确
		//从数据库中取值验证：订单金额是否正确
		mysql_conn mysql_conn = new mysql_conn();
		String amount = mysql_conn.getData("SELECT payment_amount from `order` where order_no='"+orderNB+"';", "payment_amount");
		assertEquals(amount, orderAplipay_page.get_money());
		driver.quit();
	}
}
