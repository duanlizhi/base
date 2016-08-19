package com.xcj.admin.dao.impl.dict;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.dict.DictDao;
import com.xcj.admin.entity.dict.Dict;

@Component("dictDaoImpl")
@SuppressWarnings( { "hiding" })
public class DictDaoImpl extends BaseDaoImpl implements DictDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public <T extends Dict> List<T> getSortList() throws DataAccessException {
		String hql = "from Dict";
		return super.getList(hql);
	}

	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from Dict t where t.id=" + id);
	}

	public List<Dict> getKeyValueList() {
		String hql = " from Dict";
		return super.getList(hql);
	}

}
