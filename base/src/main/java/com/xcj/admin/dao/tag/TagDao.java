package com.xcj.admin.dao.tag;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.tag.Tag;
import com.xcj.common.page.Page;

/**
 * 
 * <b>function:</b> tag标签管理表
 * 
 * @package com.xcj.admin.dao.tag.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 11:19:52 CST 2015
 * @author name yang.yan
 */
public interface TagDao extends BaseDao {

	/**
	 * 
	 * <b>function:</b> tag标签管理表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tag.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 11:19:52 CST 2015
	 * @return List<T>
	 * @author name yang.yan
	 */
	public <T extends Tag> List<T> getAllList() throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> tag标签管理表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tag.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 11:19:52 CST 2015
	 * @return void
	 * @author name yang.yan
	 */
	public void removeById(Integer id) throws DataAccessException;

	/**
	 * 根据order排序获取标签集合
	 * 
	 * @param ps
	 * @param tag
	 * @return
	 * @throws Exception 
	 */

	public Page<Tag> getByOrder(Page<Tag> ps, Tag tag, String screening) throws Exception;

	/**
	 * 根据id批量删除实体
	 */

	public void deleteByIds(String ids) throws Exception;

}
