package com.xcj.admin.service.users;
import java.util.List;

import com.xcj.admin.entity.users.Score;
import com.xcj.admin.entity.users.Users;
import com.xcj.admin.base.BaseService;

/**
 *
  * <b>function:</b>  users
  * @package com.xcj.admin.service.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:40 CST 2015
  * @author name_**
 */
public interface UsersService extends BaseService{

   /**
    *
     * <b>function:</b>  users---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.service.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return List<Users>
     * @author name_**
    */
   List<Users> getSortList() throws Exception;

   /**
    *
     * <b>function:</b>  users---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.service.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return void
     * @author name_**
    */
   void removeById(Integer id)throws Exception;
   
   /**
      * <b>function:</b> 增加用户
      * @project xcjtest
      * @package com.xcj.admin.service.users  
      * @fileName com.xcj.*** 
      * @createDate Jan 19, 2015 2:46:13 PM
      * @return void
      * @author dapeng_wu
    */
   public void saveUsers(Users user)throws Exception;
   /**
      * <b>function:</b> 获取分页数据
      * @project xcjtest
      * @package com.xcj.admin.service.users  
      * @fileName com.xcj.*** 
      * @createDate Jan 19, 2015 4:55:49 PM
      * @return void
      * @author dapeng_wu
    */
   public List<Users> getUserList(int count) throws Exception;
   
   /**
      * <b>function:</b> 根据id修改name
      * @project xcjtest
      * @package com.xcj.admin.service.users  
      * @fileName com.xcj.*** 
      * @createDate Jan 19, 2015 5:25:20 PM
      * @return void
      * @author dapeng_wu
    */
   public boolean updateUsers(Integer id,String name)throws Exception;
   
   /**
      * <b>function:</b> 根据id修改班级
      * @project xcjtest
      * @package com.xcj.admin.service.users  
      * @fileName com.xcj.*** 
      * @createDate Jan 19, 2015 5:52:34 PM
      * @return boolean
      * @author dapeng_wu
    */
   public boolean updateUsersAndClassId(Integer id,Integer classId)throws Exception;
   /**
      * <b>function:</b> 删除用户
      * @project xcjtest
      * @package com.xcj.admin.service.users  
      * @fileName com.xcj.*** 
      * @createDate Jan 19, 2015 6:17:42 PM
      * @return boolean
      * @author dapeng_wu
    */
   public boolean delUsersAndName(String name)throws Exception;
   /**
      * <b>function:</b> 保存成绩并修改平均值
      * @project xcjtest
      * @package com.xcj.admin.service.users  
      * @fileName com.xcj.*** 
      * @createDate Jan 20, 2015 9:55:20 AM
      * @return boolean
      * @author dapeng_wu
    */
   public boolean saveScoreAndAvgSocre(Score score)throws Exception;
   /**
      * <b>function:</b> 保存成绩并修改平均值（修改平均分报错）
      * @project xcjtest
      * @package com.xcj.admin.service.users  
      * @fileName com.xcj.*** 
      * @createDate Jan 20, 2015 11:02:04 AM
      * @return boolean
      * @author dapeng_wu
    */
   public boolean saveScoreAndAvgSocre2(Score score)throws Exception;
}
