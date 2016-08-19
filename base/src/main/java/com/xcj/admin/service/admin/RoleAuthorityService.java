package com.xcj.admin.service.admin;
import java.util.List;
import com.xcj.common.page.Page;
import com.xcj.admin.entity.admin.RoleAuthority;
import com.xcj.admin.base.BaseService;

  /**
  *
  * <b>function:</b>  role_authority角色权限
  * @package com.xcj.admin.service.admin.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 09 17:04:10 CST 2015
  * @author name yang.yan
 */
public interface RoleAuthorityService extends BaseService{


   /**
    *
     * <b>function:</b>  role_authority角色权限---获取所有数据方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:04:10 CST 2015
     * @return List<RoleAuthority>
  * @author name yang.yan
    */
   List<RoleAuthority>  getAllList()  throws Exception;

   /**
    *
     * <b>function:</b>  role_authority角色权限---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:04:10 CST 2015
     * @return void
  * @author name yang.yan
    */
   void removeById(Integer id)throws Exception;
}
