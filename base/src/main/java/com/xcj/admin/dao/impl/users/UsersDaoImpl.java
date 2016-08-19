package com.xcj.admin.dao.impl.users;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.users.UsersDao;
import com.xcj.admin.entity.users.Users;

/**
 *
  * <b>function:</b>  users
  * @package com.xcj.admin.dao.impl.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:40 CST 2015
  * @author name_**
 */
@Component("usersDaoImpl")
@SuppressWarnings({"hiding"})
public class UsersDaoImpl extends BaseDaoImpl implements UsersDao {

  @Resource
  private JdbcTemplate jdbcTemplate;

   /**
    *
     * <b>function:</b>  users---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.dao.impl.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return List<T>
     * @author name_**
    */
  public <T extends Users> List<T> getSortList() throws DataAccessException  {
		String hql = "from Users";
		return super.getList(hql); 
  }

   /**
     * <b>function:</b>  users---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.dao.impl.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return void
     * @author name_**
    */
	  public void removeById(Integer id) throws DataAccessException { 
			super.delete("delete from Users t where t.id="+id);
	  }
	  
	  /**
	     * <b>function:</b> 根据用户名的到集合
	     * @project xcjtest
	     * @package com.xcj.admin.dao.impl.users  
	     * @fileName com.xcj.*** 
	     * @createDate Jan 19, 2015 3:35:01 PM
	     * @return List<Users>
	     * @author dapeng_wu
	   */
	public List<Users> getUsersListAndName(String name) throws Exception {
		String sql = "select * from users where name = ?";
		return super.findByJDBC(sql, new Object[]{name}, Users.class); 
	}

	/**
	   * <b>function:</b> 
	   * @project xcjtest
	   * @package com.xcj.admin.dao.impl.users  
	   * @fileName com.xcj.*** 
	   * @createDate Jan 19, 2015 4:54:21 PM
	   * @return List<Users>
	   * @author dapeng_wu
	 */
	 public boolean updateUsersAndId(Integer id,String name)throws Exception{
		 String sql = "update users set name= ? where id = ?";
		 super.saveOrUpdateOrDeleteByJDBC(sql, new Object[]{name,id}, Users.class); 
		 return true;
	 }
	 
	 /**
	   * <b>function:</b> 
	   * @project xcjtest
	   * @package com.xcj.admin.dao.impl.users  
	   * @fileName com.xcj.*** 
	   * @createDate Jan 19, 2015 4:54:21 PM
	   * @return List<Users>
	   * @author dapeng_wu
	 */
	 public boolean updateUsersAndClassId(Integer id,Integer classId)throws Exception{
		 String sql = "update users set classname_id = ? where id = ?";
		 super.saveOrUpdateOrDeleteByJDBC(sql, new Object[]{classId,id}, Users.class); 
		 return true;
	 }
	 
	 /**
	    * <b>function:</b> 
	    * @project xcjtest
	    * @package com.xcj.admin.dao.impl.users  
	    * @fileName com.xcj.*** 
	    * @createDate Jan 20, 2015 9:40:57 AM
	    * @return boolean
	    * @author dapeng_wu
	  */
	 public boolean updateUsersAndAvgScore(Integer id,Integer avgScore) throws Exception{
		 String sql = "update users set avg_score = ? where id = ?";
		 super.saveOrUpdateOrDeleteByJDBC(sql, new Object[]{avgScore,id}, Users.class); 
		 return true;
	 }
	 /**
	    * <b>function:</b> 
	    * @project xcjtest
	    * @package com.xcj.admin.dao.impl.users  
	    * @fileName com.xcj.*** 
	    * @createDate Jan 20, 2015 11:04:09 AM
	    * @return boolean
	    * @author dapeng_wu
	  */
	 public boolean updateUsersAndAvgScore2(Integer id,Integer avgScore) throws Exception{
		 int i = 1/0; 
		 return true;
	 }
}
