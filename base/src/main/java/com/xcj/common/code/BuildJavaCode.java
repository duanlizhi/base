/**
 * 
 */
package com.xcj.common.code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xcj.common.code.entity.DataModel;
import com.xcj.common.db.DataSource;

/** 
 * <b>function:</b> 
 * @project base_frame 
 * @package com.xcj.common.code  
 * @fileName com.xcj.*
 * @createDate Apr 16, 2014 3:38:36 PM 
 * @author niu_yy 
 */
public class BuildJavaCode {
	/**
	   * <b>function:</b> Main测试方法
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 17, 2014 12:18:57 PM
	   * @return void
	   * @author niu_yy
	 */
	public static void main(String[] args) {
		String bankuai="admin";//板块 类似于admin
 
		String mokuai="tag";//模块  类似于test
		String tableName="wxsite_article_tag";
		String path="src/main/java/com/xcj/"+bankuai+"/"; 
 
		String packageName="com.xcj."+bankuai;
		String lujing="/"+bankuai+"/"+mokuai;
		String projectName = "base";  //注意!!!工程名称
		String wpath="F:\\workspace\\myEclipse8.6\\base\\WebRoot\\WEB-INF\\template\\admin\\";
		String detailName=wpath+mokuai+"\\"+tableName+"detail.jsp";
		String editName=wpath+mokuai+"\\"+tableName+"edit.jsp";
		String listName=wpath+mokuai+"\\"+tableName+"list.jsp";
		String saveName=wpath+mokuai+"\\"+tableName+"save.jsp";
		
		BuildJavaCode buildJavaCode =new BuildJavaCode();
		//生成JSP
		 //buildJavaCode.generateControllerJsp(tableName,path,packageName,lujing,mokuai,detailName,editName,listName,saveName);
		
		 //生成实体类
		 buildJavaCode.generateEntity(tableName, path, packageName,mokuai);
		
		//生成Controller
		  // buildJavaCode.generateController(tableName,path,packageName,lujing,mokuai,projectName);
		
		// 生成Service 
		 buildJavaCode.generateService(tableName, path, packageName, lujing, mokuai,projectName);
		
		//生成ServiceImpl
		  buildJavaCode.generateServiceImpl(tableName, path, packageName, lujing, mokuai,projectName);
		
		// 生成Dao 
		  buildJavaCode.generateDao(tableName, path, packageName, lujing, mokuai,projectName);
		
		//生成DaoImpl
		 buildJavaCode.generateDaoImpl(tableName, path, packageName, lujing, mokuai,projectName);
		
		
		System.out.println("code is generate success");
		
		
	}
	
	 


		/**
	   * <b>function:</b> 
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 18, 2014 2:31:02 PM
	   * @return void
	   * @author niu_yy
	   * */
		private void generateControllerJsp(String tableName, String path,
			String packageName, String lujing, String mokuai,
			String detailName, String editName, String listName, String saveName) {
		 //CodeUtil.delFile(detailName);
		//CodeUtil.createFile(detailName);
		//this.getDetailJspStr(detailName);
		
		CodeUtil.delFile(listName);
		CodeUtil.createFile(listName);
		this.getListJSPstr(listName,tableName,lujing,mokuai);
		
		CodeUtil.delFile(saveName);
		 CodeUtil.createFile(saveName);
		 this.getSaveJSPstr(saveName,tableName,lujing,mokuai);
		
		CodeUtil.delFile(editName);
		CodeUtil.createFile(editName);
		this.geteditJSPstr(editName,tableName,lujing,mokuai);
	}


