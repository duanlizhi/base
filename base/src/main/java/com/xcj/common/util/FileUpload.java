/**
	 * 
     * <b>function:</b> 
     * @project 
     * @package   
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
package com.xcj.common.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <b>function:</b> 读取SBT故障
 * @project base
 * @package com.xcj.common.util  
 * @fileName @return
 * @createDate February 16, 2015 3:05:04 PM
 * @author yy.niu
 */
public class FileUpload {

	
	/**
	 * 
	 * 
     * <b>function:</b> 上传xml临时文件
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	 
	public static JSONObject fileUploadTemporary (HttpServletRequest request, @RequestParam("fileUpload")
			MultipartFile fileUpload) throws Exception {
		JSONObject data = new JSONObject();
		JSONObject obj = new JSONObject();
		String fileUrl =""; 
		//判断是否有文件
		if (!fileUpload.isEmpty()) {
			try {
				String fileName = fileUpload.getOriginalFilename();
				// 获取文件后缀
				String[] suffixs = fileName.split("\\.");
				String suffix = "." + suffixs[suffixs.length - 1];
				//判断上传的文件格式是否正确
				if ((".xml".indexOf(suffix.toLowerCase()) != -1)) {
					byte[] bytes = fileUpload.getBytes();
					Integer fileSize = (int) fileUpload.getSize() / 1024;
					// 如果文件小于10M，则上传文件，否则提示用户不能超过10M
					if (fileSize <= 10240) {
						String uploadPath = "data/sbtwbt/" + tokenUtil.toKen()+fileName;
						File file = new File(request.getSession().getServletContext().getRealPath("data/sbtwbt"));
						//File file = new File("/usr/local/nginx/html/data/sbtwbt/");
						
						file.mkdirs();//创建文件夹
						//File filePath = new File("/usr/local/nginx/html/data/sbtwbt/"+tokenUtil.toKen()+fileName);
						
						File filePath = new File(request.getSession().getServletContext().getRealPath(uploadPath));
						fileUrl = filePath.toString();
						data.put("statue","1");
						data.put("fileUrl",fileUrl); 
						data.put("uploadPath",uploadPath);//数据库路径
						data.put("errMsg","成功"); 
						obj.put("xmlFile", data);
						//文件开始上传到服务器上
						FileCopyUtils.copy(bytes, filePath);
					} else {
						data.put("statue","0");
						data.put("errMsg","上传的文件太大，文件大小不能超过10M"); 
						obj.put("xmlFile", data);
						return obj;
					}
				} else {
					data.put("statue","0");
					data.put("errMsg","上传的文件格式不支持"); 
					obj.put("xmlFile", data);
					return obj;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
}
