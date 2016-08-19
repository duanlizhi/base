package com.xcj.admin.service.impl.log;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xcj.admin.service.log.AdminLogService;
import com.xcj.admin.dao.log.AdminLogDao;
import com.xcj.admin.entity.log.AdminLog;
import com.xcj.admin.base.BaseServiceImpl;

@Service("adminLogServiceImpl")
public class AdminLogServiceImpl extends BaseServiceImpl implements AdminLogService{

	@Resource(name ="adminLogDaoImpl")
	private AdminLogDao adminLogDao;

  public List<AdminLog> getSortList() throws Exception { 
    return adminLogDao.getSortList(); 
   }

 public void removeById(Integer id) throws Exception {
   adminLogDao.removeById(id);
  //baseDao.delete(baseDao.getById(AdminLog,id));
} 
}
