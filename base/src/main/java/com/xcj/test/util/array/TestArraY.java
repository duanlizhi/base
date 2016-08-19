/**
 * 
 */
package com.xcj.test.util.array;

import java.util.ArrayList;
import java.util.List;

import com.xcj.admin.entity.common.KeyValueEntity;

/** 
 * <b>function:</b> 
 * @project base_frame 
 * @package com.xcj.test.test.array  
 * @fileName com.xcj.*
 * @createDate Jun 18, 2014 2:18:22 PM 
 * @author su_jian 
 */
public class TestArraY {
	public static void main(String[] args) {
		List<KeyValueEntity> listdj=new ArrayList<KeyValueEntity>();
		List<KeyValueEntity> listjg=new ArrayList<KeyValueEntity>();
		List<KeyValueEntity> zsk=new ArrayList<KeyValueEntity>();
		for (int i = 0; i < 8; i++) {
			KeyValueEntity entity =new KeyValueEntity();
			entity.setId(i);
			entity.setName("等级"+i);
			listdj.add(entity);
		}
		for (int i = 0; i < 5; i++) {
			KeyValueEntity entity =new KeyValueEntity();
			entity.setId(i);
			entity.setName("结构"+i);
			listjg.add(entity);
		}
		
		for (int i = 0; i < 30; i++) {
			KeyValueEntity entity =new KeyValueEntity();
			entity.setId(i);
			entity.setName("知识块"+i);
			zsk.add(entity);
		}
		String a[][] = new String[listdj.size()][listjg.size()];//
		 for (int i = 0; i < listdj.size(); i++) {
			for (int j = 0; j < listjg.size(); j++) {
				a[listdj.get(i).getId()][listjg.get(j).getId()]="jg"+i+j;
				System.out.println(a[listdj.get(i).getId()][listjg.get(j).getId()]);
			}
		}
		
		 for (int i = 0; i < zsk.size(); i++) {
			 KeyValueEntity entity=zsk.get(i);
			 a[1][3]=entity.getName();
			 
		}
		 
		 System.out.println(a);
		 
		
	}
	

}
