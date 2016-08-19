/**
 * 
 */
package com.xcj.test.util.json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xcj.admin.entity.test.TestUserInfo;

/**
 * <b>function:</b> 使用阿里巴巴开源的JSON工具包使得Java和Json之间互转
 * 
 * @project base_frame
 * @package com.xcj.test.test
 * @fileName com.xcj.*
 * @createDate May 7, 2014 9:19:43 AM
 * @author su_jian
 */
public class TestJson {
	public static void main(String[] args) {
		List<TestUserInfo> list = new ArrayList<TestUserInfo>();
		for (int i = 1; i < 5; i++) {
			TestUserInfo t = new TestUserInfo();
			t.setId(i);
			t.setUsername("xcjusr" + i);
			t.setPassword("xcjpas" + i);
			list.add(t);
		}
		TestUserInfo t = new TestUserInfo();
		t.setId(2);
		t.setUsername("xcjusr");
		t.setPassword("xcjpas");

		System.out.println(JSON.toJSONString(list));
		// System.out.println(JSON.toJSONString(list, true));

		//String str = "{\"id\":2,\"password\":\"xcjpas\",\"username\":\"xcjusr\"}";
		String str2 = "[{\"id\":1,\"password\":\"xcjpas1\",\"username\":\"xcjusr1\"},{\"id\":2,\"password\":\"xcjpas2\",\"username\":\"xcjusr2\"},{\"id\":3,\"password\":\"xcjpas3\",\"username\":\"xcjusr3\"},{\"id\":4,\"password\":\"xcjpas4\",\"username\":\"xcjusr4\"}]";

		List<TestUserInfo> list2 = JSON.parseArray(str2, TestUserInfo.class);
		// System.out.println(t2.getUsername());
		// System.out.println(t2.getPassword());
		System.out.println(list2.size());
	}

}
