package com.xcj.admin.service.course;

import java.util.List;

import com.xcj.common.page.Page;
import com.xcj.admin.entity.course.SbtTestScore;
import com.xcj.admin.base.BaseService;

/**
 *
 * <b>function:</b> sbt_test_scoresbt_test_score
 * 
 * @package com.xcj.admin.service.course.
 * @fileName com.xcj.admin.*
 * @createDate Tue Dec 15 13:53:25 CST 2015
 * @author name su_jian
 */
public interface SbtTestScoreService extends BaseService {

	/**
	 *
	 * <b>function:</b> sbt_test_scoresbt_test_score---获取分页List方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 15 13:53:25 CST 2015
	 * @return List<SbtTestScore>
	 * @author name su_jian
	 */
	public Page<SbtTestScore> getBySbtTestScorePage(Page<SbtTestScore> ps,
			SbtTestScore sbtTestScore) throws Exception;

	/**
	 *
	 * <b>function:</b> sbt_test_scoresbt_test_score---获取所有数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Dec 15 13:53:25 CST 2015
	 * @return List<SbtTestScore>
	 * @author name su_jian
	 */
	List<SbtTestScore> getAllList() throws Exception;

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
	void removeById(Integer id) throws Exception;

	/**
	 * 
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>同步sbtTestScore</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">逻辑说明:</span></dt>
	 * <dd>1、同步sbtTestScore</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">创建时间:</span></dt>
	 * <dd>2015年12月18日 上午10:15:45</dd>
	 * </dl>
	 * <dt><span class="strong">author:</span></dt> <dd>wu_dapeng</dd> </dl>
	 */
	public void syncSbtTestScore(String courseNumber) throws Exception;

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
			String faultNumber) throws Exception;
}
