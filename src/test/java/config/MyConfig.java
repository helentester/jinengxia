/**
 * @author Helen 
 * @date 2018年6月12日  
 */
package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import common.BaseData;

/**
 * 描述：操作配置文件
 */
public class MyConfig {
	static MyConfig configFile=new MyConfig();
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//MyConfigFile configFile=new MyConfigFile();
		System.out.println(configFile.getPropertyValue("testDataFilePath"));
	}
	
	/*获取配置文件*/
	public Properties LoadProperties() throws FileNotFoundException, IOException {
		BaseData baseData = new BaseData();
		Properties properties = new Properties();
		properties.load(new FileInputStream(baseData.getFilePath("config/config.properties")));//取得文件
		return properties;
	}
	
	/*根据key获取value*/
	public String getPropertyValue(String PropertyKey) throws FileNotFoundException, IOException {
		Properties properties = configFile.LoadProperties();
		return properties.getProperty(PropertyKey);
	}
}
