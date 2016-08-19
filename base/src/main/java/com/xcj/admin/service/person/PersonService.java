package com.xcj.admin.service.person;
import java.util.List;
import com.xcj.admin.entity.person.Person;
import com.xcj.admin.base.BaseService;

/**
 *
  * <b>function:</b>  person
  * @package com.xcj.admin.service.person.
  * @fileName com.xcj.admin.*
  * @createDate Tue Dec 23 18:15:51 CST 2014
  * @author name_**
 */
public interface PersonService extends BaseService{

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
   List<Person> getSortList() throws Exception;

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
   void removeById(Integer id)throws Exception;
}
