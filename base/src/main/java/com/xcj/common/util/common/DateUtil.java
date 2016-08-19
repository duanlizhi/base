package com.xcj.common.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 提供日期类型的处理方法
 * 
 * @author su_jian
 * @version 1.0
 * @create <br>
 *         2011-08-18
 */
public class DateUtil {

	/** 年月日的日期格式化 yyy-MM-dd */
	public static String SJ_YYYY_MM_DD = "yyyy-MM-dd";

	/** 年月的日期格式化 yyyy-MM */
	public static String sj_dateMonthPattern = "yyyy-MM";

	/** 年月日时分秒的日期格式化 MM/dd/yyyy HH:mm:ss */
	public static String sj_datePatterns = "MM/dd/yyyy HH:mm:ss";

	/** 年月日时分秒大写HH表示的是24小时进行限制 yyyy-MM-dd HH:mm:ss */
	public static String sj_dateTime24Patten = "yyyy-MM-dd HH:mm:ss";

	/** 年月日时分秒大写HH表示的是12小时进行限制 yyyy-MM-dd hh:mm:ss */
	public static String sj_dateTime12Patten = "yyyy-MM-dd hh:mm:ss";
	/** 小时分钟的格式化方法 HH:mm */
	public static String sj_timePattern = "HH:mm";
	public static String sj_dateTime24PattenNo="yyyyMMddhhmmss";
	
	/** 年月日时分秒大写HH表示的是12小时进行限制 yyyy-MM-dd hh:mm:ss */
	public static String sj_dateTime12Patten_minus = "yyyy-MM-dd HH:mm";

	
	/**
	 * 获取当前时间
	 * 
	 * @return CurrentTime
	 */
	public static String getCurrentTimeMinus() {
		return getFormatDateTime(sj_dateTime12Patten_minus, new Date());
	}
	
	
	
	public static Date getCurrentTimeByDateNo() {
		return ConvertUtil.convertStringToDate(sj_dateTime24PattenNo,getCurrentTime());
	}
	
	
	/**
	 * 取得今天日期
	 * 
	 * @return 今天
	 */
	public static String today() {
		SimpleDateFormat fmtDate = new SimpleDateFormat(DateUtil.SJ_YYYY_MM_DD);
		return fmtDate.format(new Date());
	}

	/**
	 * 取得本月日期
	 * 
	 * @return 本月
	 */
	public static String thismonth() {
		SimpleDateFormat fmtMonth = new SimpleDateFormat(
				DateUtil.sj_dateMonthPattern);
		return fmtMonth.format(new Date());
	}

	/**
	 * 取得本月的第一天
	 * 
	 * @return 本月的第一天
	 */
	public static String firstOfThisMonth() {
		SimpleDateFormat fmtDate = new SimpleDateFormat(DateUtil.SJ_YYYY_MM_DD);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		return fmtDate.format(cal.getTime());
	}

	/**
	 * 取得本月的最后一天
	 * 
	 * @return 本月的最后一天
	 */
	public static String lastOfThisMonth() {
		SimpleDateFormat fmtDate = new SimpleDateFormat(DateUtil.SJ_YYYY_MM_DD);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return fmtDate.format(cal.getTime());
	}

