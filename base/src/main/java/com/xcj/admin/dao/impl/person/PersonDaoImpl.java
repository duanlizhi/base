package com.xcj.admin.dao.impl.person;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.entity.person.Person;
import com.xcj.admin.dao.person.PersonDao;

/**
 *
  * <b>function:</b>  person
  * @package com.xcj.admin.dao.impl.person.
  * @fileName com.xcj.admin.*
  * @createDate Tue Dec 23 18:15:51 CST 2014
  * @author name_**
 */
@Component("personDaoImpl")
@SuppressWarnings({"hiding"})
public class PersonDaoImpl extends BaseDaoImpl implements PersonDao {

  @Resource
  private JdbcTemplate jdbcTemplate;

   /**
    *
     * <b>function:</b>  person---获取list方法
     * @project testbaseframe
     * @package com.xcj.admin.dao.impl.person.
     * @fileName com.xcj.admin.*
     * @createDate Tue Dec 23 18:15:51 CST 2014
     * @return List<T>
     * @author name_**
    */
  public <T extends Person> List<T> getSortList() throws DataAccessException  {
		String hql = "from Person";
		return super.getList(hql); 
  }

   /**
    *
     * <b>function:</b>  person---根据id删除数据方法
     * @project testbaseframe
     * @package com.xcj.admin.dao.impl.person.
     * @fileName com.xcj.admin.*
     * @createDate Tue Dec 23 18:15:51 CST 2014
     * @return void
     * @author name_**
    */
  public void removeById(Integer id) throws DataAccessException { 
		super.delete("delete from Person t where t.id="+id);
  }

}
