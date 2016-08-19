package com.xcj.admin.dao.impl.log;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.entity.log.AdminLog;
import com.xcj.admin.dao.log.AdminLogDao;

@Component("adminLogDaoImpl")
@SuppressWarnings({"hiding"})
public class AdminLogDaoImpl extends BaseDaoImpl implements AdminLogDao {

	 @Resource
	 private JdbcTemplate jdbcTemplate;
  public <T extends AdminLog> List<T> getSortList() throws DataAccessException  {
		String hql = "from AdminLog";
		return super.getList(hql); 
   }
 public void removeById(Integer id) throws DataAccessException { 
 super.delete("delete from AdminLog t where t.id="+id);
	 }

}
