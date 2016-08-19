package com.xcj.admin.dao.impl.users;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.entity.users.Score;
import com.xcj.admin.entity.users.Users;
import com.xcj.admin.dao.users.ScoreDao;

/**
 *
  * <b>function:</b>  score
  * @package com.xcj.admin.dao.impl.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:13 CST 2015
  * @author name_**
 */
@Component("scoreDaoImpl")
@SuppressWarnings({"hiding"})
public class ScoreDaoImpl extends BaseDaoImpl implements ScoreDao {

  @Resource
  private JdbcTemplate jdbcTemplate;

   /**
    *
     * <b>function:</b>  score---获取list方法
     * @project xcjtest
     * @package com.xcj.admin.dao.impl.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:13 CST 2015
     * @return List<T>
     * @author name_**
    */
  public <T extends Score> List<T> getSortList() throws DataAccessException  {
		String hql = "from Score";
		return super.getList(hql); 
  }

   /**
     * <b>function:</b>  score---根据id删除数据方法
     * @project xcjtest
     * @package com.xcj.admin.dao.impl.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:13 CST 2015
     * @return void
     * @author name_**
    */
  public void removeById(Integer id) throws DataAccessException { 
		super.delete("delete from Score t where t.id="+id);
  }

  /**
     * <b>function:</b> 
     * @project xcjtest
     * @package com.xcj.admin.dao.impl.users  
     * @fileName com.xcj.*** 
     * @createDate Jan 20, 2015 9:25:42 AM
     * @return List<Score>
     * @author dapeng_wu
   */
  public List<Score> getScoreListAndUsersId(Integer userid) throws Exception {
		String sql = "select * from score where user_id = ?";
		return super.findByJDBC(sql, new Object[]{userid}, Score.class); 
  }
}
