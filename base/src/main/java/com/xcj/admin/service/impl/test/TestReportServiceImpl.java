package com.xcj.admin.service.impl.test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Objects;
import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.test.TestReportDao;
import com.xcj.admin.entity.tests.TestReport;
import com.xcj.admin.service.test.TestReportService;
import com.xcj.api.sync.RabbitMQProducerUtil;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;

/**
 *
 * <b>function:</b> test_report测评报表
 * 
 * @package com.xcj.admin.service.impl.test.
 * @fileName com.xcj.admin.*
 * @createDate Thu Oct 15 13:55:11 CST 2015
 * @author name su_jian
 */
@Service("testReportServiceImpl")
public class TestReportServiceImpl extends BaseServiceImpl implements
		TestReportService {

	@Resource(name = "testReportDaoImpl")
	private TestReportDao testReportDao;

	/**
	 *
	 * <b>function:</b> test_report测评报表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:55:11 CST 2015
	 * @return List<TestReport>
	 * @author name su_jian
	 */
	public List<TestReport> getAllList() throws Exception {
		return testReportDao.getAllList();
	}

	/**
	 *
	 * <b>function:</b> test_report测评报表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:55:11 CST 2015
	 * @return List<TestReport>
	 * @author name su_jian
	 */
	public Page<TestReport> getByTestReportPage(Page<TestReport> ps,
			TestReport testReport) throws Exception {
		return testReportDao.getByTestReportPage(ps, testReport);
	}

	/**
	 *
	 * <b>function:</b> test_report测评报表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:55:11 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		testReportDao.removeById(id);

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
	 * <dd>2015年10月15日 下午2:02:40</dd>
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
	public void syncTestReport(String userEmail, String domainUsername,
			String courseNumber, String sessionId, String testsPublicId)
			throws Exception {
		try {
			 	Thread.currentThread();
				//TODO 线程等待时间有待调整
				 Thread.sleep(1*10*1000);//线程等待一分钟然后继续插入
		} catch (Exception e) {
			 
		}
		List<TestReport> listTestReport=testReportDao.getResultByFiveParam(userEmail,domainUsername,courseNumber,sessionId,testsPublicId);
		//发送数据
		if(listTestReport!=null){
			if(Constants.TRAINZONE_DOAMIN_NAME.equals(domainUsername)&&Objects.equal("trainzone",domainUsername)){
				//MQProducerUtil.SendTestReport(JSON.toJSONString(listTestReport), 5, "syncTestReport", 
					//	APIConstants.Topic_xcj_test_report,APIConstants.Topic_xcj_test_report);
				RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_xcj_test_report, JSON.toJSONString(listTestReport));
			}
			//同步到demo2期平台
			if(Objects.equal("admin",domainUsername)){
				RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_demo_test_report, JSON.toJSONString(listTestReport));
			}
		}
	}
}
