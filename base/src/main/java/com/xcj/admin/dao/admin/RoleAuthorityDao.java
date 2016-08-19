package com.xcj.admin.dao.admin;
import java.util.List;
import com.xcj.common.page.Page;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.entity.admin.RoleAuthority;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  role_authority角色权限
  * @package com.xcj.admin.dao.admin.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 09 17:04:10 CST 2015
  * @author name yang.yan
 */
public interface RoleAuthorityDao extends BaseDao{

   /**
    *
     * <b>function:</b>  role_authority角色权限---获取list方法
     * @project base
     * @package com.xcj.admin.dao.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:04:10 CST 2015
     * @return List<T>
  * @author name yang.yan
    */
  public <T extends RoleAuthority> List<T> getAllList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  role_authority角色权限---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:04:10 CST 2015
     * @return void
  * @author name yang.yan
    */
  public void removeById(Integer id) throws DataAccessException;

}
