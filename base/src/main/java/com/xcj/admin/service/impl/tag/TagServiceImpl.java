package com.xcj.admin.service.impl.tag;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.tag.TagDao;
import com.xcj.admin.entity.tag.Tag;
import com.xcj.admin.service.tag.TagService;
import com.xcj.common.page.Page;

/**
 * 
 * <b>function:</b> tag标签管理表
 * 
 * @package com.xcj.admin.service.impl.tag.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 11:19:52 CST 2015
 * @author name su_jian
 */
@Service("tagServiceImpl")
public class TagServiceImpl extends BaseServiceImpl implements TagService {

	@Resource(name = "tagDaoImpl")
	private TagDao tagDao;

	/**
	 * 
	 * <b>function:</b> tag标签管理表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tag.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 11:19:52 CST 2015
	 * @return List<Tag>
	 * @author name su_jian
	 */
	public List<Tag> getAllList() throws Exception {
		return tagDao.getAllList();
	}

	/**
	 * 
	 * <b>function:</b> tag标签管理表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tag.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 11:19:52 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		tagDao.removeById(id);

	}

	/**
	 * 根据order排序获取标签集合
	 * @throws Exception 
	 */
	public Page<Tag> getByOrder(Page<Tag> ps, Tag tag,String screening) throws Exception {
		return tagDao.getByOrder(ps, tag,screening);
	}

	/**
	 * 根据id批量删除实体
	 * @throws Exception 
	 */
	public void deleteByIds(String ids) throws Exception {
		tagDao.deleteByIds(ids);
	}
}
