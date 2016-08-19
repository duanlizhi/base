package com.xcj.admin.dao.knowledgePoint;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;

/**
 * 
 * <b>function:</b> knowledge_point知识点树
 * 
 * @package com.xcj.admin.dao.knowledgePoint.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 09 18:29:07 CST 2015
 * @author name yang.yan
 */
public interface KnowledgePointDao extends BaseDao {
	/**
	 * 
	 * <b>function:</b> knowledge_point知识点树---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.knowledgePoint.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 09 18:29:07 CST 2015
	 * @return void
	 * @author name yang.yan
	 */
	public void removeById(Integer id) throws DataAccessException;

	/**
	 * 获取第一个
	 * 
	 * @return
	 */

	public KnowledgePoint getFirst() throws DataAccessException;

	/**
	 * 获取一级菜单的集合
	 * 
	 * @return
	 */

	public List<KnowledgePoint> findFirstGrade() throws DataAccessException;

	/**
	 * 获取孩子节点
	 * 
	 * @param id
	 * @return
	 */

	public List<KnowledgePoint> getKnowledgePoints(Integer id)
			throws DataAccessException;

	/**
	 * 获取根节点
	 */

	public KnowledgePoint findRoot() throws DataAccessException;
	
	/**
	   * <b>function:</b> 根据code获取知识点集合
	   * @project base
	   * @package com.xcj.admin.dao.knowledgePoint  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-1 上午10:06:34
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public  List<KnowledgePoint> getListByCode(String code)throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 根据id获取父类
	   * @project base
	   * @package com.xcj.admin.dao.knowledgePoint  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午02:25:44
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public  List<KnowledgePoint> getParentListById(String id)throws DataAccessException;
}
