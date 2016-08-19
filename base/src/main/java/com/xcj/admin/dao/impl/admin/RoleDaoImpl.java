package com.xcj.admin.dao.impl.admin;
import java.util.List;
import org.hibernate.Session;
import com.xcj.common.page.Page;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.entity.admin.Role;
import com.xcj.admin.dao.admin.RoleDao;

/**
 *
  * <b>function:</b>  role角色
  * @package com.xcj.admin.dao.impl.admin.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 09 17:01:41 CST 2015
  * @author name yang.yan
 */
@Component("roleDaoImpl")
@SuppressWarnings({"hiding"})
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

  @Resource
  private JdbcTemplate jdbcTemplate;

   /**
    *
     * <b>function:</b>  role角色---获取list方法
     * @project base
     * @package com.xcj.admin.dao.impl.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:01:41 CST 2015
     * @return List<T>
  * @author name yang.yan
    */
  public <T extends Role> List<T> getAllList() throws DataAccessException  {
		String hql = "from Role";
		return super.getList(hql); 
  }

   /**
    *
     * <b>function:</b>  role角色---获取分页list方法
     * @project base
     * @package com.xcj.admin.dao.impl.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:01:41 CST 2015
     * @return List<T>
  * @author name yang.yan
    */
  public Page<Role>  getByRolePage(Page<Role> page,Role role)  throws DataAccessException  {
 try {                                                                                                                
 	Session session = this.getSession();                                                                             
 	if(role.getName()!=null&&!role.getName().equals("")){                                      
 		page.setResult(session.createQuery(                                                                          
 				"from " + role.getClass().getName()                                                       
 						+ " where name like '%"+role.getName()+"%' order  by orders").setFirstResult(     
 				(page.getCurrentPage() - 1) * page.getPageSize())                                                    
 				.setMaxResults(page.getPageSize()).list());                                                          
 		page.setTotalsCount(Integer.parseInt(session.createQuery(                                                    
 				"select count(id) as cnt from "                                                                      
 						+ role.getClass().getName()+" where name like '%"+role.getName()+"%'") 
 				.setMaxResults(1).uniqueResult().toString()));                                                       
 	}else{                                                                                                           
 		page.setResult(session.createQuery(                                                                          
 				"from " + role.getClass().getName()                                                       
 						+ " order by orders ").setFirstResult(                                                       
 				(page.getCurrentPage() - 1) * page.getPageSize())                                                    
 				.setMaxResults(page.getPageSize()).list());                                                          
 		page.setTotalsCount(Integer.parseInt(session.createQuery(                                                    
 				"select count(id) as cnt from "                                                                      
 						+ role.getClass().getName())                                                      
 				.setMaxResults(1).uniqueResult().toString()));                                                       
 	}                                                                                                                
 } catch (Exception e) {                                                                                              
 	throw new RuntimeException(e);                                                                                   
 }                                                                                                                    
		return page; 
  }

   /**
    *
     * <b>function:</b>  role角色---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.impl.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:01:41 CST 2015
     * @return void
  * @author name yang.yan
    */
  public void removeById(Integer id) throws DataAccessException { 
		super.delete("delete from Role t where t.id="+id);
  }

}
