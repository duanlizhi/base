package com.xcj.admin.dao.impl.course;
import java.util.List;

import org.hibernate.Session;

import com.xcj.common.page.Page;
import com.xcj.common.util.common.StringUtil;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.entity.course.SbtTestScore;
import com.xcj.admin.dao.course.SbtTestScoreDao;

/**
 *
  * <b>function:</b>  sbt_test_scoresbt_test_score
  * @package com.xcj.admin.dao.impl.course.
  * @fileName com.xcj.admin.*
  * @createDate Tue Dec 15 13:53:26 CST 2015
  * @author name su_jian
 */
@Component("sbtTestScoreDaoImpl")
@SuppressWarnings({"hiding"})
public class SbtTestScoreDaoImpl extends BaseDaoImpl implements SbtTestScoreDao {

  @Resource
  private JdbcTemplate jdbcTemplate;

   /**
    *
     * <b>function:</b>  sbt_test_scoresbt_test_score---获取list方法
     * @project base
     * @package com.xcj.admin.dao.impl.course.
     * @fileName com.xcj.admin.*
     * @createDate Tue Dec 15 13:53:26 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public <T extends SbtTestScore> List<T> getAllList() throws DataAccessException  {
		String hql = "from SbtTestScore";
		return super.getList(hql); 
  }

   /**
    *
     * <b>function:</b>  sbt_test_scoresbt_test_score---获取分页list方法
     * @project base
     * @package com.xcj.admin.dao.impl.course.
     * @fileName com.xcj.admin.*
     * @createDate Tue Dec 15 13:53:26 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public Page<SbtTestScore>  getBySbtTestScorePage(Page<SbtTestScore> page,SbtTestScore sbtTestScore)  throws DataAccessException  {
 try {                                                                                                                
 	Session session = this.getSession();                                                                             
 	if(sbtTestScore.getName()!=null&&!sbtTestScore.getName().equals("")){                                      
 		page.setResult(session.createQuery(                                                                          
 				"from " + sbtTestScore.getClass().getName()                                                       
 						+ " where name like '%"+sbtTestScore.getName()+"%' order  by orders").setFirstResult(     
 				(page.getCurrentPage() - 1) * page.getPageSize())                                                    
 				.setMaxResults(page.getPageSize()).list());                                                          
 		page.setTotalsCount(Integer.parseInt(session.createQuery(                                                    
 				"select count(id) as cnt from "                                                                      
 						+ sbtTestScore.getClass().getName()+" where name like '%"+sbtTestScore.getName()+"%'") 
 				.setMaxResults(1).uniqueResult().toString()));                                                       
 	}else{                                                                                                           
 		page.setResult(session.createQuery(                                                                          
 				"from " + sbtTestScore.getClass().getName()                                                       
 						+ " order by orders ").setFirstResult(                                                       
 				(page.getCurrentPage() - 1) * page.getPageSize())                                                    
 				.setMaxResults(page.getPageSize()).list());                                                          
 		page.setTotalsCount(Integer.parseInt(session.createQuery(                                                    
 				"select count(id) as cnt from "                                                                      
 						+ sbtTestScore.getClass().getName())                                                      
 				.setMaxResults(1).uniqueResult().toString()));                                                       
 	}                                                                                                                
 } catch (Exception e) {                                                                                              
 	throw new RuntimeException(e);                                                                                   
 }                                                                                                                    
		return page; 
  }

   /**
    *
     * <b>function:</b>  sbt_test_scoresbt_test_score---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.impl.course.
     * @fileName com.xcj.admin.*
     * @createDate Tue Dec 15 13:53:26 CST 2015
     * @return void
  * @author name su_jian
    */
  public void removeById(Integer id) throws DataAccessException { 
	  super.delete("delete from SbtTestScore t where t.id="+id);
  }

  public void removeByCourseWareId(Integer courseWareId) throws DataAccessException{
	  super.delete("delete from SbtTestScore t where t.courseWareId="+courseWareId);
  }
  
  /**
   * 
  	 *<dl>
  	 * 	<dt><span class="strong">方法说明:</span></dt>
  	 *  <dd>根据课件编号，故障编号获取工单集合</dd>
  	 * </dl> 
  	 * 	<dl><dt><span class="strong">逻辑说明:</span></dt>
  	 *  <dd>1、根据课件编号，故障编号获取工单集合，理论上集合只能最多出现一个元素</dd></dl> 
  	 * 	<dl><dt><span class="strong">创建时间:</span></dt>
  	 *  <dd> 2016年2月26日 下午4:49:14</dd></dl> 
  	 * 	<dt><span class="strong">author:</span></dt>
  	 *  <dd>wu_dapeng </dd>
  	 * </dl> 
   */
  public List<SbtTestScore> getListByCourseNumber(String courseNumber,String faultNumber)throws DataAccessException{
	  if(StringUtil.isNotEmpty(faultNumber)){
		  String sql = "select * from sbt_test_score where course_number = ? and fault_number = ? and is_enable=1 ";
		  return super.findByJDBC(sql, new Object[]{courseNumber,faultNumber},SbtTestScore.class);
	  }else{
		  String sql = "select * from sbt_test_score where course_number = ? and is_enable=1 ";
		  return super.findByJDBC(sql, new Object[]{courseNumber},SbtTestScore.class);
	  }
  }
  
  public void updateSbtTestScore(Integer id,String text) throws DataAccessException{
	  String sql = "update sbt_test_score set text = '"+text+"' where id = "+id;
	  super.saveOrUpdateOrDeleteByJDBC(sql, new Object[]{},SbtTestScore.class);
  }
  
}
