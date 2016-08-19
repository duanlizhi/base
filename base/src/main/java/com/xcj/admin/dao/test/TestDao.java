package com.xcj.admin.dao.test;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.test.TestUserInfo;

public interface TestDao extends BaseDao{

	public void saveTest(TestUserInfo test) throws DataAccessException;

	public boolean removeTest(TestUserInfo test) throws DataAccessException;


	public <T extends TestUserInfo> List<T> getSortList()	throws DataAccessException;

	public void saveTestByJdbc(TestUserInfo test) throws Exception;

	public void saveTestByJdbc2(TestUserInfo test) throws Exception;

	public void editTestByJdbc2(TestUserInfo test) throws Exception;

	public void editTestByJdbc(TestUserInfo test) throws Exception;
	List<TestUserInfo> findAllByRowMapperResultReader (TestUserInfo test)  throws SQLException;
	public void removeById(Integer id) throws DataAccessException;

	public String getTestByName(String username);
}
