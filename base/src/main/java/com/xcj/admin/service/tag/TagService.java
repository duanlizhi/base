package com.xcj.admin.service.tag;

import java.util.List;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.tag.Tag;
import com.xcj.common.page.Page;

/**
 * 
 * <b>function:</b> tag标签管理表
 * 
 * @package com.xcj.admin.service.tag.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 11:19:52 CST 2015
 * @author name yang.yan
 */
public interface TagService extends BaseService {

	/**
	 * 
	 * <b>function:</b> tag标签管理表---获取所有数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tag.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 11:19:52 CST 2015
	 * @return List<Tag>
	 * @author name yang.yan
	 */
	List<Tag> getAllList() throws Exception;

	/**
	 * 
	 * <b>function:</b> tag标签管理表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tag.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 11:19:52 CST 2015
	 * @return void
	 * @author name yang.yan
	 */
	void removeById(Integer id) throws Exception;

	/**
	 * 1.根据order排序获取标签集合
	 * 
	 * @param ps
	 * @param tag
	 * @return
	 */

	public Page<Tag> getByOrder(Page<Tag> ps, Tag tag,String screening)throws Exception;

	/**
	 * 批量删除id
	 */
	void deleteByIds(String ids)throws Exception;
}
