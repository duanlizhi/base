package com.xcj.admin.dao.test;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.tests.TestReport;
import com.xcj.common.page.Page;

/**
 *
  * <b>function:</b>  test_report测评报表
  * @package com.xcj.admin.dao.test.
  * @fileName com.xcj.admin.*
  * @createDate Thu Oct 15 13:55:11 CST 2015
  * @author name su_jian
 */
public interface TestReportDao extends BaseDao{

   /**
    *
     * <b>function:</b>  test_report测评报表---获取list方法
     * @project base
     * @package com.xcj.admin.dao.test.
     * @fileName com.xcj.admin.*
     * @createDate Thu Oct 15 13:55:11 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public <T extends TestReport> List<T> getAllList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  test_report测评报表---获取分页list方法
     * @project base
     * @package com.xcj.admin.dao.test.
     * @fileName com.xcj.admin.*
     * @createDate Thu Oct 15 13:55:11 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public Page<TestReport>  getByTestReportPage(Page<TestReport> ps,TestReport testReport)	throws DataAccessException;

   /**
    *
     * <b>function:</b>  test_report测评报表---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.test.
     * @fileName com.xcj.admin.*
     * @createDate Thu Oct 15 13:55:11 CST 2015
     * @return void
  * @author name su_jian
    */
  public void removeById(Integer id) throws DataAccessException;

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
  *  <dd> 2015年10月15日 下午2:10:06</dd></dl> 
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
public List<TestReport> getResultByFiveParam(String userEmail, String domainUsername,
		String courseNumber, String sessionId, String testsPublicId)throws DataAccessException;

}
