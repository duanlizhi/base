package com.xcj.api.oper.controller;


import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.FileUpload;
import com.xcj.common.util.tokenUtil;
import com.xcj.common.util.xmlUtil;
import com.xcj.common.util.common.StringUtil;
/**
 * 
	 * 
     * <b>function:</b> 上传接口
     * @project base
     * @package com.xcj.common.util  
     * @fileName @return
     * @createDate February 16, 2015 3:05:04 PM
     * @author yy.niu
 */
@Controller("fileToolAPIController")
@RequestMapping("/api/oper/file")
public class FileToolAPIController extends BaseController {
	

	private static final Logger log = LoggerFactory.getLogger(FileToolAPIController.class);
	@Resource(name = "courseWareServiceImpl")
	private CourseWareService courseWareService;
	 
	/**
     * <b>function:</b> 上传xml文件
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	@ResponseBody
	@RequestMapping(value = "/fileUpload" ,method=RequestMethod.POST)
	public static JSONObject fileUpload (HttpServletRequest request, @RequestParam("fileUpload")
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
						String uploadPath = "data/" + tokenUtil.toKen()+fileName;
						File file = new File(request.getContextPath()+"data");
						file.mkdirs();//创建文件夹
						File filePath = new File(request.getSession().getServletContext().getRealPath(uploadPath));
						fileUrl = filePath.toString();
						data.put("statue","1");
						data.put("fileUrl",fileUrl); 
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
				log.error("上传错误");
			}
		}
		return obj;
	}

	/**
	 * 复制于CourseWareController.java
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/fileUploadXml")
	public  JSONObject fileUploadXml(HttpServletResponse response,HttpServletRequest request, @RequestParam("fileUpload")
			MultipartFile fileUpload) throws Exception {
			JSONObject msgObject = new JSONObject();
			String msg = ""; //返回的提示信息
			String saveDataIsSuccess = null;//sbtFault是否存在
			//调用上传临时文件方法，返回json数据
			JSONObject fileSuccess = FileUpload.fileUploadTemporary(request,fileUpload);
			JSONObject jsonObj = fileSuccess.getJSONObject("xmlFile");
			String statue = jsonObj.get("statue").toString();
			//如果statue==1 正确 返回 路径 如果是0错误
			if(statue.equals("1")){
				String fielUrl = jsonObj.get("fileUrl").toString();
				String uploadPath = jsonObj.get("uploadPath").toString();
				JSONObject fileObj = xmlUtil.fileType(fielUrl);
				JSONObject fileData = fileObj.getJSONObject("fileData");
				String fileType = fileData.getString("types");
				String courseNumber = fileData.getString("courseNumber");
				String faultNumber = fileData.getString("faultNumber");
				//判断fileType是否为空
				if(fileType!=null	&&	!fileType.equals("")	&&	StringUtil.isTypeMatching(fileType)){
					//判断courseNumber是否为空
					if(courseNumber!=null	||	faultNumber!=null){
						//判断courseNumber是否为空
						String realPath = request.getSession().getServletContext().getRealPath("");
						saveDataIsSuccess = courseWareService.saveData(fileType,fielUrl,courseNumber,faultNumber,uploadPath,realPath);
						//如果saveDataIsSuccess等于true成功
						if(saveDataIsSuccess.equals("0")){
							msgObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
							msgObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SUCCESS);
							msg=APIConstants.ERRMSG_ADD_SUCCESS;
						}else{
							if(saveDataIsSuccess.equals("1")){
								//courseNumber或type不能为空
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_DATA_ERROR);
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_DATA_ERROR);
								msg=APIConstants.ERRCODE_DATA_ERROR+APIConstants.ERRCODE_DATA_ERROR;
							}else if(saveDataIsSuccess.equals("2")){
								//写入数据失败
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_WRITETEXT_ERROR);
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_WRITETEXT_ERROR);
								msg=APIConstants.ERRCODE_WRITETEXT_ERROR+APIConstants.ERRMSG_WRITETEXT_ERROR;
							}else if(saveDataIsSuccess.equals("3")){
								//知识点没有关联
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_KP_ERROR);
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_KP_ERROR);
								msg=APIConstants.ERRCODE_KP_ERROR+APIConstants.ERRMSG_KP_ERROR;
							}else if(saveDataIsSuccess.equals("4")){
								//节点知识点没有关联
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_KPS_ERROR);
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_KPS_ERROR);
								msg=APIConstants.ERRCODE_KPS_ERROR+APIConstants.ERRMSG_KPS_ERROR;
							}else if(saveDataIsSuccess.equals("5")){
								//请先上传sbt课件
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_UPLOADSBT_ERROR);
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_UPLOADSBT_ERROR);
								msg=APIConstants.ERRCODE_UPLOADSBT_ERROR+APIConstants.ERRMSG_UPLOADSBT_ERROR;
							}else if(saveDataIsSuccess.equals("6")){
								//courseNumber错误
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_NUMBER_ERROR);
								msgObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_NUMBER_ERROR);
								msg=APIConstants.ERRCODE_NUMBER_ERROR+APIConstants.ERRMSG_NUMBER_ERROR;
							}
							//msg=APIConstants.ERRCODE_DATA_ERROR+APIConstants.ERRMSG_DATA_ERROR;
						}
					}else{
						msgObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_NUMBER_ERROR);
						msgObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_NUMBER_ERROR);
						msg=APIConstants.ERRCODE_NUMBER_ERROR+APIConstants.ERRCODE_NUMBER_ERROR;
					}
				}else{
					msgObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TYPE_ERROR);
					msgObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TYPE_ERROR);
					msg=APIConstants.ERRCODE_TYPE_ERROR+APIConstants.ERRMSG_TYPE_ERROR;
				}
			} 
		return msgObject;
	}
}
