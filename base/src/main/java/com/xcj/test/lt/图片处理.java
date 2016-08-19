/** 
 * <b>function:</b> 
 * @project   base
 * @package  com.xcj.test.lt  
 * @fileName  TT.java 
 * @createDate  2015-3-3 下午05:53:02 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */ 
package com.xcj.test.lt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * @author Administrator
 *
 */
public class 图片处理 {
	public static void main(String[] args) throws IOException {
		for (int i = 1; i <=100; i++) {
			/*Image[] array = new Image[10];
			Image image = ImageIO.read(new File("D:\\android\\aa\\1.jpg"));//根据你实际情况改文件路径吧
			array[0] = image;*/
			
			BufferedImage image2 = ImageIO.read(new File("D:\\android\\aa\\1.jpg"));
			//要想保存这个对象的话你要把image声明为BufferedImage 类型
			ImageIO.write(image2, "JPEG", new File("D:\\android\\aa\\"+i+".jpg"));
		}
	}

}
