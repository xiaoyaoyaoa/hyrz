package cn.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String DAY_FROMAT = "yyyy-MM-dd";
	/** 获得当前豪秒值 */
	public static long millisTime(){
		return System.currentTimeMillis();
	}
	/** 获得当前日期 */
	public static Date getDate(String dateStr){
		SimpleDateFormat sdf=new SimpleDateFormat(DAY_FROMAT); 
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}	
	
}
