package com.xcj.common.util.md5;

/**
 * MD5工具类
 * @author su_jian
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Uitl {
	private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
	
	private static String byteArrayToHexString(byte[] b) { 
		StringBuffer resultSb = new StringBuffer(); 
		for (int i = 0; i < b.length; i++) { 
			resultSb.append(byteToHexString(b[i])); 
		} 
		return resultSb.toString(); 
	} 

	private static String byteToHexString(byte b) { 
		int n = b; 
		if (n < 0) n = 256 + n; 
		int d1 = n / 16; 
		int d2 = n % 16; 
		return hexDigits[d1] + hexDigits[d2]; 
	} 
	public static String MD5Encode(String origin) { 
		String resultString = null; 
		try { 
			resultString=new String(origin); 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			resultString=byteArrayToHexString(md.digest(resultString.getBytes())); 
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		return resultString; 
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sj = MD5Uitl.MD5Encode("admin");
		System.out.println(sj);
		
	}
}

