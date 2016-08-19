package com.xcj.admin.service.impl.knowledgePoint;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.knowledgePoint.KnowledgePointDao;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.service.knowledgePoint.KnowledgePointService;

/**
 * 
 * <b>function:</b> knowledge_point知识点树
 * 
 * @package com.xcj.admin.service.impl.knowledgePoint.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 09 18:29:07 CST 2015
 * @author name yang.yan
 */
@Service("knowledgePointServiceImpl")
public class KnowledgePointServiceImpl extends BaseServiceImpl implements
		KnowledgePointService {

	@Resource(name = "knowledgePointDaoImpl")
	private KnowledgePointDao knowledgePointDao;

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
	public void removeById(Integer id) throws Exception {
		knowledgePointDao.removeById(id);

	}

	public KnowledgePoint getFirst() throws Exception {
		return knowledgePointDao.getFirst();
	}

	public List<KnowledgePoint> findFirstGrade() throws DataAccessException {
		return knowledgePointDao.findFirstGrade();
	}

	public List<KnowledgePoint> getKnowledgePoints(Integer id) throws Exception {

		return knowledgePointDao.getKnowledgePoints(id);
	}

	public KnowledgePoint savefindRoot() throws Exception {
		return knowledgePointDao.findRoot();
	}
	
	/**
	   * <b>function:</b> 根据code获取知识点集合
	   * @project base
	   * @package com.xcj.admin.service.impl.knowledgePoint  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-1 上午10:08:34
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public  List<KnowledgePoint> getListByCode(String code)throws Exception{
		return knowledgePointDao.getListByCode(code);
	}
}
