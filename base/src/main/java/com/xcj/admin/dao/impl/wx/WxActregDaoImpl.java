package com.xcj.admin.dao.impl.wx;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.wx.WxActregDao;
import com.xcj.admin.entity.wx.WxActreg;
import com.xcj.common.page.Page;

/**
 * 
 * <b>function:</b> wx_actreg微信活动报名
 * 
 * @package com.xcj.admin.dao.impl.wx.
 * @fileName com.xcj.admin.*
 * @createDate Thu May 21 16:24:23 CST 2015
 * @author name su_jian
 */
@Component("wxActregDaoImpl")
@SuppressWarnings( { "hiding" })
public class WxActregDaoImpl extends BaseDaoImpl implements WxActregDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * <b>function:</b> wx_actreg微信活动报名---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.wx.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu May 21 16:24:23 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public <T extends WxActreg> List<T> getAllList() throws DataAccessException {
		String hql = "from WxActreg";
		return super.getList(hql);
	}

	/**
	 * 
	 * <b>function:</b> wx_actreg微信活动报名---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.wx.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu May 21 16:24:23 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public Page<WxActreg> getByWxActregPage(Page<WxActreg> page,
			WxActreg wxActreg) throws DataAccessException {
		try {
			Session session = this.getSession();
			if (wxActreg.getName() != null && !wxActreg.getName().equals("")) {
				page.setResult(session.createQuery(
						"from " + wxActreg.getClass().getName()
								+ " where name like '%" + wxActreg.getName()
								+ "%' order  by id desc").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ wxActreg.getClass().getName()
								+ " where name like '%" + wxActreg.getName()
								+ "%'").setMaxResults(1).uniqueResult()
						.toString()));
			} else {
				page.setResult(session.createQuery(
						"from " + wxActreg.getClass().getName()
								+ " order by id desc").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ wxActreg.getClass().getName()).setMaxResults(
						1).uniqueResult().toString()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	public boolean getWxActregByMobile(String mobile)
			throws DataAccessException {
		String hql = "select count(*) from WxActreg act where lower(act.mobile) = lower(:mobile)";
		Long wxActreg= Long.valueOf(super.getSession().createQuery(hql).setParameter("mobile", mobile).uniqueResult().toString());
		if(wxActreg>0){
			return true;
		}else{
			return false;	
		}
	}

}
