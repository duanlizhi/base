/**
 * 
 */
package com.xcj.admin.service.impl.admin;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.service.admin.CommonAdminService;

/** 
 * <b>function:</b> 
 * @project base_frame 
 * @package com.xcj.admin.service.impl.admin  
 * @fileName com.xcj.*
 * @createDate May 30, 2014 2:37:01 PM 
 * @author su_jian 
 */
@Service("commonAdminServiceImpl")
public class CommonAdminServiceImpl extends BaseServiceImpl implements CommonAdminService {
	
	/* *//**
	 * <b>function:</b> 企业列表下拉框数据从缓存获取
	 * @project base_frame
	 * @package com.xcj.admin.service.impl.admin
	 * @fileName com.xcj.*
	 * @createDate May 30, 2014 2:37:01 PM
	 * @author su_jian
	 *//*
	@SuppressWarnings("unchecked")
	public String getParJsonFromMem(){
		 MemcachedClient mc;
		 List<KeyValueEntity> list=null;
		 try {
			mc = MemcachedClientFactory.getInstance();
			mc.delete(Constants.MEM_PAR_PREFIX);
			list= (List<KeyValueEntity>) mc.get(Constants.MEM_PAR_PREFIX);
			if(list==null||list.equals("[]")){
				 HashMap map=new HashMap();
				 list=partnerDao.getParMemList();
				 map.put(Constants.MEM_PAR_PREFIX, list);
				 MemcachedUtil.putMemcacheIntance(map);
				 list=(List<KeyValueEntity>) mc.get(Constants.MEM_PAR_PREFIX);
			}
		} catch (IOException e) {
		}
		return JSON.toJSONString(list);
	 }
*/
	 
	 
}
