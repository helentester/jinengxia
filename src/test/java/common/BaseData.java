/**
 * @author Helen 
 * @date 2018年4月18日  
 */
package common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

/**
 * 描述：一些数据处理函数
 */
public class BaseData {
	public static void main(String[] args) {
		BaseData baseData = new BaseData();
		//System.out.println(baseData.getRandomName("技能班")); 
		//System.out.println(baseData.getTargetList("https://backend.dev.jinengxia.com/course/course-teacher-list?course_id=39", "\\d+").get(0));
		for (int h = 0; h < 3; h++) {
			System.out.println("开始时间："+baseData.getTimeByMonthsAndDays(h,1)+",结束时间"+baseData.getTimeByMonthsAndDays(h+1,0)); 
		}
	}

	/*返回参数与随机数并接成新的值*/
	public String getRandomName(String s) {
		Random random = new Random();
		return s+Integer.toString(random.nextInt(10000));
	}
	
	/* 通过正则表达式匹配，返回结果列表 */
	public List<String> getTargetList(String matcherStr, String compileStr) {
		List<String> targetList= new ArrayList<String>();
		Pattern p = Pattern.compile(compileStr);// 规则
		Matcher m = p.matcher(matcherStr);
		while (m.find()){
			targetList.add(m.group());//把匹配到的结果存到列表中
		}
		return targetList;
	}
	
	/*生成时间:根据当前月份加减*/
	public String getTimeByMonthsAndDays(int months,int day2) {
		Calendar calendar = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。 
		calendar.add(calendar.MONTH, months);//当前时间加减月份
		calendar.add(calendar.DAY_OF_MONTH, day2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}
}
