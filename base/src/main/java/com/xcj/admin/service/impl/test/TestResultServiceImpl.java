package com.xcj.admin.service.impl.test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Objects;
import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.test.TestResultDao;
import com.xcj.admin.entity.tests.TestsResult;
import com.xcj.admin.service.test.TestResultService;
import com.xcj.api.sync.RabbitMQProducerUtil;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;

/**
 *
 * <b>function:</b> test_result测评成绩
 * 
 * @package com.xcj.admin.service.impl.test.
 * @fileName com.xcj.admin.*
 * @createDate Thu Oct 15 13:54:57 CST 2015
 * @author name su_jian
 */
@Service("testResultServiceImpl")
public class TestResultServiceImpl extends BaseServiceImpl implements
		TestResultService {

	@Resource(name = "testResultDaoImpl")
	private TestResultDao testResultDao;

	/**
	 *
	 * <b>function:</b> test_result测评成绩---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:54:57 CST 2015
	 * @return List<TestResult>
	 * @author name su_jian
	 */
	public List<TestsResult> getAllList() throws Exception {
		return testResultDao.getAllList();
	}

	/**
	 *
	 * <b>function:</b> test_result测评成绩---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:54:57 CST 2015
	 * @return List<TestResult>
	 * @author name su_jian
	 */
	public Page<TestsResult> getByTestResultPage(Page<TestsResult> ps,
			TestsResult testsResult) throws Exception {
		return testResultDao.getByTestResultPage(ps, testsResult);
	}

	/**
	 *
	 * <b>function:</b> test_result测评成绩---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:54:57 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		testResultDao.removeById(id);

	}

	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>主要是实现了什么功能</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">逻辑说明:</span></dt>
	 * <dd>1、xx(处理逻辑是什么样子的)</dd>
	 * <dd>2、xx</dd>
	 * <dd>3、xx</dd>
	 * </dl>
	 * 
	 * <dl>
	 * <dt><span class="strong">创建时间:</span></dt>
	 * <dd>2015年10月15日 下午2:01:56</dd>
	 * </dl>
	 * <dt><span class="strong">author:</span></dt> <dd>su_jian</dd> </dl>
	 * 
	 * @param <T>
	 *            对象类型
	 * @param entity
	 *            对象
	 * @see {@link org.springframework.dao.DataAccessException}
	 * @return boolean true/false
	 * @since 1.0
	 * @throws Exception
	 *             XX异常
	 */
	@Async
	public void syncTestResult(String userEmail, String domainUsername,
			String courseNumber, String sessionId, String testsPublicId)
			throws Exception {
		try {
			Thread.currentThread();
			//TODO 线程等待时间有待调整
			 Thread.sleep(1*10*1000);//线程等待一分钟然后继续插入
			TestsResult testsResult = testResultDao.getResultByFiveParam(
					userEmail, domainUsername, courseNumber, sessionId,
					testsPublicId);
			//System.out.println(":::::::::::" + JSON.toJSONString(testsResult));
			// 发送数据
			if (testsResult != null) {
				if(Constants.TRAINZONE_DOAMIN_NAME.equals(domainUsername)&&Objects.equal("trainzone",domainUsername)){
					//MQProducerUtil.SendTestResult(JSON.toJSONString(testsResult), 3, "syncTestResult",
					//		APIConstants.Topic_xcj_test_result,APIConstants.ProducerId_value_xcj_test_result);
					RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_xcj_test_report, JSON.toJSONString(testsResult));
				}
				if(Objects.equal("admin",domainUsername)){
					//MQProducerUtil.SendTestResult(JSON.toJSONString(testsResult), 3, "syncTestResult",
					//		APIConstants.Topic_xcj_test_result,APIConstants.ProducerId_value_xcj_test_result);
					RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_xcj_test_result, JSON.toJSONString(testsResult));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
