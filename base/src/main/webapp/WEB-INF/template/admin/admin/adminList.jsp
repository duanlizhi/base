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
		<link href="<%=path%>/resources/admin/css/bootstrap.css" rel="stylesheet">
		<link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet">
		<!-- 校验CSS -->
		<link href="<%=path%>/resources/admin/css/tip.css" rel="stylesheet">
		<script src="<%=path%>/resources/admin/js/jquery-2.1.3.min.js"> </script>
		<script src="<%=path%>/resources/admin/js/bootstrap.min.js"> </script>
		<script src="<%=path%>/resources/admin/js/jquery.validate.js" type="text/javascript"></script>
		<script type='text/javascript' src='<%=path%>/resources/admin/js/messages_zh.js'></script>
		<script src="<%=path%>/resources/admin/js/common.js" type="text/javascript"></script>
		<script type="text/javascript">
		
		
		
		//校验方法 
		$().ready(function(){
			
			//保存的校验方法
			 $("#saveAdminForm").validate({
			 rules: { 
				email:{required:true,email:true,emailValidate:true} 
			 }, 
			messages: {  //提示信息
			    email: { required: "请输入邮箱",email:"邮箱格式不正确"} 
				}
			 });
    	 });
		
		 //自定义校验CAS用户信息
		 jQuery.validator.addMethod("emailValidate", function(value, element) { 
			 var flag=false;
			 $.ajax( { //一个Ajax过程 
					type : "get",
					async: false,
					url : "<%=path%>/admin/admin/emailValidate",
					data : {
						"emailName" : value.trim()
					},
					success : function(data) {
						var obj =eval(data);
						if(obj.username==undefined){
							flag=false;
						}else{
							flag=true;
							$("#username").attr("value",obj.username);
							$("#mobile").attr("value",obj.mobile);
							$("#name").attr("value",obj.name);
						}
					}
				});
			return flag;
	  	}, "系统中存在次用户名或者统一账户中不存在此用户"); 
		
		
		
		
	/*成员变量，作用，当全选的时候按钮可点击，否则是不可点击状态*/
	var button_flag = true;
	/* 全选*/
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
	var str = "",
    unames = '';
	$("input[name='checkbox']:checked").each(function() {
		str += this.value + ",";
        unames += $(this).attr("data-name")+",";
	});
	var ids = str.substr(0, str.length - 1);
        unames = unames.substring(0, unames.length-1);
		$.ajax( { //一个Ajax过程 
					type : "get",
					url : "<%=path%>/admin/admin/delete",
					data : {
						"ids" : ids,
                        "unames" : unames
					},
					success : function(data) {
					location.href = "<%=path%>/admin/admin/adminList"
				},
					error : function() {
			}
		});
}
/*
 * 搜索
 * */
function search(){
	var searchByName=$("#searchByName").val();
	if(searchByName==null||searchByName==""){
		return false;
	}
	location.href="<%=path%>/admin/admin/adminList?screening="+searchByName;
}
/*
 * 按钮校验
 * */
function jiance(){
	if($("input[name='checkbox']:checked").length>=1){
		$("#batch_delete").attr("disabled", false);
	}else{
		$("#batch_delete").attr("disabled", "disabled");
	}
}

//    启用禁用功能
 function adminIsAbled(id){
	location.href="<%=path%>/admin/admin/adminIsAbled?id="+id;
 }
</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<jsp:include page="../top.jsp">
					<jsp:param value="e" name="MeauTag"/>
				</jsp:include>
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<ol class="breadcrumb">
							<li>
								<a href="<%=path%>/admin/">系统管理</a>
							</li>
							<li class="active">
								账户管理
							</li>
						</ol>
					</div>
					<div class="btn-toolbar" role="toolbar">
						<div class="btn-group">
							<button type="button" class="btn btn-info" data-toggle="modal"
								data-target="#adminAdd">
								新增
							</button>
							<button type="button" class="btn btn-info"   id="batch_delete"  disabled="disabled" data-toggle="modal" data-target="#myModaltest">
								删除
							</button>
						</div>
						<div class="btn-group" style="float: right; padding-right: 60px;">
							<input class="form-control"   name="admin.username" type="text" id="searchByName"
								  placeholder="邮箱、账号名称、手机号搜索" style="background: #fff" value="${screening}"/>
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
									<!-- <th>
										编号
									</th> -->
									<th>
										账户名称
									</th>
									<th>
										邮箱
									</th>
									<th>
										手机号
									</th>
									<th>
										状态
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.result}" var="admin" varStatus="status">
									<tr <c:if test="${admin.isEnabled==0}">class=" active" style="cursor:not-allowed"</c:if>>
										<td>
											<input type="checkbox" name="checkbox" value="${admin.id}" data-name="${admin.username}" onclick="jiance()">
										</td>
										<td>
											${status.index+1}
										</td>
										<%-- <td>
											${admin.adminNumber}
										</td> --%>
										<td>
											${admin.username}
										</td>
										<td>
											${admin.email}
										</td>
										<td>
											${admin.mobile}
										</td>
										<td>
											<c:if test="${admin.isEnabled==1}">启用</c:if>
											<c:if test="${admin.isEnabled==0}">禁用</c:if>
										</td>
										<td>
											<!-- <button type="button" class="btn btn-info btn-xs">
												编辑
											</button> -->
											<c:if test="${admin.isEnabled==1}"><button type="button" class="btn btn-info btn-xs" onclick="adminIsAbled('${admin.id}')">禁用</button></c:if>
											<c:if test="${admin.isEnabled==0}"><button type="button" class="btn btn-info btn-xs" onclick="adminIsAbled('${admin.id}')">启用</button></c:if>
											<%-- <c:if test="${not empty updateAccountUrl}">
												<a type="button" class="btn btn-info btn-xs" target="_blank" href="${updateAccountUrl}?username=${admin.username}">修改账户信息</a>
											</c:if> --%>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!--新曾窗口开始-->
	<form action="<%=path%>/admin/admin/save"   id="saveAdminForm"  method="post"  >
	<div class="modal fade" id="adminAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增账户</h4>
				</div>
				<div class="modal-body">
					<div id="passwordDiv" class="form-group has-feedback">
						<label class="control-label" for="email">邮箱：</label> 
							<input type="text" class="form-control" name="email" namid="email" placeholder="请输入邮箱,会从CAS获取用户信息"> 
					</div>
					<div id="passwordDiv" class="form-group has-feedback">
						<label class="control-label" for="username">账号名称：</label> 
						<input type="text" class="form-control" id="username" name="username" id="username" placeholder="账号名称" readonly="readonly">  
					</div>
					<div id="passwordDiv" class="form-group has-feedback">
						<label class="control-label" for="mobile">手机：</label> 
						<input type="text" class="form-control" id="mobile" name="mobile" id="mobile" placeholder="手机号" readonly="readonly"> 
					</div>
					<div id="passwordDiv" class="form-group has-feedback">
						<label class="control-label" for="name">姓名：</label> 
						<input type="text" class="form-control" id="name"  id="name" placeholder="姓名" readonly="readonly"> 
 					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						关闭</button>
					<input type="submit" class="btn btn-primary" value="提交">
				</div>
			</div>
		</div>
	</div>
	</form>
	<!--新曾窗口结束-->
		<!-- 弹出框开始-->
					<div class="modal fade bs-example-modal-sm" id="myModaltest" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
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
		</div>
		<w:pager pageSize="${page.pageSize}" currentPage="${page.currentPage}"
			url="./adminList" totalsCount="${page.totalsCount}" />
	</body>
</html>