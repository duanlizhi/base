package com.xcj.common.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * <b>function:</b> 生成编码的规则
 *   此类是为了生成唯一的全局编码，
 *   如果缺失则自己根据getTagNumber（）方法填写自己所需要的方法。
 * @project base   
 * @package   com.xcj.common.base
 * @fileName  
 * @createDate
 * @return  
 * @author su_jian
 **/
public class GenerateNumber {

	private GenerateNumber() {
	}
	private static GenerateNumber generateNumber = null;
	public static GenerateNumber getInstance() {
		if (generateNumber == null) {
			synchronized (GenerateNumber.class) {
				if (generateNumber == null) {
					generateNumber = new GenerateNumber();
				}
			}
		}
		return generateNumber;
	}
	
	/**
	 * 生成标签的流水编号
	 */
	public synchronized String getTagNumber() {
		return "T"+GenerateNumber.getInstance().generaterNumber()+GenerateNumber.getInstance().getRandomString(6);
	}
	
	/**
	 * 生成域编号的流水编号
	 */
	public synchronized static String getCourseWareCode() {
		return "C"+GenerateNumber.getInstance().generaterNumber()+GenerateNumber.getInstance().getRandomString(6);
	}
	
	/**
	 * 生成域编号的流水编号
	 */
	public synchronized static  String getDoaminNumber() {
		return "D"+GenerateNumber.getInstance().generaterNumber()+GenerateNumber.getInstance().getRandomString(6);
	}
	/**
	 * 生成yonghu编号的流水编号
	 */
	public synchronized String getAdminNumber() {
		return "A"+GenerateNumber.getInstance().generaterNumber()+GenerateNumber.getInstance().getRandomString(6);
	}
	
	
	
	/**
	 * 生成的流水编号
	 */
	public synchronized String generaterNumber() {
		Calendar calCurrent = Calendar.getInstance();
    	Date date =calCurrent.getTime();
    	SimpleDateFormat fmtDate = new SimpleDateFormat("yyMMddHHmmss");
    	String str =fmtDate.format(date);
		return str;
	}
	/**
	 * 生成随机的字符窜
	 * @param length
	 * @return
	 */
	public synchronized String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }   

	
	
	public static void main(String[] args){
		 FileWriter fileWriter;
		try {
		
		    BufferedWriter writer = new BufferedWriter(new FileWriter(new File("d:\\Resulsssst.txt")));

		     
		     
		for (int i = 0; i < 1000; i++) {
			String str = GenerateNumber.getInstance().getDoaminNumber();
//			for (int j = 0; j < 100; j++) {
//				writer.write("{\"userEmail\":\""+str+"@163.com\",\"token\":\"A3BE4839C0820D7B5D766A49C355798B\",\"sessionId\":\"37C6F268BE2CF4814A05662AAF5E8C98\",\"domainUsername\":\"admin\",  "+
//					"\"courseNumber\":\"wbt\",\"progress\":\"1\",\"score\":\"1\",\"actionTime\":\"1\",\"receive\":\"1\", "+
//					"\"character\":\"{'action':'ay_01','question':[{'question':'q01','answer':'fdsafsdf'}],'param':'qqw'}\"}"+"\n");
//			}
			writer.write(str+"\n");
			System.out.println("INSERT INTO `admin` VALUES ("+(i+10)+",'2015-02-03 18:01:41','2015-02-03 18:01:43','ptb','"+str+"@163.com',1,1,'2015-02-03 18:02:12','2015-02-03 18:02:14',0,'182.168.1.1','高高高','21232f297a57a5a743894a0e4a801fc3','"+str+"',1882006"+(1000+i)+");");
		}
		writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
