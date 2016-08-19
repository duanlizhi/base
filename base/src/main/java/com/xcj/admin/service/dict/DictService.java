package com.xcj.admin.service.dict;

import java.util.HashMap;
import java.util.List;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.dict.Dict;

public interface DictService extends BaseService {

	List<Dict> getSortList() throws Exception;

	void removeById(Integer id) throws Exception;

	/**
	 * <b>function:</b> 取得数据字典放到缓存里<br>
	 * 格式是key 为数据字典类型 value 数据字典List
	 * 
	 * @project base_frame
	 * @package com.xcj.admin.service.dict
	 * @fileName com.xcj.***
	 * @createDate May 8, 2014 12:11:20 PM
	 * @return List<Dict>
	 * @author su_jian
	 */
	HashMap getDictMap();
	/**
	 * <b>function:</b> 获取字典的键值对到缓存中
	 * 格式是key 为数据字典id  值为value 
	 * @project base_frame
	 * @package com.xcj.admin.service.dict
	 * @fileName com.xcj.***
	 * @createDate May 8, 2014 12:11:20 PM
	 * @return List<Dict>
	 * @author su_jian
	 */
	public HashMap<String, String> getKeyValueMap() throws Exception;
}
