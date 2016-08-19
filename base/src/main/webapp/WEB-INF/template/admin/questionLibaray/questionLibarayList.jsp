<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.xiaochejiang.com/tags/pager" prefix="w"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<meta name="keywords" content="关键字">
		<meta name="description" content="描述信息">
		<title>小车匠内容中心</title>
		<link href="<%=path%>/resources/admin/css/bootstrap.css" rel="stylesheet">
		<link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet">
		<script src="<%=path%>/resources/admin/js/jquery-2.1.3.min.js"></script>
		<script src="<%=path%>/resources/admin/js/bootstrap.min.js"></script>
		<link href="<%=path%>/resources/admin/js/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    	<script src="<%=path%>/resources/admin/js/uploadify/jquery.uploadify.js" type="text/javascript"></script>
		<link href="<%=path%>/resources/admin/css/myDiv.css" rel="stylesheet" />
		<script type="text/javascript">
 
		var button_flag = true;
		/* 全选*/
		function checkAll(who, obj) {
			var curCheckBox = document.getElementsByName(who);
			for (i = 0; i < curCheckBox.length; i++) {
				curCheckBox.item(i).checked = obj.checked;
			}
			if (button_flag) {
					$("#btnDel").removeAttr("disabled");
				} else {
					$("#btnDel").attr("disabled", "disabled");
				}
			button_flag = !button_flag;
		}
		
		/**
		 * 批量删除
		 */
		function deleteBatch() {
			//获取选中的id拼接
			var str = "";
			var courseNumber="";
			var type = "";
			$("input[name='checkbox']:checked").each(function() {
				str += this.value + ",";
			});
			$("input[name='checkbox']:checked").each(function() {
				 courseNumber += $(this).parent().next().next().next().text()+ ",";
			});
			$("input[name='checkbox']:checked").each(function() {
				 type += $(this).parent().next().next().next().next().text().replace(/\s+/g, "")+ ",";
			});
			var ids = str.substr(0, str.length - 1);
			var courseNumbers =  courseNumber.replace(/\s+/g, "");
			var types = type.replace(/\s+/g, "");
			if(ids.length>0){
				 
				$.ajax( { //一个Ajax过程 
						type : "get",
						url : "<%=path%>/admin/questionLibaray/delete",
						async:false,
						data : {
							"ids" : ids,
							"courseNumber" : courseNumbers,
							"type" : types
						},
						success : function(data) {
							  if(data.data=="success"){
								 $(".modal-body").html("删除成功");
							 }else{
								  $(".modal-body").html(data.data+"数据有关联，不能删除！");
							 }
						},
						error : function() {
						}
					});
				
			} 
			
		}
		
		function checkboxs(){
				if($("[name=checkbox]:checked").length < 1){
					$("#btnDel").attr("disabled", "disabled");
				} else {
					$("#btnDel").removeAttr("disabled");
				}
			}
		
		 $(function () {
            $("#uploadify").uploadify({ //初始化函数
					'buttonText' : '请选择',  
                    'height' : 30,  
                    'swf': '/resources/admin/js/uploadify/uploadify.swf',//flash文件位置，注意路径              
			 		'uploader':'<%=path%>/admin/questionLibaray/addCourse',//后台处理的请求 
                    'width' : 120,  
                    'auto':true,//是否自动上传   
                    'fileObjName' : 'file',  
                    'onUploadSuccess': function (file, data, response) {
	                     $("#tishi").html(data);
                	}
            });
        });
        
		 //如果没选中复选框则不弹出删除框
			function delCourse(obj){
				if($("[name=checkbox]:checked").length < 1){
					$(obj).attr("data-target","");
				}else{
					$(obj).attr("data-target","#myModalDelete");
				}
			}
		 
         function addsx(){
        	location.href = "<%=path%>/admin/questionLibaray/questionLibarayList"	
        }
         
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<jsp:include page="../top.jsp" >
				<jsp:param value="h" name="MeauTag"/>
				</jsp:include>
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<ol class="breadcrumb">
							<li>
								<a href="#">首页</a>
							</li>
							<li class="active">
								题库
							</li>
						</ol>
					</div>
					<div class="btn-toolbar" role="toolbar" >
						<div class="btn-group">
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#tagAdd">
								新增课题
							</button>
							<button type="button" id="btnDel" class="btn btn-info" data-toggle="modal" onclick="delCourse(this)"  data-target="#myModalDelete" disabled="disabled">
								删除
							</button>
							 
						</div>
						<div class="btn-group" style="float: right; padding-right: 60px;">
							<form method="post" action="<%=path%>/admin/questionLibaray/questionLibarayList" name="searchPagerForm">
								<c:if test='${search == null || search == ""}'>
								<input class="form-control" name="search" type="text" id="search"
									value="" placeholder="名称" style="background: #fff" />
								</c:if>
								<c:if test='${search != null && search != ""}'>
								<input class="form-control" name="search" type="text" id="search"
									value="${search}" style="background: #fff" />
								</c:if>
								<input name="accateid" type="hidden" id="accateid" value="" />
								<input type="submit" class="btn btn-info" value="搜索"
									style="position: absolute; right: 0px; top: 0px;"/>
							</form>
						</div>
					</div>

					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>
										<input type="checkbox" onclick="checkAll('checkbox',this)">
									</th>
									<th>
										序号
									</th>
									<th>
										名称
									</th>
									<th>
										课程编号
									</th>
									<th>
										课程类型
									</th>
									<th>
										播放时常
									</th>
									<th>
										难度系数
									</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.result}" var="course" varStatus="status">
									<tr>
										<td>
											<input type="checkbox" onclick="checkboxs()" name="checkbox" value="${course.id}"/>
										</td>
										<td>
											${status.index+1}
										</td>
										<td>
											${course.name}
										</td>
										<td>
											${course.courseNumber}
										</td>
										<td>
											<c:if test="${course.type==1}">sbt</c:if>
											<c:if test="${course.type==2}">wbt</c:if>
											<c:if test="${course.type==3}">emu</c:if>
											<c:if test="${course.type==4}">dmc</c:if>
										</td>
										<td>
											${course.duration}
										</td>
										<td>
											${course.difficulty}
										</td>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<!--删除窗口开始-->
		<div class="modal fade" id="myModalDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" id="cancelCourse">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="myModalLabel">您是否要删除数据</h4>
			  </div>
			  <div class="modal-body" align="center">
					<button type="button"  class="btn btn-primary" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-default"  onclick="deleteBatch()">确定</button>
			  </div>
			  <div class="modal-footer">
				<button type="button"  class="btn btn-primary" data-dismiss="modal" onclick="addsx()">关闭</button>
			  </div>
			</div>
		  </div>
		</div>
		<!--删除窗口结束-->
		<!--新增弹出窗口开始-->
		 
			<div class="modal fade" id="tagAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								新增
							</h4>
						</div>
						<div class="modal-body">
							
							<div id="passwordDiv" class="form-group has-feedback">
								<label class="control-label" for="icon">
									
								</label>
								 
								<input type="file" name="fileName" id="uploadify" />  
<%--    							<a href="javascript:$('#uploadify').uploadify('upload', '*')">上传文件</a> | --%>
<%--    							<a href="javascript:$('#uploadify').uploadify('stop')">停止上传!</a>  --%>
								<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
								<div id="tishi" align="center" style="color:#F00"></div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="addsx()">
								关闭
							</button>
							 
							</button>
						</div>
					</div>
				</div>
			</div>
		 
		<!--新曾窗口结束-->

		<div class="modal-footer"></div>
		</div>
		<!--删除窗口结束-->
		<!--内容选项窗口开始-->
		 
		</div>
		<w:pager pageSize="${page.pageSize}" currentPage="${page.currentPage}"
			url="./questionLibarayList" totalsCount="${page.totalsCount}" />
	</body>
	 
</html>