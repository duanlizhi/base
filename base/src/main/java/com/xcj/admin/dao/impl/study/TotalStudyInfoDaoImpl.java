package com.xcj.admin.dao.impl.study;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.study.TotalStudyInfoDao;
import com.xcj.admin.entity.study.TotalStudyInfo;
import com.xcj.common.page.Page;

/**
 *
 * <b>function:</b> total_study_info学习总计信息
 * 
 * @package com.xcj.admin.dao.impl.study.
 * @fileName com.xcj.admin.*
 * @createDate Wed Oct 14 15:25:04 CST 2015
 * @author name su_jian
 */
@Component("totalStudyInfoDaoImpl")
public class TotalStudyInfoDaoImpl extends BaseDaoImpl implements
		TotalStudyInfoDao {
	private static final long serialVersionUID = -6560008511599684951L;
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 *
	 * <b>function:</b> total_study_info学习总计信息---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.study.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Oct 14 15:25:04 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public <T extends TotalStudyInfo> List<T> getAllList()
			throws DataAccessException {
		String hql = "from TotalStudyInfo";
		return super.getList(hql);
	}

	/**
	 *
	 * <b>function:</b> total_study_info学习总计信息---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.study.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Oct 14 15:25:04 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	@SuppressWarnings("unchecked")
	public Page<TotalStudyInfo> getByTotalStudyInfoPage(
			Page<TotalStudyInfo> page, TotalStudyInfo totalStudyInfo)
			throws DataAccessException {
		try {
			Session session = this.getSession();
			page.setResult(session
					.createQuery(
							"from " + totalStudyInfo.getClass().getName()
									+ " order by orders ")
					.setFirstResult(
							(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session
					.createQuery(
							"select count(id) as cnt from "
									+ totalStudyInfo.getClass().getName())
					.setMaxResults(1).uniqueResult().toString()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	/**
	 *
	 * <b>function:</b> total_study_info学习总计信息---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.study.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Oct 14 15:25:04 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from TotalStudyInfo t where t.id=" + id);
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
	 * <dl>
	 * <dt><span class="strong">创建时间:</span></dt>
	 * <dd>2015年10月14日 下午4:11:01</dd>
	 * </dl>
	 * <dt><span class="strong">author:</span></dt> <dd>su_jian</dd> </dl>
	 * @param <T>
	 *            对象类型
	 * @param entity
	 *            对象
	 * @see {@link org.springframework.dao.DataAccessException}
	 * @return boolean true/false
	 * @since 1.0
	 * @throws DataAccessException
	 */
	public TotalStudyInfo getbyEmailAndDomainAndCourseNumber(String userEmail,
			String domainUsername, String courseNumber,String faultNumber,String studyFlag)
			throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("from TotalStudyInfo where courseNumber='" + courseNumber
				+ "' and userEmail='" + userEmail + "' and domainUsername='"
				+ domainUsername + "' ");
		sb.append(" and faultNumber='"+faultNumber+"' ");
		sb.append(" and studyFlag='"+studyFlag+"' ");
		/*if(faultNumber==null||"".equals(faultNumber)){
		}
		if(studyFlag==null||"".equals(studyFlag)){
		}*/
		List<TotalStudyInfo> list = super.getList(sb.toString());
		if (list == null || list.size() <= 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
