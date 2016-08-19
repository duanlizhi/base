package com.xcj.admin.dao.impl.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.annotations.Parent;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.test.TestDao;
import com.xcj.admin.entity.test.TestUserInfo;

@Repository("testDaoImpl")
@SuppressWarnings({"hiding"})
public class TestDaoImpl extends BaseDaoImpl implements TestDao {
	
	//jdbcTemplate.update("update test_user_info set username='aaaaaaaaa'");
	@Resource
	private JdbcTemplate jdbcTemplate;  
	
	
	
	 /** 
     * <b>function:</b> Hibernate 添加DEMO
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return 空 
     */  
	public void saveTest(TestUserInfo test) throws DataAccessException {
	super.save(test);
		
	}
	
	 /** 
     * <b>function:</b> Hibernate 删除DEMO
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return 空 
     */    
    public boolean removeTest(TestUserInfo test)  throws DataAccessException {  
        return super.delete(test);  
    }  
      
	
    /** 
     * <b>function:</b> Hibernate 查询DEMO
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return 空 
     */    
	public <T extends TestUserInfo> List<T> getSortList() throws DataAccessException  {  
        String hql = "from testUserInfo";  
        return super.getList(hql);  
    }
	
	
	/** 
     * <b>function:</b> JDBC添加通过baseDao封装 DEMO
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return 空 
     */  
	public void saveTestByJdbc(TestUserInfo test) throws Exception {
		String sql = "insert into test_user_info(username,passoword) values(?,?)";
		super.saveOrUpdateOrDeleteByJDBC(sql,new Object[]{test.getUsername(),test.getPassword()}, TestUserInfo.class);
	}
	
	
	 /** 
     * <b>function:</b> JDBC添加通过原始的方式 DEMO
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return 空 
     */  
	public void saveTestByJdbc2(TestUserInfo test) throws Exception {
		final String username=test.getUsername();
		final String password=test.getPassword();
		jdbcTemplate.update("insert into test_user_info(username,password) values(?,?)",  
                new PreparedStatementSetter() {  
                    public void setValues(PreparedStatement ps) throws SQLException {  
                        ps.setString(1, username);  
                        ps.setString(2, password);  
                    }  
        });  
	}

	
	
	/** 
     * <b>function:</b> JDBC修改通过baseDao封装 DEMO
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return 空 
     */  
	public void editTestByJdbc(TestUserInfo test) throws Exception {
		//此处为了测试，正式需要将此事例通过前台传进来
		test =new TestUserInfo();
		test.setUsername("aaa");
		test.setPassword("bbb");
		String sql = "update test_user_info set username=?,password=? where username='测试密码'";
		super.saveOrUpdateOrDeleteByJDBC(sql,new Object[]{test.getUsername(),test.getPassword()}, TestUserInfo.class);
	}
	
	 /** 
     * <b>function:</b> JDBC修改通过原始的方式 DEMO
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return 空 
     */  
	public void editTestByJdbc2(TestUserInfo test) throws Exception {
		final String username="ccc";
		final String password="ddd";
		jdbcTemplate.update("update test_user_info set username=?,password=? where username='测试密码'",  
                new PreparedStatementSetter() {  
                    public void setValues(PreparedStatement ps) throws SQLException {  
                        ps.setString(1, username);  
                        ps.setString(2, password);  
                    }  
        });  
	}
	
	
	 /** 
     * <b>function:</b> JDBC通过封装查询数据的 DEMO
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return List 
     */  
	public List<TestUserInfo> findTestsByJdbc(TestUserInfo test){
		String username="ccc";
		String password="ddd";
		String sql="select username,password from test_user_info where username=? and password=?";
		return super.findByJDBC(sql,new Object[]{username,password} ,TestUserInfo.class);
	}
	
	
	
	/** 
     * <b>function:</b> JDBC通过封装查询数据的RowMapper DEMO
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return List 
     */ 
	@SuppressWarnings("unchecked")
	public List<TestUserInfo> findAllByRowMapperResultReader(TestUserInfo test)  throws SQLException{  
	    String sql = "SELECT id,username,password FROM test_user_info";  
	    return jdbcTemplate.query(sql,new UserRowMapper());  
	}  
	
	
	
	/** 
     * <b>function:</b> JDBC通过内部类继承RowMapper 查询DEMO
     * @createDate 2014-01-01 
     * @author su_jian 
     * @return List 
     */ 
	@SuppressWarnings("unchecked")
	class UserRowMapper implements RowMapper{  
	    public Object mapRow(ResultSet rs, int index) throws SQLException {  
	    TestUserInfo user = new TestUserInfo();  
	       user.setId(rs.getInt(1)==0?0:rs.getInt(1));
	       user.setUsername(rs.getString(2)==null?"":rs.getString(2));
	       user.setPassword(rs.getString(2)==null?"":rs.getString(2));
	       return user;  
	    }  
	}
	
	
	 
	/** 
     * <b>function:</b> 根据ID删除实例
     * @createDate 2014-01-01 
     * @author su_jian 
     * @return List 
     */ 
	public void removeById(Integer id) throws DataAccessException {
		//super.delete(super.getById(TestUserInfo.class, id));
		super.delete("delete from testUserInfo t where t.id="+id);
	}
	
	/** 
     * <b>function:</b> 检查用户名是否重复
     * @createDate 2014-01-01 
     * @author su_jian 
     * @return List 
     */ 
	public String getTestByName(String username) {
		TestUserInfo test=super.get("from testUserInfo tu where tu.username='"+username+"'");
		if(test==null){
			return "true";
		}else{
			return "false";
		}
	}  
	

}