	/**
	 * 取得今年的第一天
	 * 
	 * @return 今年的第一天
	 */
	public static String firstOfThisYear() {
		SimpleDateFormat fmtDate = new SimpleDateFormat(DateUtil.SJ_YYYY_MM_DD);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.getActualMinimum(Calendar.MONTH));
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		return fmtDate.format(cal.getTime());
	}

	/**
	 * 取得今年的最后一天
	 * 
	 * @return 今年的最后一天
	 */
	public static String lastOfThisYear() {
		SimpleDateFormat fmtDate = new SimpleDateFormat(DateUtil.SJ_YYYY_MM_DD);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return fmtDate.format(cal.getTime());
	}

	/**
	 * 得到当前年
	 * 
	 * @return year
	 */
	public static int getCurrentYear() {
		Calendar calendar = new GregorianCalendar();
		int year = calendar.get(1);
		return year;
	}

	/**
	 * 格式化日期的方法
	 * 
	 * @param aMask
	 *            格式化的格式例如 yyyy-MM-dd
	 * @param aDate
	 *            传入的日期
	 * @return returnValue 格式化后的时间格式
	 */
	public static String getFormatDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		df = new SimpleDateFormat(aMask);
		returnValue = df.format(aDate);
		return (returnValue);
	}
	
	public static String getFormatLongDateTime(String aMask, String strDate) {
		Date date=ConvertUtil.convertStringToDate(aMask, strDate);
		SimpleDateFormat df = null;
		String returnValue = "";
		df = new SimpleDateFormat(aMask);
		returnValue = df.format(date);
		return (returnValue);
	}
	

	/**
	 * 获取当前时间
	 * 
	 * @return CurrentTime
	 */
	public static String getCurrentTime() {
		return getFormatDateTime(sj_dateTime24Patten, new Date());
	}
	
	public static Date getCurrentTimeByDate() {
		return ConvertUtil.convertStringToDate(sj_dateTime24Patten,getCurrentTime());
	}

	/**
	 * 得到当前年月
	 * 
	 * @return getCurrentYearMonth
	 */
	public static String getCurrentYearMonth() {
		return getFormatDateTime(sj_dateMonthPattern, new Date());
	}

	/**
	 *得到当前月的第一天
	 * 
	 * @return FirstDayOfPreviousMonth
	 */
	public static String getFirstDayOfNowMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		return getFormatDateTime(SJ_YYYY_MM_DD, calendar.getTime());
	}

	/**
	 * 得到当前月的最后一天
	 * 
	 * @return FormatDateTime
	 */
	public static String getLastDayOfNowMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getFormatDateTime(SJ_YYYY_MM_DD, calendar.getTime());
	}

	/**
	 *得到某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return 某年某月的最后一天
	 */
	public static int getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		// 某年某月的最后一天
		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 得到某年某月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return 某年某月的第一天
	 */
	public static int getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		// 某年某月的第一天
		return cal.getActualMinimum(Calendar.DATE);
	}

	/**
	 * 得到传进去后几天后的日期 例子如下：
	 * 
	 * @param date
	 *            传进去的日期
	 * @param days要得出的几天后的日期传的是天数
	 *            。
	 * @return gc.getTime
	 */
	public static Date getNextDays(Date date, int days) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, days);
		return gc.getTime();
	}

	/**
	 * 得到传进去后几天前的日期 例子如下：
	 * 
	 * @param date
	 *            传进去的日期
	 * @param days要得出的几天后的日期传的是天数
	 *            。
	 * @return gc.getTime
	 */
	public static Date getPreviousDays(Date date, int days) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, -days);
		return gc.getTime();
	}

	/**
	 * 得到传进去后的明天日期 例子如下：
	 * 
	 * @param date
	 *            传进去的日期
	 * @param days
	 *            要得出的几天后的日期传的是天数。
	 * @return gc.getTime
	 */
	public static Date getNextDay(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, 1);
		return gc.getTime();
	}

	/**
	 * 得到传进去后的昨天天日期 例子如下：
	 * 
	 * @param date
	 *            传进去的日期
	 * @param days
	 *            要得出的几天后的日期传的是天数。
	 * @return gc.getTime 得到传进去日期的倒数第一天
	 */
	public static Date getPreviousDay(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, -1);
		return gc.getTime();
	}

	/**
	 * 需要首先格式化hao
	 * 
	 * @param date3
	 * @param date4
	 * @return
	 */
	public static long getTwoTimeDay(String mask, Date date3, Date date4) {
		long day = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(mask);
			String str1 = sdf.format(date3);
			String str2 = sdf.format(date4);
			Date date1 = sdf.parse(str1);
			Date date2 = sdf.parse(str2);
			day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ? (date1
					.getTime() - date2.getTime())
					/ (24 * 60 * 60 * 1000)
					: (date2.getTime() - date1.getTime())
							/ (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	 public static void main(String[] args) {
		System.out.println(DateUtil.today());
		String s=DateUtil.today()+" 00:00:00";
		String e=DateUtil.today()+" 23:59:59";
		/*System.out.println(s);
		System.out.println(e);
		System.out.println(DateUtil.getCurrentTime());
		*/
		Long ss=ConvertUtil.convertStringToDate(sj_dateTime24Patten,s).getTime();
		Long se=ConvertUtil.convertStringToDate(sj_dateTime24Patten,e).getTime();
		Long d=ConvertUtil.convertStringToDate(sj_dateTime24Patten,DateUtil.getCurrentTime()).getTime();
		if(d>=ss&&d<=se){
			System.out.println("正确的");
		}
		
	}

}
