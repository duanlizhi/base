package com.xcj.admin.service.impl.study;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Objects;
import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.study.TotalStudyInfoDao;
import com.xcj.admin.entity.study.TotalStudyInfo;
import com.xcj.admin.service.study.TotalStudyInfoService;
import com.xcj.api.oper.controller.StudyApiController;
import com.xcj.api.sync.RabbitMQProducerUtil;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;

/**
 *
 * <b>function:</b> total_study_info学习总计信息
 * 
 * @package com.xcj.admin.service.impl.study.
 * @fileName com.xcj.admin.*
 * @createDate Wed Oct 14 15:25:02 CST 2015
 * @author name su_jian
 */
@Service("totalStudyInfoServiceImpl")
public class TotalStudyInfoServiceImpl extends BaseServiceImpl implements
		TotalStudyInfoService {
	private static Log logger = LogFactory.getLog(StudyApiController.class);

	@Resource(name = "totalStudyInfoDaoImpl")
	private TotalStudyInfoDao totalStudyInfoDao;

	/**
	 *
	 * <b>function:</b> total_study_info学习总计信息---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.study.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Oct 14 15:25:02 CST 2015
	 * @return List<TotalStudyInfo>
	 * @author name su_jian
	 */
	public List<TotalStudyInfo> getAllList() throws Exception {
		return totalStudyInfoDao.getAllList();
	}

	/**
	 *
	 * <b>function:</b> total_study_info学习总计信息---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.study.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Oct 14 15:25:02 CST 2015
	 * @return List<TotalStudyInfo>
	 * @author name su_jian
	 */
	public Page<TotalStudyInfo> getByTotalStudyInfoPage(
			Page<TotalStudyInfo> ps, TotalStudyInfo totalStudyInfo)
			throws Exception {
		return totalStudyInfoDao.getByTotalStudyInfoPage(ps, totalStudyInfo);
	}

	/**
	 *
	 * <b>function:</b> total_study_info学习总计信息---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.study.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Oct 14 15:25:02 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		totalStudyInfoDao.removeById(id);

	}

	public TotalStudyInfo getbyEmailAndDomainAndCourseNumber(String userEmail,String  domainUsername,String  courseNumber,String faultNumber,String studyFlag) throws Exception{
		return totalStudyInfoDao.getbyEmailAndDomainAndCourseNumber(userEmail, domainUsername, courseNumber, faultNumber, studyFlag);
	}
	
	@Async
	public void syncTotalStudyResult(String userEmail, String domainUsername,
			String courseNumber,String faultNumber,String studyFlag) {
		//System.out.println(userEmail+":"+domainUsername+":"+courseNumber);
		//TODO 此处获取结果然后处理然后推送相关信息
		try {
			Thread.currentThread();
			//TODO 线程等待时间有待调整
			Thread.sleep(1*10*1000);//线程等待一分钟然后继续插入
		} catch (Exception e) {
			logger.error("同步学习结果出错："+e);
		}
		//获取结果
		TotalStudyInfo totalStudyInfo =totalStudyInfoDao.getbyEmailAndDomainAndCourseNumber(userEmail,domainUsername,courseNumber,faultNumber,studyFlag);
		 if(totalStudyInfo!=null){
			 if(Constants.TRAINZONE_DOAMIN_NAME.equals(domainUsername)&&Objects.equal("trainzone",domainUsername)){
				 //推送结果
				 //MQProducerUtil.SendTotalStudyResult(JSON.toJSONString(totalStudyInfo), 2, "totalStudyCommit", APIConstants.Topic_total_study_result,APIConstants.ProducerId_value_total_study_result);
				 RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_total_study_result, JSON.toJSONString(totalStudyInfo));
					
			 }
			 if(Constants.SCHOOL_DOAMIN_NAME.equals(domainUsername)&&Objects.equal("school",domainUsername)){
				 //推送结果
				 //MQProducerUtil.SendTotalStudyResultZx(JSON.toJSONString(totalStudyInfo), 2, "totalStudyCommit", APIConstants.Topic_total_study_result_zx,APIConstants.ProducerId_value_total_study_result_zx);
				 RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_total_study_result_zx, JSON.toJSONString(totalStudyInfo));
			 }
			 if(Constants.DEMO2Q_DOAMIN_NAME.equals(domainUsername)&&Objects.equal("demo2q",domainUsername)){
				 //推送结果
				 //MQProducerUtil.SendTotalStudyResultZx(JSON.toJSONString(totalStudyInfo), 2, "totalStudyCommit", APIConstants.Topic_total_study_result_zx,APIConstants.ProducerId_value_total_study_result_zx);
				 RabbitMQProducerUtil.sendMsgToQuery(APIConstants.Topic_total_study_result_demo2q, JSON.toJSONString(totalStudyInfo));
			 }
		 }
	}
	
	
	public static void main(String[] args) {
		System.out.println("222222222222");
		try {
			Thread.currentThread();
			Thread.sleep(5000);
			System.out.println("333333333333333");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
