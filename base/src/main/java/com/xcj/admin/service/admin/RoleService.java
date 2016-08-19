package com.xcj.admin.service.admin;
import java.util.List;
import com.xcj.common.page.Page;
import com.xcj.admin.entity.admin.Role;
import com.xcj.admin.base.BaseService;

  /**
  *
  * <b>function:</b>  role角色
  * @package com.xcj.admin.service.admin.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 09 17:01:40 CST 2015
  * @author name yang.yan
 */
public interface RoleService extends BaseService{

   /**
    *
     * <b>function:</b>  role角色---获取分页List方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:01:40 CST 2015
     * @return List<Role>
  * @author name yang.yan
    */
 public  Page<Role>  getByRolePage(Page<Role> ps,Role role) throws Exception;

   /**
    *
     * <b>function:</b>  role角色---获取所有数据方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:01:40 CST 2015
     * @return List<Role>
  * @author name yang.yan
    */
   List<Role>  getAllList()  throws Exception;

   /**
    *
     * <b>function:</b>  role角色---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:01:40 CST 2015
     * @return void
  * @author name yang.yan
    */
   void removeById(Integer id)throws Exception;
}
