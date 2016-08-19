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
public class JavaDiGuiTest {

	@Test
	public void multiply() {
		multiply(0);
	}
	
	/**
	 * 阶乘的例子
	 */
	private int  multiply(int i) {
		if(i==0){
			return 1;
		}
		if(i==1){
			return i;
		}else{
			int j=multiply(i-1);
			return i*j;
		}
	}

}
