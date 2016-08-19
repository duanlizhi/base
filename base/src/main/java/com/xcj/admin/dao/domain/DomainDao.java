package com.xcj.admin.dao.domain;
import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.course.DomainCourseWare;
import com.xcj.admin.entity.domain.Domain;
import com.xcj.common.page.Page;

import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 *
  * <b>function:</b>  domain域管理表
  * @package com.xcj.admin.dao.domain.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 02 17:39:32 CST 2015
  * @author name su_jian
 */
public interface DomainDao extends BaseDao{

   /**
    *
     * <b>function:</b>  domain域管理表---获取list方法
     * @project base
     * @package com.xcj.admin.dao.domain.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 02 17:39:32 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public <T extends Domain> List<T> getAllList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  domain域管理表---获取分页list方法
     * @project base
     * @package com.xcj.admin.dao.domain.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 02 17:39:32 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public Page<Domain>  getByDomainPage(Page<Domain> ps,Domain domain)	throws DataAccessException;

   /**
    *
     * <b>function:</b>  domain域管理表---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.domain.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 02 17:39:32 CST 2015
     * @return void
  * @author name su_jian
    */
  public void removeById(Integer id) throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> 根据用户名和密码获取域信息
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:32 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public Domain getByUsernameAndPassword(String username, String password)
			throws DataAccessException;

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
	public boolean validateName(String name, String id) throws DataAccessException;

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
    public boolean validateUsername(String username, String id) throws DataAccessException;
	
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
	public List<DomainCourseWare> getCourseWares4Delete(String domainIds) throws Exception;
	
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
	public boolean deleteDomainCoursePaperByDomainId(String ids) throws Exception;
	
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
	public boolean deleteDomainCourseWareByDomainId(String ids) throws Exception;

	/** 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>主要是实现了什么功能</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、xx(处理逻辑是什么样子的)</dd>
	  *  <dd>2、xx</dd>
	  *  <dd>3、xx</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年10月16日 上午10:44:16</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>su_jian </dd>
	  * </dl> 
	  * @param <T> 对象类型 
	  * @param entity 对象 
	  * @see {@linkplain java.lang.String }
	  * @return boolean true/false
	  * @since 1.0
	  * @throws DataAccessException  数据库访问异常
	  */
	public Domain getByUsername(String domainAccount);
}
