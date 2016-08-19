/**
 * 
 */
package com.xcj.common.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.xcj.common.file.FileInfo;
import com.xcj.common.service.FileService;
import com.xcj.common.util.common.SettingUtil;

/** 
 * <b>function:</b> 文件处理Service类
 * @project base_frame 
 * @package com.xcj.common.service.impl  
 * @fileName com.xcj.*
 * @createDate May 28, 2014 1:58:17 PM 
 * @author su_jian 
 */
@Service("fileServiceImpl")
public class FileServiceImpl implements FileService,ServletContextAware{
	
	@Resource(name = "settingUtil")
	private SettingUtil settingUtil;
	
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	public JSONObject browser(String path, String fileType,
			String orderType,String dir) {
		String strout=null;
		//根目录路径，可以指定绝对路径，比如 /var/www/attached/
		String rootPath = servletContext.getRealPath("/") + "data/";
		//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl  = servletContext.getContextPath() + "/data/";
		String dirName = dir;
		if (dirName != null) {
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//根据path参数，设置各路径和URL
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}
		

		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			strout=("Access is not allowed.");
			return null;
		}
		//最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			strout=("Parameter is not valid.");
			return null;
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			strout=("Directory does not exist.");
		}
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		fileList = browserFile(currentPathFile);
		if ("SIZE".equals(orderType)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("TYPE".equals(orderType)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}		 
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		return result;
	}
	
	private class NameComparator implements Comparator<FileInfo> {
		public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
			return new CompareToBuilder().append(!fileInfos1.getIs_dir(), !fileInfos2.getIs_dir()).append(fileInfos1.getFilename(), fileInfos2.getFilename()).toComparison();
		}
	}

	private class SizeComparator implements Comparator<FileInfo> {
		public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
			return new CompareToBuilder().append(!fileInfos1.getIs_dir(), !fileInfos2.getIs_dir()).append(fileInfos1.getFilesize(), fileInfos2.getFilesize()).toComparison();
		}
	}

	private class TypeComparator implements Comparator<FileInfo> {
		public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
			return new CompareToBuilder().append(!fileInfos1.getIs_dir(), !fileInfos2.getIs_dir()).append(FilenameUtils.getExtension(fileInfos1.getFilename()), FilenameUtils.getExtension(fileInfos2.getFilename())).toComparison();
		}
	}
	
	 /**
	   * <b>function:</b> 浏览文件的方法
	   * @project base_frame
	   * @package com.xcj.common.service.impl  
	   * @fileName com.xcj.*** 
	   * @createDate May 28, 2014 2:29:53 PM
	   * @return List<FileInfo>
	   * @author su_jian
	   */
	private List<FileInfo> browserFile(File currentPathFile) {
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		if (currentPathFile.exists() && currentPathFile.isDirectory()) {
			for (File file : currentPathFile.listFiles()) {
				FileInfo fileInfo = new FileInfo();
				String fileName = file.getName();
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				fileInfo.setFilename(fileName);
				fileInfo.setUrl(settingUtil.getSetting("siteUrl") + currentPathFile + file.getName());
				fileInfo.setIs_dir(file.isDirectory());
				fileInfo.setFilesize(file.length());
				fileInfo.setDatetime(new Date(file.lastModified()));
				fileInfo.setFiletype(fileExt);
				String str=settingUtil.getSetting("upImageExtension");
				fileInfo.setIs_photo(str.contains(fileExt));
				fileInfo.setHas_file(true);
				fileInfos.add(fileInfo);
			}
		}
		return fileInfos;
	}
	
	/**
	 * 判断文件是否合法
	 */
	public boolean isValid(String dir, MultipartFile multipartFile) {
		if (multipartFile == null) {
			return false;
		}
		Integer uploadMaxSize=Integer.valueOf(settingUtil.getSetting("uploadSzie"));
		if (uploadMaxSize != null && uploadMaxSize != 0 && multipartFile.getSize() > uploadMaxSize * 1024L * 1024L) {
			return false;
		}
		String []uploadExtensions;
		if (dir.equals("image")) {
			uploadExtensions = settingUtil.getSetting("upImageExtension").split(",");
		} else if (dir.equals("media")) {
			uploadExtensions = settingUtil.getSetting("upMediaExtension").split(",");
		} else if(dir.equals("file")) {
			uploadExtensions =  settingUtil.getSetting("upFileExtension").split(",");
		} else {
			uploadExtensions =  settingUtil.getSetting("upFlashExtension").split(",");
		}
		  return FilenameUtils.isExtension(multipartFile.getOriginalFilename(), uploadExtensions);
	}

	/**
	 * 上传文件的方法
	 */
	public String upload(String dir, MultipartFile multipartFile, boolean b) {
		if (multipartFile == null) {
			return null;
		}
		
		@SuppressWarnings("unused")
		String uploadPath;
		if (dir.equals("image")) {
			uploadPath = settingUtil.getSetting("imageUploadPath");
		} else if (dir.equals("media")) {
			uploadPath = settingUtil.getSetting("mediaUploadPath");
		} else if(dir.equals("file")) {
			uploadPath =  settingUtil.getSetting("fileUploadPath");
		} else {
			uploadPath =  settingUtil.getSetting("flashUploadPath");
		}
		try {
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	 
}
