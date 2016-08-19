package com.xcj.admin.service.test;

import java.util.List;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.test.TestUserInfo;

public interface TestService extends BaseService{
	List<TestUserInfo> getSortList() throws Exception;
	void removeById(Integer id)throws Exception;
	void editByEntity(TestUserInfo oldTest) throws Exception;
	String getByName(String username)throws Exception;
}
