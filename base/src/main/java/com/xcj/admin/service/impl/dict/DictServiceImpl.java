package com.xcj.admin.service.impl.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.dict.DictDao;
import com.xcj.admin.entity.dict.Dict;
import com.xcj.admin.service.dict.DictService;
import com.xcj.common.util.Constants;

@Service("dictServiceImpl")
public class DictServiceImpl extends BaseServiceImpl implements DictService {

	@Resource(name = "dictDaoImpl")
	private DictDao dictDao;

	@SuppressWarnings("unchecked")
	public List<Dict> getSortList() throws Exception {
		return dictDao.getSortList();
	}

	public void removeById(Integer id) throws Exception {
		dictDao.removeById(id);
	}
	
	
	/**
	 * <b>function:</b> 获取字典的键值对到缓存中
	 * 格式是key 为数据字典id  值为value 
	 * @project base_frame
	 * @package com.xcj.admin.service.dict
	 * @fileName com.xcj.***
	 * @createDate May 8, 2014 12:11:20 PM
	 * @return map
	 * @author su_jian
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getKeyValueMap() throws Exception {
		HashMap<String, String> map =new HashMap<String, String>();
		List<Dict> list=dictDao.getKeyValueList();
		for (int i = 0; i < list.size(); i++) {
			Dict dict=list.get(i);
			map.put(Constants.MEM_DICT_PREFIX+dict.getId(), dict.getDictName());
		}
		return map;
	}

	/**
	 * <b>function:</b> 取得数据字典放到缓存里<br>
	 * 格式是key 为数据字典类型 value 数据字典List
	 * @project base_frame
	 * @package com.xcj.admin.service.dict
	 * @fileName com.xcj.***
	 * @createDate May 8, 2014 12:11:20 PM
	 * @return List<Dict>
	 * @author su_jian
	 */
	@SuppressWarnings("unchecked")
	public HashMap getDictMap() {
		List<Dict> list = dictDao.getSortList();
		Set<String> setType = new HashSet<String>();
		HashMap map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Dict dict = list.get(i);
			setType.add(dict.getTypeCode());
		}
		for (String t : setType) {
			Dict dict = null;
			List<Dict> listNew = new ArrayList<Dict>();
			for (int j = 0; j < list.size(); j++) {
				dict = list.get(j);
				if (t.equals(dict.getTypeCode())) {
					listNew.add(dict);
				}
			}
			map.put(t, listNew);
		}
		return map;
	}
}
