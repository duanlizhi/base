package com.xcj.admin.dao.impl.admin;
import java.util.List;
import org.hibernate.Session;
import com.xcj.common.page.Page;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.entity.admin.RoleAuthority;
import com.xcj.admin.dao.admin.RoleAuthorityDao;

/**
 *
  * <b>function:</b>  role_authority角色权限
  * @package com.xcj.admin.dao.impl.admin.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 09 17:04:11 CST 2015
  * @author name yang.yan
 */
@Component("roleAuthorityDaoImpl")
@SuppressWarnings({"hiding"})
public class RoleAuthorityDaoImpl extends BaseDaoImpl implements RoleAuthorityDao {

  @Resource
  private JdbcTemplate jdbcTemplate;

   /**
    *
     * <b>function:</b>  role_authority角色权限---获取list方法
     * @project base
     * @package com.xcj.admin.dao.impl.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:04:11 CST 2015
     * @return List<T>
  * @author name yang.yan
    */
  public <T extends RoleAuthority> List<T> getAllList() throws DataAccessException  {
		String hql = "from RoleAuthority";
		return super.getList(hql); 
  }


   /**
    *
     * <b>function:</b>  role_authority角色权限---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.impl.admin.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 09 17:04:11 CST 2015
     * @return void
  * @author name yang.yan
    */
  public void removeById(Integer id) throws DataAccessException { 
		super.delete("delete from RoleAuthority t where t.id="+id);
  }

}
