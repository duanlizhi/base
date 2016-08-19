package com.xcj.admin.dao.user;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.entity.user.User;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  user会员信息表
  * @package com.xcj.admin.dao.user.
  * @fileName com.xcj.admin.*
  * @createDate Sat Aug 23 17:32:46 CST 2014
  * @author name su_jian
 */
public interface UserDao extends BaseDao{

   /**
    *
     * <b>function:</b>  user会员信息表---获取list方法
     * @project base
     * @package com.xcj.admin.dao.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:48 CST 2014
     * @return List<T>
  * @author name su_jian
    */
  public <T extends User> List<T> getSortList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  user会员信息表---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:49 CST 2014
     * @return void
  * @author name su_jian
    */
  public void removeById(Integer id) throws DataAccessException;
  /**
   * <b>function:</b> 根据邮箱获取用户信息
   * @project xcjpar
   * @package com.xcj.web.dao.user  
   * @fileName com.xcj.*** 
   * @createDate Aug 21, 2014 3:13:37 PM
   * @return List<User>
   * @author su_jian
   */
   public List<User> getlistByEmail(String username);

 /**
   * <b>function:</b> 
   * @project baseframe
   * @package com.xcj.admin.dao.user  
   * @fileName com.xcj.*** 
   * @createDate Aug 23, 2014 5:46:11 PM
   * @return User
   * @author su_jian
   */
 public User getByUserName(String webname);
}
