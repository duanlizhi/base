package com.xcj.common.util.common;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 */
public class ConvertUtil {
	public static Date convertStringToDate(String aMask, String strDate) {
		SimpleDateFormat df = null;
		Date date = null;
		try {
			df = new SimpleDateFormat(aMask);
			date = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (date);
	}

	/**
	 */
	public static String convertDateToString(Date aDate) {
		return DateUtil.getFormatDateTime(DateUtil.SJ_YYYY_MM_DD, aDate);
	}
	/**
	 */
	public static String convertDateToStringHHss(Date aDate) {
		return DateUtil.getFormatDateTime(DateUtil.sj_timePattern, aDate);
	} 
	
	
}
