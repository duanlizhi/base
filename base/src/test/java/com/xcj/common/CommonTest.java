/** 
  *<dl>
  *<dt><span class="strong">方法说明:</span></dt>
  *<dd>主要是实现了什么功能</dd>
  *</dl> 
  *<dl><dt><span class="strong">创建时间:</span></dt>
  *<dd> 2016年2月22日 下午6:06:43</dd></dl> 
  *</dl> 
  *@param <T> 对象类型 
  *@param entity 对象 
  *@return boolean true/false
  *@throws Exception  XX异常
  */
package com.xcj.common;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/** 
 *<dl>
 *<dt><span class="strong">方法说明:</span></dt>
 *<dd>主要是实现了什么功能</dd>
 *</dl> 
 *<dl><dt><span class="strong">创建时间:</span></dt>
 *<dd> 2016年2月22日 下午6:06:43</dd></dl> 
 *</dl> 
 *@param <T> 对象类型 
 *@param entity 对象 
 *@return boolean true/false
 *@throws Exception  XX异常
 */
public class CommonTest {
	private static final Logger log = LoggerFactory.getLogger(CommonTest.class);
	 /**
     *<dl>
     *<dt><span class="strong">方法说明:</span></dt>
     *<dd>@beforeClass1针对所有测试，只执行一次，且必须为static void</dd>
    */
	@BeforeClass  
    public static void beforeClass1(){  
		log.info("@beforeClass1针对所有测试，只执行一次，且必须为static void");  
    }  
    
	 /**
     *<dl>
     *<dt><span class="strong">方法说明:</span></dt>
     *<dd>@Before：初始化方法   对于每一个测试方法都要执行一次（注意与BeforeClass区别，后者是对于所有方法执行一次）</dd>
    */
	@Before
    public void before() {  
    	log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~方法分割 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		log.info("@Before：初始化方法   对于每一个测试方法都要执行一次");     
    }     
  
	
	/**
     *<dl>
     *<dt><span class="strong">方法说明:</span></dt>
     *<dd>正常的测试方法</dd>
     *</dl> 
     */
    @Test  
    public void testStr() {
    	 /**
    	  * 字符窜为(66#22#77#55#11)分割并且排序
    	  * 预期结果：11#22#55#66#77
    	 */
    	String str="66#22#77#55#11";
    	//直接调用写好的业务处理方法
    	String[] strs=str.split("#");
    	Arrays.sort(strs);
    	String string="";
    	for (int i = 0; i < strs.length; i++) {
    		string=string+"#"+strs[i];
		}
    	//直接诶调用写好的业务处理方法
    	assertEquals("11#22#55#66#77", string.substring(1));
    }  

    
    /**
     *<dl>
     *<dt><span class="strong">方法说明:</span></dt>
     *<dd>@Ignore 忽略当前测试的方法</dd>
     *</dl> 
     */
    @Ignore("testIgnore() Not yet implemented")  
    @Test  
    public void testIgnore() {  
        log.info("@Ignore test Ignore");
        fail("Not yet implemented");  
    }  
    
   /**
    *<dl>
    *<dt><span class="strong">方法说明:</span></dt>
    *<dd>用来指示时间上限。比如把这个属性设置为100毫秒</dd>
    *</dl> 
    */
    @Test(timeout = 100)  
    public void testTimeout(){  
        log.info("@test main 用来指示时间上限。比如把这个属性设置为100毫秒");  
    }  
  
    /**
     *<dl>
     *<dt><span class="strong">方法说明:</span></dt>
     *<dd>测试抛出异常;
     * 注意：期望抛出ArithmeticException
     * 异常，则下面的写法不会出错。测试通过</dd>
     *</dl> 
     */
    @Test(expected = ArithmeticException.class)  
    public void divideByZero() {
    	int a=3;
    	int b=0;
    	int c=a/b;
    	log.info("@test main eexpected:"+c);  
    }  
    
    
    /**
     *<dl>
     *<dt><span class="strong">方法说明:</span></dt>
     *<dd>释放资源  对于每一个测试方法都要执行一次（注意与AfterClass区别，后者是对于所有方法执行一次）</dd>
     *</dl> 
    */
    @After  
    public void after1(){  
    	log.info("@after1释放资源  对于每一个测试方法都要执行一次");  
    	log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~方法分割 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }  
    
    /**
      *<dl>
      *<dt><span class="strong">方法说明:</span></dt>
      *<dd>@afterClass1针对所有测试，只执行一次，且必须为static void </dd>
     */
    @AfterClass  
    public static void afterClass1(){  
    	log.info("@afterClass1针对所有测试，只执行一次，且必须为static void ");  
    }  

}
