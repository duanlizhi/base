/** 
  *<dl>
  *<dt><span class="strong">方法说明:</span></dt>
  *<dd>主要是实现了什么功能</dd>
  *</dl> 
  *<dl><dt><span class="strong">创建时间:</span></dt>
  *<dd> 2016年3月3日 下午2:11:56</dd></dl> 
  *</dl> 
  *@param <T> 对象类型 
  *@param entity 对象 
  *@return boolean true/false
  *@throws Exception  XX异常
  */
package com.xcj.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/** 
 *<dl>
 *<dt><span class="strong">方法说明:</span></dt>
 *<dd>主要是实现了什么功能</dd>
 *</dl> 
 *<dl><dt><span class="strong">创建时间:</span></dt>
 *<dd> 2016年3月3日 下午2:11:56</dd></dl> 
 *</dl> 
 *@param <T> 对象类型 
 *@param entity 对象 
 *@return boolean true/false
 *@throws Exception  XX异常
 */
public class ArrayListTest {
	@Test
	public void testSs(){
		test1();
		test2();
	}
	/**
	 * TODO  ？？？疑问为什么list 初始容量越小跑的越快。
	 */
	public static void test1(){
		Long s1=System.currentTimeMillis();
		List<String> list = new ArrayList<String>(2);
		for (int i = 0; i < 1000000; i++) {
			list.add(":"+i);
		}
		Long s2=System.currentTimeMillis();
		System.out.println("test1:"+(s2-s1));
	}
	
	public static void test2(){
		Long s1=System.currentTimeMillis();
		List<String> list = new ArrayList<String>(10);
		for (int i = 0; i < 1000000; i++) {
			list.add(":"+i);
		}
		Long s2=System.currentTimeMillis();
		System.out.println("test2:"+(s2-s1));
	}

	public static void main(String[] args) {
		int y=5;int x=6;
		y+=x--;
		System.out.println(y);
		System.out.println(x);
	}
}
