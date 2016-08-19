package com.xcj.admin.service.impl.domain;

import java.util.List;

import javax.annotation.Resource;

import net.spy.memcached.MemcachedClient;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.domain.DomainDao;
import com.xcj.admin.entity.course.DomainCourseWare;
import com.xcj.admin.entity.domain.Domain;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.md5.MD5Uitl;
/**
 * <b>function:</b> domain域管理表
 * @package com.xcj.admin.service.impl.domain.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 17:39:31 CST 2015
 * @author name su_jian
 */
@Service("domainServiceImpl")
public class DomainServiceImpl extends BaseServiceImpl implements DomainService {

	@Resource(name = "domainDaoImpl")
	private DomainDao domainDao;

	/**
	 * 
	 * <b>function:</b> domain域管理表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:31 CST 2015
	 * @return List<Domain>
	 * @author name su_jian
	 */
	public List<Domain> getAllList() throws Exception {
		return domainDao.getAllList();
	}

	/**
	 * 
	 * <b>function:</b> domain域管理表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:31 CST 2015
	 * @return List<Domain>
	 * @author name su_jian
	 */
	public Page<Domain> getByDomainPage(Page<Domain> ps, Domain domain)
			throws Exception {
		return domainDao.getByDomainPage(ps, domain);
	}

