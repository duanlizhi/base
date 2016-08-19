package com.xcj.admin.service.impl.course;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.course.SbtTestScoreDao;
import com.xcj.admin.entity.course.SbtTestScore;
import com.xcj.admin.service.course.SbtTestScoreService;
import com.xcj.api.sync.MQProducerUtil;
import com.xcj.api.sync.RabbitMQProducerUtil;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;

/**
 *
 * <b>function:</b> sbt_test_scoresbt_test_score
 * 
 * @package com.xcj.admin.service.impl.course.
 * @fileName com.xcj.admin.*
 * @createDate Tue Dec 15 13:53:25 CST 2015
 * @author name su_jian
 */
@Service("sbtTestScoreServiceImpl")
public class SbtTestScoreServiceImpl extends BaseServiceImpl implements
		SbtTestScoreService {

	@Resource(name = "sbtTestScoreDaoImpl")
	private SbtTestScoreDao sbtTestScoreDao;

	/**
	 *
	 * <b>function:</b> sbt_test_scoresbt_test_score---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 15 13:53:25 CST 2015
	 * @return List<SbtTestScore>
	 * @author name su_jian
	 */
	public List<SbtTestScore> getAllList() throws Exception {
		return sbtTestScoreDao.getAllList();
	}

	/**
	 *
	 * <b>function:</b> sbt_test_scoresbt_test_score---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 15 13:53:25 CST 2015
	 * @return List<SbtTestScore>
	 * @author name su_jian
	 */
	public Page<SbtTestScore> getBySbtTestScorePage(Page<SbtTestScore> ps,
			SbtTestScore sbtTestScore) throws Exception {
		return sbtTestScoreDao.getBySbtTestScorePage(ps, sbtTestScore);
	}

	/**
	 *
	 * <b>function:</b> sbt_test_scoresbt_test_score---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 15 13:53:25 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		sbtTestScoreDao.removeById(id);
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
	public void syncSbtTestScore(String courseNumber) throws Exception {
		try {
			Thread.currentThread();
			// TODO 线程等待时间有待调整
			// Thread.sleep(1*10*1000);//线程等待一分钟然后继续插入
			List<SbtTestScore> sbtTestScoreList = sbtTestScoreDao.getListByCourseNumber(courseNumber, null);
			// 发送数据
			if (sbtTestScoreList != null && sbtTestScoreList.size() > 0) {
				 //MQProducerUtil.SendSbtTestScoreZx(JSON.toJSONString(sbtTestScoreList), 3,"syncSbtTestScore",APIConstants.Topic_xcj_sbt_test_score_zx,APIConstants.ProducerId_value_xcj_sbt_test_score_zx);
				 RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_xcj_sbt_test_score_zx,JSON.toJSONString(sbtTestScoreList));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>根据课件编号，故障编号获取工单集合</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">逻辑说明:</span></dt>
	 * <dd>1、根据课件编号，故障编号获取工单集合，理论上集合只能最多出现一个元素</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">创建时间:</span></dt>
	 * <dd>2016年2月26日 下午4:49:14</dd>
	 * </dl>
	 * <dt><span class="strong">author:</span></dt> <dd>wu_dapeng</dd> </dl>
	 */
	public List<SbtTestScore> getListByCourseNumber(String courseNumber,
			String faultNumber) throws Exception {
		return sbtTestScoreDao.getListByCourseNumber(courseNumber, faultNumber);
	}
}
