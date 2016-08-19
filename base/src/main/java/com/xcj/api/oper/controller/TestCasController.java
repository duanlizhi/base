package com.xcj.api.oper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xcj.common.util.APIConstants;
import com.xcj.common.util.HttpCasUtil;

/**
 * 
 * <b>function:</b> 组合试卷token
 * 
 * @project base
 * @package com.xcj.api.oper.controller
 * @fileName com.xcj.*
 * @createDate 2015年4月7日 下午3:32:41
 * @author hehujun
 * @email hehujun@126.com
 */
@Controller("testCasController")
@RequestMapping("/api/testcas/")
public class TestCasController {

	@ResponseBody
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public String login(String username, String password) {
		try {
			final String server = APIConstants.CAS_URL_PREFIX_REST+"/v1/tickets";
			final String service = "http://content.xiaochejiang.com";
			final String proxyValidate = APIConstants.CAS_URL_PREFIX_REST+"/proxyValidate";
			HttpCasUtil.ticketValidate(proxyValidate, HttpCasUtil.getTicket(
					server, username, password, service), service);
			// 首次登录使用方法
			String tgt = HttpCasUtil.getTicketGrantingTicket(server, username,
					password);
			String st = HttpCasUtil.getServiceTicket(server, tgt, service);
			String result = HttpCasUtil.ticketValidate(proxyValidate, st,
					service);
			String returnResult = "";
			if (result.contains("authenticationFailure")) {
				returnResult = ("登录失败");
			} else {
				returnResult = ("登录成功");
			}

			// 退出使用方法
			//HttpCasUtil.logout(tgt,server);
			return returnResult;
		} catch (Exception e) {
			return "登录失败";
		}

	}
}