	/**
	 * <b>function:</b> domain域管理表---根据id删除数据方法
	 * @project base
	 * @package com.xcj.admin.service.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:31 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		domainDao.removeById(id);
	}
	
	/**
	 * <b>function:</b> 根据用户名和密码获取域信息
	 * @project base
	 * @package com.xcj.admin.service.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:31 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public boolean getByUsernameAndPassword(String username, String password) {
		Domain doamin=domainDao.getByUsernameAndPassword(username,password);
		if(doamin==null){
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
	 * <b>function:</b> 生成token的方法
	 * @project base
	 * @package com.xcj.admin.service.domain.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 17:39:31 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public String generateToken(String username, String password,String userEmail) {
		//生成token的逻辑
		String str=DateUtil.today()
		+MD5Uitl.MD5Encode(username).toUpperCase()
		+MD5Uitl.MD5Encode(password)
		+MD5Uitl.MD5Encode(userEmail)
		+APIConstants.XCJ_MESSAGE_API;
		return MD5Uitl.MD5Encode(MD5Uitl.MD5Encode(str)).toUpperCase();
	}

	/**
	 * <b>function:</b> 判断学习token的方法
	 * DomainService
	 * @createDate  2015-2-9 下午02:46:10
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String
	 */
	public boolean judgeTokenIsTime(String token,String domainName,String userEmail) throws Exception {
		if(token.length()!=32){
			return false;
		}
		MemcachedClient mc1 = MemcachedClientFactory.getInstance();
		return  (token.equals((String)mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_STUDY_TOKEN+domainName+"_"+userEmail)));
	}
	
	/**
	   * <b>function:</b> 判断测评token的方法
	   * @project base
	   * @package com.xcj.admin.service.impl.domain  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 上午10:36:47
	   * @return boolean
	   * @author dapeng_wu
	 */
	public boolean judgeTestTokenIsTime(String token,String domainName,String userEmail)throws Exception {
		if(token.length()!=32){
			return false;
		}
		MemcachedClient mc1 = MemcachedClientFactory.getInstance();
		return  (token.equals((String)mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainName+"_"+userEmail)));
	}

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
	public boolean validateName(String name, String id) throws Exception {
		return domainDao.validateName(name, id);
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
    public boolean validateUsername(String username, String id) throws Exception {
        return domainDao.validateUsername(username, id);
    }

	/* 
	 * @see
	 * @param domainAccount
	 * @param domainPassword
	 * @param userEmail
	 * @return
	 * @throws Exception
	 */
	public String generateTestToken(String domainAccount,
			String domainPassword, String userEmail) throws Exception {
		//生成token的逻辑
		String str=DateUtil.today()
		+MD5Uitl.MD5Encode(domainAccount).toUpperCase()
		//+MD5Uitl.MD5Encode(DateUtil.thismonth()+"_"+DateUtil.today())
		+MD5Uitl.MD5Encode(domainPassword)
		+MD5Uitl.MD5Encode(userEmail)
		+APIConstants.XCJ_MESSAGE_TEST;
		return MD5Uitl.MD5Encode(MD5Uitl.MD5Encode(str)).toUpperCase();
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
	public List<DomainCourseWare> getCourseWares4Delete(String domainIds) throws Exception {
		return domainDao.getCourseWares4Delete(domainIds);
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
	@Transactional(rollbackFor=Exception.class)
	public boolean deleteDomainAllByDomainId(String ids) throws Exception {
		return domainDao.deleteDomainCourseWareByDomainId(ids) && domainDao.deleteDomainCoursePaperByDomainId(ids);
	}

	/** 
	 * <b>function:</b> 生成SessionID
	 * DomainService
	 * @createDate  2015-4-25 下午03:56:06
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String 
	 */ 
	public String generateTestPublicId(String domainAccount, String userEmail,
			String sessionId) {
		StringBuilder builder = new StringBuilder();
		builder.append(DateUtil.today());
		builder.append(domainAccount);
		builder.append(MD5Uitl.MD5Encode(userEmail).toUpperCase());
		builder.append(APIConstants.XCJ_MESSAGE_API);
		builder.append(APIConstants.XCJ_MESSAGE_API_TESTS);
		builder.append(sessionId);
		return MD5Uitl.MD5Encode(MD5Uitl.MD5Encode(builder.toString())).toUpperCase();
	}

	/** 
	 * <b>function:</b> 生成testpublicID
	 * DomainService
	 * @createDate  2015-4-25 下午03:56:23
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String 
	 */ 
	public String generateTestSessionId(String userEmail, String token) {
		StringBuilder builder = new StringBuilder();
		builder.append(DateUtil.getCurrentTime());
		builder.append(MD5Uitl.MD5Encode(userEmail).toUpperCase());
		builder.append(APIConstants.XCJ_MESSAGE_API);
		builder.append(APIConstants.XCJ_MESSAGE_API_TESTS);
		builder.append(token);
		return MD5Uitl.MD5Encode(MD5Uitl.MD5Encode(builder.toString()))
				.toUpperCase();
		 
	}


	/** 
	 * <b>function:</b> 生成学习的SessionID
	 * DomainService
	 * @createDate  2015-4-25 下午05:50:12
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String 
	 */ 
	public String generateStudySessionId(String userEmail, String token) {
		StringBuilder builder = new StringBuilder();
		builder.append(DateUtil.getCurrentTime());
		builder.append(MD5Uitl.MD5Encode(userEmail).toUpperCase());
		builder.append(APIConstants.XCJ_MESSAGE_API);
		builder.append(token);
		return MD5Uitl.MD5Encode(MD5Uitl.MD5Encode(builder.toString())).toUpperCase();
	}
	/** 
	 * <b>function:</b> 生成学习token
	 * DomainService
	 * @createDate  2015-4-25 下午05:50:00
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String 
	 */ 
	public String generateStudyToken(String domainAccount,
			String domainPassword, String userEmail) {
		String str=DateUtil.today()
		+MD5Uitl.MD5Encode(domainAccount).toUpperCase()
		+MD5Uitl.MD5Encode(domainPassword)
		+MD5Uitl.MD5Encode(userEmail)
		+APIConstants.XCJ_MESSAGE_API;
		return MD5Uitl.MD5Encode(MD5Uitl.MD5Encode(str)).toUpperCase();
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
		 *  <dd> 2015年10月16日 上午10:43:24</dd></dl> 
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
		
		return domainDao.getByUsername(domainAccount);
	}
	
}