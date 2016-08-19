package com.xcj.admin.controller.tag;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.tag.Tag;
import com.xcj.admin.service.tag.TagService;
import com.xcj.common.base.GenerateNumber;
import com.xcj.common.page.Page;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.SettingUtil;
import com.xcj.common.util.common.StringUtil;
import com.xcj.common.util.file.FileNameUtils;

/**
 * <b>function:</b> tag标签管理表
 * 
 * @package com.xcj.admin.controller.tag.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 11:19:47 CST 2015
 * @author name yang.yan
 */
@Controller("tagController")
@RequestMapping("/admin/tag")
public class TagController extends BaseController implements
		ServletContextAware {

	private static final Logger log = LoggerFactory.getLogger(TagController.class);
	@Resource(name = "tagServiceImpl")
	private TagService tagService;
	@Resource(name = "settingUtil")
	private SettingUtil settingUtil;
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 标签管理表:分页获取数据的方法
	 **/
	@RequestMapping(value = "/tagList")
	public String tagList(Model model, Page<Tag> ps, String screening) {
		try {
			// 1.根据order和权重排序获取标签集合
			// 2.screening为根据标签名称筛选的条件值
			Page<Tag> tagPages = tagService.getByOrder(ps, new Tag(), screening);
			model.addAttribute("page", tagPages);
			model.addAttribute("tagName", screening);
		} catch (Exception e) {
			log.error("标签管理表:获取分页对象出错：" + e.getMessage());
		}
		return "/admin/tag/tagList_jsp";
	}

	/**
	 * 标签管理表:保存的方法
	 **/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, Model model) {
		try {
			Tag tag = new Tag();
//			1、获取上传的文件，放到指定路径中
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
					.getFile("icon");
			
//			 2.获取要存放文件的路径
			String uploadPath = settingUtil.getSetting("imageUploadPath");
			if (file.getSize() != 0) {
				String path = servletContext.getRealPath("/");
				String lujing = path + uploadPath + "tag/";
				File files = new File(lujing);
				if (!files.exists()) {
					files.mkdirs();
				}
				
				String uuid = FileNameUtils.genFileName();
				String fileName=FilenameUtils.getExtension(file.getOriginalFilename());
				file.transferTo(new File(lujing+ uuid+ "."+ fileName));
				tag.setIcon(uploadPath+ "tag/"+ uuid+ "."+fileName);
			} else {
				tag.setIcon(uploadPath + "/tag.png");
			}
			
//			3.保存tag标签
			tag.setName(request.getParameter("name"));
			tag.setOrders(Integer.parseInt(request.getParameter("orders")));
			tag.setTagNumber(GenerateNumber.getInstance().getTagNumber());

			tag.setCreateDate(DateUtil.getCurrentTimeByDate());
			tag.setModifyDate(DateUtil.getCurrentTimeByDate());
			tagService.save(tag);
		} catch (Exception e) {
			log.error("标签管理表:保存对象出错：" + e.getMessage());
		}
		return "redirect:/admin/tag/tagList_jsp";
	}

	/**
	 * 标签管理表:更新初始化的方法
	 */
	@ResponseBody
	@RequestMapping(value = "/updateById", method = RequestMethod.GET)
	public  JSONObject updateById(Integer id, Model model) {
		JSONObject json = new JSONObject();
		try {
			// 根据id获取当前标签
			Tag tag = tagService.getById(Tag.class, id);
			json.put("data", tag);
		} catch (Exception e) {
			log.error("标签管理表:更新对象初始化对象出错：" + e.getMessage());
		}
		return json;
	}

	/**
	 * 标签管理表:更新数据的方法
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, Model model) {
		try {
			Tag oldtag = tagService.getById(Tag.class, Integer.parseInt(request
					.getParameter("id")));
			/*
			 * 1、获取上传的文件，放到指定路径中
			 */
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
					.getFile("icon");
			if (file.getSize() != 0) {
				/*
				 * 2.获取要存放文件的路径
				 */
				String path = servletContext.getRealPath("/");
				String uploadPath = settingUtil.getSetting("imageUploadPath");
				String lujing = path + uploadPath + "tag/";
				File files = new File(lujing);
				if (!files.exists()) {
					files.mkdirs();
				}
				
				String fileName=FilenameUtils.getExtension(file.getOriginalFilename());
				String uuid = FileNameUtils.genFileName();
				file.transferTo(new File(lujing+ uuid+ "."+fileName));
				oldtag.setIcon(uploadPath+ "tag/"+ uuid+ "."+ fileName);
			}
			/*
			 * 3.修改标签信息
			 */
			oldtag.setName(request.getParameter("name"));
			oldtag.setOrders(Integer.parseInt(request.getParameter("orders")));
			oldtag.setModifyDate(DateUtil.getCurrentTimeByDate());
			tagService.update(oldtag);
		} catch (Exception e) {
			log.error("标签管理表:更新对象出错：" + e.getMessage());
		}
		return "redirect:/admin/tag/tagList_jsp";
	}

	/**
	 * 标签管理表---删除方法
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public  JSONObject delete(String ids) {
		JSONObject json = new JSONObject();
		try {
			if (StringUtil.isEmptyYl(ids)) {
				json.put("data", "请选择要删除的项");
			} else {
				// 根据ID的1,2形式执行sql id in(1,2)格式删除标签
				tagService.deleteByIds(ids);
				// 返回页面删除结果
				json.put("data", "删除成功");
			}
		} catch (Exception e) {
			log.error("标签管理表:删除对象出错：" + e.getMessage());
		}
		return json;
	}

	/**
	 * 标签管理表---添加页面 校验标签名称是否重复
	 */
	@ResponseBody
	@RequestMapping(value = "/nameValidate", method = RequestMethod.GET)
	public JSONObject nameValidate(String name) {
		JSONObject json = new JSONObject();
		try {
			// 根据名称属性值判断标签表中是否已存在
			List<Tag> tagList = tagService.findByProperty(Tag.class, "name",name);
			if (tagList.size() > 0) {
				json.put("data", "0");
			} else {
				json.put("data", "1");
			}
		} catch (Exception e) {
			log.error("标签管理表:标签名称是否重复出错：" + e.getMessage());
		}
		json.put("data", "0");
		return json;
	}

	/**
	 * 标签管理表---编辑页面 校验标签名称是否重复
	 */
	@ResponseBody
	@RequestMapping(value = "/nameEditValidate", method = RequestMethod.GET)
	public JSONObject nameEditValidate(String name, String editName) {
		JSONObject json = new JSONObject();
		try {
			// 根据名称属性值判断标签表中是否已存在
			List<Tag> tagList = tagService.findByReProperty(Tag.class, "name",name, editName);
			if (tagList.size() > 0) {
				json.put("data", "0");
			} else {
				json.put("data", "1");
			}
		} catch (Exception e) {
			log.error("标签管理表:标签名称是否重复出错：" + e.getMessage());
		}
		return json;
	}

}
