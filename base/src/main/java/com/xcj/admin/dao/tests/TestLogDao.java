package com.xcj.admin.dao.tests;
import java.util.List;
import com.xcj.common.page.Page;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.entity.tests.TestLog;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  test_log测试日志表
  * @package com.xcj.admin.dao.tests.
  * @fileName com.xcj.admin.*
  * @createDate Mon Mar 16 10:21:45 CST 2015
  * @author name su_jian
 */
public interface TestLogDao extends BaseDao{

   /**
    *
     * <b>function:</b>  test_log测试日志表---获取list方法
     * @project base
     * @package com.xcj.admin.dao.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:45 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public <T extends TestLog> List<T> getAllList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  test_log测试日志表---获取分页list方法
     * @project base
     * @package com.xcj.admin.dao.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:45 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public Page<TestLog>  getByTestLogPage(Page<TestLog> ps,TestLog testLog)	throws DataAccessException;

   /**
    *
     * <b>function:</b>  test_log测试日志表---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:45 CST 2015
     * @return void
  * @author name su_jian
    */
  public void removeById(Integer id) throws DataAccessException;

}