	    /**
		   * <b>function:</b> 
		   * @project base_frame
		   * @package com.xcj.common.code  
		   * @fileName com.xcj.*** 
		   * @createDate Apr 18, 2014 3:26:54 PM
		   * @return void
		   * @author niu_yy
		   */
		private void geteditJSPstr(String editName,String tableName,String lujing,String mokuai) {
			String tableComment=tableComment(tableName);
			 StringBuffer sb =new StringBuffer();
			 sb.append(" <!DOCTYPE html>                                                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" <%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" <%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" <%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" <%                                                                                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 	String path = request.getContextPath();                                                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" %>                                                                                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" <!--[if IE 8]> <html lang=\"en\" class=\"ie8\"> <![endif]-->                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" <!--[if IE 9]> <html lang=\"en\" class=\"ie9\"> <![endif]-->                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" <!--[if !IE]><!--> <html lang=\"en\"> <!--<![endif]-->                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" <!-- BEGIN HEAD -->                                                                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" <head>                                                                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 	<meta charset=\"utf-8\" />                                                                                                                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" 	<title>后台管理系统</title>                                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 	<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 	<meta content=\"\" name=\"description\" />                                                                                                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" 	<meta content=\"\" name=\"author\" />                                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 	<!-- BEGIN GLOBAL MANDATORY STYLES -->                                                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/bootstrap-responsive.min.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/style-metro.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/style-responsive.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/default-main.css\" rel=\"stylesheet\" type=\"text/css\" id=\"style_color\"/>                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/uniform.default.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/tip.css\" rel=\"stylesheet\">                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 	<!-- END GLOBAL MANDATORY STYLES -->                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 	<!-- BEGIN PAGE LEVEL STYLES -->                                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 	<link rel=\"stylesheet\" type=\"text/css\" href=\"<%=path%>/resources/admin/css/select2_metro.css\" />                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 	<link rel=\"stylesheet\" href=\"<%=path%>/resources/admin/css/DT_bootstrap.css\" />                                                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 	<!-- END PAGE LEVEL STYLES -->                                                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 	<link rel=\"shortcut icon\" href=\"resources/image/favicon.ico\" />                                                                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 	</head>                                                                                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 	<body class=\"page-header-fixed\">                                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 	<!-- BEGIN PAGE TITLE & BREADCRUMB-->                                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 						<h3 class=\"page-title\">                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 						</h3>                                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 						<ul class=\"breadcrumb\">                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 							<li>                                                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 								<i class=\"icon-home\"></i>                                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 								<a href=\"<%=path%>/admin/articleCategory/articleCategoryList\">文章分类管理</a>                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 								<i class=\"icon-angle-right\"></i>                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 							</li>                                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 							<li><a href=\"#\">"+tableComment+"修改</a></li>                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 						</ul>                                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 						<!-- END PAGE TITLE & BREADCRUMB-->                                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 	 					<div class=\"row-fluid\">                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 		<!-- BEGIN EXAMPLE TABLE PORTLET-->                                                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 						<div class=\"portlet box blue\">                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 							<div class=\"portlet-title\">                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 								<div class=\"caption\">                                                                                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 								<!--<i class=\"icon-globe\"></i>-->                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 								"+tableComment+"修改                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 								</div>                                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 								<div class=\"actions\">                                                                                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 								 <div class=\"input-prepend\">                                                                                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" 											<!-- &nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 												<button class=\"btn green\" type=\"button\" >返&nbsp;&nbsp;回</button>-->                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 										</div>			                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 								</div>                                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 							</div>                                                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 							<div class=\"portlet-body\">                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 							<form action=\"<%=path%>/admin/articleCategory/updata\" method=\"post\" id=\"acForm\">                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 							<input type=\"hidden\" id=\"grade\" name=\"grade\" value=\"0\">                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 							<input type=\"hidden\" name=\"id\" value=\"${articleCategory.id}\">                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 									 	<table class=\"table table-bordered \" id=\"sample_2\">                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 								     <tbody>                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 											<th>名称</th>                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 											<td>                                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 												<input class=\"m-wrap medium\" size=\"10\"  name=\"name\" type=\"text\" value=\"${articleCategory.name}\" placeholder=\"文章分类名称\" />                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 											<th class=\"hidden-480\">"+tableComment+"</th>                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 											<td class=\"hidden-480\">                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 													<div class=\"btn-group\">                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 													<span style = \"border:1px solid #000000;overflow:hidden;width:198px\">                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 													  <select  name=\"acid\" value=\"${articleCategory.acid}\"   style=\"scrollbar{overflow:auto;SCROLLBAR-FACE-COLOR: #e8e8e8;SCROLLBAR-HIGHLIGHT-COLOR: #dddddd;SCROLLBAR-SHADOW-COLOR: #CCC;SCROLLBAR-3DLIGHT-COLOR: #FFFFFF;SCROLLBAR-ARROW-COLOR: #AAA;SCROLLBAR-TRACK-COLOR: #FFFFFF;SCROLLBAR-DARKSHADOW-COLOR: #FFF;SCROLLBAR-BASE-COLOR: #FFF;}\">     ").append("\n");
			 sb.append(" 										  				<option value=\"0\" <c:if test=\"${ac.id==0}\">selected</c:if>>顶级分类</option>                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 										  				  <c:forEach var=\"ac\" items=\"${aclist}\">                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 															 <option value=\"${ac.id}\" <c:if test=\"${ac.id==articleCategory.acid}\">selected</c:if> >                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 																<c:choose>                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 														       <c:when test=\"${ac.grade==0}\">                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 														       </c:when>                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 														       <c:when test=\"${ac.grade==1}\">                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 														              &nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" 														       </c:when>                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 														        <c:when test=\"${ac.grade==2}\">                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 														              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 														       </c:when>                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 														       <c:otherwise>                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 														              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 														       </c:otherwise>                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 														       </c:choose>                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 																	${ac.name}&nbsp;&nbsp;|&nbsp;&nbsp;${ac.orders}                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 															 </option>                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 														  </c:forEach>                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 										  		 	  </select>                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 										  		 	</span>                                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 											  </div>                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 										</tr>                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 											<th>排序</th>                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 											<td colspan=\"3\">                                                                                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" 												<input class=\"m-wrap medium\" size=\"10\" type=\"text\" value=\"${articleCategory.orders}\" name=\"orders\" placeholder=\"排序\" />                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 										</tr>                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 											<th>页面标题</th>                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 											<td  colspan=\"3\" class=\"hidden-480\">                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 												<input type=\"text\" class=\"m-wrap span12\" size=\"16\" value=\"${articleCategory.seoTitle}\"  name=\"seoTitle\" type=\"text\" placeholder=\"页面标题\" >                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 										</tr>                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 											<th>页面关键词</th>                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 											<td  colspan=\"3\" class=\"hidden-480\">                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 												<input type=\"text\" class=\"m-wrap span12\" size=\"16\"  value=\"${articleCategory.seoKeywords}\" name=\"seoKeywords\" type=\"text\" placeholder=\"页面关键词\" >                                                                                                                                                                                              ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 										</tr>                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 											<th>页面描述</th>                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 											<td  colspan=\"3\" class=\"hidden-480\">                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 												<input type=\"text\" class=\"m-wrap span12\" size=\"16\"   value=\"${articleCategory.seoDescription}\"  name=\"seoDescription\" type=\"text\" placeholder=\"页面描述\" >                                                                                                                                                                                        ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 										</tr>		                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 									</tbody>                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 								</table>                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 										<div class=\"form-actions\">                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 													<button class=\"btn blue\" type=\"button\" onclick=\"location.href='<%=path%>/admin/articleCategory/articleCategoryList'\">返&nbsp;&nbsp;回</button>                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 													<button class=\"btn blue\" type=\"submit\"><i class=\"icon-ok\"></i> 确&nbsp;&nbsp;定</button>                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 										 </div>                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 							     </form>                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 								                                                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 							</div>                                                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 						<!-- END EXAMPLE TABLE PORTLET-->                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 			</div>	                                                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 			</div>                                                                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 	                                                                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/jquery-1.10.1.min.js\" type=\"text/javascript\"></script>                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 	                                                                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/jquery.validate.js\" type=\"text/javascript\"></script>                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 	<script type='text/javascript' src='<%=path%>/resources/admin/js/messages_zh.js'></script>                                                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" 		                                                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append("     <!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append("    <script src=\"<%=path%>/resources/admin/js/bootstrap.min.js\" type=\"text/javascript\"></script>                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 	<!--[if lt IE 9]>                                                                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/excanvas.min.js\"></script>                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/respond.min.js\"></script>                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 	<![endif]-->                                                                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/jquery.slimscroll.min.js\" type=\"text/javascript\"></script>                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append("    <!-- END CORE PLUGINS -->                                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 	<!-- BEGIN PAGE LEVEL PLUGINS -->                                                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/select2.min.js\"></script>                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/jquery.dataTables.min.js\"></script>                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/DT_bootstrap.js\"></script>                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 	<!-- END PAGE LEVEL PLUGINS -->                                                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 	<!-- BEGIN PAGE LEVEL SCRIPTS -->                                                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/app.js\"></script>                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/table-advanced.js\"></script>                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 	                                                                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 	                                                                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append("  <script>                                                                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 		jQuery(document).ready(function() {                                                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 		   App.init();                                                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 		   //TableAdvanced.init();                                                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 		});                                                                                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 		                                                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 		  $().ready(function() {                                                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append("  		   $(\"#acForm\").validate({                                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 					 	 // 定义验证规则                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 			    rules: {                                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 			         name:{ required:true,minlength:2,maxlength:80},                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 			         parent:{ required:true},                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 			         orders: {  required: true,  minlength: 1,maxlength:50}                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 			    },                                                                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 			     messages: {  //提示信息                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 			         name:{required: \"请输入文章分类名称\",minlength: \"文章分类名称至少需要2个字符\",maxlength:\"文章分类名称输入字符不能超过80个字符\"},                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 			         parent:{ required:\"请选择父类分级\"},                                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 			         orders: { required: \"请输入排序\",minlength: \"排序至少需要1个字符\",maxlength:\"排序输入字符不能超过80个字符\"}                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 			        }                                                                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 		  });                                                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 	 });                                                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 	                                                                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 	</script>                                                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" <script type=\"text/javascript\">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);                                                                                                                                                                                ").append("\n");
			 sb.append(" (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>                                                                      ").append("\n");
			 sb.append(" <!-- END BODY -->                                                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" </html>	 ").append("\n");																																																																																																																																																													                                                                                                                 
			 writeFile(sb.toString(), editName);
		}




	/**
	   * <b>function:</b> 
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 18, 2014 3:26:50 PM
	   * @return void
	   * @author niu_yy
	   */
		private void getSaveJSPstr(String saveName,String tableName,String lujing,String mokuai) {
			 StringBuffer sb= new StringBuffer();
			 String tableComment=tableComment(tableName);
			 sb.append(" <!DOCTYPE html>                                                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" <%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>                                                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" <%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" <%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" <%                                                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 	String path = request.getContextPath();                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" %>                                                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" <!--[if IE 8]> <html lang=\"en\" class=\"ie8\"> <![endif]-->                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" <!--[if IE 9]> <html lang=\"en\" class=\"ie9\"> <![endif]-->                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" <!--[if !IE]><!--> <html lang=\"en\"> <!--<![endif]-->                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" <!-- BEGIN HEAD -->                                                                                                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" <head>                                                                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 	<meta charset=\"utf-8\" />                                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 	<title>后台管理系统</title>                                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 	<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 	<meta content=\"\" name=\"description\" />                                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 	<meta content=\"\" name=\"author\" />                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 	<!-- BEGIN GLOBAL MANDATORY STYLES -->                                                                                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/bootstrap-responsive.min.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/style-metro.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/style-responsive.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/default-main.css\" rel=\"stylesheet\" type=\"text/css\" id=\"style_color\"/>                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/uniform.default.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 	<link href=\"<%=path%>/resources/admin/css/tip.css\" rel=\"stylesheet\">                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 	<!-- END GLOBAL MANDATORY STYLES -->                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 	<!-- BEGIN PAGE LEVEL STYLES -->                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 	<link rel=\"stylesheet\" type=\"text/css\" href=\"<%=path%>/resources/admin/css/select2_metro.css\" />                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" 	<link rel=\"stylesheet\" href=\"<%=path%>/resources/admin/css/DT_bootstrap.css\" />                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 	<!-- END PAGE LEVEL STYLES -->                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 	<link rel=\"shortcut icon\" href=\"resources/image/favicon.ico\" />                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 	</head>                                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 	<body class=\"page-header-fixed\">                                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 	<!-- BEGIN PAGE TITLE & BREADCRUMB-->                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 						<h3 class=\"page-title\">                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 						</h3>                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 						<ul class=\"breadcrumb\">                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 							<li>                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 								<i class=\"icon-home\"></i>                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 								<a href=\"<%=path%>/admin/articleCategory/articleCategoryList\">"+tableComment+"管理</a>                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 								<i class=\"icon-angle-right\"></i>                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 							</li>                                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 							<li><a href=\"#\">"+tableComment+"添加</a></li>                                                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 						</ul>                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 						<!-- END PAGE TITLE & BREADCRUMB-->                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 	 					<div class=\"row-fluid\">                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 		<!-- BEGIN EXAMPLE TABLE PORTLET-->                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 						<div class=\"portlet box blue\">                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 							<div class=\"portlet-title\">                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 								<div class=\"caption\">                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 								<!--<i class=\"icon-globe\"></i>-->                                                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 								"+tableComment+"添加                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 								</div>                                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 								<div class=\"actions\">                                                                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 								 <div class=\"input-prepend\">                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 										</div>			                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 								</div>                                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 							</div>                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 							<div class=\"portlet-body\">                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 							<form action=\"<%=path%>/admin/articleCategory/save\" method=\"post\" id=\"acForm\">                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 							<input type=\"hidden\" id=\"grade\" name=\"grade\" value=\"0\">                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 							<input type=\"hidden\" id=\"parent\" name=\"parent\" value=\"\">                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 									 	<table class=\"table table-bordered \" id=\"sample_2\">                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 								     <tbody>                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 											<th>名称</th>                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 											<td>                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 												<input class=\"m-wrap medium\" size=\"10\"  name=\"name\" type=\"text\" placeholder=\""+tableComment+"名称\" />                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 											<th class=\"hidden-480\">"+tableComment+"</th>                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 											<td class=\"hidden-480\">                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 													<div class=\"btn-group\">                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 													<span style = \"border:1px solid #000000;overflow:hidden;width:198px\">                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 													  <select  name=\"id\"   style=\"scrollbar{overflow:auto;SCROLLBAR-FACE-COLOR: #e8e8e8;SCROLLBAR-HIGHLIGHT-COLOR: #dddddd;SCROLLBAR-SHADOW-COLOR: #CCC;SCROLLBAR-3DLIGHT-COLOR: #FFFFFF;SCROLLBAR-ARROW-COLOR: #AAA;SCROLLBAR-TRACK-COLOR: #FFFFFF;SCROLLBAR-DARKSHADOW-COLOR: #FFF;SCROLLBAR-BASE-COLOR: #FFF;}\">                     ").append("\n");
			 sb.append(" 										  				<option value=\"0\" selected>顶级分类</option>                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 										  				  <c:forEach var=\"ac\" items=\"${aclist}\">                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 															 <option value=\"${ac.id}\">                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 															  <c:choose>                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 														       <c:when test=\"${ac.grade==0}\">                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 														       </c:when>                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 														       <c:when test=\"${ac.grade==1}\">                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 														              &nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 														       </c:when>                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 														        <c:when test=\"${ac.grade==2}\">                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 														              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 														       </c:when>                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 														       <c:otherwise>                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 														              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 														       </c:otherwise>                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 																 </c:choose>${ac.name}&nbsp;&nbsp;|&nbsp;&nbsp;${ac.orders}                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 															 </option>                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 														  </c:forEach>                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 										  		 	  </select>                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 										  		 	</span>                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 											  </div>                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 										</tr>                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 											<th>排序</th>                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 											<td colspan=\"3\">                                                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append(" 												<input class=\"m-wrap medium\" size=\"10\" type=\"text\"  name=\"orders\" placeholder=\"排序\" />                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 										</tr>                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 											<th>页面标题</th>                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append("                                                                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 											<td  colspan=\"3\" class=\"hidden-480\">                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 												<input type=\"text\" class=\"m-wrap span12\" size=\"16\"  name=\"seoTitle\" type=\"text\" placeholder=\"页面标题\" >                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 										</tr>                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 											<th>页面关键词</th>                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 											<td  colspan=\"3\" class=\"hidden-480\">                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 												<input type=\"text\" class=\"m-wrap span12\" size=\"16\"  name=\"seoKeywords\" type=\"text\" placeholder=\"页面关键词\" >                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 										</tr>                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 										<tr>                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 											<th>页面描述</th>                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 											<td  colspan=\"3\" class=\"hidden-480\">                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 												<input type=\"text\" class=\"m-wrap span12\" size=\"16\"    name=\"seoDescription\" type=\"text\" placeholder=\"页面描述\" >                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 											</td>                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 										</tr>		                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 									</tbody>                                                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 								</table>                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 										<div class=\"form-actions\">                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 													<button class=\"btn blue\" type=\"button\" onclick=\"location.href='<%=path%>/admin/articleCategory/articleCategoryList'\">返&nbsp;&nbsp;回</button>                                                                                                                                                                                    ").append("\n");
			 sb.append(" 													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 													<button class=\"btn blue\" type=\"submit\"><i class=\"icon-ok\"></i> 确&nbsp;&nbsp;定</button>                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 										 </div>                                                                                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append(" 							     </form>                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 								                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 							</div>                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 						<!-- END EXAMPLE TABLE PORTLET-->                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 			</div>	                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 			</div>                                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 	                                                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/jquery-1.10.1.min.js\" type=\"text/javascript\"></script>                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/jquery.validate.js\" type=\"text/javascript\"></script>                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 	<script type='text/javascript' src='<%=path%>/resources/admin/js/messages_zh.js'></script>                                                                                                                                                                                                                                                                                              ").append("\n");
			 sb.append("     <!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->                                                                                                                                                                                                                                                ").append("\n");
			 sb.append("     <script src=\"<%=path%>/resources/admin/js/bootstrap.min.js\" type=\"text/javascript\"></script>                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 	<!--[if lt IE 9]>                                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/excanvas.min.js\"></script>                                                                                                                                                                                                                                                                                                                  ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/respond.min.js\"></script>                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 	<![endif]-->                                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/jquery.slimscroll.min.js\" type=\"text/javascript\"></script>                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append("     <!-- END CORE PLUGINS -->                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 	<!-- BEGIN PAGE LEVEL PLUGINS -->                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/select2.min.js\"></script>                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/jquery.dataTables.min.js\"></script>                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/DT_bootstrap.js\"></script>                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 	<!-- END PAGE LEVEL PLUGINS -->                                                                                                                                                                                                                                                                                                                                                         ").append("\n");
			 sb.append(" 	<!-- BEGIN PAGE LEVEL SCRIPTS -->                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/app.js\"></script>                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 	<script src=\"<%=path%>/resources/admin/js/table-advanced.js\"></script>                                                                                                                                                                                                                                                                                                                ").append("\n");
			 sb.append("  <script>                                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 		jQuery(document).ready(function() {                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 		   App.init();                                                                                                                                                                                                                                                                                                                                                                      ").append("\n");
			 sb.append(" 		   //TableAdvanced.init();                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 		});                                                                                                                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 		  $().ready(function() {                                                                                                                                                                                                                                                                                                                                                            ").append("\n");
			 sb.append("  		   $(\"#acForm\").validate({                                                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 				  // 定义验证规则                                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" 			    rules: {                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 			         name:{ required:true,minlength:2,maxlength:80},                                                                                                                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 			         orders: {  required: true, isDigits:true, minlength: 1,maxlength:50}                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" 			    },                                                                                                                                                                                                                                                                                                                                                                          ").append("\n");
			 sb.append(" 			     messages: {  //提示信息                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 			         name:{required: \"请输入文章分类名称\",minlength: \"文章分类名称至少需要2个字符\",maxlength:\"文章分类名称输入字符不能超过80个字符\"},                                                                                                                                                                                                                                 ").append("\n");
			 sb.append(" 			         orders: { required: \"请输入排序\",isDigits:\"排序只能输入数字\",minlength: \"排序至少需要1个字符\",maxlength:\"排序输入字符不能超过80个字符\"}                                                                                                                                                                                                                        ").append("\n");
			 sb.append(" 			        }                                                                                                                                                                                                                                                                                                                                                                       ").append("\n");
			 sb.append(" 		  });                                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" 	 });                                                                                                                                                                                                                                                                                                                                                                                    ").append("\n");
			 sb.append(" 	</script>                                                                                                                                                                                                                                                                                                                                                                               ").append("\n");
			 sb.append(" <script type=\"text/javascript\">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append(" _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append("   (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;                                                                                                                                                                                                                                                                                 ").append("\n");
			 sb.append("   ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';                                                                                                                                                                                                                                                                             ").append("\n");
			 sb.append("   var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script>                                                                                                                                                                                                                                                                                     ").append("\n");
			 sb.append("   </body>                                                                                                                                                                                                                                                                                                                                                                                   ").append("\n");
			 sb.append(" <!-- END BODY -->                                                                                                                                                                                                                                                                                                                                                                           ").append("\n");
			 sb.append(" </html>                                                                                                                                                                                                                                                                                                                                                                                     ").append("\n"); 
			 writeFile(sb.toString(), saveName);
		}




	/**
		   * <b>function:</b> 
		   * @project base_frame
		   * @package com.xcj.common.code  
		   * @fileName com.xcj.*** 
		   * @createDate Apr 18, 2014 3:18:13 PM
		   * @return void
		   * @author niu_yy
		   */
		private void getListJSPstr(String listName,String tableName,String lujing,String mokuai) {
			StringBuffer sb= new StringBuffer();
			String bname = CodeUtil.getBusessName(tableName);//业务名称和实体类的名称
			 String bmethodName=CodeUtil.lowerfristWordNo_(bname); //小写业务名称  bname 大写业务名称
			 String tableComment=tableComment(tableName);
			sb.append(" <!DOCTYPE html>                                                                                                                                                                                                                                                 ").append("\n");
			sb.append(" <%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>                                                                                                                                                                                     ").append("\n");
			sb.append(" <%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>                                                                                                                                                                                             ").append("\n");
			sb.append(" <%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>                                                                                                                                                                                           ").append("\n");
			sb.append(" <%@taglib uri=\"http://www.xiaochejiang.com/tags/pager\" prefix=\"w\"%>                                                                                                                                                                                        ").append("\n");
			sb.append(" <%                                                                                                                                                                                                                                                              ").append("\n");
			sb.append(" 	String path = request.getContextPath();                                                                                                                                                                                                                     ").append("\n");
			sb.append(" %>                                                                                                                                                                                                                                                              ").append("\n");
			sb.append(" <!--[if IE 8]> <html lang=\"en\" class=\"ie8\"> <![endif]-->                                                                                                                                                                                                   ").append("\n");
			sb.append(" <!--[if IE 9]> <html lang=\"en\" class=\"ie9\"> <![endif]-->                                                                                                                                                                                                   ").append("\n");
			sb.append(" <!--[if !IE]><!--> <html lang=\"en\"> <!--<![endif]-->                                                                                                                                                                                                          ").append("\n");
			sb.append(" <!-- BEGIN HEAD -->                                                                                                                                                                                                                                             ").append("\n");
			sb.append(" <head>                                                                                                                                                                                                                                                          ").append("\n");
			sb.append(" 	<meta charset=\"utf-8\" />                                                                                                                                                                                                                                 ").append("\n");
			sb.append(" 	<title>后台管理系统</title>                                                                                                                                                                                                                                 ").append("\n");
			sb.append(" 	<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />                                                                                                                                                                              ").append("\n");
			sb.append(" 	<meta content=\"\" name=\"description\" />                                                                                                                                                                                                                 ").append("\n");
			sb.append(" 	<meta content=\"\" name=\"author\" />                                                                                                                                                                                                                      ").append("\n");
			sb.append(" 	<!-- BEGIN GLOBAL MANDATORY STYLES -->                                                                                                                                                                                                                      ").append("\n");
			sb.append(" 	<link href=\"<%=path%>/resources/admin/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                     ").append("\n");
			sb.append(" 	<link href=\"<%=path%>/resources/admin/css/bootstrap-responsive.min.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                          ").append("\n");
			sb.append(" 	<link href=\"<%=path%>/resources/admin/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                  ").append("\n");
			sb.append(" 	<link href=\"<%=path%>/resources/admin/css/style-metro.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                       ").append("\n");
			sb.append(" 	<link href=\"<%=path%>/resources/admin/css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                             ").append("\n");
			sb.append(" 	<link href=\"<%=path%>/resources/admin/css/style-responsive.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                  ").append("\n");
			sb.append(" 	<link href=\"<%=path%>/resources/admin/css/default-main.css\" rel=\"stylesheet\" type=\"text/css\" id=\"style_color\"/>                                                                                                                                  ").append("\n");
			sb.append(" 	<link href=\"<%=path%>/resources/admin/css/uniform.default.css\" rel=\"stylesheet\" type=\"text/css\"/>                                                                                                                                                   ").append("\n");
			sb.append(" 	<!-- END GLOBAL MANDATORY STYLES -->                                                                                                                                                                                                                        ").append("\n");
			sb.append(" 	<!-- BEGIN PAGE LEVEL STYLES -->                                                                                                                                                                                                                            ").append("\n");
			sb.append(" 	<link rel=\"stylesheet\" type=\"text/css\" href=\"<%=path%>/resources/admin/css/select2_metro.css\" />                                                                                                                                                   ").append("\n");
			sb.append(" 	<link rel=\"stylesheet\" href=\"<%=path%>/resources/admin/css/DT_bootstrap.css\" />                                                                                                                                                                       ").append("\n");
			sb.append(" 	<!-- END PAGE LEVEL STYLES -->                                                                                                                                                                                                                              ").append("\n");
			sb.append(" 	<link rel=\"shortcut icon\" href=\"resources/image/favicon.ico\" />                                                                                                                                                                                       ").append("\n");
			sb.append(" 	</head>                                                                                                                                                                                                                                                     ").append("\n");
			sb.append(" 	<body class=\"page-header-fixed\">                                                                                                                                                                                                                          ").append("\n");
			sb.append(" 	<!-- BEGIN PAGE TITLE & BREADCRUMB-->                                                                                                                                                                                                                       ").append("\n");
			sb.append(" 						<h3 class=\"page-title\">                                                                                                                                                                                                               ").append("\n");
			sb.append(" 						</h3>                                                                                                                                                                                                                                   ").append("\n");
			sb.append(" 						<ul class=\"breadcrumb\">                                                                                                                                                                                                               ").append("\n");
			sb.append(" 							<li>                                                                                                                                                                                                                                ").append("\n");
			sb.append(" 								<i class=\"icon-home\"></i>                                                                                                                                                                                                     ").append("\n");
			sb.append("                                                                                                                                                                                                                                                                 ").append("\n");
			sb.append(" 								<a href=\"###\">首页</a>                                                                                                                                                                                                        ").append("\n");
			sb.append("                                                                                                                                                                                                                                                                 ").append("\n");
			sb.append(" 								<i class=\"icon-angle-right\"></i>                                                                                                                                                                                              ").append("\n");
			sb.append(" 							</li>                                                                                                                                                                                                                               ").append("\n");
			sb.append(" 							<li><a href=\"<%=path%>/"+mokuai+"/"+bmethodName+"/"+listName+"\">"+tableComment+"列表</a></li>                                                                                                                                          ").append("\n");
			sb.append(" 						</ul>                                                                                                                                                                                                                                   ").append("\n");
			sb.append(" 						<!-- END PAGE TITLE & BREADCRUMB-->                                                                                                                                                                                                     ").append("\n");
			sb.append(" 	                                                                                                                                                                                                                                                            ").append("\n");
			sb.append(" 	 					<div class=\"row-fluid\">                                                                                                                                                                                                               ").append("\n");
			sb.append(" <div class=\"span12\">                                                                                                                                                                                                                                          ").append("\n");
			sb.append(" 		<!-- BEGIN EXAMPLE TABLE PORTLET-->                                                                                                                                                                                                                     ").append("\n");
			sb.append("                                                                                                                                                                                                                                                                 ").append("\n");
			sb.append(" 						<div class=\"portlet box blue\">                                                                                                                                                                                                        ").append("\n");
			sb.append(" 							<div class=\"portlet-title\">                                                                                                                                                                                                       ").append("\n");
			sb.append(" 								<div class=\"caption\">                                                                                                                                                                                                         ").append("\n");
			sb.append(" 								<!--<i class=\"icon-globe\"></i>-->                                                                                                                                                                                             ").append("\n");
			sb.append(" 							"+tableComment(tableName)+"                                                                                                                                                                                                                       ").append("\n");
			sb.append(" 								</div>                                                                                                                                                                                                                          ").append("\n");
			sb.append(" 							<div class=\"actions\">                                                                                                                                                                                                             ").append("\n");
			sb.append(" 								                                                                                                                                                                                                                                ").append("\n");
			sb.append(" 								  <div class=\"input-prepend\">                                                                                                                                                                                                 ").append("\n");
			sb.append(" 								  <form action=\"<%=path%>/admin/articleCategory/articleCategoryList\" method=\"post\" id=\"acform\">                                                                                                                         ").append("\n");
			sb.append(" 												&nbsp;&nbsp;&nbsp;                                                                                                                                                                                              ").append("\n");
			sb.append(" 												<input class=\"m-wrap medium\" value=\"${inname}\" name=\"inname\" type=\"text\" value=\"\" placeholder=\"请输入搜索关键字\" style=\"background:#fff\"/>                                                   ").append("\n");
			sb.append(" 												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                            ").append("\n");
			sb.append(" 												<button class=\"btn green\" type=\"submit\" >搜索</button>                                                                                                                                                    ").append("\n");
			sb.append(" 								  </form>                                                                                                                                                                                                                       ").append("\n");
			sb.append(" 								 </div>                                                                                                                                                                                                                         ").append("\n");
			sb.append(" 							 </div>                                                                                                                                                                                                                             ").append("\n");
			sb.append(" 							</div>                                                                                                                                                                                                                              ").append("\n");
			sb.append(" 							<div class=\"portlet-body\">                                                                                                                                                                                                        ").append("\n");
			sb.append(" 								<table class=\"table table-striped table-bordered table-hover table-full-width\" id=\"sample_2\">                                                                                                                              ").append("\n");
			sb.append(" 									<thead>                                                                                                                                                                                                                     ").append("\n");
			sb.append(" 										<tr>                                                                                                                                                                                                                    ").append("\n");
			sb.append(" 											<th>ID</th>                                                                                                                                                                                                         ").append("\n");
			sb.append(" 											<th class=\"hidden-480\">名称</th>                                                                                                                                                                                  ").append("\n");
			sb.append(" 											<th class=\"hidden-480\">排序</th>                                                                                                                                                                                  ").append("\n");
			sb.append(" 											<th class=\"hidden-480\">等级</th>                                                                                                                                                                                  ").append("\n");
			sb.append(" 											<th class=\"hidden-480\">创建时间</th>                                                                                                                                                                              ").append("\n");
			sb.append(" 											<th class=\"hidden-480\">操作</th>                                                                                                                                                                                  ").append("\n");
			sb.append(" 										</tr>                                                                                                                                                                                                                   ").append("\n");
			sb.append(" 									</thead>                                                                                                                                                                                                                    ").append("\n");
			sb.append(" 									<tbody>                                                                                                                                                                                                                     ").append("\n");
			sb.append("                                                                                                                                                                                                                                                                 ").append("\n");
			sb.append(" 						          <c:forEach items=\"${page.result}\" var=\"ac\" varStatus=\"status\">                                                                                                                                                         ").append("\n");
			sb.append(" 											<tr>                                                                                                                                                                                                                ").append("\n");
			sb.append(" 											 <td>                                                                                                                                                                                                               ").append("\n");
			sb.append(" 												${status.index+1}                                                                                                                                                                                               ").append("\n");
			sb.append(" 											</td>                                                                                                                                                                                                               ").append("\n");
			sb.append(" 											<td>                                                                                                                                                                                                                ").append("\n");
			sb.append(" 												<span style=\"margin-left: ${ac.grade * 20}px;\">${ac.name}</span>                                                                                                                                              ").append("\n");
			sb.append(" 											</td>                                                                                                                                                                                                               ").append("\n");
			sb.append(" 											<td class=\"hidden-480\">${ac.orders}</td>                                                                                                                                                                          ").append("\n");
			sb.append(" 											<td class=\"hidden-480\">                                                                                                                                                                                           ").append("\n");
			sb.append(" 												<c:choose>                                                                                                                                                                                                      ").append("\n");
			sb.append(" 													       <c:when test=\"${ac.grade==0}\">                                                                                                                                                                     ").append("\n");
			sb.append(" 													       	一级                                                                                                                                                                                                ").append("\n");
			sb.append(" 													       </c:when>                                                                                                                                                                                            ").append("\n");
			sb.append(" 													       <c:when test=\"${ac.grade==1}\">                                                                                                                                                                     ").append("\n");
			sb.append(" 													             二级                                                                                                                                                                                           ").append("\n");
			sb.append(" 													       </c:when>                                                                                                                                                                                            ").append("\n");
			sb.append(" 													        <c:when test=\"${ac.grade==2}\">                                                                                                                                                                    ").append("\n");
			sb.append(" 													              三级                                                                                                                                                                                          ").append("\n");
			sb.append(" 													       </c:when>                                                                                                                                                                                            ").append("\n");
			sb.append(" 													       <c:otherwise>                                                                                                                                                                                        ").append("\n");
			sb.append(" 													              四级                                                                                                                                                                                          ").append("\n");
			sb.append(" 													       </c:otherwise>                                                                                                                                                                                       ").append("\n");
			sb.append(" 												</c:choose>                                                                                                                                                                                                     ").append("\n");
			sb.append(" 											</td>                                                                                                                                                                                                               ").append("\n");
			sb.append(" 											<td class=\"hidden-480\">                                                                                                                                                                                           ").append("\n");
			sb.append(" 											<fmt:formatDate value=\"${ac.createDate}\" type=\"both\" pattern=\"yyyy-MM-dd HH:mm:ss\" />                                                                                                                       ").append("\n");
			sb.append(" 											</td>                                                                                                                                                                                                               ").append("\n");
			sb.append(" 										 	<td class=\"hidden-480\">                                                                                                                                                                                           ").append("\n");
			sb.append(" 										  <a href=\"<%=path%>/admin/articleCategory/save\">添加</a>&nbsp;                                                                                                                                                       ").append("\n");
			sb.append(" 										 |&nbsp;<a href=\"<%=path%>/admin/articleCategory/${ac.id}/update\">编辑</a>&nbsp;                                                                                                                                      ").append("\n");
			sb.append(" 										 |&nbsp;<a href=\"javascript:window.openDel('<%=path%>/admin/articleCategory/${ac.id}/delete');\">删除</a>&nbsp;                                                                                                        ").append("\n");
			sb.append(" 										 </td>                                                                                                                                                                                                                  ").append("\n");
			sb.append(" 										</tr>                                                                                                                                                                                                                   ").append("\n");
			sb.append(" 							       </c:forEach>                                                                                                                                                                                                                 ").append("\n");
			sb.append(" 									</tbody>                                                                                                                                                                                                                    ").append("\n");
			sb.append(" 								</table>                                                                                                                                                                                                                        ").append("\n");
			sb.append(" 								<table class=\"table  table-full-width\" id=\"sample_2\">                                                                                                                                                                      ").append("\n");
			sb.append(" 								<w:pager pageSize=\"${page.pageSize}\" currentPage=\"${page.currentPage}\" url=\"./articleCategoryList\" totalsCount=\"${page.totalsCount}\" />                                                                                ").append("\n");
			sb.append(" 								</table>                                                                                                                                                                                                                        ").append("\n");
			sb.append(" 							</div>                                                                                                                                                                                                                              ").append("\n");
			sb.append(" 							                                                                                                                                                                                                                                    ").append("\n");
			sb.append(" 					</div>                                                                                                                                                                                                                                      ").append("\n");
			sb.append(" 						<!-- END EXAMPLE TABLE PORTLET-->                                                                                                                                                                                                       ").append("\n");
			sb.append(" 			</div>	                                                                                                                                                                                                                                            ").append("\n");
			sb.append(" 			</div>                                                                                                                                                                                                                                              ").append("\n");
			sb.append(" 	                                                                                                                                                                                                                                                            ").append("\n");
			sb.append(" 	 <script src=\"<%=path%>/resources/admin/js/jquery-1.10.1.min.js\" type=\"text/javascript\"></script>                                                                                                                                                      ").append("\n");
			sb.append("     <!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->                                                                                                                    ").append("\n");
			sb.append("    <script src=\"<%=path%>/resources/admin/js/bootstrap.min.js\" type=\"text/javascript\"></script>                                                                                                                                                            ").append("\n");
			sb.append(" 	<!--[if lt IE 9]>                                                                                                                                                                                                                                           ").append("\n");
			sb.append(" 	<script src=\"<%=path%>/resources/admin/js/excanvas.min.js\"></script>                                                                                                                                                                                      ").append("\n");
			sb.append(" 	<script src=\"<%=path%>/resources/admin/js/respond.min.js\"></script>                                                                                                                                                                                       ").append("\n");
			sb.append(" 	<![endif]-->                                                                                                                                                                                                                                                ").append("\n");
			sb.append(" 	<script src=\"<%=path%>/resources/admin/js/jquery.slimscroll.min.js\" type=\"text/javascript\"></script>                                                                                                                                                   ").append("\n");
			sb.append("    <!-- END CORE PLUGINS -->                                                                                                                                                                                                                                    ").append("\n");
			sb.append(" 	<!-- BEGIN PAGE LEVEL PLUGINS -->                                                                                                                                                                                                                           ").append("\n");
			sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/select2.min.js\"></script>                                                                                                                                                             ").append("\n");
			sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/jquery.dataTables.min.js\"></script>                                                                                                                                                   ").append("\n");
			sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/DT_bootstrap.js\"></script>                                                                                                                                                            ").append("\n");
			sb.append(" 	<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/jquery.bootstrap.teninedialog.js\"></script>                                                                                                                                           ").append("\n");
			sb.append(" 	<!-- END PAGE LEVEL PLUGINS -->                                                                                                                                                                                                                             ").append("\n");
			sb.append(" 	<script src=\"<%=path%>/resources/admin/js/app.js\"></script>                                                                                                                                                                                               ").append("\n");
			sb.append(" 	<script src=\"<%=path%>/resources/admin/js/table-advanced.js\"></script>                                                                                                                                                                                    ").append("\n");
			sb.append(" 	<script src=\"<%=path%>/resources/admin/js/ui-modals.js\"></script>                                                                                                                                                                                         ").append("\n");
			sb.append("  	<script>                                                                                                                                                                                                                                                    ").append("\n");
			sb.append(" 		jQuery(document).ready(function() {                                                                                                                                                                                                                     ").append("\n");
			sb.append(" 		   App.init();                                                                                                                                                                                                                                          ").append("\n");
			sb.append(" 		   //TableAdvanced.init();                                                                                                                                                                                                                              ").append("\n");
			sb.append(" 		});                                                                                                                                                                                                                                                     ").append("\n");
			sb.append(" 		                                                                                                                                                                                                                                                        ").append("\n");
			sb.append(" 		                                                                                                                                                                                                                                                        ").append("\n");
			sb.append(" 		function openDel(url){                                                                                                                                                                                                                                  ").append("\n");
			sb.append(" 			$.teninedialog({                                                                                                                                                                                                                                    ").append("\n");
			sb.append("                     title:'系统提示',                                                                                                                                                                                                                           ").append("\n");
			sb.append("                     content:'确认删除本条记录?',                                                                                                                                                                                                                ").append("\n");
			sb.append("                     showCloseButton:false,                                                                                                                                                                                                                      ").append("\n");
			sb.append("                     otherButtons:[\"确定\",\"取消\"],                                                                                                                                                                                                           ").append("\n");
			sb.append("                     otherButtonStyles:['btn red','btn-primary'],                                                                                                                                                                                                ").append("\n");
			sb.append("                     bootstrapModalOption:{keyboard: true},                                                                                                                                                                                                      ").append("\n");
			sb.append("                     dialogShow:function(){                                                                                                                                                                                                                      ").append("\n");
			sb.append("                          //alert('即将显示对话框');                                                                                                                                                                                                             ").append("\n");
			sb.append("                     },                                                                                                                                                                                                                                          ").append("\n");
			sb.append("                     dialogShown:function(){                                                                                                                                                                                                                     ").append("\n");
			sb.append("                        // alert('显示对话框');                                                                                                                                                                                                                  ").append("\n");
			sb.append("                     },                                                                                                                                                                                                                                          ").append("\n");
			sb.append("                     dialogHide:function(){                                                                                                                                                                                                                      ").append("\n");
			sb.append("                        // alert('即将关闭对话框');                                                                                                                                                                                                              ").append("\n");
			sb.append("                     },                                                                                                                                                                                                                                          ").append("\n");
			sb.append("                     dialogHidden:function(){                                                                                                                                                                                                                    ").append("\n");
			sb.append("                         //alert('关闭对话框');                                                                                                                                                                                                                  ").append("\n");
			sb.append("                     },                                                                                                                                                                                                                                          ").append("\n");
			sb.append("                     clickButton:function(sender,modal,index){                                                                                                                                                                                                   ").append("\n");
			sb.append("                     	if(index==0){                                                                                                                                                                                                                           ").append("\n");
			sb.append("                     		window.location.href=url;                                                                                                                                                                                                           ").append("\n");
			sb.append("                     	}                                                                                                                                                                                                                                       ").append("\n");
			sb.append("                         $(this).closeDialog(modal);                                                                                                                                                                                                             ").append("\n");
			sb.append("                     }                                                                                                                                                                                                                                           ").append("\n");
			sb.append("                 });                                                                                                                                                                                                                                             ").append("\n");
			sb.append(" 	}                                                                                                                                                                                                                                                           ").append("\n");
			sb.append(" 	</script>                                                                                                                                                                                                                                                   ").append("\n");
			sb.append(" <script type=\"text/javascript\">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);                                                                ").append("\n");
			sb.append("   _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    ").append("\n");
			sb.append("   var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>                                                                                                                                                  ").append("\n");
			sb.append(" <!-- END BODY -->                                                                                                                                                                                                                                               ").append("\n");
			sb.append(" </html>          ").append("\n");   
			writeFile(sb.toString(),listName);
		}




	/**
	   * <b>function:</b> 
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 18, 2014 3:00:44 PM
	   * @return void
	   * @author niu_yy
	   */
	private void getDetailJspStr(String filename) {
		StringBuffer sb= new StringBuffer();
		sb.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>                           ").append("\n");
		sb.append("<%                                                                                                   ").append("\n");
		sb.append("	String path = request.getContextPath();                                                             ").append("\n");
		sb.append("%>                                                                                                   ").append("\n");
		sb.append("<!doctype html>                                                                                      ").append("\n");
		sb.append("<html ng-app=\"testServiceDetail\">                                                                  ").append("\n");
		sb.append(" <head>                                                                                              ").append("\n");
		sb.append(" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">                             ").append("\n");
		sb.append(" <title>系统</title>                                                                                 ").append("\n");
		sb.append(" <!--[if lte IE 8]>                                                                                  ").append("\n");
		sb.append("<script>                                                                                             ").append("\n");
		sb.append("document.createElement('ng-include');                                                                ").append("\n");
		sb.append("document.createElement('ng-pluralize');                                                              ").append("\n");
		sb.append("document.createElement('ng-view');                                                                   ").append("\n");
		sb.append("// Optionally these for CSS                                                                          ").append("\n");
		sb.append("document.createElement('ng:include');                                                                ").append("\n");
		sb.append("document.createElement('ng:pluralize');                                                              ").append("\n");
		sb.append("document.createElement('ng:view');                                                                   ").append("\n");
		sb.append("</script>                                                                                            ").append("\n");
		sb.append("<![endif]-->                                                                                         ").append("\n");
		sb.append("<script type=\"text/javascript\"	src=\"<%=path%>/resources/admin/js/jquery-1.11.0.js\"></script>     ").append("\n");
		sb.append("<script type=\"text/javascript\"	src=\"<%=path%>/resources/admin/js/angular.js\"></script>           ").append("\n");
		sb.append("<script type=\"text/javascript\" src=\"<%=path%>/resources/admin/js/angular-resource.js\"></script>  ").append("\n");
		sb.append("<link href=\"<%=path%>/resources/admin/css/page.css\" type=\"text/css\" rel=\"stylesheet\" />        ").append("\n");
		sb.append(" <script type=\"text/javascript\">                                                                   ").append("\n");
		sb.append("	var projectName = '/' + window.location.pathname.split('/')[1];                                     ").append("\n");
		sb.append("	var save = angular.module('testServiceDetail', [ 'ngResource' ], angular.noop);                     ").append("\n");
		sb.append(" 	                                                                                                ").append("\n");
		sb.append(" 	                                                                                                ").append("\n");
		sb.append(" 	                                                                                                ").append("\n");
		sb.append("</script>                                                                                            ").append("\n");
		sb.append("		<style type=\"text/css\">                                                                       ").append("\n");
		sb.append("input.ng-invalid {                                                                                   ").append("\n");
		sb.append("	border: 1px solid red;                                                                              ").append("\n");
		sb.append("}                                                                                                    ").append("\n");
		sb.append("                                                                                                     ").append("\n");
		sb.append("input.ng-valid {                                                                                     ").append("\n");
		sb.append("	border: 1px solid green;                                                                            ").append("\n");
		sb.append("}                                                                                                    ").append("\n");
		sb.append(".ng-pristine {}                                                                                      ").append("\n");
		sb.append(".ng-dirty {}                                                                                         ").append("\n");
		sb.append(".ng-valid {}                                                                                         ").append("\n");
		sb.append(".ng-invalid {}                                                                                       ").append("\n");
		sb.append("</style>                                                                                             ").append("\n");
		sb.append("<body>                                                                                               ").append("\n");
		sb.append("<div id=\"detail\"                                                                                   ").append("\n");
		sb.append("  style=\"display: block; margin-left: auto; margin-right: auto;\">                                  ").append("\n");
		sb.append("  <table class=\"table table-hover table-nomargin table-bordered usertable dataTable\">              ").append("\n");
		sb.append("   <tr>                                                                                              ").append("\n");
		sb.append("    <td>id</td>                                                                                      ").append("\n");
		sb.append("    <td>                                                                                             ").append("\n");
		sb.append("    	 ${test.id}                                                                                     ").append("\n");
		sb.append("     </td>                                                                                           ").append("\n");
		sb.append("   </tr>                                                                                             ").append("\n");
		sb.append("                                                                                                     ").append("\n");
		sb.append("   <tr>                                                                                              ").append("\n");
		sb.append("    <td>用户名</td>                                                                                  ").append("\n");
		sb.append("    <td>                                                                                             ").append("\n");
		sb.append("    	  ${test.username}                                                                              ").append("\n");
		sb.append("     </td>                                                                                           ").append("\n");
		sb.append("   </tr>                                                                                             ").append("\n");
		sb.append("   <tr>                                                                                              ").append("\n");
		sb.append("    <td>密码</td>                                                                                    ").append("\n");
		sb.append("    <td>                                                                                             ").append("\n");
		sb.append("    	 ${test.password}                                                                               ").append("\n");
		sb.append("     </td>                                                                                           ").append("\n");
		sb.append("   </tr>                                                                                             ").append("\n");
		sb.append("  </table>                                                                                           ").append("\n");
		sb.append(" </div>                                                                                              ").append("\n");
		sb.append("                                                                                                     ").append("\n");
		sb.append("                                                                                                     ").append("\n");
		sb.append(" 	</body>                                                                                         ").append("\n");
		sb.append("</html>                                                                                              ").append("\n");     
		writeFile(sb.toString(), filename);
		
	}


		/**
	   * <b>function:</b> 生成DaoIMpl
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 18, 2014 11:09:50 AM
	   * @return void
	   * @author niu_yy
	   */
	private void generateDaoImpl(String tableName, String path,
			String packageName, String lujing, String mokuai ,String projectName) {
		String beanName = CodeUtil.getBusessName(tableName);//业务名称和实体类的名称
		String tableComment=tableComment(tableName);
		
		String fileName = path+"dao/impl/"+mokuai+"/"+beanName+"DaoImpl.java";
		//删除文件 ，假如文件存在的话
		File filedel=new File(fileName);
		filedel.delete();
		// 先创建文件
		CodeUtil.createFile(fileName);
		StringBuffer sb= new StringBuffer();
		sb.append("package ").append(packageName).append(".dao.impl."+mokuai+";");
		sb.append("\r\n");
	//	sb.append("import java.sql.PreparedStatement;").append("\n");
	//	sb.append("import java.sql.ResultSet;").append("\n");
	//	sb.append("import java.sql.SQLException;").append("\n");
		sb.append("import java.util.List;").append("\n");
		sb.append("import org.hibernate.Session;").append("\n");
		sb.append("import com.xcj.common.page.Page;").append("\n");
		sb.append("import javax.annotation.Resource;").append("\n");
		sb.append("import org.springframework.dao.DataAccessException;").append("\n");
		sb.append("import org.springframework.jdbc.core.JdbcTemplate;").append("\n");
	//	sb.append("import org.springframework.jdbc.core.PreparedStatementSetter;").append("\n");
	//	sb.append("import org.springframework.jdbc.core.RowMapper;").append("\n");
		sb.append("import org.springframework.stereotype.Component;").append("\n");
		sb.append("import com.xcj.admin.base.BaseDaoImpl;").append("\n");
		sb.append("import "+packageName+".entity."+mokuai+".").append(beanName).append(";\n");
		sb.append("import "+packageName+".dao."+mokuai+".").append(beanName).append("Dao;\n");
		
		sb.append("\n");
		
		//DaoImpl注释
		 sb.append("/**").append("\n");
		 sb.append(" *").append("\n");
		 sb.append("  * <b>function:</b>").append("  ").append(tableName);
		 sb.append(tableComment).append("\n");
		 sb.append("  * @package ").append(packageName+".dao.impl."+mokuai+".").append("\n");
		 sb.append("  * @fileName ").append(packageName+".*").append("\n");
		 sb.append("  * @createDate ").append(new Date()).append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append(" */").append("\n");
		 
		String bmethodName=CodeUtil.lowerfristWordNo_(beanName); //小写业务名称  bname 大写业务名称
		sb.append("@Component(\""+bmethodName+"DaoImpl\")").append("\n");
		sb.append("@SuppressWarnings({\"hiding\"})").append("\n");
		sb.append("public class "+beanName+"DaoImpl extends BaseDaoImpl implements "+beanName+"Dao {").append("\n");
		sb.append("\n");
		sb.append("  @Resource").append("\n");
		sb.append("  private JdbcTemplate jdbcTemplate;").append("\n");
		sb.append("\n");
		
		 //获取list注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComment);
		 sb.append("---获取list方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".dao.impl."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return"+" List<T>").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		 //获取list
		sb.append("  public <T extends "+beanName+"> List<T> getAllList() throws DataAccessException  {").append("\n");
		sb.append("		String hql = \"from "+beanName+"\";").append("\n");
		sb.append("		return super.getList(hql); ").append("\n");
		sb.append("  }").append("\n");
		sb.append("\n");
		
		
		
		//获取分页list注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComment);
		 sb.append("---获取分页list方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".dao.impl."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return"+" List<T>").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		 //获取分页list
		 sb.append("  public Page<"+beanName+">  getBy"+beanName+"Page(Page<"+beanName+"> page,"+beanName+" "+bmethodName+")  throws DataAccessException  {").append("\n");
		sb.append(" try {                                                                                                                ").append("\n");
		sb.append(" 	Session session = this.getSession();                                                                             ").append("\n");
		sb.append(" 	if("+bmethodName+".getName()!=null&&!"+bmethodName+".getName().equals(\"\")){                                      ").append("\n");
		sb.append(" 		page.setResult(session.createQuery(                                                                          ").append("\n");
		sb.append(" 				\"from \" + "+bmethodName+".getClass().getName()                                                       ").append("\n");
		sb.append(" 						+ \" where name like '%\"+"+bmethodName+".getName()+\"%' order  by orders\").setFirstResult(     ").append("\n");
		sb.append(" 				(page.getCurrentPage() - 1) * page.getPageSize())                                                    ").append("\n");
		sb.append(" 				.setMaxResults(page.getPageSize()).list());                                                          ").append("\n");
		sb.append(" 		page.setTotalsCount(Integer.parseInt(session.createQuery(                                                    ").append("\n");
		sb.append(" 				\"select count(id) as cnt from \"                                                                      ").append("\n");
		sb.append(" 						+ "+bmethodName+".getClass().getName()+\" where name like '%\"+"+bmethodName+".getName()+\"%'\") ").append("\n");
		sb.append(" 				.setMaxResults(1).uniqueResult().toString()));                                                       ").append("\n");
		sb.append(" 	}else{                                                                                                           ").append("\n");
		sb.append(" 		page.setResult(session.createQuery(                                                                          ").append("\n");
		sb.append(" 				\"from \" + "+bmethodName+".getClass().getName()                                                       ").append("\n");
		sb.append(" 						+ \" order by orders \").setFirstResult(                                                       ").append("\n");
		sb.append(" 				(page.getCurrentPage() - 1) * page.getPageSize())                                                    ").append("\n");
		sb.append(" 				.setMaxResults(page.getPageSize()).list());                                                          ").append("\n");
		sb.append(" 		page.setTotalsCount(Integer.parseInt(session.createQuery(                                                    ").append("\n");
		sb.append(" 				\"select count(id) as cnt from \"                                                                      ").append("\n");
		sb.append(" 						+ "+bmethodName+".getClass().getName())                                                      ").append("\n");
		sb.append(" 				.setMaxResults(1).uniqueResult().toString()));                                                       ").append("\n");
		sb.append(" 	}                                                                                                                ").append("\n");
		sb.append(" } catch (Exception e) {                                                                                              ").append("\n");
		sb.append(" 	throw new RuntimeException(e);                                                                                   ").append("\n");
		sb.append(" }                                                                                                                    ").append("\n");  
		 
		 sb.append("		return page; ").append("\n");
		 sb.append("  }").append("\n");
		 sb.append("\n");
		
		
		
		//根据id删除数据注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComment);
		 sb.append("---根据id删除数据方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".dao.impl."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return void").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		 //根据id删除数据
		sb.append("  public void removeById(Integer id) throws DataAccessException { ").append("\n");
		sb.append("		super.delete(\"delete from "+beanName+" t where t.id=\"+id);").append("\n");
		sb.append("  }");
		sb.append("\n");
		sb.append("\n");
		sb.append("}");
		writeFile(sb.toString(), fileName);
		
	}



		/**
	   * <b>function:</b> 生成Dao
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 18, 2014 11:09:45 AM
	   * @return void
	   * @author niu_yy
	   */
	private void generateDao(String tableName, String path, String packageName,
			String lujing, String mokuai, String projectName) {
		String beanName = CodeUtil.getBusessName(tableName);//业务名称和实体类的名称
		String fileName = path+"dao/"+mokuai+"/"+beanName+"Dao.java";
		String tableComent=tableComment(tableName);
		//删除文件 ，假如文件存在的话
		File filedel=new File(fileName);
		filedel.delete();
		// 先创建文件
		CodeUtil.createFile(fileName);
		StringBuffer sb= new StringBuffer();
		sb.append("package ").append(packageName).append(".dao."+mokuai+";");
		sb.append("\r\n");
		sb.append("import java.util.List;").append("\n");
		 sb.append("import com.xcj.common.page.Page;").append("\n");
		sb.append("import org.springframework.dao.DataAccessException;").append("\n");
		//sb.append("import java.sql.SQLException;").append("\n");
		sb.append("import "+packageName+".entity."+mokuai+".").append(beanName).append(";\n");
		sb.append("import com.xcj.admin.base.BaseDao;").append("\n");
		sb.append("\n");
		String bmethodName=CodeUtil.lowerfristWordNo_(beanName); //小写业务名称  bname 大写业务名称
		
		//Dao注释
		 sb.append("/**").append("\n");
		 sb.append(" *").append("\n");
		 sb.append("  * <b>function:</b>").append("  ").append(tableName);
		 sb.append(tableComent).append("\n");
		 sb.append("  * @package ").append(packageName+".dao."+mokuai+".").append("\n");
		 sb.append("  * @fileName ").append(packageName+".*").append("\n");
		 sb.append("  * @createDate ").append(new Date()).append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append(" */").append("\n");
		sb.append("public interface "+beanName+"Dao extends BaseDao{");
		sb.append("\n");
		sb.append("\n");
		
		
		 //获取list注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComent);
		 sb.append("---获取list方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".dao."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return"+" List<T>").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		 //获取list
		 sb.append("  public <T extends "+beanName+"> List<T> getAllList()	throws DataAccessException;");
		 sb.append("\n");
		 sb.append("\n");
		
		 
		 //获取分页list注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComent);
		 sb.append("---获取分页list方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".dao."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return"+" List<T>").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		//获取list
		sb.append("  public Page<"+beanName+">  getBy"+beanName+"Page(Page<"+beanName+"> ps,"+beanName+" "+bmethodName+")	throws DataAccessException;");
		sb.append("\n");
		sb.append("\n");
		
		
		
		 //根据id删除数据注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComent);
		 sb.append("---根据id删除数据方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".dao."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return void").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		 //根据id删除数据
		sb.append("  public void removeById(Integer id) throws DataAccessException;");
		sb.append("\n");
		sb.append("\n");
		sb.append("}");
		writeFile(sb.toString(), fileName);
		
	}



	/**
	   * <b>function:</b> 生成SErviceImpl接口
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 18, 2014 10:56:11 AM
	   * @return void
	   * @author niu_yy
	   */
	private void generateServiceImpl(String tableName, String path,
			String packageName, String lujing, String mokuai, String projectName) {
		String beanName = CodeUtil.getBusessName(tableName);//业务名称和实体类的名称
		String fileName = path+"service/impl/"+mokuai+"/"+beanName+"ServiceImpl.java";
		//删除文件 ，假如文件存在的话
		File filedel=new File(fileName);
		filedel.delete();
		// 先创建文件
		String tableComent=tableComment(tableName);
		CodeUtil.createFile(fileName);
		StringBuffer sb= new StringBuffer();
		sb.append("package ").append(packageName).append(".service.impl."+mokuai+";");
		sb.append("\r\n");
		sb.append("import java.util.List;").append("\n");
		sb.append("import javax.annotation.Resource;").append("\n");
		 sb.append("import com.xcj.common.page.Page;").append("\n");
		sb.append("import org.springframework.stereotype.Service;").append("\n");
		sb.append("import "+packageName+".service."+mokuai+".").append(beanName).append("Service;\n");
		sb.append("import "+packageName+".dao."+mokuai+".").append(beanName).append("Dao;\n");
		sb.append("import "+packageName+".entity."+mokuai+".").append(beanName).append(";\n");
		sb.append("import com.xcj.admin.base.BaseServiceImpl;").append("\n");
		sb.append("\n");
		String bmethodName=CodeUtil.lowerfristWordNo_(beanName); //小写业务名称  bname 大写业务名称
		
		
		//ServiceImpl注释
		 sb.append("/**").append("\n");
		 sb.append(" *").append("\n");
		 sb.append("  * <b>function:</b>").append("  ").append(tableName);
		 sb.append(tableComent).append("\n");
		 sb.append("  * @package ").append(packageName+".service.impl."+mokuai+".").append("\n");
		 sb.append("  * @fileName ").append(packageName+".*").append("\n");
		 sb.append("  * @createDate ").append(new Date()).append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append(" */").append("\n");
		
		sb.append("@Service(\""+bmethodName+"ServiceImpl\")").append("\n");
		sb.append("public class "+beanName+"ServiceImpl extends BaseServiceImpl implements "+beanName+"Service{").append("\n");
		sb.append("\n");
		sb.append("	@Resource(name =\""+bmethodName+"DaoImpl\")").append("\n");
		sb.append("	private "+beanName+"Dao "+bmethodName+"Dao;").append("\n");
		sb.append("\n");
		
		
		 //获取list注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComent);
		 sb.append("---获取list方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".service."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return"+" List<"+beanName+">").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		//获取list
		sb.append("  public List<"+beanName+"> getAllList() throws Exception { ").append("\n");
		sb.append("    return "+bmethodName+"Dao.getAllList(); ").append("\n");
		sb.append("   }").append("\n");
		sb.append("\n");
		
		
		//获取分页list注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComent);
		 sb.append("---获取list方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".service."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return"+" List<"+beanName+">").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		//获取list
		sb.append("  public Page<"+beanName+">  getBy"+beanName+"Page(Page<"+beanName+"> ps,"+beanName+" "+bmethodName+")  throws Exception { ").append("\n");
		sb.append("    return "+bmethodName+"Dao.getBy"+beanName+"Page(ps,"+bmethodName+"); ").append("\n");
		sb.append("   }").append("\n");
		sb.append("\n");
		
		
		//根据id删除数据注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComent);
		 sb.append("---根据id删除数据方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".service."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return void").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");		 
		 sb.append("    */").append("\n");
		 //根据id删除数据
		sb.append(" public void removeById(Integer id) throws Exception {").append("\n");
		sb.append("   "+bmethodName+"Dao.removeById(id);").append("\n");
		sb.append("\n");
		sb.append("} \n");
		
		sb.append("}");
		writeFile(sb.toString(), fileName);
	}



	/**
	   * <b>function:</b> 生成SErvice接口
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 18, 2014 10:44:48 AM
	   * @return void
	   * @author niu_yy
	   */
	private void generateService(String tableName, String path,
			String packageName, String lujing, String mokuai, String projectName) {
		String beanName = CodeUtil.getBusessName(tableName);//业务名称和实体类的名称
		String fileName = path+"service/"+mokuai+"/"+beanName+"Service.java";
		//删除文件 ，假如文件存在的话
		File filedel=new File(fileName);
		filedel.delete();
		String tableComent=tableComment(tableName);
		// 先创建文件
		CodeUtil.createFile(fileName);
		StringBuffer sb= new StringBuffer();
		sb.append("package ").append(packageName).append(".service."+mokuai+";");
		sb.append("\r\n");
		sb.append("import java.util.List;").append("\n");
		 sb.append("import com.xcj.common.page.Page;").append("\n");
		sb.append("import "+packageName+".entity."+mokuai+".").append(beanName).append(";\n");
		sb.append("import com.xcj.admin.base.BaseService;").append("\n");
		sb.append("\n");
		String bmethodName=CodeUtil.lowerfristWordNo_(beanName); //小写业务名称  bname 大写业务名称
		
		//Service注释
		 sb.append("  /**").append("\n");
		 sb.append("  *").append("\n");
		 sb.append("  * <b>function:</b>").append("  ").append(tableName);
		 sb.append(tableComent).append("\n");
		 sb.append("  * @package ").append(packageName+".service."+mokuai+".").append("\n");
		 sb.append("  * @fileName ").append(packageName+".*").append("\n");
		 sb.append("  * @createDate ").append(new Date()).append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append(" */").append("\n");
		 
		sb.append("public interface "+beanName+"Service extends BaseService{");
		sb.append("\n");
		sb.append("\n");
		
		 //获取list注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComent);
		 sb.append("---获取分页List方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".service."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return"+" List<"+beanName+">").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		//获取list
		sb.append(" public  Page<"+beanName+">  getBy"+beanName+"Page(Page<"+beanName+"> ps,"+beanName+" "+bmethodName+") throws Exception;");
		sb.append("\n");
		sb.append("\n");
		
		
		//获取list注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComent);
		 sb.append("---获取所有数据方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".service."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return"+" List<"+beanName+">").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		//获取list
		sb.append("   List<"+beanName+">  getAllList()  throws Exception;");
		sb.append("\n");
		sb.append("\n");
		
		
		//根据id删除数据注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComent);
		 sb.append("---根据id删除数据方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".service."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return void").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		//根据id删除数据
		sb.append("   void removeById(Integer id)throws Exception;");
		sb.append("\n");
		sb.append("}");
		writeFile(sb.toString(), fileName);
		
	}



	/**
	   * <b>function:</b> 生成Controller
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 17, 2014 3:33:13 PM
	   * @return void
	   * @author niu_yy
	   */
	private void generateController(String tableName, String path,
			String packageName,String lujing,String mokuai,String projectName) {
		String bname = CodeUtil.getBusessName(tableName);//业务名称和实体类的名称
		String fileName = path+"controller/"+mokuai+"/"+bname+"Controller.java";
		//删除文件 ，假如文件存在的话
		File filedel=new File(fileName);
		filedel.delete();
		// 先创建文件
		String tableComment=tableComment(tableName);
		CodeUtil.createFile(fileName);
		StringBuffer sb= new StringBuffer();
		sb.append("package ").append(packageName).append(".controller."+mokuai+";");
		sb.append("\r\n");
		 sb.append("import java.util.ArrayList;").append("\n");
		 sb.append("import java.util.List;").append("\n");
		 sb.append("import com.xcj.common.page.Page;").append("\n");
		 sb.append("import org.slf4j.Logger;").append("\n");
		 sb.append("import org.slf4j.LoggerFactory;").append("\n");
		 sb.append("import com.xcj.common.util.DateUtil;").append("\n");
		 sb.append("import javax.annotation.Resource;").append("\n");
		 sb.append("import javax.servlet.http.HttpServletRequest;").append("\n");
		 sb.append("import javax.servlet.http.HttpServletResponse;").append("\n");
		 sb.append("import org.springframework.stereotype.Controller;").append("\n");
		 sb.append("import org.springframework.ui.Model;").append("\n");
		 sb.append("import org.springframework.ui.ModelMap;").append("\n");
		 sb.append("import org.springframework.validation.annotation.Validated;").append("\n");
		 sb.append("import org.springframework.web.bind.annotation.PathVariable;").append("\n");
		 sb.append("import org.springframework.web.bind.annotation.RequestMapping;").append("\n");
		 sb.append("import org.springframework.web.bind.annotation.RequestMethod;").append("\n");
		 sb.append("import org.springframework.web.bind.annotation.ResponseBody;").append("\n");
		 sb.append("import org.springframework.web.servlet.ModelAndView;").append("\n");
		 sb.append("import com.xcj.admin.base.BaseController;").append("\n");
		 sb.append("import "+packageName+".entity."+mokuai+".").append(bname).append(";\n");
		 sb.append("import "+packageName+".service."+mokuai+".").append(bname).append("Service;\n");
		 sb.append("\n");
		 String serviceName=CodeUtil.lowerfristWordNo_(bname)+"Service";
		 String bmethodName=CodeUtil.lowerfristWordNo_(bname); //小写业务名称  bname 大写业务名称
		 
		 //controller注释
		 sb.append(" /**").append("\n");
		 sb.append("  * <b>function:</b>").append("  ").append(tableName);
		 sb.append(tableComment).append("\n");
		 sb.append("  * @package ").append(packageName+".controller."+mokuai+".").append("\n");
		 sb.append("  * @fileName ").append(packageName+".*").append("\n");
		 sb.append("  * @createDate ").append(new Date()).append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append(" */").append("\n");
		 
		 sb.append("@Controller(\""+bmethodName+"Controller\")");
		 sb.append("\n");
		 //TODO 需要修改
		 sb.append("@RequestMapping(\""+lujing+"/"+bmethodName+"\")");
		 sb.append("\n");
		 sb.append("public class "+bname+"Controller extends BaseController{");
		 sb.append("\n");
		 sb.append("\n");
		 sb.append(" private static final Logger log = LoggerFactory.getLogger("+bname+"Controller.class); ").append("\n");
		 sb.append("   @Resource(name=\""+bmethodName+"ServiceImpl\")").append("\n");
		 sb.append("   private "+bname+"Service "+serviceName+";");
		 sb.append("\n\n");	
		 /**
		 //init方法注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComment(tableName));
		 sb.append("---init方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".controller."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return ModelAndView").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");**/
		 //sb.append("    */").append("\n");
		 // init 方法
		 /**
		 String initMethod=bmethodName+"Init";
		 String initstr=(lujing+"/"+bmethodName+"list_jsp").toLowerCase();
		 sb.append("   @RequestMapping(value=\"/"+initMethod+"\",method=RequestMethod.GET)");
		 sb.append("\n");
		 sb.append("   public ModelAndView "+initMethod+"(HttpServletRequest request,HttpServletResponse response,ModelMap model) {");
		 sb.append("\n");
		 sb.append("     return new ModelAndView(\""+initstr+"\",model);");
		 sb.append("\n");
		 sb.append("   }");
		 sb.append("\n");
		 sb.append("\n");
		  **/
		 //获取数据的方法注释
		 sb.append("    /**").append("\n");
		 sb.append("     *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComment);
		 sb.append(":分页获取数据的方法");
		 sb.append("\n");
		 sb.append("    * @project ").append(projectName).append("\n");
		 sb.append("    * @package ").append(packageName+".controller."+mokuai+".").append("\n");
		 sb.append("    * @fileName ").append(packageName+".*").append("\n");
		 sb.append("    * @createDate ").append(new Date()).append("\n");
		 sb.append("    * @return List<"+bname+"> ").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("     **/").append("\n");
		 //获取数据分页的方法
		 String listtMethod=bmethodName+"List";
		 sb.append("   @RequestMapping(value=\"/"+listtMethod+"\")");
		 sb.append("\n");
		 sb.append("   public String   "+listtMethod+"(Model model,Page<"+bname+"> ps,String inname) {");
		 sb.append("\n");
		 sb.append("      try{").append("\n");
		 sb.append("\n");
		 sb.append("		"+bname+" ").append(bmethodName).append("= new "+bname+"(); ");
		 sb.append("\n");
		 sb.append("         "+bmethodName+".setName(inname);  ");
		 sb.append("\n");
		 sb.append("		Page<"+bname+"> p="+serviceName+".getBy"+bname+"Page(ps,"+bmethodName+"); ").append("\n");
		 sb.append("		model.addAttribute(\"page\", p); ");
		 sb.append("\n");
		 sb.append("		model.addAttribute(\"inname\", inname); ");
		 sb.append("\n");
		 sb.append("	  }catch (Exception e) {").append("\n");
		 sb.append("          log.error(\""+tableComment(tableName)+":获取分页对象出错：\" + e.getMessage()); ");
		 sb.append("\n");
		 sb.append("	      e.printStackTrace();").append("\n");
		 sb.append("      }").append("\n");
		 sb.append("     return  \""+lujing+"/"+listtMethod.toLowerCase()+"_jsp\";");
		 sb.append("\n");
		 sb.append("   }").append("\n");
		 sb.append("\n");
		 sb.append("\n");
		 
		 
		 
		//保存初始化的注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComment);
		 sb.append(":保存初始化的方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".controller."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return String").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("     **/").append("\n");
		 //保存初始化
		 String saveUrl=(lujing+"/"+bmethodName+"save_jsp").toLowerCase();
		 sb.append("   @RequestMapping(value=\"/save\",method=RequestMethod.GET)").append("\n");
		 sb.append("   public String save(Model model) {").append("\n");
		 sb.append("         return \""+saveUrl+"\";").append("\n");
		 sb.append("   }");
		 sb.append("\n");
		 sb.append("\n");
		 
		 
		//保存的注释
		 sb.append("    /**").append("\n");
		 sb.append("     *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("     *").append(tableName);
		 sb.append(tableComment);
		 sb.append(":保存的方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".controller."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return String").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("     **/").append("\n");
		 //保存
		 String saveingURL=(lujing+"/"+bmethodName+"s_jsp").toLowerCase();;
		 sb.append("   @RequestMapping(value=\"/save\",method=RequestMethod.POST)").append("\n");
		 sb.append("   public String save(@Validated "+bname+" "+bmethodName+",Model model) {").append("\n");
		 sb.append("      try{").append("\n");
		 sb.append("           "+bmethodName+".setCreateDate(DateUtil.getCurrentTimeByDate());  ").append("\n");
		 sb.append("           "+bmethodName+".setModifyDate(DateUtil.getCurrentTimeByDate());  ").append("\n");
		 sb.append("          "+serviceName+".save("+bmethodName+");").append("\n");
		 sb.append("	  }catch (Exception e) {").append("\n");
		 sb.append("          log.error(\""+tableComment(tableName)+":保存对象出错：\" + e.getMessage()); ").append("\n");
		 sb.append("	      e.printStackTrace();").append("\n").append("\n");
		 sb.append("      }").append("\n");
		 sb.append("     return  \"redirect:"+lujing+"/"+listtMethod.toLowerCase()+"_jsp\";").append("\n");
		 sb.append("     }");
		 sb.append("\n");
		 sb.append("\n");
		 
		 
		 //查看详细信息的注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComment);
		 sb.append("---查看详细信息的方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".controller."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return String").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		 //查看详细信息
		 String detailURL=(lujing+"/"+bmethodName+"detail_jsp").toLowerCase();;
		 sb.append("   @RequestMapping(value=\"/{id}\",method=RequestMethod.GET)").append("\n");
		 sb.append("   public String detail(@PathVariable Integer id,Model model) {").append("\n");
		 sb.append("     "+bname+" "+bmethodName+"=new "+bname+"(); ").append("\n");
		 sb.append("      try{").append("\n");
		 sb.append("           "+bmethodName+"="+serviceName+".getById("+bname+".class, id);").append("\n");
		 sb.append("			model.addAttribute(\""+bmethodName+"\", "+bmethodName+"); ").append("\n");
		 sb.append("	  }catch (Exception e) {").append("\n");
		 sb.append("          log.error(\""+tableComment(tableName)+":查看对象出错：\" + e.getMessage()); ").append("\n");
		 sb.append("	      e.printStackTrace();").append("\n");
		 sb.append("      }").append("\n");
		 sb.append("         return \""+detailURL+"\";").append("\n");
		 sb.append("     }");
		 sb.append("\n");
		 sb.append("\n");
		 sb.append("\n");
		 sb.append("\n");
		 
		 
		 //更新初始化的注释
		 sb.append("   /**").append("\n");
		 sb.append("     *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComment);
		 sb.append(":更新初始化的方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".controller."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return String").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		 //更新初始化方法
		 String updateInitURL=(lujing+"/"+bmethodName+"edit_jsp").toLowerCase();;
		 sb.append("   @RequestMapping(value=\"/{id}/update\",method=RequestMethod.GET)").append("\n");
		 sb.append("   public String update(@PathVariable Integer id,Model model) {").append("\n");
		 sb.append("     "+bname+" "+bmethodName+"=new "+bname+"(); ").append("\n");
		 sb.append("      try{").append("\n");
		 sb.append("           "+bmethodName+"="+serviceName+".getById("+bname+".class, id);").append("\n");
		 sb.append("	     }catch (Exception e) {").append("\n");
		 sb.append("             log.error(\""+tableComment(tableName)+":更新对象初始化对象出错：\" + e.getMessage()); ").append("\n");
		 sb.append("	         e.printStackTrace();").append("\n");
		 sb.append("         }").append("\n");
		 sb.append("          model.addAttribute(\""+bmethodName+"\","+bmethodName+"); ").append("\n");
		 sb.append("         return \""+updateInitURL+"\";").append("\n");
		 sb.append("     }");
		 sb.append("\n");
		 sb.append("\n");
		 
		 
		 //更新数据的注释
		 sb.append("     /**").append("\n");
		 sb.append("      *").append("\n");
		 sb.append("      * <b>function:</b>");
		 sb.append("      ").append(tableName);
		 sb.append(tableComment);
		 sb.append(":更新数据的方法");
		 sb.append("\n");
		 sb.append("      * @project ").append(projectName).append("\n");
		 sb.append("      * @package ").append(packageName+".controller."+mokuai+".").append("\n");
		 sb.append("      * @fileName ").append(packageName+".*").append("\n");
		 sb.append("      * @createDate ").append(new Date()).append("\n");
		 sb.append("      * @return ").append(bname).append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("      */").append("\n");
		 //更新数据
		 sb.append("   @RequestMapping(value=\"/updata\",method=RequestMethod.POST)");
		 sb.append("\n");
		 sb.append("   public String  updata(@Validated "+bname+" "+bmethodName+",Model model) {");
		 sb.append("\n");
		 sb.append("     "+bname+" old"+bmethodName+"=new "+bname+"(); ").append("\n");
		 sb.append("      try{").append("\n");
		 sb.append("           old"+bmethodName+"="+serviceName+".getById("+bname+".class, "+bmethodName+".getId());").append("\n");
		 sb.append("           "+bmethodName+".setCreateDate( old"+bmethodName+".getCreateDate());  ").append("\n");
		 sb.append("           "+bmethodName+".setModifyDate(DateUtil.getCurrentTimeByDate());  ").append("\n");
		 sb.append("  			"+serviceName+".update("+bmethodName+"); ").append("\n");
		 sb.append("	     }catch (Exception e) {").append("\n");
		 sb.append("             log.error(\""+tableComment(tableName)+":更新对象出错：\" + e.getMessage()); ").append("\n");
		 sb.append("	         e.printStackTrace();").append("\n");
		 sb.append("         }").append("\n");
		 sb.append("     return  \"redirect:"+lujing+"/"+listtMethod.toLowerCase()+"_jsp\";");
		 sb.append("     }");
		 sb.append("\n");
		 sb.append("\n");
		 
		 //删除单条数据的注释
		 sb.append("   /**").append("\n");
		 sb.append("    *").append("\n");
		 sb.append("     * <b>function:</b>");
		 sb.append("  ").append(tableName);
		 sb.append(tableComment);
		 sb.append("---删除单条数据的方法");
		 sb.append("\n");
		 sb.append("     * @project ").append(projectName).append("\n");
		 sb.append("     * @package ").append(packageName+".controller."+mokuai+".").append("\n");
		 sb.append("     * @fileName ").append(packageName+".*").append("\n");
		 sb.append("     * @createDate ").append(new Date()).append("\n");
		 sb.append("     * @return String").append("\n");
		 sb.append(CodeUtil.AUTHOR_NAME).append("\n");
		 sb.append("    */").append("\n");
		//删除单条数据
		 sb.append("   @RequestMapping(value=\"/{id}/delete\",method=RequestMethod.GET)").append("\n");
		 sb.append("   public String update(@PathVariable Integer id) {").append("\n");
		 sb.append("      try{").append("\n");
		 sb.append("           "+serviceName+".removeById(id);").append("\n");
		 sb.append("	     }catch (Exception e) {").append("\n");
		 sb.append("             log.error(\""+tableComment(tableName)+":删除对象出错：\" + e.getMessage()); ").append("\n");
		 sb.append("	         e.printStackTrace();").append("\n");
		 sb.append("         }").append("\n");
		 sb.append("     return  \"redirect:"+lujing+"/"+listtMethod.toLowerCase()+"_jsp\";").append("\n");
		 sb.append("     }");
		 
 		 /////////////////////////////////////////////////////////////////////
		 sb.append("\n");
		 sb.append("\n");
		sb.append("}");
		writeFile(sb.toString(), fileName);
	}


	/**
	   * <b>function:</b> 生成实体类
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 17, 2014 11:47:02 AM
	   * @return void
	   * @author niu_yy
	 */
	public  void generateEntity(String tableName,String path,String packageName,String mokuai) {
		String bname = CodeUtil.getBusessName(tableName);
		String fileName = path+"entity/"+mokuai+"/"+bname+".java";
		File filedel=new File(fileName);
		filedel.delete();
		CodeUtil.createFile(fileName);
		StringBuffer sb = new StringBuffer();
		sb.append("package ").append(packageName).append(".entity."+mokuai+";");
		sb.append("\r\n");
		sb.append("import javax.persistence.Column; ");
		sb.append("\n");
		sb.append("import javax.persistence.Entity; ");
		sb.append("\n");
		sb.append("import javax.persistence.SequenceGenerator;");
		sb.append("\n");
		sb.append("import javax.persistence.Table;");
		sb.append("\n");
		sb.append("import com.xcj.admin.base.BaseEntity;");
		
		
		sb.append("\n");
		sb.append("@Entity(name=\""+bname+"\")");
		sb.append("\n");
		sb.append("@Table(name=\""+tableName+"\")");
		sb.append("\n");
		sb.append("@SequenceGenerator(name=\"sequenceGenerator\",sequenceName=\"secquence_"+tableName+"\")");
		sb.append("\n");
		sb.append("public class " + bname + " extends BaseEntity  implements java.io.Serializable{");
		sb.append("\n");
		sb.append("   private static final long serialVersionUID = 1L;");
		BuildJavaCode code = new BuildJavaCode();
		List<DataModel> list = code.getDBinfo(tableName);
		//溢出元素是0 的ID ID已经在base 里边生成了
		list.remove(0);
		//生成属性
		for (int i = 0; i < list.size(); i++) {
			DataModel d = list.get(i);
			sb.append("\n");
			sb.append("    private ").append(
					CodeUtil.getJavaTypeBydbType(d.getFieldtype())+ "  "
							+ CodeUtil.getFieldName(d.getFieldname()) + ";");
			sb.append("//");sb.append(d.getRemarks());
			sb.append("\n");
		}
		
		// 生成get set 方法
		for (int i = 0; i < list.size(); i++) {
			DataModel dm = list.get(i);
			sb.append("\n");
			sb.append("    @Column(name=\""+dm.getFieldname()+"\",nullable = "+dm.getNullable()+", length="+dm.getFieldLength()+")");
			sb.append("\n");
			sb.append("    public ").append(CodeUtil.getJavaTypeBydbType(dm.getFieldtype())+" ");
			sb.append(" get").append(CodeUtil.UpfristWordNo_(CodeUtil.getFieldName(dm.getFieldname())));
			sb.append("()");
			sb.append("{");
			sb.append("\n");
			sb.append("       return ").append(CodeUtil.getFieldName(dm.getFieldname())).append(";");
			sb.append("\n");
			sb.append("    }");
			sb.append("\n");
			sb.append("\n");
			
			sb.append("    public ").append(" void ");
			sb.append(" set").append(CodeUtil.UpfristWordNo_(CodeUtil.getFieldName(dm.getFieldname())));
			sb.append("(").append(CodeUtil.getJavaTypeBydbType(dm.getFieldtype())).append(" "+CodeUtil.getFieldName(dm.getFieldname())).append(")");
			sb.append("{");
			sb.append("\n");
			sb.append("       this.").append(CodeUtil.getFieldName(dm.getFieldname())).append("=").append(CodeUtil.getFieldName(dm.getFieldname())).append(";");
			sb.append("\n");
			sb.append("    }");
			sb.append("\n");
			sb.append("\n");
		}
		sb.append("}");
		writeFile(sb.toString(), fileName);
	}
	
	

	/**
	 * <b>function:</b> 写内容进入文件
	 * @project base_frame
	 * @package com.xcj.common.code  
	 * @fileName com.xcj.*** 
	 * @createDate Apr 16, 2014 5:59:27 PM
	 * @return void
	 * @author niu_yy
	 */
	private static void writeFile(String content, String filename) {
		FileWriter writer = null;
		try {
			 //设置输出编码，解决中文乱码  
		    OutputStreamWriter filerWriter = new OutputStreamWriter(new FileOutputStream(filename,true),"UTF-8");  
		    //FileWriter filerWriter = new FileWriter(file, true); // 追加  
		    BufferedWriter bufWriter = new BufferedWriter(filerWriter);  
		    bufWriter.write(content);  
		    bufWriter.newLine();  
		    bufWriter.close();  
		    filerWriter.close();  
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	 
	/**
	   * <b>function:</b> 取得表结构
	   * @project base_frame
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 17, 2014 12:17:24 PM
	   * @return List<DataModel>
	   * @author niu_yy
	 */
	public   List<DataModel> getDBinfo(String tableName_old) {
		List<DataModel> list = new ArrayList<DataModel>();
		Connection conn = DataSource.getConnection();
		try {
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet resultSet = dbmd.getTables(null, "%", "%",
					new String[] { "TABLE" });
			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				//System.out.println(tableName);
				if (tableName.equals(tableName_old)) {
					//ResultSet rs =getConnection.getMetaData().getColumns(null, getXMLConfig.getSchema(),tableName.toUpperCase(), "%");//其他数据库不需要这个方法的，直接传null，这个是oracle和db2这么用
					ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
					//System.out.println("表名：" + tableName + "\t\n表字段信息：");
					while (rs.next()) {
						DataModel model = new DataModel();
						model.setFieldname(rs.getString("COLUMN_NAME"));
						model.setFieldLength(Integer.valueOf(rs.getString("COLUMN_SIZE")));
						model.setFieldtype(rs.getString("TYPE_NAME"));
						model.setRemarks(rs.getString("REMARKS"));
						model.setNullable(rs.getInt("NULLABLE"));
						list.add(model);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataSource.closeDB(conn);
		} finally {
			DataSource.closeDB(conn);
		}
		return list;
	}

	
	/**
	 * 
	   * <b>function:</b> //根据表名获取表的注释
	   * @project base
	   * @package com.xcj.common.code  
	   * @fileName com.xcj.*** 
	   * @createDate Aug 6, 2014 10:36:55 AM
	   * @return String
	   * @author dapeng_wu
	 */
    public String tableComment(String tableName){
    	try {
    		List tables = getAllTableName();
            Map tablesComment = getCommentByTableName(tables);
            Set names = tablesComment.keySet();
            Iterator iter = names.iterator();
            String comment="";
            while(iter.hasNext()) {
                    String name = (String)iter.next();
                    String tableComment = tablesComment.get(name).toString();
                   // System.out.println(tableComment);
                   // System.out.println("Table Name: " + name + ", Comment: " + new String(tableComment.getBytes("ISO-8859-1"), "gbk"));
                   if(name.equals(tableName)){
                	   comment = tableComment;
                   }
            }
            return comment;
		} catch (Exception e) {
			System.out.println("tableAnnotation获取表名注释错误!");
			e.printStackTrace();
			return "";
		}
		
    }
	
	 public static Map getCommentByTableName(List tableName) throws Exception {
	        Map map = new HashMap();
	        Connection conn = DataSource.getConnection();
	        Statement stmt = conn.createStatement();
	        for(int i = 0; i < tableName.size(); i++) {
	                String table = (String)tableName.get(i);
	                ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + table);
	                if(rs != null && rs.next()) {
	                        String create = rs.getString(2);
	                        String comment = parse(create);
	                        map.put(table, comment);
	                }
	                rs.close();
	        }
	        stmt.close();
	        conn.close();
	        return map;
	}
	
	public static List getAllTableName() throws Exception{
	        List tables = new ArrayList();
	        Connection conn = DataSource.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SHOW TABLES ");
	        while(rs.next()) {
	                String tableName = rs.getString(1);
	                tables.add(tableName);
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	        return tables;
	}
	
	public static String parse(String all) {
	        String comment = null;
	        int index = all.indexOf("COMMENT='");
	        if(index < 0) {
	                return "";
	        }
	        comment = all.substring(index+9);
	        comment = comment.substring(0,comment.length() - 1);
	        return comment;
	}
}
