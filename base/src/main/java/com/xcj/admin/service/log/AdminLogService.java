package com.xcj.admin.service.log;
import java.util.List;
import com.xcj.admin.entity.log.AdminLog;
import com.xcj.admin.base.BaseService;

public interface AdminLogService extends BaseService{

   List<AdminLog> getSortList() throws Exception;   void removeById(Integer id)throws Exception;
}
