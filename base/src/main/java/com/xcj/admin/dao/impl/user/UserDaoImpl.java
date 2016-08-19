package com.xcj.admin.dao.impl.user;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.user.UserDao;
import com.xcj.admin.entity.user.User;

/**
 *
  * <b>function:</b>  user会员信息表
  * @package com.xcj.admin.dao.impl.user.
  * @fileName com.xcj.admin.*
  * @createDate Sat Aug 23 17:32:50 CST 2014
  * @author name su_jian
 */
@Component("userDaoImpl")
@SuppressWarnings({"hiding"})
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

  @Resource
  private JdbcTemplate jdbcTemplate;

   /**
    *
     * <b>function:</b>  user会员信息表---获取list方法
     * @project base
     * @package com.xcj.admin.dao.impl.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:51 CST 2014
     * @return List<T>
  * @author name su_jian
    */
  public <T extends User> List<T> getSortList() throws DataAccessException  {
		String hql = "from User";
		return super.getList(hql); 
  }

   /**
    *
     * <b>function:</b>  user会员信息表---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.impl.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:52 CST 2014
     * @return void
  * @author name su_jian
    */
  public void removeById(Integer id) throws DataAccessException { 
		super.delete("delete from User t where t.id="+id);
  }
  /**
   * <b>function:</b> 根据邮箱获取用户信息
   * @project xcjpar
   * @package com.xcj.web.dao.user  
   * @fileName com.xcj.*** 
   * @createDate Aug 21, 2014 3:13:37 PM
   * @return List<User>
   * @author su_jian
   */
  /**
	  * 根据邮箱获取用户信息
	  */
	public List<User> getlistByEmail(String username) {
		String hql = "from User user where user.email='" + username + "'";
		return super.get(hql); 
	}
	public User getByUserName(String webname) {
		String hql = "from User user where user.username='" + webname + "'";
		return super.get(hql);
	}
}
