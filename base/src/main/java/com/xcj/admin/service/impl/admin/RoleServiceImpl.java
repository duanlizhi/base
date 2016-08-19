package com.xcj.admin.service.impl.admin;
import java.util.List;
import javax.annotation.Resource;
import com.xcj.common.page.Page;
import org.springframework.stereotype.Service;
import com.xcj.admin.service.admin.RoleService;
import com.xcj.admin.dao.admin.RoleDao;
import com.xcj.admin.entity.admin.Role;
import com.xcj.admin.base.BaseServiceImpl;

/**
 *
  * <b>function:</b>  role角色
  * @package com.xcj.admin.service.impl.admin.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 09 17:01:40 CST 2015
  * @author name yang.yan
 */
@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService{

	@Resource(name ="roleDaoImpl")
	private RoleDao roleDao;

   /**
    *
     * <b>function:</b>  role角色---获取list方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:01:40 CST 2015
     * @return List<Role>
  * @author name yang.yan
    */
  public List<Role> getAllList() throws Exception { 
    return roleDao.getAllList(); 
   }

   /**
    *
     * <b>function:</b>  role角色---获取list方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:01:40 CST 2015
     * @return List<Role>
  * @author name yang.yan
    */
  public Page<Role>  getByRolePage(Page<Role> ps,Role role)  throws Exception { 
    return roleDao.getByRolePage(ps,role); 
   }

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
 public void removeById(Integer id) throws Exception {
   roleDao.removeById(id);

} 
}
