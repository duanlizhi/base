package com.xcj.admin.base;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.xcj.admin.entity.log.AdminLog;

public class BaseController {
	/** 错误视图 */
	protected static final String ERROR_VIEW = "/admin/common/error";

	/**
	 * 添加瞬时消息
	 * 
	 * @param redirectAttributes
	 *            RedirectAttributes
	 * @param message
	 *            消息
	 */

	 
	/**
	 * 添加日志
	 * @param content
	 *  内容
	 */
	protected void addLog(String content) {
		if (content != null) {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(AdminLog.LOG_CONTENT_PROPERTIES_NAME, content, RequestAttributes.SCOPE_REQUEST);
		}
	}

}
