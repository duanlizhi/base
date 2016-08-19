package com.xcj.admin.dao.impl.knowledgePoint;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.knowledgePoint.KnowledgePointDao;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;

/**
 * 
 * <b>function:</b> knowledge_point知识点树
 * 
 * @package com.xcj.admin.dao.impl.knowledgePoint.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 09 18:29:08 CST 2015
 * @author name yang.yan
 */
@Component("knowledgePointDaoImpl")
public class KnowledgePointDaoImpl extends BaseDaoImpl implements
		KnowledgePointDao {

	private static final long serialVersionUID = 6077108793899822157L;

	/**
	 * 
	 * <b>function:</b> knowledge_point知识点树---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.knowledgePoint.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 09 18:29:08 CST 2015
	 * @return void
	 * @author name yang.yan
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from KnowledgePoint t where t.id=" + id);
	}

	public KnowledgePoint getFirst()  throws DataAccessException{
		String  sql="select * from knowledge_point where pid is not null  limit 1";
		List<KnowledgePoint> knowledgePoints = super.findByJDBC(sql, new Object[]{}, KnowledgePoint.class);
		if (knowledgePoints.size()>0) {
			return knowledgePoints.get(0);
		} else {
			return new KnowledgePoint();
		}
	}

	@SuppressWarnings("unchecked")
	public List<KnowledgePoint> findFirstGrade() throws DataAccessException {
		Session session = this.getSession();
		String sql="select * from knowledge_point where pid =(select id from knowledge_point where isnull(pid))";
		List<KnowledgePoint> list = session.createSQLQuery(sql).addEntity(KnowledgePoint.class).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public KnowledgePoint findRoot() throws DataAccessException {
//		Session session = this.getSession();
//		String sql="select * from knowledge_point where  isnull(pid)";
//		KnowledgePoint list = (KnowledgePoint) session.createSQLQuery(sql).addEntity(KnowledgePoint.class).list().get(0);
//		return list;
		Session session = this.getSession();
		String sql="select * from knowledge_point where  name = 'root'";
		List<KnowledgePoint> list = session.createSQLQuery(sql).addEntity(KnowledgePoint.class).list();
		if (list != null && list.size()>0) {
			return list.get(0);
		} else {
			KnowledgePoint knowLedgePoint = new KnowledgePoint();
			knowLedgePoint.setId(1);
			knowLedgePoint.setName("root");
			knowLedgePoint.setCode("root");
			knowLedgePoint.setCreateDate(new Date());
			knowLedgePoint.setModifyDate(new Date());
			super.save(knowLedgePoint);
			return knowLedgePoint;
		}
	}

	@SuppressWarnings("unchecked")
	public List<KnowledgePoint> getKnowledgePoints(Integer id) throws DataAccessException {
		Session session = this.getSession();
//		String sql="select * from knowledge_point where pid='"+id+"'";
 		String sql="select * from knowledge_point where find_in_set(id, querychildrenknowledge("+ id+")) ";
		List<KnowledgePoint> list = session.createSQLQuery(sql).addEntity(KnowledgePoint.class).list();
		return list;
	}
	
	/**
	   * <b>function:</b> 根据code获取集合
	   * @project base
	   * @package com.xcj.admin.dao.impl.knowledgePoint  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-1 上午10:05:22
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public  List<KnowledgePoint> getListByCode(String code)throws DataAccessException {
		String sql = "select id,code,name,pid from knowledge_point where code = ?";
		return super.findByJDBC(sql, new Object[] {code},KnowledgePoint.class);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据id获取父类
	   * @project base
	   * @package com.xcj.admin.dao.impl.knowledgePoint  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午02:24:50
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public  List<KnowledgePoint> getParentListById(String id)throws DataAccessException {
		String sql = "select id,code,name,pid from knowledge_point where id = (select pid from knowledge_point where id =?)";
		return super.findByJDBC(sql, new Object[] {id},KnowledgePoint.class);
	}
	
	
	
}
