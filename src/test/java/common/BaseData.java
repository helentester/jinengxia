/**
 * @author Helen 
 * @date 2018年4月18日  
 */
package common;

import java.util.Random;
import java.util.regex.Pattern;

import bsh.This;

import java.util.regex.Matcher;

/**
 * 描述：一些数据处理函数
 */
public class BaseData {
	public static void main(String[] args) {
		BaseData baseData = new BaseData();
		System.out.println(baseData.getRandomName("技能班")); 
		System.out.println(baseData.getTargetWord("https://backend.dev.jinengxia.com/course/course-score-list?course_id=38", "//d+"));
	}

	/*返回参数与随机数并接成新的值*/
	public String getRandomName(String s) {
		Random random = new Random();
		return s+Integer.toString(random.nextInt(10000));
	}
	
	/*通过正则表达式匹配，返回结果*/
	public String getTargetWord(String s,String compileStr) {
		Pattern p = Pattern.compile(compileStr);//规则
		Matcher m = p.matcher(s);
		m.find();
		return m.group();
	}
}
