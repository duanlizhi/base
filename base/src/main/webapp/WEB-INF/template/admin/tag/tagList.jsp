<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.xiaochejiang.com/tags/pager" prefix="w"%>
<%
	String path = request.getContextPath();
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
		<link href="<%=path%>/resources/admin/css/bootstrap.css" rel="stylesheet"/>
		<link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet"/>
		<!-- 校验CSS -->
		<link href="<%=path%>/resources/admin/css/tip.css" rel="stylesheet"/>
		<script src="<%=path%>/resources/admin/js/jquery-2.1.3.min.js"> </script>
		<script src="<%=path%>/resources/admin/js/bootstrap.min.js"> </script>
		<script src="<%=path%>/resources/admin/js/jquery.validate.js" type="text/javascript"></script>
		<script type='text/javascript' src='<%=path%>/resources/admin/js/messages_zh.js'></script>
		<script src="<%=path%>/resources/admin/js/common.js" type="text/javascript"></script>
		<script type="text/javascript">
//		获取浏览器的基本信息
		var maxsize = 100*1024;//100kb
		var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过100kb，建议使用IE、FireFox、Chrome浏览器。";
		var  browserCfg = {};
		var ua = window.navigator.userAgent;
		if (ua.indexOf("MSIE")>=1){
			browserCfg.ie = true;
		}else if(ua.indexOf("Firefox")>=1){
			browserCfg.firefox = true;
		}else if(ua.indexOf("Chrome")>=1){
			browserCfg.chrome = true;
		}
		//校验图片大小
	 jQuery.validator.addMethod("checkImgSize", function(value, element) { 
		 if(value!=""){
		     var obj_file = document.getElementById("icon");
		      var filesize = 0;
			 	if(browserCfg.firefox || browserCfg.chrome ){
			 		filesize = obj_file.files[0].size;
			 	}else if(browserCfg.ie){
			 		var obj_img = document.getElementById('icon');
			 		obj_img.dynsrc=obj_file.value;
			 		filesize = obj_img.fileSize;
			 	}else{
			 		alert(tipMsg);
			   	return;
			 	}
			 	
			 	if(filesize==-1){
			 		alert(tipMsg);
			 		return false;
			 	}else if(filesize>maxsize){
			 		return false;
				}else{
			 		return true;
				}
		 }
  	}, "文件必须小于100Kb"); 
		
		
 //    校验权重输入值的大小
	 jQuery.validator.addMethod("vlidateValue", function(value, element) { 
		 if(value!=""){
			 if(value>0&&value<1000){
				 return true;
			 }else{
				 return false;
			 }
		 }
  	}, "权重必须大于1小于1000"); 
 
 
 //    校验标签名称是否重复
	 jQuery.validator.addMethod("nameValidate", function(value, element) { 
		var flag=false;
		$.ajax( { //一个Ajax过程 
				type : "get",
				url : "<%=path%>/admin/tag/nameValidate",
				data : {
					"name" : value.trim()
				},
				success : function(data) {
					if(data=="0"){
						flag=false;
					}else{
						flag=true;
					}
				}
			});
		return flag;
  	}, "亲，标签名称重复了，请换个名称"); 
 
 
 //    校验标签名称是否重复
	 jQuery.validator.addMethod("nameEditValidate", function(value, element) { 
		var editName= $("#editName").val();
		$.ajax( { //一个Ajax过程 
				type : "get",
				url : "<%=path%>/admin/tag/nameEditValidate",
				data : {
					"name" : value,
					"editName" : editName
				},
				success : function(data) {
					if(data.data=="1"){
						return true;
					}else{
						return false;
					}
				},
				error : function() {
					return false;
				}
			});
  	}, "亲，标签名称重复了，请换个名称"); 
 
 
		//校验方法 
		$().ready(function() {
			$("#saveTagForm").validate({ 
			 rules: { 
				name:{required:true,minlength:1,maxlength:100,specialCharValidate:true}, 
				orders:{required:true,isInteger:true ,vlidateValue:true}, 
				icon:{validateImg:true}
			 }, 
			messages: {  //提示信息
			    name: { required: "请输入标签名称",minlength: "标签名称至少需要2个字符",maxlength:"标签名称输入字符不能超过100个字符"},
			    orders: { required: "请输入权重值",isInteger:"权重必须为数字"},
			    icon:{validateImg:"图片格式不正确，支持jpg|gif|png|jpeg格式" }
				}
			 });
		  $("#editTagForm").validate({ 
			 rules: { 
				name:{required:true,minlength:1,maxlength:100,specialCharValidate:true}, 
				orders:{required:true,isInteger:true ,vlidateValue:true},
				icon:{validateImg:true}
			 }, 
		     messages: {  //提示信息
		     name: { required: "请输入标签名称",minlength: "标签名称至少需要2个字符",maxlength:"标签名称输入字符不能超过100个字符"},
		     orders: { required: "请输入权重值",isInteger:"权重必须为数字"},
		     icon:{validateImg:"图片格式不正确，支持jpg|gif|png|jpeg格式" }
			}
		 });
     });
		
		
	//编辑功能传参
	function editTag(id) {
		$.ajax( { //一个Ajax过程 
				type : "get",
				url : "<%=path%>/admin/tag/updateById",
				data : {
					"id" : id
				},
				success : function(data) {
					var name = data.data.name;
					var id = data.data.id;
					var orders = data.data.orders;
					var icon = data.data.icon;
					$("#editName").attr("value", name);
					$("#editOrders").attr("value", orders);
					$("#editTagId").attr("value", id);
					$("#editIcon").attr("src", "<%=path%>"+icon);
				},
			});
	}

	/**
	 * 成员变量，作用，当全选的时候按钮可点击，否则是不可点击状态*/
	var button_flag = true;
	/**
	 * 全选*/
	function checkAll(who, obj) {
		var curCheckBox = document.getElementsByName(who);
		for (i = 0; i < curCheckBox.length; i++) {
			curCheckBox.item(i).checked = obj.checked;
		}
		if (button_flag) {
			$("#batch_delete").attr("disabled", false);
		} else {
			$("#batch_delete").attr("disabled", "disabled");
		}
	button_flag = !button_flag;
	}

	/**
	 * 批量删除
	 */
	function deleteBatch() {
	//获取选中的id拼接
	var str = "";
	$("input[name='checkbox']:checked").each(function() {
		str += this.value + ",";
	})
	var ids = str.substr(0, str.length - 1);
		$.ajax( { //一个Ajax过程 
					type : "get",
					url : "<%=path%>/admin/tag/delete",
					data : {
						"ids" : ids
					},
					success : function(data) {
						//alert(data.data)
						location.href = "<%=path%>/admin/tag/tagList"
					},
					error : function() {
					}
		});
}
	
