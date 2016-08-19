package com.xcj.admin.dao.users;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.entity.users.Classname;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  classname
  * @package com.xcj.admin.dao.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:26:57 CST 2015
  * @author name_**
 */
public interface ClassnameDao extends BaseDao{

   /**
    *
     * <b>function:</b>  classname---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.dao.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return List<T>
     * @author name_**
    */
  public <T extends Classname> List<T> getSortList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  classname---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.dao.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return void
     * @author name_**
    */
  public void removeById(Integer id) throws DataAccessException;

}
