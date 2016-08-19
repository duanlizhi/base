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

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.entity.domain.Domain;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.common.util.common.HttpUtil;
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
public class GetTsPTest {
	@Resource(name = "domainServiceImpl")
	private DomainService domainService;

	private static final Logger log = LoggerFactory.getLogger(GetTsPTest.class);

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

	}

	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>正常的测试方法</dd>
	 * </dl>
	 */
	@Test
	public void testGetTS() {
		List<Domain> listDomain=domainService.getList(Domain.class);
		for (int i = 0; i < listDomain.size(); i++) {
			String url = "http://content.xiaochejiang.com/api/oper/base/getTsp";
			Map<String, String> params = new HashMap<String, String>();
			params.put("domainAccount", listDomain.get(i).getUsername());
			params.put("domainPassword", "admin");
			params.put("userEmail", "xx@163.com");
			params.put("studyFlag", "休息休息");
			String rets = HttpUtil.doGet(url, params);
			JSONObject obj=JSONObject.parseObject(rets);
			if(!"0".equals(obj.get("errCode"))){
				fail("获取Token出错了");
			}
			log.info(rets);
		}
	}
	
	
	@Test
	public void testGetTSNotPass() {
		List<Domain> listDomain=domainService.getList(Domain.class);
		for (int i = 0; i < listDomain.size(); i++) {
			String url = "http://content.xiaochejiang.com/api/oper/base/getTsp";
			Map<String, String> params = new HashMap<String, String>();
			params.put("domainAccount", listDomain.get(i).getUsername());
			params.put("domainPassword", "xxxxxxx");//错误密码
			params.put("userEmail", "xx@163.com");
			params.put("studyFlag", "休息休息");
			String rets = HttpUtil.doGet(url, params);
			JSONObject obj=JSONObject.parseObject(rets);
			if(!"20004".equals(obj.get("errCode"))){
				fail("用户名和密码匹配出错了");
			}
			log.info(rets);
		}
	}
	
	
	@Test
	public void testGetTSNullParam() {
		List<Domain> listDomain=domainService.getList(Domain.class);
		for (int i = 0; i < listDomain.size(); i++) {
			String url = "http://content.xiaochejiang.com/api/oper/base/getTsp";
			Map<String, String> params = new HashMap<String, String>();
			params.put("domainAccount", listDomain.get(i).getUsername());
			params.put("domainPassword", "admin");
			String rets = HttpUtil.doGet(url, params);
			JSONObject obj=JSONObject.parseObject(rets);
			log.info(obj.toJSONString());
			if(!"20001".equals(obj.get("errCode"))){
				fail("参数是否匹配错误");
			}
			log.info(rets);
		}
	}
	
	@Test
	public void testGetTSEmptyParamstudyFlag() {
		List<Domain> listDomain=domainService.getList(Domain.class);
		for (int i = 0; i < listDomain.size(); i++) {
			String url = "http://content.xiaochejiang.com/api/oper/base/getTsp";
			Map<String, String> params = new HashMap<String, String>();
			params.put("domainAccount", listDomain.get(i).getUsername());
			params.put("domainPassword", "admin");
			params.put("userEmail", "xx@163.com");
			params.put("studyFlag", "");
			String rets = HttpUtil.doGet(url, params);
			JSONObject obj=JSONObject.parseObject(rets);
			if(!"20001".equals(obj.get("errCode"))){
				fail("参数是否匹配错误");
			}
			log.info(rets);
		}
	}
	
	
	@Test
	public void testGetTSEmptyParamUserEmail() {
		List<Domain> listDomain=domainService.getList(Domain.class);
		for (int i = 0; i < listDomain.size(); i++) {
			String url = "http://content.xiaochejiang.com/api/oper/base/getTsp";
			Map<String, String> params = new HashMap<String, String>();
			params.put("domainAccount", listDomain.get(i).getUsername());
			params.put("domainPassword", "admin");
			params.put("userEmail", "");
			params.put("studyFlag", "xs");
			String rets = HttpUtil.doGet(url, params);
			JSONObject obj=JSONObject.parseObject(rets);
			log.info(obj.toJSONString());
			if(!"20001".equals(obj.get("errCode"))){
				fail("参数是否匹配错误");
			}
			log.info(rets);
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
