/**
 * @author Helen 
 * @date 2018年6月11日  
 */
package jinengxia_apiTest;

import common.MyExcel;
import java.io.IOException;
import org.testng.annotations.DataProvider;

/**
 * 描述：接口测试的数据源
 */
public class myApiTestData {
	MyExcel myExcel = new MyExcel();

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
		return myExcel.readExcel("login");
	}
}