/**
 * 搜索
 * */
function search(){
	var searchByName=$("#searchByName").val();
	if(searchByName==null||searchByName==""){
		return false;
	}
	location.href="<%=path%>/admin/tag/tagList?screening="+searchByName;
}

/**
 * 按钮校验
 * */
function jiance(){
	if($("input[name='checkbox']:checked").length>=1){
		$("#batch_delete").attr("disabled", false);
	}else{
		$("#batch_delete").attr("disabled", "disabled");
	}
}

</script>
	</head>
	<body> 
		<div class="container-fluid">
			<div class="row">
				<jsp:include page="../top.jsp">
					<jsp:param value="d" name="MeauTag"/>
				</jsp:include>
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<ol class="breadcrumb">
							<li>
								<a href="<%=path%>/admin/">系统管理</a>
							</li>
							<li class="active">
								标签管理
							</li>
						</ol>
					</div>
					<div class="btn-toolbar" role="toolbar">
						<div class="btn-group">
							<button type="button" class="btn btn-info" data-toggle="modal"
								data-target="#tagAdd">
								新增
							</button>
							<button type="button" class="btn btn-info"   id="batch_delete"  disabled="disabled"  data-toggle="modal" data-target="#myModal">
								删除
							</button>
						</div>
						<div class="btn-group" style="float: right; padding-right: 60px;">
							<input class="form-control"   name="tag.name" type="text" id="searchByName"
								  placeholder="请输入名称" style="background: #fff" value="${tagName}"/>
							<button type="button" class="btn btn-info"
								style="position: absolute; right: 0px; top: 0px;" onclick="search();">
								搜索
							</button>
						</div>
					</div>

					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>
										<input type="checkbox" onclick="checkAll('checkbox',this)">
									</th>
									<th>
										序号
									</th>
									<th>
										标签名称
									</th>
									<th>
										图标
									</th>
									<th>
										权重等级
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.result}" var="tag" varStatus="status">
									<tr>
										<td>
											<input type="checkbox" name="checkbox" value="${tag.id}" onclick="jiance()">
										</td>
										<td>
											${status.index+1}
										</td>
										<td>
											${tag.name}
										</td>
										<td>
											<img src="<%=path%>${tag.icon}" alt="" style="width: 30px;height: 30px">
										</td>
										<td>
											${tag.orders}
										</td>
										<td>
											<button type="button" class="btn btn-info btn-xs"
												data-toggle="modal" data-target="#tagEdit"
												onclick="editTag('${tag.id}')">
												编辑
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!--新增弹出窗口开始-->
		<form action="<%=path%>/admin/tag/save"   id="saveTagForm"  method="post"  enctype ="multipart/form-data">
			<div class="modal fade" id="tagAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								新增标签
							</h4>
						</div>
						<div class="modal-body">
							<div id="passwordDiv" class="form-group has-feedback">
								<label class="control-label" for="name">
									名称：
								</label>
								<input type="text" class="form-control" id="name" placeholder="请输入标签名称" name="name">
								<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
							</div>
							<div id="passwordDiv" class="form-group has-feedback">
								<label class="control-label" for="orders">
									权重等级：
								</label>
								<input type="text" class="form-control" id="orders" name="orders" placeholder="请输入权重等级">
								<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
							</div>
							<div id="passwordDiv" class="form-group has-feedback">
								<label class="control-label" for="icon">
									icon：
								</label>
						      	<input type="file" class="form-control" id="icon" name="icon" placeholder="图标图片" >
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">
								关闭
							</button>
							<input type="submit" class="btn btn-primary" value="提交">
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--新曾窗口结束-->

		<!--编辑窗口开始-->
		<form action="<%=path%>/admin/tag/update" method="post"  id="editTagForm" enctype ="multipart/form-data">
			<input type="hidden" name="id" id="editTagId">
			<div class="modal fade" id="tagEdit" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								编辑标签
							</h4>
						</div>
						<div class="modal-body">
							<div id="passwordDiv" class="form-group has-feedback">
								<label class="control-label" for="editName">
									名称：
								</label>
								<input type="text" class="form-control" id="editName" name="name" placeholder="请输入账号">
								<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
							</div>
							<div id="passwordDiv" class="form-group has-feedback">
								<label class="control-label" for="editOrders">
									权重等级：
								</label>
								<input type="text" class="form-control" id="editOrders" name="orders" placeholder="请输入权重等级">
								<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
							</div>
							<div id="passwordDiv" class="form-group has-feedback">
								<label class="control-label" for="editIcon">
									icon：
								</label>
								<img alt="" src="" id="editIcon" style="width: 20px;height: 20px"/>
								<input type="file" name="icon" class="form-control"  placeholder="请选择图标"  onclick="getFileSize(this.value)" >
								<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								关闭
							</button>
							<input type="submit" class="btn btn-primary" value="提交">
						</div>
					</div>
				</div>
			</div>
		</form>
		<!-- 编辑弹出框结束 -->
		<!-- 弹出框开始-->
					<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-sm">
						<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<h4 class="modal-title" id="myModalLabel"><font color="#000000">你是否确定删除</font></h4>
						  </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<input type="button" class="btn btn-primary" value="确定" onclick="deleteBatch()">
						  </div>
						</div>
					  </div>
					</div>
		<!--弹出框结束-->
		<w:pager pageSize="${page.pageSize}" currentPage="${page.currentPage}"
			url="./tagList" totalsCount="${page.totalsCount}" />
	</body>
</html>