package com.xcj.admin.service.impl.admin;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.admin.AdminRoleDao;
import com.xcj.admin.entity.admin.AdminRole;
import com.xcj.admin.service.admin.AdminRoleService;

/**
 *
  * <b>function:</b>  admin_role角色
  * @package com.xcj.admin.service.impl.admin.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 09 17:00:10 CST 2015
  * @author name yang.yan
 */
@Service("adminRoleServiceImpl")
public class AdminRoleServiceImpl extends BaseServiceImpl implements AdminRoleService{

	@Resource(name ="adminRoleDaoImpl")
	private AdminRoleDao adminRoleDao;

   /**
    *
     * <b>function:</b>  admin_role角色---获取list方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:00:10 CST 2015
     * @return List<AdminRole>
  * @author name yang.yan
    */
  public List<AdminRole> getAllList() throws Exception { 
    return adminRoleDao.getAllList(); 
   }


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
 public void removeById(Integer id) throws Exception {
   adminRoleDao.removeById(id);

} 
}
