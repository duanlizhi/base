package com.xcj.admin.dao.users;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.entity.users.Users;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  users
  * @package com.xcj.admin.dao.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:40 CST 2015
  * @author name_**
 */
public interface UsersDao extends BaseDao{

   /**
    *
     * <b>function:</b>  users---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.dao.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return List<T>
     * @author name_**
    */
  public <T extends Users> List<T> getSortList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  users---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.dao.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return void
     * @author name_**
    */
  public void removeById(Integer id) throws DataAccessException;
  
  /**
     * <b>function:</b> 根据用户名的到集合
     * @project xcjtest
     * @package com.xcj.admin.dao.users  
     * @fileName com.xcj.*** 
     * @createDate Jan 19, 2015 3:23:18 PM
     * @return void
     * @author dapeng_wu
   */
  public List<Users> getUsersListAndName(String name) throws Exception;
  
  /**
     * <b>function:</b> 根据id修改用户名
     * @project xcjtest
     * @package com.xcj.admin.dao.users  
     * @fileName com.xcj.*** 
     * @createDate Jan 19, 2015 5:32:57 PM
     * @return boolean
     * @author dapeng_wu
   */
  public boolean updateUsersAndId(Integer id,String name)throws Exception;
  /**
     * <b>function:</b> 
     * @project xcjtest
     * @package com.xcj.admin.dao.users  
     * @fileName com.xcj.*** 
     * @createDate Jan 19, 2015 5:50:10 PM
     * @return boolean
     * @author dapeng_wu
   */
  public boolean updateUsersAndClassId(Integer id,Integer classId)throws Exception;
  
  /**
     * <b>function:</b> 
     * @project xcjtest
     * @package com.xcj.admin.dao.users  
     * @fileName com.xcj.*** 
     * @createDate Jan 20, 2015 9:41:28 AM
     * @return boolean
     * @author dapeng_wu
   */
  public boolean updateUsersAndAvgScore(Integer id,Integer avgScore) throws Exception;
  /**
     * <b>function:</b> 测试
     * @project xcjtest
     * @package com.xcj.admin.dao.users  
     * @fileName com.xcj.*** 
     * @createDate Jan 20, 2015 11:04:55 AM
     * @return boolean
     * @author dapeng_wu
   */
  public boolean updateUsersAndAvgScore2(Integer id,Integer avgScore) throws Exception;

}
