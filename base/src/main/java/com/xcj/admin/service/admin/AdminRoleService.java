package com.xcj.admin.service.admin;
import java.util.List;
import com.xcj.common.page.Page;
import com.xcj.admin.entity.admin.AdminRole;
import com.xcj.admin.base.BaseService;

  /**
  *
  * <b>function:</b>  admin_role角色
  * @package com.xcj.admin.service.admin.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 09 17:00:10 CST 2015
  * @author name yang.yan
 */
public interface AdminRoleService extends BaseService{

   /**
    *
     * <b>function:</b>  admin_role角色---获取所有数据方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:00:10 CST 2015
     * @return List<AdminRole>
  * @author name yang.yan
    */
   List<AdminRole>  getAllList()  throws Exception;

   /**
    *
     * <b>function:</b>  admin_role角色---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:00:10 CST 2015
     * @return void
  * @author name yang.yan
    */
   void removeById(Integer id)throws Exception;
}
