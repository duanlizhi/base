package com.xcj.admin.dao.person;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.entity.person.Person;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  person
  * @package com.xcj.admin.dao.person.
  * @fileName com.xcj.admin.*
  * @createDate Tue Dec 23 18:15:51 CST 2014
  * @author name_**
 */
public interface PersonDao extends BaseDao{

   /**
    *
     * <b>function:</b>  person---获取list方法
     * @project testbaseframe
     * @package com.xcj.admin.dao.person.
     * @fileName com.xcj.admin.*
     * @createDate Tue Dec 23 18:15:51 CST 2014
     * @return List<T>
     * @author name_**
    */
  public <T extends Person> List<T> getSortList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  person---根据id删除数据方法
     * @project testbaseframe
     * @package com.xcj.admin.dao.person.
     * @fileName com.xcj.admin.*
     * @createDate Tue Dec 23 18:15:51 CST 2014
     * @return void
     * @author name_**
    */
  public void removeById(Integer id) throws DataAccessException;

}
