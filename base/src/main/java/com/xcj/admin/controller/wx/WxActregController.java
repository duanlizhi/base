package com.xcj.admin.controller.wx;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.wx.WxActreg;
import com.xcj.admin.service.wx.WxActregService;
import com.xcj.common.page.Page;

/**
 * <b>function:</b> wx_actreg微信活动报名
 * 
 * @package com.xcj.admin.controller.wx.
 * @fileName com.xcj.admin.*
 * @createDate Thu May 21 16:23:36 CST 2015
 * @author name su_jian
 */
@Controller("wxActregController")
@RequestMapping("/admin/wx")
public class WxActregController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(WxActregController.class);
	@Resource(name = "wxActregServiceImpl")
	private WxActregService wxActregService;

	/**
	 * 
	 * <b>function:</b> wx_actreg微信活动报名:分页获取数据的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.wx.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu May 21 16:23:36 CST 2015
	 * @return List<WxActreg>
	 * @author name su_jian
	 **/
	@RequestMapping(value = "/wxactreglist")
	public String wxActregList(Model model, Page<WxActreg> ps, String inname) {
		try {
			WxActreg wxActreg = new WxActreg();
			wxActreg.setName(inname);
			Page<WxActreg> p = wxActregService.getByWxActregPage(ps, wxActreg);
			model.addAttribute("page", p);
			model.addAttribute("inname", inname);
		} catch (Exception e) {
			log.error("微信活动报名:获取分页对象出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "/admin/wx/wxactreglist_jsp";
	}

}
