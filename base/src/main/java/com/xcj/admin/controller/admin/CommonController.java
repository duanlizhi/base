/**
 * 
 */
package com.xcj.admin.controller.admin;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xcj.common.service.FileService;
import com.xcj.common.util.common.SettingUtil;
import com.xcj.common.util.file.FileNameUtils;

/**
 * <b>function:</b>公共页面 CommonController
 * 
 * @project base_frame
 * @package com.xcj.admin.controller.admin
 * @filename PublicController.java
 * @createDate May 5, 2014 3:03:27 PM
 * @author su_Jian
 * 
 */
@Controller("commonController")
@RequestMapping("/admin/common")
public class CommonController implements ServletContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Resource(name = "fileServiceImpl")
	private FileService fileService;

	@Resource(name = "multipartResolver")
	private CommonsMultipartResolver multipartResolver;

	@Resource(name = "settingUtil")
	private SettingUtil settingUtil;

	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * <b>function:</b>公用页面，里面存放公用的js、css
	 * 
	 * @project base_frame
	 * @package com.xcj.admin.controller.admin
	 * @filename PublicController.java
	 * @createDate May 5, 2014 3:03:27 PM
	 * @author su_Jian
	 * 
	 */
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public ModelAndView base(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("/admin/common/member_jsp");
	}

	/**
	 * <b>function:</b>返回顶部页面
	 * 
	 * @project base_frame
	 * @package com.xcj.admin.controller.admin
	 * @filename PublicController.java
	 * @createDate May 5, 2014 3:03:27 PM
	 * @author su_Jian
	 * 
	 */
	@RequestMapping(value = "/partnerList", method = RequestMethod.GET)
	public ModelAndView top(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("/admin/partner/partnerlist_jsp");
	}

	/**
	 * <b>function:</b>浏览文件
	 * 
	 * @project base_frame
	 * @package com.xcj.admin.controller.admin
	 * @filename PublicController.java
	 * @createDate May 5, 2014 3:03:27 PM
	 * @author su_Jian
	 * 
	 */
	@RequestMapping(value = "/browser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	JSONObject browser(String path, String fileType, String order, String dir,
			HttpServletResponse response) {
		JSONObject json = fileService.browser(path, fileType, order, dir);
		/*
		 * PrintWriter out = null;
		 * response.setContentType("application/json; charset=UTF-8"); try { out
		 * = response.getWriter(); } catch (IOException e) {
		 * e.printStackTrace(); } out.println(json); out.close(); out = null;
		 */
		return json;
	}

	/**
	 * 上传
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject upload(String dir, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject data = new JSONObject();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		if (multipartResolver.isMultipart(multipartRequest)) { // 判断 request
																// 是否有文件上传,即多部分请求...
			// srcfname 是指 文件上传标签的 name=值
			MultiValueMap<String, MultipartFile> multfiles = multipartRequest
					.getMultiFileMap();
			for (String srcfname : multfiles.keySet()) {
				MultipartFile mfile = multfiles.getFirst(srcfname);
				if (!fileService.isValid(dir, mfile)) {
					data.put("error", 1);
					data.put("message", "上传文件不符合要求");
					return data;
				}
				try {
					String path = servletContext.getRealPath("/");
					//linux上部署上传静态到nginx下
					//String path = "/usr/local/nginx/html/";
					String uploadPath;
					if (dir.equals("image")) {
						uploadPath = settingUtil.getSetting("imageUploadPath");
					} else if (dir.equals("media")) {
						uploadPath = settingUtil.getSetting("mediaUploadPath");
					} else if (dir.equals("file")) {
						uploadPath = settingUtil.getSetting("fileUploadPath");
					} else {
						uploadPath = settingUtil.getSetting("flashUploadPath");
					}
					String uuid = FileNameUtils.genFileName();
					String lujing = path + uploadPath
							+ FileNameUtils.genPathName() + "/";
					File files = new File(lujing);
					if (!files.exists()) {
						files.mkdir();
					}
					String repath = lujing
							+ uuid
							+ "."
							+ FilenameUtils.getExtension(mfile
									.getOriginalFilename());
					mfile.transferTo(new File(repath));
					data.put("error", 0);
					data.put("message", "成功");
					data.put("url", settingUtil.getSetting("siteUrl")
							+ uploadPath
							+ FileNameUtils.genPathName()
							+ "/"
							+ uuid
							+ "."
							+ FilenameUtils.getExtension(mfile
									.getOriginalFilename()));
				} catch (IllegalStateException e) {
					data.put("error", 1);
					data.put("message", "上传文件不符合要求");
					logger.error(CommonController.class.getName()+" upload() exception: "+e.getMessage());
				} catch (IOException e1) {
					data.put("error", 1);
					data.put("message", "上传文件不符合要求");
					logger.error(CommonController.class.getName()+" upload() exception: "+e1.getMessage());
				}

			}

			/*
			 * if (!fileService.isValid(dir, file)) { data.put("error", 1);
			 * data.put("message", "文件不合法"); } else { String url =
			 * fileService.upload(dir, file, false); if (url == null) {
			 * data.put("error", 1); data.put("message","上传文件不合法"); } else {
			 * data.put("error", 0); data.put("message", "成功"); data.put("url",
			 * url); } }
			 */
		}
		return data;
	}

	/***
	 * <b>function:</b> 默认进入列表页面
	 * 
	 * @project Lgguo
	 * @package com.xcj.admin.controller
	 * @fileName TestController.java
	 * @createDate 2010-4-1 下午05:28:03
	 * @author su_jian
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("/admin/main_jsp", model);
	}

	/***
	 * <b>function:</b> 默认进入列表页面
	 * 
	 * @project Lgguo
	 * @package com.xcj.admin.controller
	 * @fileName TestController.java
	 * @createDate 2010-4-1 下午05:28:03
	 * @author su_jian
	 */
	@RequestMapping(value = "/right", method = RequestMethod.GET)
	public ModelAndView right(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("/admin/main_jsp", model);
	}

	/**
	 * <b>function:</b> 找不到路径404页面
	 * 
	 * @project base
	 * @fileName com.xcj.***
	 * @createDate 2015-3-31 下午05:22:11
	 * @return String
	 * @author dapeng_wu
	 */
	@RequestMapping(value = "/notFind")
	public String notFind(Model mode) {
		return "/admin/notFind_jsp";
	}

	/**
	 * <b>function:</b> 没有权限
	 * 
	 * @project base
	 * @fileName com.xcj.***
	 * @createDate 2015-3-31 下午05:27:09
	 * @return String
	 * @author dapeng_wu
	 */
	@RequestMapping(value = "/notAuth")
	public String notAuth(Model mode) {
		return "/admin/notAuth_jsp";
	}

}
