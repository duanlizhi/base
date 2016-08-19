package com.xcj.admin.dao.wx;
import java.util.List;
import com.xcj.common.page.Page;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.entity.wx.WxActreg;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  wx_actreg微信活动报名
  * @package com.xcj.admin.dao.wx.
  * @fileName com.xcj.admin.*
  * @createDate Thu May 21 16:24:21 CST 2015
  * @author name su_jian
 */
public interface WxActregDao extends BaseDao{

   /**
    *
     * <b>function:</b>  wx_actreg微信活动报名---获取list方法
     * @project base
     * @package com.xcj.admin.dao.wx.
     * @fileName com.xcj.admin.*
     * @createDate Thu May 21 16:24:21 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public <T extends WxActreg> List<T> getAllList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  wx_actreg微信活动报名---获取分页list方法
     * @project base
     * @package com.xcj.admin.dao.wx.
     * @fileName com.xcj.admin.*
     * @createDate Thu May 21 16:24:21 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public Page<WxActreg>  getByWxActregPage(Page<WxActreg> ps,WxActreg wxActreg)	throws DataAccessException;

/** 
 * <b>function:</b> 
 * WxActregDao
 * @createDate  2015-5-21 下午07:19:14
 * @author su_jian 
 * @email sujiansoft@163.com 
 * return type boolean 
 */ 
public boolean getWxActregByMobile(String mobile)	throws DataAccessException;
}
