package com.xcj.admin.service.study;

import java.util.List;

import com.xcj.common.page.Page;
import com.xcj.admin.entity.study.TotalStudyInfo;
import com.xcj.admin.base.BaseService;

/**
 *
 * <b>function:</b> total_study_info学习总计信息
 * 
 * @package com.xcj.admin.service.study.
 * @fileName com.xcj.admin.*
 * @createDate Wed Oct 14 15:25:00 CST 2015
 * @author name su_jian
 */
public interface TotalStudyInfoService extends BaseService {

	/**
	 *
	 * <b>function:</b> total_study_info学习总计信息---获取分页List方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.study.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Oct 14 15:25:00 CST 2015
	 * @return List<TotalStudyInfo>
	 * @author name su_jian
	 */
	public Page<TotalStudyInfo> getByTotalStudyInfoPage(
			Page<TotalStudyInfo> ps, TotalStudyInfo totalStudyInfo)
			throws Exception;

	/**
	 *
	 * <b>function:</b> total_study_info学习总计信息---获取所有数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.study.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Oct 14 15:25:00 CST 2015
	 * @return List<TotalStudyInfo>
	 * @author name su_jian
	 */
	List<TotalStudyInfo> getAllList() throws Exception;

	/**
	 *
	 * <b>function:</b> total_study_info学习总计信息---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.study.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Oct 14 15:25:00 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	void removeById(Integer id) throws Exception;

	public void syncTotalStudyResult(String userEmail, String domainUsername,
			String courseNumber, String faultNumber, String studyFlag);

	public TotalStudyInfo getbyEmailAndDomainAndCourseNumber(String userEmail,
			String domainUsername, String courseNumber, String faultNumber,
			String studyFlag) throws Exception;
}
