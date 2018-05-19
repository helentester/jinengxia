/**
 * @author Helen 
 * @date 2018年5月19日  
 */
package common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/**
 * 描述：实现IAnnotationTransformer接口，设置监听
 */
public class TestngRetryListener implements IAnnotationTransformer{

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();  
        if (retry == null) {  
            annotation.setRetryAnalyzer(TestngRetry.class);  
        } 
		
	}

}
