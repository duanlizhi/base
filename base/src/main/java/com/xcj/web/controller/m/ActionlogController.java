/**
 * 
 *//*
package com.xcj.web.controller.m;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.entity.log.ActionLog;
import com.xcj.admin.service.log.ActionLogService;
import com.xcj.common.util.DateUtil;
import com.xcj.common.util.StringUtil;

*//**
 * <b>function:</b> 行为日志
 * 
 * @project base
 * @package com.xcj.web.controller.m
 * @fileName com.xcj.*
 * @createDate Dec 6, 2014 4:42:15 PM
 * @author su_jian
 *//*
@Controller("actionlogController")
@RequestMapping("/m")
public class ActionlogController {
	@Resource(name = "actionLogServiceImpl")
	private ActionLogService actionLogService;

	*//**
	 * <b>function:</b> 手机获取数据的方法
	 * 
	 * @project base
	 * @package com.xcj.web.controller.m
	 * @fileName com.xcj.***
	 * @createDate Dec 6, 2014 4:47:02 PM
	 * @return String
	 * @author su_jian
	 *//*
	@ResponseBody
	@RequestMapping(value = "/action/put", produces = "application/json;charset=UTF-8")
	public JSONObject loves(Integer type, Integer shareType, String version,
			String nickName, String openid, String content,
			HttpServletRequest request, HttpServletResponse response) {
		
		//final String versions = version; 
		//http://localhost:91/m/action/put?type=1&shareType=2&version=
		//  4.4.4@xiaomi@19@1412312312312@mi2%20sc&nickName=&openid=sujianskd23sdf&content=
		String arr[] = StringUtil.splitAndTrim(version, "@", "");
		JSONObject data = new JSONObject();
		try {
			ActionLog entity =new ActionLog();
			entity.setContent(content);
			entity.setCreateDate(DateUtil.getCurrentTimeByDate());
			entity.setMobileImei(arr[3]);//系统识别码
			entity.setMobileModel(arr[4]);
			entity.setModifyDate(DateUtil.getCurrentTimeByDate());
			entity.setName(nickName);
			entity.setOpenid(openid);
			entity.setSdkRelease(arr[0]);//系统版本号
			entity.setSdkVersion(arr[2]);//SDK版本
			entity.setShareType(shareType);
			entity.setSystemBrand(arr[1]);//品牌
			entity.setType(type);
			actionLogService.save(entity);
			data.put("errCode", "0");
			data.put("errMsg", "success");
		} catch (Exception e1) {
			e1.printStackTrace();
			data.put("errCode", "50001");
			data.put("errMsg", "出错了没有保存进去");
		}
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				if (versions != null) {
					// 4.4.4@xiaomi@19@1412312312312@mi2 sc
					try {
						String arr[] = StringUtil.splitAndTrim(versions, "@",
								"");
						ArticleStatistics entity = new ArticleStatistics();
						entity.setArticleId(0);
						entity.setArticleTitle(arr[4]);// 品牌
						entity.setBoring(0);
						entity.setBrowserName(arr[1]);// 系统型号
						entity.setBrowserVersion(arr[0]);// 系统版本
						entity.setCreateDate(DateUtil.getCurrentTimeByDate());
						entity.setHits(types);// 如果是列表 则1是第一个 2.第二个 3 第三个
						entity.setIsWeixin(3);
						entity.setLoves(0);
						entity.setStype(4);
						entity.setModifyDate(DateUtil.getCurrentTimeByDate());
						entity.setUserAgent(arr[3]);// 识别码
						entity.setSystem(arr[2]);// 系统SDK版本
						// articleStatisticsService.save(entity);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		return data;
	}

}
*/