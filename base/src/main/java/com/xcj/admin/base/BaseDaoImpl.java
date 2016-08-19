package com.xcj.admin.base;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.xcj.common.page.Page;

/*** 
 * <b>function:</b> 增删改查组件规范接口
 * @project Lgguo 
 * @package com.xcj.admin.base  
 * @fileName BaseDao.java 
 * @createDate 2010-03-30 下午05:28:03 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */
@SuppressWarnings(value={"unused","unchecked"})
@Component(value="baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao,java.io.Serializable {
	private static final long serialVersionUID = -2299477704753361070L;
	@Resource
	private JdbcTemplate jdbcTemplate; 
	/**
	 * <b>function:</b>发生RuntimeException 事务回滚，
	 * 故此类抛出RuntimeException 
	 */
	private static Log log = LogFactory.getLog(BaseDaoImpl.class);
	private SessionFactory sessionFactory;
	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	/** 
	 * <b>function:</b> 增加一个entity对象，返回是否添加成功 
	 * @createDate 2010-03-30 下午05:28:38 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 对象 
	 * @return boolean true/false 
	 * @throws DataAccessException 
	 */
	public <T> boolean save(T entity) throws DataAccessException {
		boolean bo = false;
		try {
			Serializable io = this.getHibernateTemplate().save(entity);
			if (io != null) {
				bo = true;
			}
		} catch (Exception e) {
			bo = false;
			throw new RuntimeException(e);
		}
		return bo;
	}

	/** 
	 * <b>function:</b> 添加一个entity对象，返回添加对象的Integer类型的主键 
	 * @createDate 2010-03-30 下午05:29:39 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 将要添加的对象 
	 * @return Integer 返回主键 
	 * @throws DataAccessException 
	 */
	public <T> Integer saveAndGetIdByInteger(T entity) throws DataAccessException {
		Integer id = null;
		try {
			id = (Integer) this.getHibernateTemplate().save(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return id;
	}

	/** 
	 * <b>function:</b> 添加一个对象并且返回该对象的String类型的主键 
	 * @createDate 2010-03-30 下午05:31:32 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 将要添加的对象 
	 * @return String 返回的主键 
	 * @throws DataAccessException 
	 */
	public <T> String saveAndGetIdByString(T entity) throws DataAccessException {
		String id = null;
		try {
			id = (String) this.getHibernateTemplate().save(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return id;
	}
	
	
	/** 
	 * <b>function:</b> 保存或者更新实体类
	 * @createDate 2010-03-30 下午05:28:38 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 对象 
	 * @throws DataAccessException 
	 */
	public <T> void  saveOrUpdate(T entity) throws DataAccessException {
		try {
			 this.getHibernateTemplate().saveOrUpdate(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/** 
	 * <b>function:</b> merge实体类
	 * @createDate 2010-03-30 下午05:28:38 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 对象 
	 * @throws DataAccessException 
	 */
	public <T> void  merge(T entity) throws DataAccessException {
		try {
			 this.getHibernateTemplate().merge(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

	/** 
	 * <b>function:</b> 修改entity对象，返回是否修改成功 
	 * @createDate 2010-03-30 下午05:35:47 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 将要修改的对象 
	 * @return boolean true/false 是否修改成功 
	 * @throws DataAccessException 
	 */
	public <T> boolean update(T entity) throws DataAccessException {
		boolean bo = false;
		try {
			this.getHibernateTemplate().update(entity);
			bo = true;
		} catch (Exception e) {
			bo = false;
			throw new RuntimeException(e);
		}
		return bo;
	}

	/** 
	 * <b>function:</b> 传入hql语句执行修改，返回是否修改成功 
	 * @createDate 2010-03-30 下午05:36:31 
	 * @author su_jian 
	 * @param hql 查询的hql语句 
	 * @return boolean true/false 返回是否修改成功 
	 * @throws DataAccessException 
	 */
	public boolean update(String hql) throws DataAccessException {
		boolean bo = false;
		try {
			int count = this.getHibernateTemplate().bulkUpdate(hql);
			bo = count > 0 ? true : false;
		} catch (Exception e) {
			bo = false;
			throw new RuntimeException(e);
		}
		return bo;
	}

	/** 
	 * <b>function:</b> 执行修改的hql语句，返回修改的行数 
	 * @createDate 2010-03-30 下午05:38:58 
	 * @author su_jian 
	 * @param hql 修改语句 
	 * @return int 返回修改的行数 
	 * @throws DataAccessException 
	 */
	public int updateByHql(String hql) throws DataAccessException {
		int count = 0;
		try {
			count = this.getHibernateTemplate().bulkUpdate(hql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return count;
	}

	/** 
	 * <b>function:</b> 传入hql语句执行 
	 * @createDate 2010-03-30 下午04:42:26 
	 * @author su_jian 
	 * @param hql String hql语句 
	 * @return int 影响行数 
	 * @throws DataAccessException 
	 */
	public int executeByHql(String hql) throws DataAccessException {
		try {
			return this.getHibernateTemplate().bulkUpdate(hql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/** 
	 * <b>function:</b> 执行原生态的sql语句，添加、删除、修改语句 
	 * @createDate 2010-03-30 下午05:33:42 
	 * @author su_jian 
	 * @param sql 将要执行的sql语句 
	 * @return int 
	 * @throws DataAccessException 
	 */
	public int executeBySql(String sql) throws DataAccessException {
		try {
			return this.getSession().createSQLQuery(sql).executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

	/** 
	 * <b>function:</b> 传入hql语句执行查询，返回list集合 
	 * @createDate 2010-03-30 上午10:00:34 
	 * @author su_jian 
	 * @param hql 查询的hql语句 
	 * @return List集合 
	 * @throws DataAccessException 
	 */
	public <T> List<T> findByHql(String hql) throws DataAccessException {
		List list = null;
		try {
			list = (List<T>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	

	/** 
	 * <b>function:</b> 传入sql语句执行查询，返回list集合 
	 * @createDate 2010-03-30 上午10:00:34 
	 * @author su_jian 
	 * @param sql 查询的sql语句 
	 * @return List集合 
	 * @throws DataAccessException 
	 */
	public <T> List<T> findBySql(String sql) throws DataAccessException {
		List list = null;
		try {
			list = (List<T>) this.getSession().createSQLQuery(sql).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	

	/** 
	 * <b>function:</b> 传入一个entity对象Class和Serializable类型主键，返回该对象 
	 * @createDate 2010-03-30 下午05:48:36 
	 * @author su_jian 
	 * @param <T> 返回、传入对象类型 
	 * @param c 对象Class 
	 * @param id 主键 
	 * @return T 返回该类型的对象 
	 * @throws DataAccessException 
	 */
	public <T> T get(Class<T> c, Serializable id) throws DataAccessException {
		T ety = null;
		try {
			ety = (T) this.getHibernateTemplate().get(c, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ety;
	}

	/** 
	 * <b>function:</b> 传入hql语句，查询对象 
	 * @createDate 2010-03-30 下午05:49:31 
	 * @author su_jian 
	 * @param <T> 返回对象类型 
	 * @param hql 查询的hql语句 
	 * @return 对象T 
	 * @throws DataAccessException 
	 */
	public <T> T get(String hql) throws DataAccessException {
		T ety = null;
		try {
			ety = (T) this.getSession().createQuery(hql).setMaxResults(1)
					.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ety;
	}

	/** 
	 * <b>function:</b> 通过hql语句查询List集合 
	 * @createDate 2010-03-30 下午05:51:05 
	 * @author su_jian 
	 * @param hql 查询hql语句 
	 * @return List<?> 
	 * @throws DataAccessException 
	 */
	public <T> List<T> getList(String hql) throws DataAccessException {
		List<T> list = null;
		try {
			list = (List<T>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/** 
	 * <b>function:</b> 传入hql查询语句和object数组类型的参数，返回查询list集合 
	 * @createDate 2010-03-30 下午05:52:36 
	 * @author su_jian 
	 * @param hql 查询的hql语句 
	 * @param obj 查询参数 
	 * @return 返回list集合 
	 * @throws DataAccessException 
	 */
	public <T> List<T> getList(String hql, Object[] obj) throws DataAccessException {
		List<T> list = null;
		try {
			list = (List<T>) this.getHibernateTemplate().find(hql, obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	

	/** 
	 * <b>function:</b> 传入一个entity对象Class和Integer类型主键，返回该对象 
	 * @createDate 2010-03-30 下午05:47:20 
	 * @author su_jian 
	 * @param <T> 返回、传入对象类型 
	 * @param c 对象Class 
	 * @param id 主键 
	 * @return T 返回该类型的对象 
	 * @throws DataAccessException 
	 */
	public <T> T getById(Class<T> c, Integer id) throws DataAccessException {
		T ety = null;
		try {
			ety = (T) this.getHibernateTemplate().get(c, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ety;
	}

	/** 
	 * <b>function:</b> 传入一个entity对象Class和String型主键，返回该对象 
	 * @createDate 2010-03-30 下午05:44:53 
	 * @author su_jian 
	 * @param <T> 返回、传入对象类型 
	 * @param c 对象Class 
	 * @param id 主键 
	 * @return T 返回传入类型对象 
	 * @throws DataAccessException 
	 */
	public <T> T getById(Class<T> c, String id) throws DataAccessException {
		T ety = null;
		try {
			ety = (T) this.getHibernateTemplate().get(c, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ety;
	}

	

	/** 
	 * <b>function:</b> 传入一个将要删除的entity对象，返回删除是否成功 
	 * @createDate 2010-03-30 下午05:42:02 
	 * @author su_jian 
	 * @param <T> 传入对象类型 
	 * @param entity 将要传入的对象 
	 * @return boolean true/false 
	 * @throws DataAccessException 
	 */
	public <T> boolean delete(T entity) throws DataAccessException {
		boolean bo = false;
		try {
			this.getHibernateTemplate().delete(entity);
			bo = true;
		} catch (Exception e) {
			bo = false;
			throw new RuntimeException(e);
		}
		return bo;
	}

	/** 
	 * <b>function:</b> 传入删除的hql语句，删除记录 
	 * @createDate 2010-03-30 上午09:53:49 
	 * @author su_jian 
	 * @param hql 将要被执行删除的hql语句 
	 * @return 是否删除成功 
	 * @throws DataAccessException 
	 */
	public boolean delete(String hql) throws DataAccessException {
		try {
			return this.executeByHql(hql) > 0 ? true : false;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** 
	 * <b>function:</b> 动态查询 
	 * @createDate 2010-03-30 上午10:53:37 
	 * @author su_jian 
	 * @param <T> 查询类的类型 
	 * @param c 动态查询组合对象 
	 * @return list集合 
	 * @throws DataAccessException 
	 */
	public <T> List<T> getList(Class<T> c) throws DataAccessException {
		List<T> list = null;
		try {
			 list =(List<T>)this.getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(c));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/** 
	 * <b>function:</b> 传入查询语句和查询总条数（总记录）的hql语句、当前页数、每页显示调试数；返回查询后的list集合； 
	 * list集合保存总记录调试和记录结果 
	 * @createDate 2010-03-30 下午05:54:01 
	 * @author su_jian 
	 * @param queryHql 查询记录hql语句 
	 * @param queryCountHql 查询记录条数hql语句 
	 * @param firstResult 当前查询页 
	 * @param maxResult 每页显示多少条 
	 * @return List返回集合 集合0保存查询结果、集合1保存总记录条数 
	 * @throws DataAccessException 
	 */
	public List<?> getByPage(String queryHql, String queryCountHql,
			int firstResult, int maxResult) throws DataAccessException {
		List<Object> list = new ArrayList<Object>();
		try {
			Session session = this.getSession();
			list.add(session.createQuery(queryHql).setFirstResult(firstResult)
					.setMaxResults(maxResult).list());
			list.add(session.createQuery(queryCountHql).setMaxResults(1)
					.uniqueResult());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/** 
	 * <b>function:</b> 传入查询语句和查询总条数（总记录）的hql语句、page分页对象；返回查询后的list集合； 
	 * @createDate 2010-03-30 上午11:16:59 
	 * @author su_jian 
	 * @param queryHql list集合结果查询 
	 * @param queryCountHql 总记录调试查询 
	 * @param page 分页对象 
	 * @throws DataAccessException 
	 */
	public <T> Page<T> getByPage(Page<T> page,T entity)
			throws DataAccessException {
		try {
			Session session = this.getSession();
			page.setResult(session.createQuery("from "+entity.getClass().getName()+" order by modifyDate desc").setFirstResult(
					(page.getCurrentPage()-1)*page.getPageSize()).setMaxResults(page.getPageSize())
					.list());
			page.setTotalsCount(Integer.parseInt(session.createQuery(
					"select count(id) as cnt from "+entity.getClass().getName()).setMaxResults(1).uniqueResult().toString()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	
	
	/** 
	 * <b>function:</b> 分页查询，传入查询count的hql语句和DetachedCriteria动态查询条件进行查询分页 
	 * @createDate 2010-03-30 上午11:04:39 
	 * @author su_jian 
	 * @param queryCountHql hql查询count语句总条数 
	 * @param cResult  DetachedCriteria 动态查询条件 
	 * @param firstResult 起始 
	 * @param maxResult 最大页数 
	 * @return List<?> 查询集合 
	 * @throws DataAccessException 
	 */
	public List<?> getByPage(String queryCountHql, DetachedCriteria cResult,
			int firstResult, int maxResult) throws DataAccessException {
		List<Object> list = new ArrayList<Object>();
		try {
			Session session = this.getSession();
			list.add(this.getHibernateTemplate().findByCriteria(cResult,
					firstResult, maxResult));
			list.add(session.createQuery(queryCountHql).setMaxResults(1)
					.uniqueResult());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/** 
	 * <b>function:</b> 分页查询，传入查询的count的hql语句和动态查询DetachedCriteria类及page分页entity 
	 * @createDate 2010-03-30 上午11:14:30 
	 * @author su_jian 
	 * @param queryCountHql 查询count语句 
	 * @param cResult DetachedCriteria 动态查询组合类 
	 * @param page Page分页实体类 
	 * @throws DataAccessException 
	 */
	public <T> void getByPage(String queryCountHql, DetachedCriteria cResult,
			Page<T> page) throws DataAccessException {
		try {
			Session session = this.getSession();
			page.setResult(this.getHibernateTemplate().findByCriteria(cResult,
					page.getCurrentPage(), page.getPageSize()));
			page.setTotalsCount(Integer.parseInt(session.createQuery(
					queryCountHql).setMaxResults(1).uniqueResult().toString()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** 
	 * <b>function:</b> 传入查询条件DetachedCriteria进行查询 
	 * @createDate 2010-03-30 上午11:55:28 
	 * @author su_jian 
	 * @param <T> 类型 
	 * @param dc DetachedCriteria动态条件查询 
	 * @return List 
	 * @throws DataAccessException 
	 */
	public <T> List<T> find(DetachedCriteria dc) throws DataAccessException {
		List<T> list = new ArrayList<T>();
		try {
			list = (List<T>) this.getHibernateTemplate().findByCriteria(dc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/** 
	 * <b>function:</b> 暴露基类session供用户使用 
	 * @createDate 2010-03-30 上午11:59:54 
	 * @author su_jian 
	 * @return Session 
	 */
	public Session session() {
		return this.getSession();
	}

	/** 
	 * <b>function:</b> 暴露HibernateTemplate模板，当基类（增删改查组件）方法不够用可以用模板进行操作 
	 * @createDate 2010-03-30 上午11:58:51 
	 * @author su_jian 
	 * @return HibernateTemplate 
	 */
	public HibernateTemplate getTemplate() {
		return this.getHibernateTemplate();
	}
	
	
	
	/** 
	 * <b>function:</b> 通过hql查找某个唯一的实体对象 
	 * @createDate 2010-04-01 上午11:58:51 
	 * @author su_jian 
	 * @return Entity 
	 */
	public Object findUniqueEntity(final String hql, final Object[] values) throws DataAccessException{
		return (Object) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.uniqueResult();
			}
		});
	}
	
	
	
	/** 
     * <b>function:</b> Spring Jdbctemplate  模版查询方法 
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return List 
     */  
	 public <T>List<T> findByJDBC(String sql, Object[] parameters, Class<T> cl) throws DataAccessException {
		  List<T> resultList = null;
		       try {
		           if (parameters != null && parameters.length > 0)
		               resultList = jdbcTemplate.query(sql, parameters,
		                       new BeanPropertyRowMapper<T>(cl));
		           else
		               // BeanPropertyRowMapper是自动映射实体类的
		               resultList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(cl));
		       } catch (Exception e) {
		    	   log.error(BaseDaoImpl.class.getName()+" findByJDBC() exception: " + e.getMessage());
		       }
		return resultList;
	 }
	
	 /** 
     * <b>function:</b> Spring Jdbctemplate  模版增加、修改、删除方法 
     * @createDate 2014-03-31 
     * @author su_jian 
     * @return List 
     */  
	 public <T> int saveOrUpdateOrDeleteByJDBC(String sql, final Object[] parameters,
			Class<T> cl) throws DataAccessException{
		int num = 0;
		try {
			if (parameters == null || parameters.length == 0)
				num = jdbcTemplate.update(sql);
			else
				num = jdbcTemplate.update(sql, new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
						for (int i = 0; i < parameters.length; i++)
							ps.setObject(i + 1, parameters[i]);
					}
				});
		} catch (Exception e) {
			log.error(BaseDaoImpl.class.getName()+" saveOrUpdateOrDeleteByJDBC() exception: " + e.getMessage());
			num = -1;
		}
		return num;
	}
		/**
		 * 按属性查找对象列表.
		 */
		public <T> List<T> findByProperty(Class<T> entityClass,
				String propertyName, Object value) {
			Assert.hasText(propertyName);
			return (List<T>) createCriteria(entityClass,
					Restrictions.eq(propertyName, value)).list();
		}
		/**
		 * 创建Criteria对象带属性比较
		 * 
		 * @param <T>
		 * @param entityClass
		 * @param criterions
		 * @return
		 */
		public <T> Criteria createCriteria(Class<T> entityClass,
				Criterion... criterions) {
			Criteria criteria = getSession().createCriteria(entityClass);
			for (Criterion c : criterions) {
				criteria.add(c);
			}
			return criteria;
		}
		/**
		 * 按一个属性查找对象，并且对象不能和根据属性2重复：例 --->编辑页面判断姓名是否重复，首先排除现在的姓名
		 */
		public <T> List<T> findByReProperty(Class<T> entityClass,
				String propertyName, Object value, Object value2) {
			Assert.hasText(propertyName);
			return (List<T>) createCriteria(entityClass,
					Restrictions.eq(propertyName, value)).add(Restrictions.ne(propertyName, value2)).list();
		}
		/**
		 * 根据主键删除指定的实体
		 * 
		 * @param <T>
		 * @param pojo
		 */
		public <T> void deleteEntityById(Class<T> entityName, Serializable id) {
			delete("delete from "+entityName.getName()+" t where t.id=" + id);
			getSession().flush();
		}
		/**
		 * 根据主键批量删除实体
		 * 
		 * @param <T>
		 * @param pojo
		 */
		public <T> void deleteEntityByIds(Class<T> entityName, String ids) {
			delete("delete from "+entityName.getName()+" t where t.id in(" + ids+")");
			getSession().flush();
		}
		/**
		 * 获取所有实体
		 * @param <T>
		 * @param entityClass
		 * @return
		 */
		public <T> List<T> loadAll(final Class<T> entityClass) {
			Criteria criteria = createCriteria(entityClass);
			return criteria.list();
		}
}
