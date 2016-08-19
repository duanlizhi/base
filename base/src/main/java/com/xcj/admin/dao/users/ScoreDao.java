package com.xcj.admin.dao.users;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.entity.users.Score;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  score
  * @package com.xcj.admin.dao.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:12 CST 2015
  * @author name_**
 */
public interface ScoreDao extends BaseDao{

   /**
    *
     * <b>function:</b>  score---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.dao.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return List<T>
     * @author name_**
    */
  public <T extends Score> List<T> getSortList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  score---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.dao.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:13 CST 2015
     * @return void
     * @author name_**
    */
  public void removeById(Integer id) throws DataAccessException;
  /**
     * <b>function:</b> 
     * @project xcjtest
     * @package com.xcj.admin.dao.users  
     * @fileName com.xcj.*** 
     * @createDate Jan 20, 2015 9:26:30 AM
     * @return List<Score>
     * @author dapeng_wu
   */
  public List<Score> getScoreListAndUsersId(Integer userid) throws Exception;

}
