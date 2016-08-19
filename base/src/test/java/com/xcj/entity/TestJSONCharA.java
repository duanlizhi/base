/** 
  *<dl>
  *<dt><span class="strong">方法说明:</span></dt>
  *<dd>主要是实现了什么功能</dd>
  *</dl> 
  *<dl><dt><span class="strong">创建时间:</span></dt>
  *<dd> 2016年3月1日 下午4:16:33</dd></dl> 
  *</dl> 
  *@param <T> 对象类型 
  *@param entity 对象 
  *@return boolean true/false
  *@throws Exception  XX异常
  */
package com.xcj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

/** 
 *<dl>
 *<dt><span class="strong">方法说明:</span></dt>
 *<dd>主要是实现了什么功能</dd>
 *</dl> 
 *<dl><dt><span class="strong">创建时间:</span></dt>
 *<dd> 2016年3月1日 下午4:16:33</dd></dl> 
 *</dl> 
 *@param <T> 对象类型 
 *@param entity 对象 
 *@return boolean true/false
 *@throws Exception  XX异常
 */
public class TestJSONCharA {
	
	@Test
	public void testJSON() {
		ChatA a=new ChatA();
		List<Handle> handle=new ArrayList<Handle>();
		for (int i = 0; i < 2; i++) {
			Handle handlesi =new Handle();
			handlesi.setId(i);
			handlesi.setScore(2+i+"");
			handlesi.setTime((new Date()).getTime());
			handle.add(handlesi);
		}
		
		List<Progress> progresss=new ArrayList<Progress>();
		for (int i = 0; i < 0; i++) {
			Progress progress =new Progress();
			progress.setId(i);
			progresss.add(progress);
		}
		
		List<Score> scores=new ArrayList<Score>();
		for (int i = 0; i < 0; i++) {
			Score score =new Score();
			
			List<Option> options=new ArrayList<Option>();
			for(int j = 0; j < 1; j++){
				Option option = new Option();
				option.setId(j);
				
				
				List<Option> optionss=new ArrayList<Option>();
				for(int k=0;k<1;k++){
					Option option2 = new Option();
					option2.setId(k);
					optionss.add(option2);
				}
				option.setOption(optionss);
				
				options.add(option);
			}
			score.setOption(options);
			
			
			List<HandleS> handle2s=new ArrayList<HandleS>();
			for (int j = 0; j < 20; j++) {
				HandleS handleS =new HandleS();
				handleS.setId(j);
				handleS.setIsAction("action"+j);
				handle2s.add(handleS);
			}
			score.setHandle(handle2s);
			
			
			score.setIsRight("1");
			score.setQuestionID("a01");
			score.setId(i);
			scores.add(score);
		}
		
		a.setProgress(progresss);
		a.setScore(scores);
		a.setHandle(handle);
		System.out.println(JSON.toJSONString(a));
	}
}
