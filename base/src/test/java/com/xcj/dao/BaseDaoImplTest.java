package com.xcj.dao;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.xcj.admin.entity.course.CourseWare;

/**
 * @Date 2014年8月28日 下午10:56:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/app-context.xml",
		"classpath:/conf/app-datasource.xml" })
public class BaseDaoImplTest {
	@Resource(name = "courseWareDaoImpl")
	private com.xcj.admin.dao.course.CourseWareDao courseWareDao;

	@Test
	public void testIsOK() {
		try {
			List<CourseWare> list = courseWareDao.getAllList();
			System.out.println(JSON.toJSONString(list));
		} catch (Exception e) {
			fail("test 查询数据库抛出异常了");
		}

	}


}