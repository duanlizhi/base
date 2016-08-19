/**
 * 
 */
package com.xcj.common.code;

import java.io.File;
import java.io.IOException;

/**
 * <b>function:</b>
 * 
 * @project baseframe
 * @package com.xcj.common.code
 * @fileName com.xcj.*
 * @createDate Apr 16, 2014 4:32:58 PM
 * @author su_jian
 */
public class CodeUtil {
	public static final String AUTHOR_NAME="  * @author name su_jian";
	/**
	 * <b>function:</b> 根据数据库类型取得Java类型
	 * 
	 * @project base_frame
	 * @package com.xcj.common.code
	 * @fileName com.xcj.***
	 * @createDate Apr 16, 2014 5:18:04 PM
	 * @return String
	 * @author su_jian
	 */
	public static String getJavaTypeBydbType(String str) {
		String strs = "";
		if (str == null) {
			return null;
		}
		if (str.equals("BIGINT")) {
			strs = "Integer";
		}
		if (str.equals("INT")) {
			strs = "Integer";
		}
		if (str.equals("VARCHAR")) {
			strs = "String";
		}

		if (str.equals("DATETIME")) {
			strs = "java.util.Date";
		}

		if (str.equals("TIMESTAMP")) {
			strs = "java.util.Date";
		}
		if (str.equals("LONGTEXT")) {
			strs = "String";
		}
		return strs;
	}

	/**
	 * <b>function:</b> 第一个单词转换成大写
	 * 
	 * @project base_frame
	 * @package com.xcj.common.code
	 * @fileName com.xcj.***
	 * @createDate Apr 16, 2014 5:16:57 PM
	 * @return String
	 * @author su_jian
	 */
	public static String UpfristWordNo_(String str) {
		if (str == null) {
			return null;
		} else
			return str.substring(0, 1).toUpperCase()
					+ str.substring(1, str.length());
	}

	/**
	 * <b>function:</b> 第一个单词转换成小写
	 * 
	 * @project base_frame
	 * @package com.xcj.common.code
	 * @fileName com.xcj.***
	 * @createDate Apr 16, 2014 5:16:57 PM
	 * @return String
	 * @author su_jian
	 */
	public static String lowerfristWordNo_(String str) {
		if (str == null) {
			return null;
		} else
			return str.substring(0, 1).toLowerCase()
					+ str.substring(1, str.length());
	}

	
	/**
	 * <b>function:</b> 取得业务名称
	 * 
	 * @project base_frame
	 * @package com.xcj.common.code
	 * @fileName com.xcj.***
	 * @createDate Apr 16, 2014 5:17:48 PM
	 * @return String
	 * @author su_jian
	 */
	public static String getBusessName(String databaseName) {
		if (databaseName.contains("_")) {
			String[] str = databaseName.split("_");
			if (str.length == 2) {
				return CodeUtil.UpfristWordNo_(str[0])
						+ CodeUtil.UpfristWordNo_(str[1]);
			}
			if (str.length == 3) {
				return CodeUtil.UpfristWordNo_(str[0])
						+ CodeUtil.UpfristWordNo_(str[1])
						+ CodeUtil.UpfristWordNo_(str[2]);
			}
			if (str.length == 4) {
				return CodeUtil.UpfristWordNo_(str[0])
						+ CodeUtil.UpfristWordNo_(str[1])
						+ CodeUtil.UpfristWordNo_(str[2])
						+ CodeUtil.UpfristWordNo_(str[3]);
			} else {
				return null;
			}
		} else {
			return CodeUtil.UpfristWordNo_(databaseName).trim();
		}
	}

	/**
	   * <b>function:</b> 取得字段在JavaBean里边的属性
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 16, 2014 5:42:16 PM
	   * @return String
	   * @author su_jian
	 */
	public static String getFieldName(String fieldName){
		if (fieldName.contains("_")) {
			String[] str = fieldName.split("_");
			if (str.length == 2) {
				return str[0]
						+ CodeUtil.UpfristWordNo_(str[1]);
			}
			if (str.length == 3) {
				return str[0]
						+ CodeUtil.UpfristWordNo_(str[1])
						+ CodeUtil.UpfristWordNo_(str[2]);
			}
			if (str.length == 4) {
				return str[0]
						+ CodeUtil.UpfristWordNo_(str[1])
						+ CodeUtil.UpfristWordNo_(str[2])
						+ CodeUtil.UpfristWordNo_(str[3]);
			}if (str.length == 5) {
				return str[0]
							+ CodeUtil.UpfristWordNo_(str[1])
							+ CodeUtil.UpfristWordNo_(str[2])
							+ CodeUtil.UpfristWordNo_(str[3])
							+ CodeUtil.UpfristWordNo_(str[4]);
				}  else {
				return null;
			}
		} else {
			return fieldName;
		}
		
	}
	/**
	   * <b>function:</b> 创建文件的方法
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 16, 2014 5:50:48 PM
	   * @return boolean
	   * @author su_jian
	 */
	 public static boolean createFile(String destFileName) {  
	        File file = new File(destFileName);  
	        if(file.exists()) {  
	            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");  
	            return false;  
	        }  
	        if (destFileName.endsWith(File.separator)) {  
	            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");  
	            return false;  
	        }  
	        //判断目标文件所在的目录是否存在  
	        if(!file.getParentFile().exists()) {  
	            //如果目标文件所在的目录不存在，则创建父目录  
	            System.out.println("目标文件所在目录不存在，准备创建它！");  
	            if(!file.getParentFile().mkdirs()) {  
	                System.out.println("创建目标文件所在目录失败！");  
	                return false;  
	            }  
	        }  
	        //创建目标文件  
	        try {  
	            if (file.createNewFile()) {  
	                System.out.println("创建单个文件" + destFileName + "成功！");  
	                return true;  
	            } else {  
	                System.out.println("创建单个文件" + destFileName + "失败！");  
	                return false;  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());  
	            return false;  
	        }  
	    }  
	 
	 public static void delFile(String path){
		 File file =new File(path);
		 file.delete();
	 }
		
	public static void main(String[] args) {
		System.out.println(CodeUtil.getBusessName("id_test_suian_vdf"));
	}

}
