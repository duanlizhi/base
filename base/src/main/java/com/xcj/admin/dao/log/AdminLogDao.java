package com.xcj.admin.dao.log;
import java.util.List;
import org.springframework.dao.DataAccessException;
import java.sql.SQLException;
import com.xcj.admin.entity.log.AdminLog;
import com.xcj.admin.base.BaseDao;

public interface AdminLogDao extends BaseDao{

  public <T extends AdminLog> List<T> getSortList()	throws DataAccessException;
  public void removeById(Integer id) throws DataAccessException;
}
