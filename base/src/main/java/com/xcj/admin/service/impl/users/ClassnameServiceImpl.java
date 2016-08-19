package com.xcj.admin.service.impl.users;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xcj.admin.service.users.ClassnameService;
import com.xcj.admin.dao.users.ClassnameDao;
import com.xcj.admin.entity.users.Classname;
import com.xcj.admin.base.BaseServiceImpl;

/**
 *
  * <b>function:</b>  classname
  * @package com.xcj.admin.service.impl.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:26:57 CST 2015
  * @author name_**
 */
@Service("classnameServiceImpl")
public class ClassnameServiceImpl extends BaseServiceImpl implements ClassnameService{

	@Resource(name ="classnameDaoImpl")
	private ClassnameDao classnameDao;

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
  public List<Classname> getSortList() throws Exception { 
    return classnameDao.getSortList(); 
   }

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
 public void removeById(Integer id) throws Exception {
   classnameDao.removeById(id);
  //baseDao.delete(baseDao.getById(Classname,id));
} 
}
