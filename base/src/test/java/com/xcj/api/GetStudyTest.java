package com.xcj.api;

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
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.HttpUtil;
import com.xcj.entity.ChatA;
import com.xcj.entity.Handle;
import com.xcj.entity.HandleS;
import com.xcj.entity.Option;
import com.xcj.entity.Progress;
import com.xcj.entity.Score;
/**
 * <dl>
 * <dt><span class="strong">方法说明:</span></dt>
 * <dd>主要是实现了什么功能</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2016年2月22日 下午6:06:43</dd>
 * </dl>
 * </dl>
 * @param <T>
 *            对象类型
 * @param entity
 *            对象
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/app-context.xml",
		"classpath:/conf/app-datasource.xml" })
public class GetStudyTest {
	@Resource(name = "domainServiceImpl")
	private DomainService domainService;
	private static JSONObject objs=null;
	private static String chara="";
	private static final String userEmail="sujian@163.com";

	private static final Logger log = LoggerFactory.getLogger(GetStudyTest.class);
	
	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>@beforeClass1针对所有测试，只执行一次，且必须为static void</dd>
	 */
	@BeforeClass
	public static void beforeClass1() {
		
	}

	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>@Before：初始化方法 对于每一个测试方法都要执行一次（注意与BeforeClass区别，后者是对于所有方法执行一次）</dd>
	 */
	@Before
	public void before() {
			String url = "http://content.xiaochejiang.com/api/oper/base/getTsp";
			Map<String, String> params = new HashMap<String, String>();
			params.put("domainAccount", "school");
			params.put("domainPassword", "admin");
			params.put("userEmail", userEmail);
			params.put("studyFlag", "aaaa");
			String rets = HttpUtil.doGet(url, params);
			JSONObject obj=JSONObject.parseObject(rets);
			objs=obj;
			if(!"0".equals(obj.get("errCode"))){
				fail("获取Token出错了");
			}
			//log.info(rets);
			//
			
			ChatA a=new ChatA();
			List<Handle> handle=new ArrayList<Handle>();
			for (int i = 0; i < 20; i++) {
				Handle handlesi =new Handle();
				handlesi.setId(i);
				handlesi.setScore(2+i+"");
				handlesi.setTime((new Date()).getTime());
				handle.add(handlesi);
			}
			
			List<Progress> progresss=new ArrayList<Progress>();
			for (int i = 0; i < 20; i++) {
				Progress progress =new Progress();
				progress.setId(i);
				progresss.add(progress);
			}
			
			List<Score> scores=new ArrayList<Score>();
			for (int i = 0; i < 20; i++) {
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
			///
			chara=JSON.toJSONString(a);
	}
	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>初始化方法，正常提交数据的方法</dd>
	 * </dl>
	 */
	@Test
	public void testAStartStudy_OK() {
		//System.out.println(objs.toJSONString());
		//System.out.println(objs.get("token"));
		//System.out.println(objs.get("sessionId"));
		String url = "http://content.xiaochejiang.com/api/oper/study/startStudy";
		Map<String, String> params = new HashMap<String, String>();
		JSONObject json =new JSONObject();
		json.put("token", objs.get("token"));
		json.put("sessionId", objs.get("sessionId"));
		json.put("domainAccount", "school");
		json.put("userEmail", userEmail);
		json.put("courseNumber", "SBT_FCO_ES");
		json.put("faultNumber", "SBT_FCO_ES_ES_001_false");
		params.put("adapter",json.toJSONString());
		for (int i = 0; i < 2; i++) {
			String rets = HttpUtil.doGet(url, params);
			JSONObject retObj=JSONObject.parseObject(rets);
			System.out.println(":初始化学习:"+retObj);
			if(!"0".equals(retObj.get("errCode"))){
				fail("初始化学习出错了");
			}
		}
	}
	
	
	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>初始化方法，正常提交数据的方法</dd>
	 * </dl>
	 */
	@Test
	public void testStartStudy_paramnull() {
		//System.out.println(":tokenANdSessonId::"+objs.toJSONString());
		//System.out.println(objs.get("token"));
		//System.out.println(objs.get("sessionId"));
		String url = "http://content.xiaochejiang.com/api/oper/study/startStudy";
		Map<String, String> params = new HashMap<String, String>();
		JSONObject json =new JSONObject();
		json.put("token", objs.get("token"));
		json.put("sessionId", objs.get("sessionId"));
		json.put("domainAccount", "school");
		json.put("userEmail", userEmail);
		json.put("faultNumber", "SBT_FCO_ES_ES_001_false");
		params.put("adapter",json.toJSONString());
			String rets = HttpUtil.doGet(url, params);
			JSONObject retObj=JSONObject.parseObject(rets);
			//System.out.println(":参数为空:"+retObj);
			if(!"20001".equals(retObj.get("errCode"))){
				fail("初始化学习出错了");
			}
	}
	
	
	
	@Test
	public void testCommit_OK() {
		System.out.println(objs.toJSONString());
		String url = "http://content.xiaochejiang.com/api/oper/study/commitStudy";
		Map<String, String> params = new HashMap<String, String>();
		JSONObject json =new JSONObject();
		json.put("token", objs.get("token"));
		json.put("sessionId", objs.get("sessionId"));
		json.put("domainAccount", "school");
		json.put("userEmail", userEmail);
		json.put("courseNumber", "SBT_FCO_ES");
		json.put("faultNumber", "SBT_FCO_ES_ES_001_false");
		json.put("type", "1");
		json.put("progress", "11");
		json.put("score", "90");
		json.put("isComplete", "0");
		json.put("isPass", "0");
		json.put("characterA",chara);
		json.put("characterC", "笑嘻嘻笑嘻嘻笑嘻嘻笑嘻嘻笑嘻嘻嘻嘻");
		json.put("courseName", "苏建测试");
		json.put("completeCondition", "98");
		Integer seconds=0;
		Integer count=3000;
		for (int i = 0; i < 5; i++) {
			try {
				Thread.currentThread().sleep(count);
			} catch (InterruptedException e) {
				fail("休眠出错了");
			}
			json.put("seconds", seconds);
			params.put("adapter",json.toJSONString());
			String rets = HttpUtil.doPost(url, params);
			seconds=seconds+count/1000;
			//log.info(rets);
			JSONObject retObj=JSONObject.parseObject(rets);
			System.out.println(":提交数据:"+i+retObj+"::"+DateUtil.getCurrentTime());
			if(!"0".equals(retObj.get("errCode"))){
				fail("提交学习出错了");
			}
		}
	}
	
	
	@Test
	public void testExit_OK() {
		System.out.println(objs.toJSONString());
		String url = "http://content.xiaochejiang.com/api/oper/study/exitStudy";
		Map<String, String> params = new HashMap<String, String>();
		JSONObject json =new JSONObject();
		json.put("token", objs.get("token"));
		json.put("sessionId", objs.get("sessionId"));
		json.put("domainAccount", "school");
		json.put("userEmail", userEmail);
		json.put("courseNumber", "SBT_FCO_ES");
		json.put("faultNumber", "SBT_FCO_ES_ES_001_false");
		json.put("type", "1");
		json.put("progress", "100");
		json.put("score", "100");
		json.put("isComplete", "0");
		json.put("isPass", "1");
		json.put("characterA",chara);
		json.put("characterC", "我要退出了");
		json.put("courseName", "苏建测试");
		json.put("completeCondition", "100");
		Integer seconds=0;
		Integer count=3000;
		json.put("seconds", seconds);
		params.put("adapter",json.toJSONString());
		String rets = HttpUtil.doPost(url, params);
		seconds=seconds+count/1000;
		//log.info(rets);
		JSONObject retObj=JSONObject.parseObject(rets);
		System.out.println(":退出数据:"+retObj+"::"+DateUtil.getCurrentTime());
		if(!"0".equals(retObj.get("errCode"))){
			fail("退出学习出错了");
		}
	}
	
	
	
	@Test
	public void testCommit_ParamNull() {
		System.out.println(objs.toJSONString());
		String url = "http://content.xiaochejiang.com/api/oper/study/commitStudy";
		Map<String, String> params = new HashMap<String, String>();
		JSONObject json =new JSONObject();
		json.put("token", objs.get("token"));
		json.put("sessionId", objs.get("sessionId"));
		//json.put("domainAccount", "school");
		json.put("userEmail", userEmail);
		json.put("courseNumber", "SBT_FCO_ES");
		json.put("faultNumber", "SBT_FCO_ES_ES_001_false");
		json.put("type", "1");
		json.put("progress", "11");
		json.put("score", "90");
		json.put("isComplete", "0");
		json.put("isPass", "0");
		json.put("characterA",chara);
		json.put("characterC", "笑嘻嘻笑嘻嘻笑嘻嘻笑嘻嘻笑嘻嘻嘻嘻");
		json.put("courseName", "苏建测试");
		json.put("completeCondition", "98");
		Integer seconds=0;
		Integer count=3000;
			try {
				Thread.currentThread().sleep(count);
			} catch (InterruptedException e) {
				fail("休眠出错了");
			}
			json.put("seconds", seconds);
			params.put("adapter",json.toJSONString());
			String rets = HttpUtil.doPost(url, params);
			seconds=seconds+count/1000;
			//log.info(rets);
			JSONObject retObj=JSONObject.parseObject(rets);
			System.out.println(":提交数据参数为空:"+retObj+"::"+DateUtil.getCurrentTime());
			if(!"20001".equals(retObj.get("errCode"))){
				fail("提交数据参数为空出错了");
			}
	}
	
	
	

	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>释放资源 对于每一个测试方法都要执行一次（注意与AfterClass区别，后者是对于所有方法执行一次）</dd>
	 * </dl>
	 */
	@After
	public void after1() {
	}

	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>@afterClass1针对所有测试，只执行一次，且必须为static void</dd>
	 */
	@AfterClass
	public static void afterClass1() {
	}

}
