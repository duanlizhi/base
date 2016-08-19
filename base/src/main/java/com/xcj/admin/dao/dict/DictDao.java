package com.xcj.admin.dao.dict;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.dict.Dict;

public interface DictDao extends BaseDao {

	public <T extends Dict> List<T> getSortList() throws DataAccessException;

	public void removeById(Integer id) throws DataAccessException;

	 /**
	   * <b>function:</b> 获取字典ID以及名称以供通过ID取得名称
	   * @project base_frame
	   * @package com.xcj.admin.dao.dict  
	   * @fileName com.xcj.*** 
	   * @createDate May 13, 2014 2:43:40 PM
	   * @return List<Dict>
	   * @author su_jian
	   */
	public List<Dict> getKeyValueList();
}
