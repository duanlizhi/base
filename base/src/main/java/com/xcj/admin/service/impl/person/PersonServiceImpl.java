package com.xcj.admin.service.impl.person;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xcj.admin.service.person.PersonService;
import com.xcj.admin.dao.person.PersonDao;
import com.xcj.admin.entity.person.Person;
import com.xcj.admin.base.BaseServiceImpl;

/**
 *
  * <b>function:</b>  person
  * @package com.xcj.admin.service.impl.person.
  * @fileName com.xcj.admin.*
  * @createDate Tue Dec 23 18:15:51 CST 2014
  * @author name_**
 */
@Service("personServiceImpl")
public class PersonServiceImpl extends BaseServiceImpl implements PersonService{

	@Resource(name ="personDaoImpl")
	private PersonDao personDao;

   /**
    *
     * <b>function:</b>  person---获取list方法
     * @project testbaseframe
     * @package com.xcj.admin.service.person.
     * @fileName com.xcj.admin.*
     * @createDate Tue Dec 23 18:15:51 CST 2014
     * @return List<Person>
     * @author name_**
    */
  public List<Person> getSortList() throws Exception { 
    return personDao.getSortList(); 
   }

   /**
    *
     * <b>function:</b>  person---根据id删除数据方法
     * @project testbaseframe
     * @package com.xcj.admin.service.person.
     * @fileName com.xcj.admin.*
     * @createDate Tue Dec 23 18:15:51 CST 2014
     * @return void
     * @author name_**
    */
 public void removeById(Integer id) throws Exception {
   personDao.removeById(id);
  //baseDao.delete(baseDao.getById(Person,id));
} 
}
