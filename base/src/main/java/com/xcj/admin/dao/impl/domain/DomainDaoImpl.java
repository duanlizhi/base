package com.xcj.admin.dao.impl.domain;

import java.util.List;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.domain.DomainDao;
import com.xcj.admin.entity.course.DomainCourseWare;
import com.xcj.admin.entity.domain.Domain;
import com.xcj.admin.entity.tests.PapersCourse;
import com.xcj.common.page.Page;
import com.xcj.common.util.common.StringUtil;

/**
 * 
 * <b>function:</b> domain域管理表
 * 
 * @package com.xcj.admin.dao.impl.domain.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 17:39:32 CST 2015
 * @author name su_jian
 */
@Component("domainDaoImpl")
public class DomainDaoImpl extends BaseDaoImpl implements DomainDao {
	
	private static final long serialVersionUID = 4148632019871425595L;
	
	/**
	 * 
	 * <b>function:</b> domain域管理表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:32 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public <T extends Domain> List<T> getAllList() throws DataAccessException {
		String hql = "from Domain";
		return super.getList(hql);
	}

	/**
	 * 
	 * <b>function:</b> domain域管理表---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:32 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	@SuppressWarnings("unchecked")
	public Page<Domain> getByDomainPage(Page<Domain> page, Domain domain)
			throws DataAccessException {
		try {
			Session session = this.getSession();
			if (domain.getName() != null && !domain.getName().equals("")) {
				page.setResult(session.createQuery(
						"from " + domain.getClass().getName()
								+ " where name like '%" + domain.getName()
								+ "%' or username like '%"+domain.getName()+"%' or url like '%"+domain.getName()+"%' order  by modify_date ").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ domain.getClass().getName()
								+ " where name like '%" + domain.getName()
								+ "%'").setMaxResults(1).uniqueResult()
						.toString()));
			} else {
				page.setResult(session.createQuery(
						"from " + domain.getClass().getName()
								+ " order by modify_date ").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ domain.getClass().getName()).setMaxResults(1)
						.uniqueResult().toString()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	/**
	 * 
	 * <b>function:</b> domain域管理表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:32 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from Domain t where t.id=" + id);
	}

	/**
	 * <b>function:</b> domain域管理表---根据id删除数据方法
	 * @project base
	 * @package com.xcj.admin.dao.impl.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:32 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public Domain getByUsernameAndPassword(String username, String password)
			throws DataAccessException {
		String hql = "from Domain t where t.username='"
				+ StringUtil.trimAll(username) + "' and t.password='"
				+ StringUtil.trimAll(password) + "' and enable=1 ";
		List<Domain> list =super.getList(hql);
		if (list!=null&&!list.isEmpty()&&list.size()>0){
			return (Domain) super.getList(hql).get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	   * 验证域名称是否存在
	   * <b>function:</b> validateName()
	   * @project base
	   * @package com.xcj.admin.dao.domain  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-9 下午02:07:54
	   * @return boolean
	   * @author hehujun
	 */
	public boolean validateName(String name, String id) throws DataAccessException {
		Session session = this.getSession();
		StringBuilder builder = new StringBuilder();
		builder.append(" from Domain where name=:name");
		if (!StringUtil.isEmptyYl(id)) {
			builder.append(" and id != ").append(id);
		}
		if (!StringUtil.isEmptyYl(name)) {
            Domain domain = (Domain)session.createQuery(builder.toString())
				.setString("name", name)
				.setMaxResults(1)
				.uniqueResult();
            return null == domain;
		} else {
            return true;
        }
	}

    /**
     * 验证域账号是否存在
     * <b>function:</b> validateName()
     *
     * @return boolean
     * @project base
     * @package com.xcj.admin.dao.domain
     * @fileName com.xcj.***
     * @createDate 2015-2-9 下午02:07:54
     * @author hehujun
     */
    public boolean validateUsername(String username, String id) throws DataAccessException {
        Session session = this.getSession();
		StringBuilder builder = new StringBuilder();
		builder.append(" from Domain where username=:username");
		if(!StringUtil.isEmptyYl(id)) {
			builder.append(" and id != ").append(id);
		}
        if (!StringUtil.isEmptyYl(username)) {
            Domain domain = (Domain) session.createQuery(builder.toString())
                    .setString("username", username)
                    .setMaxResults(1)
                    .uniqueResult();
            return null == domain;
        } else {
            return true;
        }
    }
	
	/**
	 * 
	   * <b>function:</b> 域删除之前查询是否有权限控制的课程、题、试卷 
	   * @project base
	   * @package com.xcj.admin.dao.domain  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-1 上午09:26:11
	   * @return List<DomainCourseWare>
	   * @author hehujun
	 */
	@SuppressWarnings("unchecked")
	public List<DomainCourseWare> getCourseWares4Delete(String domainIds) throws Exception {
		Session session = this.getSession();
		return (List<DomainCourseWare>)session.createSQLQuery("SELECT dcw.id AS id, dcw.domain_id AS domain_id, dcw.course_ware_id AS course_ware_id FROM domain_course_ware AS dcw WHERE dcw.domain_id IN (?) UNION ALL SELECT dcp.id AS id, dcp.domain_id AS domain_id, dcp.course_paper_id AS course_ware_id FROM domain_course_paper AS dcp WHERE dcp.domain_id IN (?)")
			.addEntity(DomainCourseWare.class)
			.setString(0, domainIds)
			.setString(1, domainIds)
			.list();
	}
	
	/**
	 * 
	   * <b>function:</b> 根据domain_id批量删除
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年4月7日 下午2:41:41
	   * @return boolean
	   * @author hehujun
	 */
	public boolean deleteDomainCoursePaperByDomainId(String ids) throws Exception {
		String sql = "delete from domain_course_paper where domain_id in (" + ids
				+ ")";
		return 0!=super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] {},PapersCourse.class);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据domain_id批量删除
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年4月7日 下午2:41:41
	   * @return boolean
	   * @author hehujun
	 */
	public boolean deleteDomainCourseWareByDomainId(String ids) throws Exception {
		String sql = "delete from domain_course_ware where domain_id in (" + ids
				+ ")";
		return 0!=super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] {},PapersCourse.class);
	}

	/** 
		 *<dl>
		 * 	<dt><span class="strong">方法说明:</span></dt>
		 *  <dd>主要是实现了什么功能</dd>
		 * </dl> 
		 * 	<dl><dt><span class="strong">逻辑说明:</span></dt>
		 *  <dd>1、xx(处理逻辑是什么样子的)</dd>
		 *  <dd>2、xx</dd>
		 *  <dd>3、xx</dd></dl> 
		 *  
		 * 	<dl><dt><span class="strong">创建时间:</span></dt>
		 *  <dd> 2015年10月16日 上午10:44:29</dd></dl> 
		 * 	<dt><span class="strong">author:</span></dt>
		 *  <dd>su_jian </dd>
		 * </dl> 
		 * @param <T> 对象类型 
		 * @param entity 对象 
		 * @see {@link org.springframework.dao.DataAccessException}
		 * @return boolean true/false
		 * @since 1.0
		 * @throws Exception  XX异常
		 */
	public Domain getByUsername(String domainAccount) {
		  Session session = this.getSession();
			StringBuilder builder = new StringBuilder();
			builder.append(" from Domain where username=:username and enable=1");
	        if (!StringUtil.isEmptyYl(domainAccount)) {
	            Domain domain = (Domain) session.createQuery(builder.toString())
	                    .setString("username", domainAccount)
	                    .setMaxResults(1)
	                    .uniqueResult();
	            return domain ;
	        } else {
	            return null;
	        }
	}

}
