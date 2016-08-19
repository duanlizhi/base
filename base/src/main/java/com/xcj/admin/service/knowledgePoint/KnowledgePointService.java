package com.xcj.admin.service.knowledgePoint;

import java.util.List;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;

/**
 * 
 * <b>function:</b> knowledge_point知识点树
 * 
 * @package com.xcj.admin.service.knowledgePoint.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 09 18:29:07 CST 2015
 * @author name yang.yan
 */
public interface KnowledgePointService extends BaseService {

	/**
	 * 
	 * <b>function:</b> knowledge_point知识点树---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.knowledgePoint.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 09 18:29:07 CST 2015
	 * @return void
	 * @author name yang.yan
	 */
	void removeById(Integer id) throws Exception;

	/**
	 * 获取第一条数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public KnowledgePoint getFirst() throws Exception;

	/**
	 * 获取一级菜单的集合
	 * 
	 * @return
	 */

	public List<KnowledgePoint> findFirstGrade() throws Exception;

	/**
	 * 获取孩子节点
	 * 
	 * @param id
	 * @return
	 */

	public List<KnowledgePoint> getKnowledgePoints(Integer id) throws Exception;

	/**
	 * 获取根节点
	 */

	public KnowledgePoint savefindRoot() throws Exception;

	/**
	   * <b>function:</b> 根据code获取知识点的集合
	   * @project base
	   * @package com.xcj.admin.service.knowledgePoint  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-1 上午10:09:37
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getListByCode(String code) throws Exception;
}
