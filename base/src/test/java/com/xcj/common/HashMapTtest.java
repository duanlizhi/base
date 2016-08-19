/** 
 *<dl>
 *<dt><span class="strong">方法说明:</span></dt>
 *<dd>主要是实现了什么功能</dd>
 *</dl> 
 *<dl><dt><span class="strong">创建时间:</span></dt>
 *<dd> 2016年3月3日 上午10:57:56</dd></dl> 
 *</dl> 
 *@param <T> 对象类型 
 *@param entity 对象 
 *@return boolean true/false
 *@throws Exception  XX异常
 */
package com.xcj.common;

import java.util.HashMap;

import org.junit.Test;

/**
 * <dl>
 * <dt><span class="strong">方法说明:</span></dt>
 * <dd>主要是实现了什么功能</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2016年3月3日 上午10:57:56</dd>
 * </dl>
 * </dl>
 *
 * @param <T>
 *            对象类型
 * @param entity
 *            对象
 * @return boolean true/false
 * @throws Exception
 *             XX异常
 */
public class HashMapTtest {

	 
	
	@Test
	public void testTotalHashMap(){
		testHashMap1();
		testHashMap2();
		testHashMap3();
		testHashMap4();
		testHashMap5();
		testHashMap6();
		testHashMap7();
		
	}
	
	public static void testHashMap1(){
		//System.out.println(1 << 10);
		Long s1=System.currentTimeMillis();
		HashMap<String, Object> map =new HashMap<String, Object>(32,3f);
		for (int i = 0; i < 10000; i++) {
			map.put(i+"", i+"");
		}
		System.out.println(map.size());
		Long s2= System.currentTimeMillis();
		System.out.println("testHashMap1:"+(s2-s1));
	}
	
	
	public static void testHashMap2(){
		//System.out.println(1 << 10);
		Long s1=System.currentTimeMillis();
		HashMap<String, Object> map =new HashMap<String, Object>(32,2f);
		for (int i = 0; i < 10000; i++) {
			map.put(i+"", i+"");
		}
		System.out.println(map.size());
		Long s2= System.currentTimeMillis();
		System.out.println("testHashMap2:"+(s2-s1));
	}
	
	
	public static void testHashMap3(){
		//System.out.println(1 << 10);
		Long s1=System.currentTimeMillis();
		HashMap<String, Object> map =new HashMap<String, Object>(32,1f);
		for (int i = 0; i < 10000; i++) {
			map.put(i+"", i+"");
		}
		System.out.println(map.size());
		Long s2= System.currentTimeMillis();
		System.out.println("testHashMap3:"+(s2-s1));
	}
	
	public static void testHashMap4(){
		System.out.println(1 << 5);
		Long s1=System.currentTimeMillis();
		HashMap<String, Object> map =new HashMap<String, Object>(32,0.75f);
		for (int i = 0; i < 10000; i++) {
			map.put(i+"", i+"");
		}
		System.out.println(map.size());
		Long s2= System.currentTimeMillis();
		System.out.println("testHashMap4:"+(s2-s1));
	}
	
	public static void testHashMap5(){
		//System.out.println(1 << 10);
		Long s1=System.currentTimeMillis();
		HashMap<String, Object> map =new HashMap<String, Object>();
		for (int i = 0; i < 10000; i++) {
			map.put(i+"", i+"");
		}
		System.out.println(map.size());
		Long s2= System.currentTimeMillis();
		System.out.println("testHashMap5:"+(s2-s1));
	}
	
	
	public static void testHashMap6(){
		System.out.println(1 << 5);
		Long s1=System.currentTimeMillis();
		HashMap<String, Object> map =new HashMap<String, Object>(32,0.75f);
		for (int i = 0; i < 10000; i++) {
			map.put(i+"", i+"");
		}
		for (int i = 10000; i < 10000; i++) {
			map.put(i+"", i+"");
		}
		System.out.println(map.size());
		Long s2= System.currentTimeMillis();
		System.out.println("testHashMap6:"+(s2-s1));
	}
	
	public static void testHashMap7(){
		System.out.println(1 << 5);
		Long s1=System.currentTimeMillis();
		HashMap<String, Object> map =new HashMap<String, Object>(32,0.75f);
		for (int i = 0; i < 10000; i++) {
			map.put(i+"", i+"");
		}
		System.out.println(map.size());
		Long s2= System.currentTimeMillis();
		System.out.println("testHashMap7:"+(s2-s1));
	}
	 

}
