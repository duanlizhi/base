package com.xcj.admin.service.domain;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.course.DomainCourseWare;
import com.xcj.admin.entity.domain.Domain;
import com.xcj.common.page.Page;

import java.util.List;

/**
 * 
 * <b>function:</b> domain域管理表
 * 
 * @package com.xcj.admin.service.domain.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 17:39:31 CST 2015
 * @author name su_jian
 */
public interface DomainService extends BaseService {

	/**
	 * 
	 * <b>function:</b> domain域管理表---获取分页List方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:31 CST 2015
	 * @return List<Domain>
	 * @author name su_jian
	 */
	public Page<Domain> getByDomainPage(Page<Domain> ps, Domain domain)
			throws Exception;

	/**
	 * 
	 * <b>function:</b> domain域管理表---获取所有数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:31 CST 2015
	 * @return List<Domain>
	 * @author name su_jian
	 */
	List<Domain> getAllList() throws Exception;

	/**
	 * 
	 * <b>function:</b> domain域管理表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:31 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	void removeById(Integer id) throws Exception;

	/**
	 * @param username 
	 * @param password
	 * @return
	 */
	public boolean getByUsernameAndPassword(String username, String password);
	/**
	 * 
	 * <b>function:</b> 生成token的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:31 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public String generateToken(String username, String password,String userEmail);
	
	/**
	 * <b>function:</b> 判断学习token的方法
	 * DomainService
	 * @createDate  2015-2-9 下午02:46:10
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String
	 */
	public boolean judgeTokenIsTime(String token,String domainName,String userEmail)throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 判断测评token的方法
	   * @project base
	   * @package com.xcj.admin.service.domain  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 上午10:39:19
	   * @return boolean
	   * @author dapeng_wu
	 */
	public boolean judgeTestTokenIsTime(String token,String domainName,String userEmail)throws Exception;
	
	/**
	 * 
	   * 验证域名称是否存在
	   * <b>function:</b> validateName
	   * @project base
	   * @package com.xcj.admin.service.domain  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-9 下午02:05:45
	   * @return boolean
	   * @author hehujun
	 */
	public boolean validateName(String name, String id) throws Exception;

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
    public boolean validateUsername(String username, String id) throws Exception;

	/** 
	 * <b>function:</b> 
	 * DomainService
	 * @createDate  2015-3-7 上午11:24:05
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String 
	 */ 
	public String generateTestToken(String domainAccount,
			String domainPassword, String userEmail)throws Exception;
	
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
	public boolean deleteDomainAllByDomainId(String ids) throws Exception;

	/** 
	 * <b>function:</b> 生成SessionID
	 * DomainService
	 * @createDate  2015-4-25 下午03:56:06
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String 
	 */ 
	public String generateTestSessionId(String userEmail, String token);

	/** 
	 * <b>function:</b> 生成testpublicID
	 * DomainService
	 * @createDate  2015-4-25 下午03:56:23
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String 
	 */ 
	public String generateTestPublicId(String domainAccount, String userEmail,
			String sessionId);

	/** 
	 * <b>function:</b> 生成学习token
	 * DomainService
	 * @createDate  2015-4-25 下午05:50:00
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String 
	 */ 
	public String generateStudyToken(String domainAccount,
			String domainPassword, String userEmail);

	/** 
	 * <b>function:</b> 生成学习的SessionID
	 * DomainService
	 * @createDate  2015-4-25 下午05:50:12
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String 
	 */ 
	public String generateStudySessionId(String userEmail, String token);

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
	  *  <dd> 2015年10月16日 上午10:43:11</dd></dl> 
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
