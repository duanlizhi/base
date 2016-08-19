package com.xcj.admin.service.tests;
import java.util.List;
import com.xcj.common.page.Page;
import com.xcj.admin.entity.tests.TestLog;
import com.xcj.admin.base.BaseService;

  /**
  *
  * <b>function:</b>  test_log测试日志表
  * @package com.xcj.admin.service.tests.
  * @fileName com.xcj.admin.*
  * @createDate Mon Mar 16 10:21:44 CST 2015
  * @author name su_jian
 */
public interface TestLogService extends BaseService{

   /**
    *
     * <b>function:</b>  test_log测试日志表---获取分页List方法
     * @project base
     * @package com.xcj.admin.service.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:44 CST 2015
     * @return List<TestLog>
  * @author name su_jian
    */
 public  Page<TestLog>  getByTestLogPage(Page<TestLog> ps,TestLog testLog) throws Exception;

   /**
    *
     * <b>function:</b>  test_log测试日志表---获取所有数据方法
     * @project base
     * @package com.xcj.admin.service.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:44 CST 2015
     * @return List<TestLog>
  * @author name su_jian
    */
   List<TestLog>  getAllList()  throws Exception;

   /**
    *
     * <b>function:</b>  test_log测试日志表---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.service.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:44 CST 2015
     * @return void
  * @author name su_jian
    */
   void removeById(Integer id)throws Exception;
}
