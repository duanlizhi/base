package com.xcj.admin.dao.study;
import java.util.List;

import com.xcj.common.page.Page;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.entity.study.TotalStudyInfo;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  total_study_info学习总计信息
  * @package com.xcj.admin.dao.study.
  * @fileName com.xcj.admin.*
  * @createDate Wed Oct 14 15:25:02 CST 2015
  * @author name su_jian
 */
public interface TotalStudyInfoDao extends BaseDao{

   /**
    *
     * <b>function:</b>  total_study_info学习总计信息---获取list方法
     * @project base
     * @package com.xcj.admin.dao.study.
     * @fileName com.xcj.admin.*
     * @createDate Wed Oct 14 15:25:02 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public <T extends TotalStudyInfo> List<T> getAllList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  total_study_info学习总计信息---获取分页list方法
     * @project base
     * @package com.xcj.admin.dao.study.
     * @fileName com.xcj.admin.*
     * @createDate Wed Oct 14 15:25:02 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public Page<TotalStudyInfo>  getByTotalStudyInfoPage(Page<TotalStudyInfo> ps,TotalStudyInfo totalStudyInfo)	throws DataAccessException;

   /**
    *
     * <b>function:</b>  total_study_info学习总计信息---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.study.
     * @fileName com.xcj.admin.*
     * @createDate Wed Oct 14 15:25:02 CST 2015
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
  *  <dd> 2015年10月14日 下午4:10:42</dd></dl> 
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
public TotalStudyInfo getbyEmailAndDomainAndCourseNumber(String userEmail,String  domainUsername,String  courseNumber,String faultNumber,String studyFlag) throws DataAccessException;

}
