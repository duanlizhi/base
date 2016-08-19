package com.xcj.admin.service.impl.users;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xcj.admin.service.users.ScoreService;
import com.xcj.admin.dao.users.ScoreDao;
import com.xcj.admin.entity.users.Score;
import com.xcj.admin.base.BaseServiceImpl;

/**
 *
  * <b>function:</b>  score
  * @package com.xcj.admin.service.impl.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:12 CST 2015
  * @author name_**
 */
@Service("scoreServiceImpl")
public class ScoreServiceImpl extends BaseServiceImpl implements ScoreService{

	@Resource(name ="scoreDaoImpl")
	private ScoreDao scoreDao;

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
  public List<Score> getSortList() throws Exception { 
    return scoreDao.getSortList(); 
   }

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
 public void removeById(Integer id) throws Exception {
   scoreDao.removeById(id);
  //baseDao.delete(baseDao.getById(Score,id));
} 
}
