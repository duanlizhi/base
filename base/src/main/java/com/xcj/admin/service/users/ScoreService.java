package com.xcj.admin.service.users;
import java.util.List;
import com.xcj.admin.entity.users.Score;
import com.xcj.admin.base.BaseService;

/**
 *
  * <b>function:</b>  score
  * @package com.xcj.admin.service.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:12 CST 2015
  * @author name_**
 */
public interface ScoreService extends BaseService{

   /**
    *
     * <b>function:</b>  score---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.service.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return List<Score>
     * @author name_**
    */
   List<Score> getSortList() throws Exception;

   /**
    *
     * <b>function:</b>  score---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.service.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return void
     * @author name_**
    */
   void removeById(Integer id)throws Exception;
}
