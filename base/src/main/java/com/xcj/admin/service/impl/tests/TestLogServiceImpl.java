package com.xcj.admin.service.impl.tests;
import java.util.List;
import javax.annotation.Resource;
import com.xcj.common.page.Page;
import org.springframework.stereotype.Service;
import com.xcj.admin.service.tests.TestLogService;
import com.xcj.admin.dao.tests.TestLogDao;
import com.xcj.admin.entity.tests.TestLog;
import com.xcj.admin.base.BaseServiceImpl;

/**
 *
  * <b>function:</b>  test_log测试日志表
  * @package com.xcj.admin.service.impl.tests.
  * @fileName com.xcj.admin.*
  * @createDate Mon Mar 16 10:21:44 CST 2015
  * @author name su_jian
 */
@Service("testLogServiceImpl")
public class TestLogServiceImpl extends BaseServiceImpl implements TestLogService{

	@Resource(name ="testLogDaoImpl")
	private TestLogDao testLogDao;

   /**
    *
     * <b>function:</b>  test_log测试日志表---获取list方法
     * @project base
     * @package com.xcj.admin.service.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:44 CST 2015
     * @return List<TestLog>
  * @author name su_jian
    */
  public List<TestLog> getAllList() throws Exception { 
    return testLogDao.getAllList(); 
   }

   /**
    *
     * <b>function:</b>  test_log测试日志表---获取list方法
     * @project base
     * @package com.xcj.admin.service.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:44 CST 2015
     * @return List<TestLog>
  * @author name su_jian
    */
  public Page<TestLog>  getByTestLogPage(Page<TestLog> ps,TestLog testLog)  throws Exception { 
    return testLogDao.getByTestLogPage(ps,testLog); 
   }

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
 public void removeById(Integer id) throws Exception {
   testLogDao.removeById(id);

} 
}
