package com.xcj.admin.service.impl.test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.test.TestDao;
import com.xcj.admin.entity.test.TestUserInfo;
import com.xcj.admin.service.test.TestService;

@Service("testServiceImpl")
public class TestServiceImpl extends BaseServiceImpl implements TestService {

	@Resource(name = "testDaoImpl")
	private TestDao testDao;
	
	public void save(TestUserInfo test) throws Exception {
		 super.save(test);
	}

	/** 
     * <b>function:</b> 根据实例列表
     * @createDate 2014-01-01 
     * @author su_jian 
     * @return List 
     */ 
	public List<TestUserInfo> getSortList() throws Exception {
		return testDao.getSortList();
	}
	
	 
	
	/** 
     * <b>function:</b> 根据ID删除实例
     * @createDate 2014-01-01 
     * @author su_jian 
     * @return List 
     */ 
	public void removeById(Integer id) throws Exception {
		 testDao.removeById(id);
		 //baseDao.delete(baseDao.getById(TestUserInfo.class, id));
	}
	/** 
     * <b>function:</b> 更新对象
     * @createDate 2014-01-01 
     * @author su_jian 
     * @return List 
     */ 
	public void editByEntity(TestUserInfo oldTest) throws Exception {
		super.update(oldTest);
	}

	/** 
     * <b>function:</b> 
     * @createDate 2014-01-01 
     * @author su_jian 
     * @return List 
     */ 
	public String getByName(String username) throws Exception{
			return testDao.getTestByName(username);
	}

}
