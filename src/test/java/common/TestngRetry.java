/**
 * @author Helen 
 * @date 2018年5月19日  
 */
package common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * 描述：重写testngRetry接口，设置场景恢复
 */
public class TestngRetry implements IRetryAnalyzer {
	private int retryCount = 1;
	private static int maxRetryCount = 3;// 最大重新执行场景的次数

	/*
	 * 场景恢复设置，重新执行失败用例的次数
	 */
	public boolean retry(ITestResult result) {
		if (retryCount <= maxRetryCount) {
			String message = "Retry for [" + result.getName() + "] on class [" + result.getTestClass().getName()
					+ "] Retry " + retryCount + " times";
			Reporter.setCurrentTestResult(result);
			Reporter.log(message);//报告中输出日志
			retryCount++;
			return true;
		}
		return false;
	}

}
