package com.xcj.admin.service.wx;
import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.wx.WxActreg;
import com.xcj.common.page.Page;

  /**
  *
  * <b>function:</b>  wx_actreg微信活动报名
  * @package com.xcj.admin.service.wx.
  * @fileName com.xcj.admin.*
  * @createDate Thu May 21 16:24:17 CST 2015
  * @author name su_jian
 */
public interface WxActregService extends BaseService{

   /**
    *
     * <b>function:</b>  wx_actreg微信活动报名---获取分页List方法
     * @project base
     * @package com.xcj.admin.service.wx.
     * @fileName com.xcj.admin.*
     * @createDate Thu May 21 16:24:17 CST 2015
     * @return List<WxActreg>
  * @author name su_jian
    */
 public  Page<WxActreg>  getByWxActregPage(Page<WxActreg> ps,WxActreg wxActreg) throws Exception;

/** 
 * <b>function:</b> 
 * WxActregService
 * @createDate  2015-5-21 下午05:57:57
 * @author su_jian 
 * @email sujiansoft@163.com 
 * return type boolean 
 */ 
 public boolean getWxActregByMobile(String mobile);

}
