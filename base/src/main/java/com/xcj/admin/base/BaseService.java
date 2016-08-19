package com.xcj.admin.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.xcj.common.page.Page;


public interface BaseService {
	/** 
	 * <b>function:</b> 增加一个entity对象，返回是否添加成功 
	 * @createDate 2010-03-30 下午05:28:38 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 对象 
	 * @return boolean true/false 
	 * @throws DataAccessException 
	 */
	public <T> boolean save(T entity) throws Exception;
	/** 
	 * <b>function:</b> 添加一个entity对象，返回添加对象的Integer类型的主键 
	 * @createDate 2010-03-30 下午05:29:39 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 将要添加的对象 
	 * @return Integer 返回主键 
	 * @throws DataAccessException 
	 */
	public <T> Integer saveAndGetIdByInteger(T entity) throws DataAccessException;
	
	/** 
	 * <b>function:</b> 添加一个对象并且返回该对象的String类型的主键 
	 * @createDate 2010-03-30 下午05:31:32 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 将要添加的对象 
	 * @return String 返回的主键 
	 * @throws DataAccessException 
	 */
	public <T> String saveAndGetIdByString(T entity) throws DataAccessException;
	
	
	/** 
	 * <b>function:</b> 保存或者更新实体类
	 * @createDate 2010-03-30 下午05:28:38 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 对象 
	 * @throws DataAccessException 
	 */
	public <T> void  saveOrUpdate(T entity) throws DataAccessException;
	
	

	/** 
	 * <b>function:</b> merge实体类
	 * @createDate 2010-03-30 下午05:28:38 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 对象 
	 * @throws DataAccessException 
	 */
	public <T> void  merge(T entity) throws DataAccessException;
	
	/** 
	 * <b>function:</b> 修改entity对象，返回是否修改成功 
	 * @createDate 2010-03-30 下午05:35:47 
	 * @author su_jian 
	 * @param <T> 对象类型 
	 * @param entity 将要修改的对象 
	 * @return boolean true/false 是否修改成功 
	 * @throws DataAccessException 
	 */
	public <T> boolean update(T entity) throws DataAccessException;
	
	/** 
	 * <b>function:</b> 传入hql语句执行修改，返回是否修改成功 
	 * @createDate 2010-03-30 下午05:36:31 
	 * @author su_jian 
	 * @param hql 查询的hql语句 
	 * @return boolean true/false 返回是否修改成功 
	 * @throws DataAccessException 
	 */
	public boolean update(String hql) throws DataAccessException;
	
	
	/** 
	 * <b>function:</b> 执行修改的hql语句，返回修改的行数 
	 * @createDate 2010-03-30 下午05:38:58 
	 * @author su_jian 
	 * @param hql 修改语句 
	 * @return int 返回修改的行数 
	 * @throws DataAccessException 
	 */
	public int updateByHql(String hql) throws DataAccessException;
	
	/** 
	 * <b>function:</b> 传入hql语句执行 
	 * @createDate 2010-03-30 下午04:42:26 
	 * @author su_jian 
	 * @param hql String hql语句 
	 * @return int 影响行数 
	 * @throws DataAccessException 
	 */
	public int executeByHql(String hql) throws DataAccessException;
	
	/** 
	 * <b>function:</b> 执行原生态的sql语句，添加、删除、修改语句 
	 * @createDate 2010-03-30 下午05:33:42 
	 * @author su_jian 
	 * @param sql 将要执行的sql语句 
	 * @return int 
	 * @throws DataAccessException 
	 */
	public int executeBySql(String sql) throws DataAccessException;
	
	/** 
	 * <b>function:</b> 传入hql语句执行查询，返回list集合 
	 * @createDate 2010-03-30 上午10:00:34 
	 * @author su_jian 
	 * @param hql 查询的hql语句 
	 * @return List集合 
	 * @throws DataAccessException 
	 */
	public <T> List<T> findByHql(String hql) throws DataAccessException;
	
