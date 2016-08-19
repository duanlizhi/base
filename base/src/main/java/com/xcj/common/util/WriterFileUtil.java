/**
 * 
 */
package com.xcj.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <b>function:</b>
 * 
 * @project base_frame
 * @package com.xcj.common.util
 * @fileName com.xcj.*
 * @createDate Jun 6, 2014 4:40:44 PM
 * @author su_jian
 */
public class WriterFileUtil {

	/**
	 * 
	  * @param filePath  文件夹路径
	  * @param fileName  文件名
	  * @param str 所要写的字符串
	 */
	public static void writerFile(String filePath, String fileName, String str)throws IOException {

		File folder = new File(filePath);
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdirs();
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath + fileName)));
		writer.write(str);
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		writerFile("D:/cc/dd/", "Resulsssst.txt", "123");
	}
}
