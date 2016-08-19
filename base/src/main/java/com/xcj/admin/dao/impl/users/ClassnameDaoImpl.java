package com.xcj.admin.dao.impl.users;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.entity.users.Classname;
import com.xcj.admin.dao.users.ClassnameDao;

/**
 *
  * <b>function:</b>  classname
  * @package com.xcj.admin.dao.impl.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:26:57 CST 2015
  * @author name_**
 */
@Component("classnameDaoImpl")
@SuppressWarnings({"hiding"})
public class ClassnameDaoImpl extends BaseDaoImpl implements ClassnameDao {

  @Resource
  private JdbcTemplate jdbcTemplate;

   /**
    *
     * <b>function:</b>  classname---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.dao.impl.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return List<T>
     * @author name_**
    */
  public <T extends Classname> List<T> getSortList() throws DataAccessException  {
		String hql = "from Classname";
		return super.getList(hql); 
  }

   /**
    *
     * <b>function:</b>  classname---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.dao.impl.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return void
     * @author name_**
    */
  public void removeById(Integer id) throws DataAccessException { 
		super.delete("delete from Classname t where t.id="+id);
  }

}