	/** 
	 * <b>function:</b> 传入sql语句执行查询，返回list集合 
	 * @createDate 2010-03-30 上午10:00:34 
	 * @author su_jian 
	 * @param sql 查询的sql语句 
	 * @return List集合 
	 * @throws DataAccessException 
	 */
	public <T> List<T> findBySql(String sql) throws DataAccessException;
	
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
	public <T> T get(Class<T> c, Serializable id) throws DataAccessException;
	
	
	/** 
	 * <b>function:</b> 传入hql语句，查询对象 
	 * @createDate 2010-03-30 下午05:49:31 
	 * @author su_jian 
	 * @param <T> 返回对象类型 
	 * @param hql 查询的hql语句 
	 * @return 对象T 
	 * @throws DataAccessException 
	 */
	public <T> T get(String hql) throws DataAccessException;
	
	
	/** 
	 * <b>function:</b> 通过hql语句查询List集合 
	 * @createDate 2010-03-30 下午05:51:05 
	 * @author su_jian 
	 * @param hql 查询hql语句 
	 * @return List<?> 
	 * @throws DataAccessException 
	 */
	public <T> List<T> getList(String hql) throws DataAccessException;
	
	
	/** 
	 * <b>function:</b> 传入hql查询语句和object数组类型的参数，返回查询list集合 
	 * @createDate 2010-03-30 下午05:52:36 
	 * @author su_jian 
	 * @param hql 查询的hql语句 
	 * @param obj 查询参数 
	 * @return 返回list集合 
	 * @throws DataAccessException 
	 */
	public <T> List<T> getList(String hql, Object[] obj) throws DataAccessException;
	
	
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
	public <T> T getById(Class<T> c, Integer id) throws DataAccessException;
	
	

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
	public <T> T getById(Class<T> c, String id) throws DataAccessException;
	
	
	/** 
	 * <b>function:</b> 传入一个将要删除的entity对象，返回删除是否成功 
	 * @createDate 2010-03-30 下午05:42:02 
	 * @author su_jian 
	 * @param <T> 传入对象类型 
	 * @param entity 将要传入的对象 
	 * @return boolean true/false 
	 * @throws DataAccessException 
	 */
	public <T> boolean delete(T entity) throws DataAccessException;
	
	
	
	
	/** 
	 * <b>function:</b> 传入删除的hql语句，删除记录 
	 * @createDate 2010-03-30 上午09:53:49 
	 * @author su_jian 
	 * @param hql 将要被执行删除的hql语句 
	 * @return 是否删除成功 
	 * @throws DataAccessException 
	 */
	public boolean delete(String hql) throws DataAccessException;
	
	
	
	/** 
	 * <b>function:</b> 动态查询 
	 * @createDate 2010-03-30 上午10:53:37 
	 * @author su_jian 
	 * @param <T> 查询类的类型 
	 * @param c 动态查询组合对象 
	 * @return list集合 
	 * @throws DataAccessException 
	 */
	public <T> List<T> getList(Class<T> c) throws DataAccessException;
	
	
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
			int firstResult, int maxResult) throws DataAccessException ;
	
	
	
	
	/** 
	 * <b>function:</b> 传入查询语句和查询总条数（总记录）的hql语句、page分页对象；返回查询后的list集合； 
	 * @createDate 2010-03-30 上午11:16:59 
	 * @author su_jian 
	 * @param queryHql list集合结果查询 
	 * @param queryCountHql 总记录调试查询 
	 * @param page 分页对象 
	 * @throws DataAccessException 
	 */
	public <T> Page<T>  getByPage(Page<T> page,T entity)
			throws DataAccessException;
	
	
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
			int firstResult, int maxResult) throws DataAccessException ;
	
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
			Page<T> page) throws DataAccessException;
	
	/** 
	 * <b>function:</b> 传入查询条件DetachedCriteria进行查询 
	 * @createDate 2010-03-30 上午11:55:28 
	 * @author su_jian 
	 * @param <T> 类型 
	 * @param dc DetachedCriteria动态条件查询 
	 * @return List 
	 * @throws DataAccessException 
	 */
	public <T> List<T> find(DetachedCriteria dc) throws DataAccessException;
	

	/** 
	 * <b>function:</b> 通过hql查找某个唯一的实体对象 
	 * @createDate 2010-04-01 上午11:58:51 
	 * @author su_jian 
	 * @return Entity 
	 */
	public Object findUniqueEntity(final String hql, final Object[] values) throws DataAccessException;
	
	   /**
	 * 按属性查找对象列表.
	 */
    public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value) ;
    /**
	 * 按一个属性查找对象，并且对象不能和根据属性2重复：例 --->编辑页面判断姓名是否重复，首先排除现在的姓名
	 */
	public <T> List<T> findByReProperty(Class<T> entityClass,
			String propertyName, Object value, Object value2);
	
	/**
	 * 根据主键删除指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void deleteEntityById(Class<T>  entityName, Serializable id) ;
	/**
	 * 根据主键批量删除实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void deleteEntityByIds(Class<T>  entityName, String ids) ;
	/**
	 * 获取所有实体
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);
	
}
