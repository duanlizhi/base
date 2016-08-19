package com.xcj.admin.service.impl.wx;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.wx.WxActregDao;
import com.xcj.admin.entity.wx.WxActreg;
import com.xcj.admin.service.wx.WxActregService;
import com.xcj.common.page.Page;

/**
 *
  * <b>function:</b>  wx_actreg微信活动报名
  * @package com.xcj.admin.service.impl.wx.
  * @fileName com.xcj.admin.*
  * @createDate Thu May 21 16:24:19 CST 2015
  * @author name su_jian
 */
@Service("wxActregServiceImpl")
public class WxActregServiceImpl extends BaseServiceImpl implements WxActregService{

	@Resource(name ="wxActregDaoImpl")
	private WxActregDao wxActregDao;

   /**
    *
     * <b>function:</b>  wx_actreg微信活动报名---获取list方法
     * @project base
     * @package com.xcj.admin.service.wx.
     * @fileName com.xcj.admin.*
     * @createDate Thu May 21 16:24:19 CST 2015
     * @return List<WxActreg>
  * @author name su_jian
    */
  public Page<WxActreg>  getByWxActregPage(Page<WxActreg> ps,WxActreg wxActreg)  throws Exception { 
    return wxActregDao.getByWxActregPage(ps,wxActreg); 
   }
	
	public boolean getWxActregByMobile(String mobile) {
		boolean  flag=wxActregDao.getWxActregByMobile(mobile);
		return flag;
	}

 
 
}
