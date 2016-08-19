/**
 * 
 */
package com.xcj.common.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xcj.admin.entity.log.AdminLog;
import com.xcj.admin.service.admin.AdminService;
import com.xcj.admin.service.log.AdminLogService;
import com.xcj.common.entity.LogConfig;
import com.xcj.common.service.LogConfigService;
import com.xcj.common.util.common.DateUtil;

/**
 * <b>function:</b> 日志记录功能
 * 
 * @project base_frame
 * @package com.xcj.common.interceptor
 * @fileName com.xcj.*
 * @createDate May 6, 2014 3:25:38 PM
 * @author su_jian
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
	/** 默认忽略参数 */
	private static final String[] DEFAULT_IGNORE_PARAMETERS = new String[] {
			"password", "rePassword", "currentPassword" };

	/** antPathMatcher */
	private static AntPathMatcher antPathMatcher = new AntPathMatcher();

	/** 忽略参数 */
	private String[] ignoreParameters = DEFAULT_IGNORE_PARAMETERS;
	@Resource(name = "logConfigServiceImpl")
	private LogConfigService logConfigService;

	@Resource(name = "adminLogServiceImpl")
	private AdminLogService adminLogService;
	
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	@SuppressWarnings("unchecked")
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Session session=SecurityUtils.getSubject().getSession();
		List<LogConfig> logConfigs = logConfigService.getAll();
		if (logConfigs != null) {
			String path = request.getServletPath();
			for (LogConfig logConfig : logConfigs) {
				if (antPathMatcher.match(logConfig.getUrlPattern(), path)) {
					String username = adminService.getCurrentUsername();//获取当前登录用户名，如果获取不到则为null
					String operation = logConfig.getOperation();
					String operator = username;
					String content =(String) request.getAttribute(AdminLog.LOG_CONTENT_PROPERTIES_NAME);
					String ip = request.getRemoteAddr();
					request.removeAttribute(AdminLog.LOG_CONTENT_PROPERTIES_NAME);
					StringBuffer parameter = new StringBuffer();
					Map<String, String[]> parameterMap = request
							.getParameterMap();
					if (parameterMap != null) {
						for (Entry<String, String[]> entry : parameterMap
								.entrySet()) {
							String parameterName = entry.getKey();
							if (!ArrayUtils.contains(ignoreParameters,
									parameterName)) {
								String[] parameterValues = entry.getValue();
								if (parameterValues != null) {
									for (String parameterValue : parameterValues) {
										parameter.append(parameterName + " = "
												+ parameterValue + "\n");
									}
								}
							}
						}
					}
					AdminLog log = new AdminLog();
					log.setOperation(operation);
					log.setOperator(operator);
					log.setContent(content);
					log.setParameter(parameter.toString());
					log.setIp(ip);
					log.setCreateDate(DateUtil.getCurrentTimeByDate());
					log.setModifyDate(DateUtil.getCurrentTimeByDate());
					adminLogService.save(log);
					break;

				}
			}
		}
	}

	/**
	 * 设置忽略参数
	 * 
	 * @return 忽略参数
	 */
	public String[] getIgnoreParameters() {
		return ignoreParameters;
	}

	/**
	 * 设置忽略参数
	 * 
	 * @param ignoreParameters
	 *            忽略参数
	 */
	public void setIgnoreParameters(String[] ignoreParameters) {
		this.ignoreParameters = ignoreParameters;
	}
}
