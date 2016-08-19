package com.xcj.admin.service.impl.admin;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.admin.RoleAuthorityDao;
import com.xcj.admin.entity.admin.RoleAuthority;
import com.xcj.admin.service.admin.RoleAuthorityService;

/**
 *
  * <b>function:</b>  role_authority角色权限
  * @package com.xcj.admin.service.impl.admin.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 09 17:04:10 CST 2015
  * @author name yang.yan
 */
@Service("roleAuthorityServiceImpl")
public class RoleAuthorityServiceImpl extends BaseServiceImpl implements RoleAuthorityService{

	@Resource(name ="roleAuthorityDaoImpl")
	private RoleAuthorityDao roleAuthorityDao;

   /**
    *
     * <b>function:</b>  role_authority角色权限---获取list方法
     * @project base
     * @package com.xcj.admin.service.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:04:10 CST 2015
     * @return List<RoleAuthority>
  * @author name yang.yan
    */
  public List<RoleAuthority> getAllList() throws Exception { 
    return roleAuthorityDao.getAllList(); 
   }


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
 public void removeById(Integer id) throws Exception {
   roleAuthorityDao.removeById(id);

} 
}
