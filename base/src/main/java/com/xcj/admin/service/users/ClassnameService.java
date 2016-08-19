package com.xcj.admin.service.users;
import java.util.List;
import com.xcj.admin.entity.users.Classname;
import com.xcj.admin.base.BaseService;

/**
 *
  * <b>function:</b>  classname
  * @package com.xcj.admin.service.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:26:57 CST 2015
  * @author name_**
 */
public interface ClassnameService extends BaseService{

   /**
    *
     * <b>function:</b>  classname---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.service.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return List<Classname>
     * @author name_**
    */
   List<Classname> getSortList() throws Exception;

   /**
    *
     * <b>function:</b>  classname---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.service.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return void
     * @author name_**
    */
   void removeById(Integer id)throws Exception;
}
