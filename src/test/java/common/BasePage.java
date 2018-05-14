/**
 * @author:Helen
 * @date：2018年4月7日 
 */
package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/*
 * 描述：处理页面元素公共类，重写页面操作事件，为每个元素加入显式等待
 */
public class BasePage {
	WebDriver driver;
	private final int timeOut = 30;//等待时间

	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	/* 重写senkeys方法 */
	public void sendkeys(WebElement element, String s) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));// 加入显式等待
		element.clear();// 先清空输入框
		element.sendKeys(s);// 输入数据
	}

	/* 重写click方法 */
	public void click(WebElement element) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));// 加入显式等待
		element.click();
	}
	
	/*重写获取对象方法*/
	public WebElement findMyElement(WebElement element) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	/*select下拉框选择*/
	public void selectValue(WebElement element,String s,String byType) {
		if(byType.equals("ByVisibleText")) {
			new Select(this.findMyElement(element)).selectByVisibleText(s);	
		}
		else if (byType.equals("ByValue")) {
			new Select(this.findMyElement(element)).selectByValue(s);	
		}
		else if (byType.equals("ByIndex")) {
			new Select(this.findMyElement(element)).selectByIndex(Integer.parseInt(s));	
		}	
	}
	
	/*执行JS*/
	public void JS(WebDriver webDriver,WebElement element,String JS) {
		WebElement myElement = this.findMyElement(element);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    jsExecutor.executeScript(JS, myElement);
	
	}

}
