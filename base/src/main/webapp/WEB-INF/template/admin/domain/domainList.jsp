<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.xiaochejiang.com/tags/pager" prefix="w"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Content Center</title>
<link href="${request.contextPath}/resources/admin/css/bootstrap.css" rel="stylesheet"/>
<link href="${request.contextPath}/resources/admin/css/dashboard.css" rel="stylesheet"/>
<style type="text/css">
.pagination {
	padding: 5px;
	float: right;
	font-size: 12px;
}

.pagination a, .pagination a:link, .pagination a:visited {
	padding: 2px 5px;
	margin: 2px;
	border: 1px solid #428bca;
	text-decoration: none;
	color: #006699;
}

.pagination a:hover, .pagination a:active {
	border: 1px solid #428bca;
	color: #000;
	text-decoration: none;
}

.pagination span.current {
	padding: 2px 5px;
	margin: 2px;
	border: 1px solid #428bca;
	font-weight: bold;
	background-color: #428bca;
	color: #FFF;
}

.pagination span.disabled {
	padding: 2px 5px;
	margin: 2px;
	border: 1px solid #428bca;
	color: #ddd;
}
</style>
<script src="${request.contextPath}/resources/admin/js/jquery-2.1.3.min.js"></script>
<script src="${request.contextPath}/resources/admin/js/bootstrap.min.js"></script>
<script src="${request.contextPath}/resources/admin/js/jquery.validate.js" type="text/javascript"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../top.jsp">
				<jsp:param value="c" name="MeauTag"/>
			</jsp:include>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<ol class="breadcrumb">
						<li><a href="${request.contextPath}/admin/">系统管理</a></li>
						<li class="active">域管理</li>
					</ol>
				</div>
				<div class="btn-toolbar" role="toolbar">
					<div class="btn-group">
						<button type="button" class="btn btn-info Js-hhj-insert-domain-user-button" data-toggle="modal"
							data-target="#regionAdd">新增</button>
						<!--<button type="button" class="btn btn-info">修改</button>-->
						<button type="button" class="btn btn-info Js-hhj-domain-delete-button" data-toggle="modal"
							data-target="#regionDelete" disabled="disabled">删除</button>

					</div>
					<div class="btn-group" style="float: right; padding-right: 60px;">
						<input class="form-control Js-hhj-domain-search-text" value="${inname}" name="inname" type="text" value="" placeholder="账号、名称、网站" style="background: #fff" /> 
						<button type="button" class="btn btn-info Js-hhj-domain-search-button" style="position: absolute; right: 0px; top: 0px;">搜索</button>
					</div>

					<!-- 
			 <div class="btn-group">
		  		<button type="button" class="btn btn-primary">启用</button>
		  		<button type="button" class="btn btn-primary">禁用</button>
		  	</div>
					
			 -->
				</div>

				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th><input type="checkbox" class="Js-hhj-region-select-all"></th>
								<th>序号</th>
								<th>账号</th>
								<th>名称</th>
								<th>网址</th>
								<th>使用状态</th>
								<th>修改时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.result}" var="item" varStatus="s">
								<tr>
									<td><input type="checkbox" class="Js-hhj-domain-item-checkbox" value="${item.id}"></td>
									<td>${s.count}</td>
									<td>${item.username}</td>
									<td>${item.name}</td>
									<td>${item.url}</td>
									<td><c:if test="${item.enable==1}">正常</c:if><c:if test="${item.enable==0}">禁用</c:if></td>
									<td>${fn:substring(item.modifyDate, 0, 19)}</td>
									<td>
										<button type="button" class="btn btn-info btn-xs Js-hhj-domain-edit" data-value="${item.id}" data-toggle="modal" data-target="#regionEdit">编辑</button>
										<button type="button" class="btn btn-info btn-xs Js-hhj-region-enable" data-value="${item.id}"><c:if test="${item.enable==1}">禁用</c:if><c:if test="${item.enable==0}">启用</c:if></button>
										<button type="button" class="btn btn-info btn-xs Js-hhj-domain-content-control" data-value="${item.id}" data-toggle="modal" data-target="#regionContent">内容权限</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<w:pager pageSize="${page.pageSize}" currentPage="${page.currentPage}"
			url="./domainList" totalsCount="${page.totalsCount}" />
	<!--新曾窗口开始-->
	<form action="${request.contextPath}/admin/domain/save" id="saveDomainForm"  method="post" autocomplete="off">
		<div class="modal fade" id="regionAdd" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">新增域</h4>
					</div>
					<div class="modal-body">
						<div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="name">名称：</label> 
							<input type="text" class="form-control Js-hhj-domain-add-name" id="name" name="name" placeholder="请输入名称">
							<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
						</div>
						<div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="url">网址：</label> 
							<input type="text" class="form-control" id="url" name="url" placeholder="请输入网址"> 
							<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
						</div>
						<%--<div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="domainNumber">编号：</label> 
							<input type="text" class="form-control" id="domainNumber" name="domainNumber" placeholder="请输入编号"> 
							<span id="passwordSpan"cclass="glyphicon form-control-feedback"></span>
						</div>
						--%><div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="username">账号：</label> 
							<input type="text" class="form-control Js-hhj-insert-domain-user-username"  name="username" placeholder="请输入账号" value="" autocomplete="off" > 
							<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
						</div>
						<div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="password">密码：</label> 
							<input type="password" class="form-control Js-hhj-insert-domain-user-password"  name="password" placeholder="请输入密码" value="" autocomplete="off"> 
							<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--新曾窗口结束-->

	<!--编辑窗口开始-->
	<form action="${request.contextPath}/admin/domain/update" method="post" id="editDomainForm">
		<input type="hidden" class="Js-hhj-domain-edit-update-id" value="" name="id">
		<div class="modal fade" id="regionEdit" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">编辑域</h4>
					</div>
					<div class="modal-body">
						<div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="name">名称：</label> 
							<input type="text" class="form-control Js-hhj-domain-edit-name" name="name" data-name="" placeholder="请输入名称">
							<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
						</div>
						<div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="url">网址：</label> 
							<input type="text" class="form-control Js-hhj-domain-edit-url" name="url" placeholder="请输入网站">
							<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
						</div>
						<%--<div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="domainNumber">编号：</label> <input
								type="text" readonly="readonly" class="form-control Js-hhj-domain-edit-domainNumber" name="domainNumber" placeholder="请输入编号">
							<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
						</div>
						--%><div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="username">账号：</label> <input
								type="text" class="form-control Js-hhj-domain-edit-username" name="username" data-username="" placeholder="请输入账号">
							<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
						</div>
						<div id="passwordDiv" class="form-group has-feedback">
							<label class="control-label" for="password">密码：</label> <input
								type="password" class="form-control Js-hhj-domain-edit-password" name="password" placeholder="请输入密码">
							<span id="passwordSpan" class="glyphicon form-control-feedback"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--编辑窗口结束-->

	<!--删除窗口开始-->
	<div class="modal fade" id="regionDelete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title Js-hhj-domain-delete-confirm-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body" align="center">
					<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-default Js-hhj-domain-delete-confirm" data-dismiss="modal">确定</button>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<!--删除窗口结束-->
	<!--内容选项窗口开始-->
	<div class="modal fade" id="regionContent" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<input type="hidden" value="" class="Js-hhj-domain-content-modal-current-domain-id">
		<input type="hidden" value="" class="Js-hhj-domain-content-modal-current-uri">
		<input type="hidden" value="" class="Js-hhj-domain-content-modal-current-selected-uri">
		<input type="hidden" value="" class="Js-hhj-domain-content-modal-current-checkbox-courseware">
		<input type="hidden" value="" class="Js-hhj-domain-content-modal-current-checkbox-tests">
		<input type="hidden" value="" class="Js-hhj-domain-content-modal-current-checkbox-papers">
		<div class="modal-dialog" style="width:1200px;">
			<div class="modal-content">
				<div class="modal-header Js-hhj-region-content-modal-header" style="border: 0px;">
					<!-- <button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">内容权限</h4> -->
					<div>
						<ul class="nav nav-tabs nav-justified Js-hhj-domain-content-control-tab">
					      <li role="presentation" class="active" data-val="course"><a href="javascript:;">课程库</a></li>
					      <li role="presentation" data-val="tests"><a href="javascript:;">题库</a></li>
					      <li role="presentation" data-val="paper"><a href="javascript:;">测评库</a></li>
					    </ul>
				    </div>
				</div>
				<div class="modal-body" style="padding-bottom: 0px;margin-bottom: 0px;">
					<!-- 课程库开始 -->
					<div class="Js-hhj-region-course">
					    <div class="pull-left" style="margin-left: 10px;">
					    	<div class="input-group">
								<input type="text" class="form-control Js-hhj-domain-courseware-keyword">
								<span class="input-group-btn" style="width: 0px;">
									<button class="btn btn-default Js-hhj-domain-courseware-keyword-all-button" type="button">搜索</button>
								</span>
							</div>
					    	<div class="Js-hhj-domain-courseware-allpage-items" style="height: 255px;width: 420px;">
						    	
						    	<%--
							    	<div class="checkbox">
										<label class="checkbox-inline">
										  <input type="checkbox" id="inlineCheckbox1" value="option1"> 课程10
										</label>
									</div>
								--%>
								</div>
							<div class="Js-hhj-domain-courseware-allpage-page" style="clear: both;">
							</div>
					    </div>
					    <div class="pull-left" style="border: 1px dotted #e5e5e5;position:fixed;left: 50%;height:280px/0;height: 315px;"></div>
					    <div class="pull-left" style="left: 600px;position: absolute;height: 376px;">
					    	<div style="">
								<div class="Js-hhj-domain-courseware-selectedpage-items" style="height: 255px;margin-top: 44px;width:420px;">
							   		<%--
								   		<div class="checkbox">
											<label class="checkbox-inline">
											  <input type="checkbox" id="inlineCheckbox1" value="option1" checked="checked"> 课程1
											</label>
										</div>
									--%>
								</div>
							</div>
							<div style="margin-left: 20px;float: left!important;width: 420px;" class="Js-hhj-domain-courseware-selectedpage-page">
							</div>
					    </div>
					</div>
					<!-- 课程库结束 -->
					
					<!-- 题库开始 -->
					<div style="display: none;" class="Js-hhj-region-tests">
					    <div class="pull-left" style="margin-left: 10px;">
					    	<div class="input-group">
								<input type="text" class="form-control Js-hhj-domain-tests-keyword">
								<span class="input-group-btn" style="width: 0px;">
									<button class="btn btn-default Js-hhj-domain-tests-keyword-all-button" type="button">搜索</button>
								</span>
							</div>
							<div>
						    	<div class="Js-hhj-domain-tests-allpage-items"  style="height: 255px;width: 420px;">
						    		<%--
								    	<div class="checkbox">
											<label class="checkbox-inline">
											  <input type="checkbox" id="inlineCheckbox1" value="option1"> 题库1
											</label>
										</div>
									--%>
								</div>
							</div>
							<div class="Js-hhj-domain-tests-allpage-page"></div>
					    </div>
					    <div class="pull-left" style="border: 1px dotted #e5e5e5;position:fixed;left: 50%;height:280px/0;height: 315px;"></div>
					    <div class="pull-left" style="left: 600px;position: absolute;height: 376px;">
					    	<div style="">
								<div class="Js-hhj-domain-tests-selectedpage-items" style="height:255px;  margin-top: 44px;width:420px;">
						   			<%--
						   			<div class="checkbox">
										<label class="checkbox-inline">
										  	<input type="checkbox" id="inlineCheckbox1" value="option1" checked="checked"> 题库1
										</label>
									</div>
									--%>
								</div>
							</div>
							<div style="margin-left: 20px;float: left!important;width: 420px;" class="Js-hhj-domain-tests-selectedpage-page">
							</div>
					    </div>
					</div>
					<!-- 题库结束 --> 
					
					<!-- 测评库开始 -->
					<div style="display: none;" class="Js-hhj-region-test">
					    <div class="pull-left" style="margin-left: 10px;">
					    	<div class="input-group">
								<input type="text" class="form-control Js-hhj-domain-papers-keyword">
								<span class="input-group-btn" style="width: 0px;">
									<button class="btn btn-default Js-hhj-domain-papers-keyword-all-button" type="button">搜索</button>
								</span>
							</div>
							<div class="Js-hhj-domain-papers-allpage-items"  style="height: 255px;width: 420px;">
						    	<%--
							    	<div>
								    	<div class="checkbox">
											<label class="checkbox-inline">
											  <input type="checkbox" id="inlineCheckbox1" value="option1"> 测评库1
											</label>
										</div>
									</div>
								--%>
							</div>
							<div class="Js-hhj-domain-papers-allpage-page">
							</div>
					    </div>
					    <div class="pull-left" style="border: 1px dotted #e5e5e5;position:fixed;left: 50%;height:280px/0;height: 315px;"></div>
					    <div class="pull-left" style="left: 600px;position: absolute;height: 376px;">
							<div style="">
								<div class="Js-hhj-domain-papers-selectedpage-items" style="height:255px;  margin-top: 44px;width:420px;">
						   			<%--
						   			<div class="checkbox">
										<label class="checkbox-inline">
										  	<input type="checkbox" id="inlineCheckbox1" value="option1" checked="checked"> 题库1
										</label>
									</div>
									--%>
								</div>
							</div>
							<div style="margin-left: 20px;float: left!important;width: 420px;" class="Js-hhj-domain-papers-selectedpage-page">
							</div>
					    </div>
					</div>
					<!-- 测评库结束 -->
					<div style="clear: both;"></div>
				</div>
				<div class="modal-footer" style="border: 0px;text-align: center;padding-top: 0px;margin-top: 0px;">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary Js-hhj-domain-content-submit">提交</button>
				</div>
			</div>
		</div>
	</div>
	<!--内容选项窗口结束-->
	<script type="text/javascript">
	
	/**
	 * 删除字符串
	 * */
	function deleteStr(str,delStr) {
		var newStr="";
		if(Boolean(str) && Boolean(delStr)){
			var	strArr = str.split(",");
			for(var i=0;i<strArr.length;i++){
				if(strArr[i]!=delStr){
					if(newStr==""){
						newStr = strArr[i];
					}else{
						newStr = newStr + "," + strArr[i];
					}
				}
			}
		}
		return newStr;
	}
	
	/**
	 * 添加字符串
	 * */
	function insertStr(str,inStr) {
		if(Boolean(str) && Boolean(inStr)){
			var	strArr = str.split(","),
			flag=true;
			for(var i=0;i<strArr.length;i++){
				if(strArr[i]==inStr){
					flag = false;
				}
			}
			if(flag) strArr.push(inStr);
			return strArr.toString();
		} else {
			if(str==""){
				return inStr;
			} else {
				return "";
			}
		}
	}
	
	/**
	 * 判断字符串是否存在
	 * */
	function hasStr(str,inStr) {
		flag=false;
		if(Boolean(str) && Boolean(inStr)){
			var	strArr = str.split(",");
			for(var i=0;i<strArr.length;i++){
				if(strArr[i]==inStr){
					flag = true;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 课程库
	 * */
	function getCoursePageAjax(no,flag,category) {
		$.ajax({
			type : "GET",
			url : "${request.contextPath}/admin/domain/getCourseWares",
			data : {
				"id" : $(".Js-hhj-domain-content-modal-current-domain-id").val(),
				"currentPage":no,
				"inname":$(".Js-hhj-domain-courseware-keyword").val(),
				"category":category
			},
			success : function(data) {
				var tabVal = $(".Js-hhj-domain-content-control-tab").find("li[class='active']").attr("data-val"),
				tmp = "",
				checkboxVal = "";
				$(".Js-hhj-domain-content-modal-current-uri").val(data.allPage.uri);
				$(".Js-hhj-domain-content-modal-current-selected-uri").val(data.selectedPage.uri);
				checkboxVal = $(".Js-hhj-domain-content-modal-current-checkbox-courseware").val();
		    	if(tabVal=="course") {
			    	$(".Js-hhj-domain-courseware-allpage-items").empty();
					$(".Js-hhj-domain-courseware-selectedpage-items").empty();
					$(".Js-hhj-domain-content-modal-current-checkbox-courseware").val();
					totalPageALl = data.allPage.totalsPage;
					$.each(data.allPage.result,function(i,item){
						tmp = '<div class="checkbox Js-hhj-domain-courseware-allpage-item">'
								+'<label class="checkbox-inline">'
								+'<input class="Js-hhj-domain-courseware-allpage-item-checkbox" '+(!Boolean(item.name)?'':'data-name='+item.name)+' type="checkbox" id="inlineCheckbox1" ';
						if (checkboxVal=="") {
							tmp = tmp + (Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id;
						} else {
							if(!hasStr(checkboxVal,item.id)) {
								tmp = tmp +' value="'+item.id;
							} else {
								tmp = tmp + ' checked="checked"'+' value="'+item.id;
							}
						}
						tmp = tmp + '"> '+(!Boolean(item.name)?'':item.name) +'</label></div>';
						$(".Js-hhj-domain-courseware-allpage-items").append(tmp);
					});
					
					var allPageString = "";
						allPageString += '<table class="table table-striped">';
						allPageString += data.allPageStr;
						allPageString += '</table>',
						selectedIds="";
					$(".Js-hhj-domain-courseware-allpage-page").html(allPageString);
					$.each(data.selectedPage.result,function(i,item){
						$(".Js-hhj-domain-courseware-selectedpage-items")
							.append('<div class="checkbox Js-hhj-domain-courseware-selectedpage-item" data-id="'+item.id+'">'
								+'<label class="checkbox-inline">'
								+(!Boolean(item.name)?'':item.name)
								+'</label>'
								+'</div>');
						selectedIds = selectedIds + item.id + ",";
					});
					if(flag && checkboxVal==""){
						selectedIds = selectedIds.substring(0,selectedIds.length-1);
					}
					var selectedPageString = "";
						selectedPageString += '<table class="table table-striped">';
						selectedPageString += data.selectedPageStr;
						selectedPageString += '</table>';
					$(".Js-hhj-domain-courseware-selectedpage-page").html(selectedPageString);
		    	}
		    	if(tabVal=="tests") {
			    	$(".Js-hhj-domain-tests-allpage-items").empty();
					$(".Js-hhj-domain-tests-selectedpage-items").empty();
					$(".Js-hhj-domain-content-modal-current-checkbox-tests").val();
					checkboxVal = $(".Js-hhj-domain-content-modal-current-checkbox-tests").val();
					totalPageALl = data.allPage.totalsPage;
					$.each(data.allPage.result,function(i,item){
						tmp = '<div class="checkbox Js-hhj-domain-tests-allpage-item">'
								+'<label class="checkbox-inline">'
								+'<input class="Js-hhj-domain-tests-allpage-item-checkbox" '+(!Boolean(item.name)?'':'data-name='+item.name)+' type="checkbox" id="inlineCheckbox1" ';
						if (checkboxVal=="") {
							tmp = tmp + (Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id;
						} else {
							if(!hasStr(checkboxVal,item.id)) {
								tmp = tmp +' value="'+item.id;
							} else {
								tmp = tmp + ' checked="checked"'+' value="'+item.id;
							}
						}
						tmp = tmp + '"> '+(!Boolean(item.name)?'':item.name) +'</label></div>';
						$(".Js-hhj-domain-tests-allpage-items").append(tmp);
					});
					
					var allPageString = "";
						allPageString += '<table class="table table-striped">';
						allPageString += data.allPageStr;
						allPageString += '</table>',
						selectedIds="";
					$(".Js-hhj-domain-tests-allpage-page").html(allPageString);
					$.each(data.selectedPage.result,function(i,item){
						$(".Js-hhj-domain-tests-selectedpage-items")
							.append('<div class="checkbox Js-hhj-domain-tests-selectedpage-item" data-id="'+item.id+'">'
								+'<label class="checkbox-inline">'
								+(!Boolean(item.name)?'':item.name)
								+'</label>'
								+'</div>');
						selectedIds = selectedIds + item.id + ",";
					});
					if(flag && checkboxVal==""){
						selectedIds = selectedIds.substring(0,selectedIds.length-1);
					}
					var selectedPageString = "";
						selectedPageString += '<table class="table table-striped">';
						selectedPageString += data.selectedPageStr;
						selectedPageString += '</table>';
					$(".Js-hhj-domain-tests-selectedpage-page").html(selectedPageString);
		    	}
			}
		});
	}
	
	/**
	 * 测评库
	 * */
	function getPaperPageAjax(no,flag) {
		$.ajax({
			type : "GET",
			url : "${request.contextPath}/admin/domain/getPapers",
			data : {
				"id" : $(".Js-hhj-domain-content-modal-current-domain-id").val(),
				"currentPage":no,
				"inname":$(".Js-hhj-domain-paper-keyword").val()
			},
			success : function(data) {
				var tmp = "",
				checkboxVal = "";
				$(".Js-hhj-domain-content-modal-current-uri").val(data.allPapersPage.uri);
				$(".Js-hhj-domain-content-modal-current-selected-uri").val(data.selectedPapersPage.uri);
				checkboxVal = $(".Js-hhj-domain-content-modal-current-checkbox-papers").val();
				$(".Js-hhj-domain-papers-allpage-items").empty();
				$(".Js-hhj-domain-papers-selectedpage-items").empty();
				$(".Js-hhj-domain-content-modal-current-checkbox-papers").val();
				totalPageALl = data.allPapersPage.totalsPage;
				$.each(data.allPapersPage.result,function(i,item){
					tmp = '<div class="checkbox Js-hhj-domain-papers-allpage-item">'
							+'<label class="checkbox-inline">'
							+'<input class="Js-hhj-domain-papers-allpage-item-checkbox" '+(!Boolean(item.paperName)?'':'data-name='+item.paperName)+' type="checkbox" id="inlineCheckbox1" ';
					if (checkboxVal=="") {
						tmp = tmp + (Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id;
					} else {
						if(!hasStr(checkboxVal,item.id)) {
							tmp = tmp +' value="'+item.id;
						} else {
							tmp = tmp + ' checked="checked"'+' value="'+item.id;
						}
					}
					tmp = tmp + '"> '+(!Boolean(item.paperName)?'':item.paperName) +'</label></div>';
					$(".Js-hhj-domain-papers-allpage-items").append(tmp);
				});
				
				var allPapersPageString = "";
					allPapersPageString += '<table class="table table-striped">';
					allPapersPageString += data.allPapersPageStr;
					allPapersPageString += '</table>',
					selectedIds="";
				$(".Js-hhj-domain-papers-allpage-page").html(allPapersPageString);
				$.each(data.selectedPapersPage.result,function(i,item){
					$(".Js-hhj-domain-papers-selectedpage-items")
						.append('<div class="checkbox Js-hhj-domain-papers-selectedpage-item" data-id="'+item.id+'">'
							+'<label class="checkbox-inline">'
							+(!Boolean(item.paperName)?'':item.paperName)
							+'</label>'
							+'</div>');
					selectedIds = selectedIds + item.id + ",";
				});
				if(flag && checkboxVal==""){
					selectedIds = selectedIds.substring(0,selectedIds.length-1);
				}
				var selectedPapersPageString = "";
					selectedPapersPageString += '<table class="table table-striped">';
					selectedPapersPageString += data.selectedPapersPageStr;
					selectedPapersPageString += '</table>';
				$(".Js-hhj-domain-papers-selectedpage-page").html(selectedPapersPageString);
			}
		});
	}
	
	/**
	 * 课程库已选
	 * */
	function getCoursePageAjaxSelected(no,flag,category) {
		$.ajax({
			type : "GET",
			url : "${request.contextPath}/admin/domain/getCourseWaresSelectedPage",
			data : {
				"id" : $(".Js-hhj-domain-content-modal-current-domain-id").val(),
				"currentPage":no,
				"inname":$(".Js-hhj-domain-courseware-keyword").val(),
				"category":category
			},
			success : function(data) {
				if(category=="1"){
					$(".Js-hhj-domain-courseware-selectedpage-items").empty();
					var itemName="";
					$.each(data.selectedPage.result,function(i,item){
						$(".Js-hhj-domain-courseware-selectedpage-items")
							.append('<div class="checkbox Js-hhj-domain-courseware-selectedpage-item" data-id="'+item.id+'">'
								+'<label class="checkbox-inline">'
								+(!Boolean(item.name)?'':item.name)
								+'</label>'
								+'</div>');
					});
					var selectedPageString = "";
						selectedPageString += '<table class="table table-striped">';
						selectedPageString += data.selectedPageStr;
						selectedPageString += '</table>';
					$(".Js-hhj-domain-courseware-selectedpage-page").html(selectedPageString);
				}
				
				if(category=="2"){
					$(".Js-hhj-domain-tests-selectedpage-items").empty();
					var itemName="";
					$.each(data.selectedPage.result,function(i,item){
						$(".Js-hhj-domain-tests-selectedpage-items")
							.append('<div class="checkbox Js-hhj-domain-tests-selectedpage-item" data-id="'+item.id+'">'
								+'<label class="checkbox-inline">'
								+(!Boolean(item.name)?'':item.name)
								+'</label>'
								+'</div>');
					});
					var selectedPageString = "";
						selectedPageString += '<table class="table table-striped">';
						selectedPageString += data.selectedPageStr;
						selectedPageString += '</table>';
					$(".Js-hhj-domain-tests-selectedpage-page").html(selectedPageString);
				}
				
			}
		});
	}
	/**
	 * 测评库已选
	 * */
	function getCoursePaperAjaxSelected(no,flag) {
		$.ajax({
			type : "GET",
			url : "${request.contextPath}/admin/domain/getCoursePapersSelectedPage",
			data : {
				"id" : $(".Js-hhj-domain-content-modal-current-domain-id").val(),
				"currentPage":no,
				"inname":$(".Js-hhj-domain-papers-keyword").val()
			},
			success : function(data) {
				$(".Js-hhj-domain-papers-selectedpage-items").empty();
				$.each(data.selectedPapersPage.result,function(i,item){
					$(".Js-hhj-domain-papers-selectedpage-items")
						.append('<div class="checkbox Js-hhj-domain-papers-selectedpage-item" data-id="'+item.id+'">'
							+'<label class="checkbox-inline">'
							+(!Boolean(item.paperName)?'':item.paperName)
							+'</label>'
							+'</div>');
				});
				var selectedPageString = "";
					selectedPageString += '<table class="table table-striped">';
					selectedPageString += data.selectedPapersPageStr;
					selectedPageString += '</table>';
				$(".Js-hhj-domain-papers-selectedpage-page").html(selectedPageString);
			}
		});
	}
	
	/**
	 * 课程库全部
	 * */
	function getCoursePageAjaxAll(no,flag,category) {
		$.ajax({
			type : "GET",
			url : "${request.contextPath}/admin/domain/getCourseWaresPage",
			data : {
				"id" : $(".Js-hhj-domain-content-modal-current-domain-id").val(),
				"currentPage":no,
				"inname":$(".Js-hhj-domain-courseware-keyword").val(),
				"category":category
			},
			success : function(data) {
				if(category=="1") {
					$(".Js-hhj-domain-courseware-allpage-items").empty();
					$(".Js-hhj-domain-content-modal-current-uri").val(data.allPage.uri);
					var tmp = "",
					checkboxVal = $(".Js-hhj-domain-content-modal-current-checkbox-courseware").val();
					$.each(data.allPage.result,function(i,item){
						tmp = '<div class="checkbox Js-hhj-domain-courseware-allpage-item">'
								+'<label class="checkbox-inline">'
								+'<input class="Js-hhj-domain-courseware-allpage-item-checkbox" '+(!Boolean(item.name)?'':'data-name='+item.name)+' type="checkbox" id="inlineCheckbox1" ';
						if (checkboxVal=="") {
							tmp = tmp + (Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id;
						} else {
							if(!hasStr(checkboxVal,item.id)) {
								tmp = tmp +' value="'+item.id;
							} else {
								tmp = tmp + ' checked="checked"'+' value="'+item.id;
							}
						}
						tmp = tmp + '"> '+(!Boolean(item.name)?'':item.name) +'</label></div>';
						$(".Js-hhj-domain-courseware-allpage-items").append(tmp);
					});
					
					var allPageString = "";
						allPageString += '<table class="table table-striped">';
						allPageString += data.allPageStr;
						allPageString += '</table>';
					$(".Js-hhj-domain-courseware-allpage-page").html(allPageString);
				}
				if(category=="2") {
					$(".Js-hhj-domain-tests-allpage-items").empty();
					$(".Js-hhj-domain-tests-modal-current-uri").val(data.allPage.uri);
					var tmp = "",
					checkboxVal = $(".Js-hhj-domain-content-modal-current-checkbox-courseware").val();
					$.each(data.allPage.result,function(i,item){
						tmp = '<div class="checkbox Js-hhj-domain-tests-allpage-item">'
								+'<label class="checkbox-inline">'
								+'<input class="Js-hhj-domain-tests-allpage-item-checkbox" '+(!Boolean(item.name)?'':'data-name='+item.name)+' type="checkbox" id="inlineCheckbox1" ';
						if (checkboxVal=="") {
							tmp = tmp + (Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id;
						} else {
							if(!hasStr(checkboxVal,item.id)) {
								tmp = tmp +' value="'+item.id;
							} else {
								tmp = tmp + ' checked="checked"'+' value="'+item.id;
							}
						}
						tmp = tmp + '"> '+(!Boolean(item.name)?'':item.name) +'</label></div>';
						$(".Js-hhj-domain-tests-allpage-items").append(tmp);
					});
					
					var allPageString = "";
						allPageString += '<table class="table table-striped">';
						allPageString += data.allPageStr;
						allPageString += '</table>';
					$(".Js-hhj-domain-tests-allpage-page").html(allPageString);
				}
			}
		});
	}
	
	/**
	 * 测评库全部
	 * */
	function getPaperPageAjaxAll(no,flag) {
		$.ajax({
			type : "GET",
			url : "${request.contextPath}/admin/domain/getCoursePapersPage",
			data : {
				"id" : $(".Js-hhj-domain-content-modal-current-domain-id").val(),
				"currentPage":no,
				"inname":$(".Js-hhj-domain-paper-keyword").val()
			},
			success : function(data) {
				$(".Js-hhj-domain-papers-allpage-items").empty();
				$(".Js-hhj-domain-content-modal-current-uri").val(data.allPapersPage.uri);
				var tmp = "",
				checkboxVal = $(".Js-hhj-domain-content-modal-current-checkbox-papers").val();
				totalPageALl = data.allPapersPage.totalsPage;
				$.each(data.allPapersPage.result,function(i,item){
					tmp = '<div class="checkbox Js-hhj-domain-papers-allpage-item">'
							+'<label class="checkbox-inline">'
							+'<input class="Js-hhj-domain-papers-allpage-item-checkbox" '+(!Boolean(item.paperName)?'':'data-name='+item.paperName)+' type="checkbox" id="inlineCheckbox1" ';
					if (checkboxVal=="") {
						tmp = tmp + (Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id;
					} else {
						if(!hasStr(checkboxVal,item.id)) {
							tmp = tmp +' value="'+item.id;
						} else {
							tmp = tmp + ' checked="checked"'+' value="'+item.id;
						}
					}
					tmp = tmp + '"> '+(!Boolean(item.paperName)?'':item.paperName) +'</label></div>';
					$(".Js-hhj-domain-papers-allpage-items").append(tmp);
				});
				
				var allPageString = "";
					allPageString += '<table class="table table-striped">';
					allPageString += data.allPapersPageStr;
					allPageString += '</table>';
				$(".Js-hhj-domain-papers-allpage-page").html(allPageString);
			}
		});
	}
	
	
	
		/**
		 * 
		 * 单条数据禁用 启用
		 */
		$(document).on("click",".Js-hhj-region-enable",function(){
			var $thiz = $(this),
			$trParent = $thiz.parents("tr"),
			id=$thiz.attr("data-value"),
			status="";
			
			// TODO 0禁用 1启用
			if($thiz.text() == '启用') {
				status="1";
			} else {
				status="0";
			}
			
			$.ajax({
				type : "get",
				url : "${request.contextPath}/admin/domain/updateStatus",
				data : {
					"id" : id,
					"status":status
				},
				success : function(data) {
					if(data.errCode=="0"){
						if($thiz.text() == '启用') {
							$thiz.text('禁用');
							$thiz.siblings().removeAttr("disabled");
							$trParent.find("input[type='checkbox']").removeAttr("disabled");
							$trParent.find("td:eq(5)").text("正常");
							$trParent.removeClass("active").removeAttr("style");
						} else {
							$thiz.text('启用');
							$thiz.siblings().attr("disabled","disabled");
							$trParent.find("input[type='checkbox']").prop("checked",false).attr("disabled","disabled");
							$trParent.find("td:eq(5)").text("禁用");
							$trParent.addClass("active").css("cursor","not-allowed");
						}
					} else {
						alert("修改失败");
					}
				}
			});
			
			
		});
		
		/**
		 * 
		 * 全选
		 */
		$(document).on("click",".Js-hhj-region-select-all",function(){
			var $thiz = $(this),
			$trParent = $thiz.parents(".table");
			if($thiz.is(":checked")) {
				$(".Js-hhj-domain-delete-button").removeAttr("disabled");
				$trParent.find("input[type='checkbox']:enabled").prop("checked",true);
			} else {
				$(".Js-hhj-domain-delete-button").attr("disabled","disabled");
				$trParent.find("input[type='checkbox']:enabled").prop("checked",false);
			}
		});
		
		/**
		 * 
		 * 单条数据的选中 
		 */
		$(document).on("click",".Js-hhj-domain-item-checkbox",function(){
			var $thiz = $(this),
			$itemsChecked = $(".Js-hhj-domain-item-checkbox:checked"),
			$items = $(".Js-hhj-domain-item-checkbox");
			if($itemsChecked.size()>=1){
				$(".Js-hhj-domain-delete-button").removeAttr("disabled");
			} else {
				$(".Js-hhj-domain-delete-button").attr("disabled","disabled");
			}
			if($itemsChecked.size() == $items.size()){
				$(".Js-hhj-region-select-all").prop("checked",true);
			} else {
				$(".Js-hhj-region-select-all").prop("checked",false);
			}
		});
		
		/**
		 * 
		 * 内容权限 
		 */
		$(document).find(".Js-hhj-region-content-modal-header .nav").on("click","li",function(){
			var $thiz = $(this);
			$thiz.attr("class","active").siblings().removeAttr("class");
			if($thiz.index() == 0) {
				// 课程库
				$(".Js-hhj-region-course").show();
				$(".Js-hhj-region-tests").hide();
				$(".Js-hhj-region-test").hide();
				getCoursePageAjax(1,true,"1");
			} else if($thiz.index() == 1) {
				// 题库
				$(".Js-hhj-region-course").hide();
				$(".Js-hhj-region-tests").show();
				$(".Js-hhj-region-test").hide();
				getCoursePageAjax(1,true,"2");
			} else if($thiz.index() == 2) {
				// 测评库
				$(".Js-hhj-region-course").hide();
				$(".Js-hhj-region-tests").hide();
				$(".Js-hhj-region-test").show();
				getPaperPageAjax(1,true);
			}
		});
		
		/**
		 * 删除确定
		 */
		$(document).on("click",".Js-hhj-domain-delete-confirm",function(){
			var $items = $(".Js-hhj-domain-item-checkbox:checked"),
			str = "";
			$.each($items,function(i,item){
				str += $(item).val() + ",";
			});
			str=str.substr(0, str.length - 1);
			$.ajax({
				type : "get",
				beforeSend : function(){
					str = $.trim(str);
					if(str == "") {
						alert("请选择删除项");
						return;
					}
				},
				url : "${request.contextPath}/admin/domain/delete",
				data : {
					"ids" : str
				},
				success : function(data) {
					$(".Js-hhj-domain-delete-confirm-title").html("您是否要删除?");
					location.reload(true);
				}
			});
		});
		
		$(document).on("click",".Js-hhj-domain-delete-button",function(){
			var $items = $(".Js-hhj-domain-item-checkbox:checked"),
			str = "";
			$.each($items,function(i,item){
				str += $(item).val() + ",";
			});
			str=str.substr(0, str.length - 1);
			$.ajax({
				type : "get",
				beforeSend : function(){
					str = $.trim(str);
					if(str == "") {
						alert("请选择删除项");
						return;
					}
				},
				url : "${request.contextPath}/admin/domain/getCourseWares4Delete",
				data : {
					"ids" : str
				},
				success : function(data) {
					if (Boolean(data.flag)) {
						$(".Js-hhj-domain-delete-confirm-title").html("当前域的内容权限有相关控制，确定要删除？")
					} else {
						$(".Js-hhj-domain-delete-confirm-title").html("您是否要删除?")
					}
				}
			});
		});
		
		/**
		 * 域编辑
		 */
		$(document).on("click",".Js-hhj-domain-edit",function(){
			var $thiz = $(this),
			id=$thiz.attr("data-value");
			$.ajax({
				type : "get",
				url : "${request.contextPath}/admin/domain/editDomain",
				data : {
					"id" : id
				},
				success : function(data) {
					$(".Js-hhj-domain-edit-name").val(data.data.name);
                    $(".Js-hhj-domain-edit-name").attr("data-name",data.data.name);
					$(".Js-hhj-domain-edit-url").val(data.data.url);
					$(".Js-hhj-domain-edit-domainNumber").val(data.data.domainNumber);
					$(".Js-hhj-domain-edit-username").val(data.data.username);
                    $(".Js-hhj-domain-edit-username").attr("data-username",data.data.username);
					$(".Js-hhj-domain-edit-password").val(data.data.password);
					$(".Js-hhj-domain-edit-update-id").val(id);
				}
			});
		});
		
		/**
		 * 内容权限控制
		 * */
		$(document).on("click",".Js-hhj-domain-content-control",function(){
			var $thiz = $(this),
			id=$thiz.attr("data-value");
			$(".Js-hhj-domain-content-modal-current-checkbox-courseware").val("");
			$(".Js-hhj-domain-content-modal-current-checkbox-tests").val(""),
			$(".Js-hhj-domain-content-modal-current-checkbox-papers").val(""),
			$(".Js-hhj-domain-content-modal-current-domain-id").val(""),
			$(".Js-hhj-domain-content-modal-current-uri").val(""),
			$(".Js-hhj-domain-content-modal-current-selected-uri").val("");
			var activeTab = $(".Js-hhj-domain-content-control-tab").find("li:eq(0)");
			activeTab.addClass("active");
			$(".Js-hhj-region-course").show();
			$(".Js-hhj-region-tests").hide();
			$(".Js-hhj-region-test").hide();
			activeTab.siblings().removeClass("active");
			$.ajax({
				type : "GET",
				url : "${request.contextPath}/admin/domain/getCourseWares",
				data : {
					"id" : id
				},
				success : function(data) {
					$(".Js-hhj-domain-courseware-allpage-items").empty();
					$(".Js-hhj-domain-courseware-selectedpage-items").empty();
					$(".Js-hhj-domain-content-modal-current-domain-id").val(id);
					$(".Js-hhj-domain-content-modal-current-uri").val(data.allPage.uri);
					$(".Js-hhj-domain-content-modal-current-selected-uri").val(data.selectedPage.uri);
					totalPageALl = data.allPage.totalsPage;
					totalPageSelected = data.selectedPage.totalsPage;
					$.each(data.allPage.result,function(i,item){
						if(!Boolean(item.name)) {
							itemName = "";
						} else {
							itemName = "data-name="+item.name;
						}
						$(".Js-hhj-domain-courseware-allpage-items")
							.append('<div class="checkbox Js-hhj-domain-courseware-allpage-item">'
								+'<label class="checkbox-inline">'
								+'<input class="Js-hhj-domain-courseware-allpage-item-checkbox" '+itemName+' type="checkbox" id="inlineCheckbox1" '+(Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id+'"> '+item.name
								+'</label>'
								+'</div>');
					});
					
					var allPageString = "";
						allPageString += '<table class="table table-striped">';
						allPageString += data.allPageStr;
						allPageString += '</table>',
						selectedIds="";
					$(".Js-hhj-domain-courseware-allpage-page").html(allPageString);
					$.each(data.selectedPage.result,function(i,item){
						$(".Js-hhj-domain-courseware-selectedpage-items")
							.append('<div class="checkbox Js-hhj-domain-courseware-selectedpage-item" data-id="'+item.id+'">'
								+'<label class="checkbox-inline">'
								+item.name
								+'</label>'
								+'</div>');
						selectedIds = selectedIds + item.id + ",";
					});
					selectedIds = selectedIds.substring(0,selectedIds.length-1);
					$(".Js-hhj-domain-content-modal-current-checkbox-courseware").val(selectedIds);
					var selectedPageString = "";
						selectedPageString += '<table class="table table-striped">';
						selectedPageString += data.selectedPageStr;
						selectedPageString += '</table>';
					$(".Js-hhj-domain-courseware-selectedpage-page").html(selectedPageString);
				}
			});
			$.ajax({
				type : "GET",
				url : "${request.contextPath}/admin/domain/getCourseWares",
				data : {
					"id" : id,
					"category":"2"
				},
				success : function(data) {
					var selectedIds = "";
					$.each(data.selectedPage.result,function(i,item){
						selectedIds = selectedIds + item.id + ",";
					});
					selectedIds = selectedIds.substring(0,selectedIds.length-1);
					$(".Js-hhj-domain-content-modal-current-checkbox-tests").val(selectedIds);
				}
			});
			$.ajax({
				type : "GET",
				url : "${request.contextPath}/admin/domain/getPapers",
				data : {
					"id" : id
				},
				success : function(data) {
					var selectedIds = "";
					$.each(data.selectedPapersPage.result,function(i,item){
						selectedIds = selectedIds + item.id + ",";
					});
					selectedIds = selectedIds.substring(0,selectedIds.length-1);
					$(".Js-hhj-domain-content-modal-current-checkbox-papers").val(selectedIds);
				}
			});
		});
		
		
		/**
		 * 课程库 和 题库 模糊搜索
		 * */
		function inputButtonCourseAndTestsSearch(coursewareKeyword, category, tabVal) {
			$.ajax({
				type : "GET",
				url : "${request.contextPath}/admin/domain/getCourseWares",
				data : {
					"id" : $(".Js-hhj-domain-content-modal-current-domain-id").val(),
					"inname" : coursewareKeyword,
					"category":category
				},
				success : function(data) {
					if(tabVal=="course") {
						$(".Js-hhj-domain-courseware-allpage-items").empty();
						$(".Js-hhj-domain-courseware-selectedpage-items").empty();
						$(".Js-hhj-domain-content-modal-current-uri").val(data.allPage.uri);
						$(".Js-hhj-domain-content-modal-current-selected-uri").val(data.selectedPage.uri);
						$.each(data.allPage.result,function(i,item){
							$(".Js-hhj-domain-courseware-allpage-items")
								.append('<div class="checkbox Js-hhj-domain-courseware-allpage-item">'
									+'<label class="checkbox-inline">'
									+'<input class="Js-hhj-domain-courseware-allpage-item-checkbox" data-name='+(!Boolean(item.name)?'""':item.name)+' type="checkbox" id="inlineCheckbox1" '+(Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id+'"> '+item.name
									+'</label>'
									+'</div>');
						});
						
						var allPageString = "";
							allPageString += '<table class="table table-striped">';
							allPageString += data.allPageStr;
							allPageString += '</table>',
							selectedIds="";
						$(".Js-hhj-domain-courseware-allpage-page").html(allPageString);
						$.each(data.selectedPage.result,function(i,item){
							$(".Js-hhj-domain-courseware-selectedpage-items")
								.append('<div class="checkbox Js-hhj-domain-courseware-selectedpage-item" data-id="'+item.id+'">'
									+'<label class="checkbox-inline">'
									+(!Boolean(item.name)?'""':item.name)
									+'</label>'
									+'</div>');
							selectedIds = selectedIds + item.id + ",";
						});
						selectedIds = selectedIds.substring(0,selectedIds.length-1);
						var selectedPageString = "";
							selectedPageString += '<table class="table table-striped">';
							selectedPageString += data.selectedPageStr;
							selectedPageString += '</table>';
						$(".Js-hhj-domain-courseware-selectedpage-page").html(selectedPageString);
					}
					if(tabVal=="tests"){
						$(".Js-hhj-domain-tests-allpage-items").empty();
						$(".Js-hhj-domain-tests-selectedpage-items").empty();
						$(".Js-hhj-domain-content-modal-current-uri").val(data.allPage.uri);
						$(".Js-hhj-domain-content-modal-current-selected-uri").val(data.selectedPage.uri);
						$.each(data.allPage.result,function(i,item){
							$(".Js-hhj-domain-tests-allpage-items")
								.append('<div class="checkbox Js-hhj-domain-tests-allpage-item">'
									+'<label class="checkbox-inline">'
									+'<input class="Js-hhj-domain-tests-allpage-item-checkbox" data-name='+(!Boolean(item.name)?'""':item.name)+' type="checkbox" id="inlineCheckbox1" '+(Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id+'"> '+item.name
									+'</label>'
									+'</div>');
						});
						
						var allPageString = "";
							allPageString += '<table class="table table-striped">';
							allPageString += data.allPageStr;
							allPageString += '</table>',
							selectedIds="";
						$(".Js-hhj-domain-tests-allpage-page").html(allPageString);
						$.each(data.selectedPage.result,function(i,item){
							$(".Js-hhj-domain-tests-selectedpage-items")
								.append('<div class="checkbox Js-hhj-domain-tests-selectedpage-item" data-id="'+item.id+'">'
									+'<label class="checkbox-inline">'
									+(!Boolean(item.name)?'""':item.name)
									+'</label>'
									+'</div>');
							selectedIds = selectedIds + item.id + ",";
						});
						selectedIds = selectedIds.substring(0,selectedIds.length-1);
						var selectedPageString = "";
							selectedPageString += '<table class="table table-striped">';
							selectedPageString += data.selectedPageStr;
							selectedPageString += '</table>';
						$(".Js-hhj-domain-tests-selectedpage-page").html(selectedPageString);
					}
					
				}
			});
		}
		
		/**
		 * 测评库 模糊搜索
		 * */
		function inputButtonPapersSearch(coursewareKeyword){
			$.ajax({
				type : "GET",
				url : "${request.contextPath}/admin/domain/getPapers",
				data : {
					"id" : $(".Js-hhj-domain-content-modal-current-domain-id").val(),
					"inname" : coursewareKeyword
				},
				success : function(data) {
					$(".Js-hhj-domain-papers-allpage-items").empty();
					$(".Js-hhj-domain-papers-selectedpage-items").empty();
					$(".Js-hhj-domain-content-modal-current-uri").val(data.allPapersPage.uri);
					$(".Js-hhj-domain-content-modal-current-selected-uri").val(data.selectedPapersPage.uri);
					$.each(data.allPapersPage.result,function(i,item){
						$(".Js-hhj-domain-papers-allpage-items")
							.append('<div class="checkbox Js-hhj-domain-papers-allpage-item">'
								+'<label class="checkbox-inline">'
								+'<input class="Js-hhj-domain-papers-allpage-item-checkbox" data-name='+(!Boolean(item.paperName)?'""':item.paperName)+' type="checkbox" id="inlineCheckbox1" '+(Boolean(item.flag)?' checked="checked"':'')+' value="'+item.id+'"> '+item.paperName
								+'</label>'
								+'</div>');
					});
					
					var allPageString = "";
						allPageString += '<table class="table table-striped">';
						allPageString += data.allPapersPageStr;
						allPageString += '</table>',
						selectedIds="";
					$(".Js-hhj-domain-papers-allpage-page").html(allPageString);
					$.each(data.selectedPapersPage.result,function(i,item){
						$(".Js-hhj-domain-papers-selectedpage-items")
							.append('<div class="checkbox Js-hhj-domain-papers-selectedpage-item" data-id="'+item.id+'">'
								+'<label class="checkbox-inline">'
								+(!Boolean(item.paperName)?'""':item.paperName)
								+'</label>'
								+'</div>');
						selectedIds = selectedIds + item.id + ",";
					});
					selectedIds = selectedIds.substring(0,selectedIds.length-1);
					var selectedPageString = "";
						selectedPageString += '<table class="table table-striped">';
						selectedPageString += data.selectedPapersPageStr;
						selectedPageString += '</table>';
					$(".Js-hhj-domain-papers-selectedpage-page").html(selectedPageString);
				}
			});
		}
		
		$(document).on("click",".Js-hhj-domain-courseware-keyword-all-button,.Js-hhj-domain-tests-keyword-all-button,.Js-hhj-domain-papers-keyword-all-button",function(){
			var tabVal = $(".Js-hhj-domain-content-control-tab").find("li[class='active']").attr("data-val"),
			coursewareKeyword = "",
			category=1;
			if(tabVal=="course") {
				category=1;
				coursewareKeyword = $(".Js-hhj-domain-courseware-keyword").val();
				inputButtonCourseAndTestsSearch(coursewareKeyword,category,tabVal);
			}
			if(tabVal=="tests"){
				category=2;
				coursewareKeyword = $(".Js-hhj-domain-tests-keyword").val();
				inputButtonCourseAndTestsSearch(coursewareKeyword,category,tabVal);
			}
			if(tabVal=="paper"){
				coursewareKeyword = $(".Js-hhj-domain-papers-keyword").val();
				inputButtonPapersSearch(coursewareKeyword);
			}
		});
		
		
		/**
		 * 左侧选中框checkbox
		 * */
		$(document).on("click",".Js-hhj-domain-courseware-allpage-item-checkbox, .Js-hhj-domain-tests-allpage-item-checkbox,.Js-hhj-domain-papers-allpage-item-checkbox",function(){
			var $thiz = $(this),
			$thizVal = $thiz.val(),
			selectedIds = "",
			flag="",
			tabVal = $(".Js-hhj-domain-content-control-tab").find("li[class='active']").attr("data-val");
			if (tabVal=="course") {
				selectedIds = $(".Js-hhj-domain-content-modal-current-checkbox-courseware").val();
			} 
			if(tabVal=='tests') {
				selectedIds = $(".Js-hhj-domain-content-modal-current-checkbox-tests").val();
			}
			if(tabVal=='paper') {
				selectedIds = $(".Js-hhj-domain-content-modal-current-checkbox-papers").val();
			}
			if($thiz.is(":checked")) {
				/* 右侧显示已选项  但是未存库（翻页有问题，暂时不用）
				$(".Js-hhj-domain-courseware-selectedpage-items")
					.append('<div class="checkbox Js-hhj-domain-courseware-selectedpage-item" data-id="'+$thiz.val()+'">'
					+'<label class="checkbox-inline">'
					+$thiz.attr("data-name")
					+'</label>'
					+'</div>');
				*/
				selectedIds = insertStr(selectedIds,$thizVal);
			} else {
				selectedIds = deleteStr(selectedIds,$thizVal);
			}
			selectedIds = selectedIds.replace(new RegExp(",+","g"),",").replace(/(^\,|\,$)/,"");
			if (tabVal=="course") {
				$(".Js-hhj-domain-content-modal-current-checkbox-courseware").val(selectedIds)
			} 
			if(tabVal=='tests') {
				$(".Js-hhj-domain-content-modal-current-checkbox-tests").val(selectedIds)
			}
			if(tabVal=='paper') {
				$(".Js-hhj-domain-content-modal-current-checkbox-papers").val(selectedIds)
			}
		});
		/**
		 * 内容权限控制提交
		 * */
		$(document).on("click",".Js-hhj-domain-content-submit",function(){
			var courseWareIds = $(".Js-hhj-domain-content-modal-current-checkbox-courseware").val(),
			testsIds=$(".Js-hhj-domain-content-modal-current-checkbox-tests").val(),
			paperIds=$(".Js-hhj-domain-content-modal-current-checkbox-papers").val(),
			tabVal = $(".Js-hhj-domain-content-control-tab").find("li[class='active']").attr("data-val"),
			category=3;
			
			if(tabVal=="course") {
				category=1;
			}
			if(tabVal=="tests"){
				category=2;
			}
			$.ajax({
				type : "POST",
				url : "${request.contextPath}/admin/domain/updateContentPrivilege",
				data : {
					"domainId" : $(".Js-hhj-domain-content-modal-current-domain-id").val(),
					"courseWareIds":courseWareIds,
					"testsIds":testsIds,
					"paperIds":paperIds,
					"category":category
				},
				success : function(data) {
					$(".Js-hhj-domain-papers-selectedpage-items").empty();
					$(".Js-hhj-domain-tests-selectedpage-items").empty();
					$(".Js-hhj-domain-papers-selectedpage-items").empty();
					if(data.paper==data.category){
						$(".Js-hhj-domain-papers-selectedpage-items").empty();
						$.each(data.selectedPapersPage.result,function(i,item){
							$(".Js-hhj-domain-papers-selectedpage-items")
								.append('<div class="checkbox Js-hhj-domain-papers-selectedpage-item" data-id="'+item.id+'">'
									+'<label class="checkbox-inline">'
									+(!Boolean(item.paperName)?'':item.paperName)
									+'</label>'
									+'</div>');
						});
						var selectedPageString = "";
							selectedPageString += '<table class="table table-striped">';
							selectedPageString += data.selectedPapersPageStr;
							selectedPageString += '</table>';
						$(".Js-hhj-domain-papers-selectedpage-page").html(selectedPageString);
					}
					if(data.course==data.category) {
						$(".Js-hhj-domain-courseware-selectedpage-items").empty();
						$.each(data.selectedPage.result,function(i,item){
							$(".Js-hhj-domain-courseware-selectedpage-items")
								.append('<div class="checkbox Js-hhj-domain-courseware-selectedpage-item" data-id="'+item.id+'">'
									+'<label class="checkbox-inline">'
									+(!Boolean(item.name)?'""':item.name)
									+'</label>'
									+'</div>');
							selectedIds = selectedIds + item.id + ",";
						});
						selectedIds = selectedIds.substring(0,selectedIds.length-1);
						var selectedPageString = "";
							selectedPageString += '<table class="table table-striped">';
							selectedPageString += data.selectedPageStr;
							selectedPageString += '</table>';
						$(".Js-hhj-domain-courseware-selectedpage-page").html(selectedPageString);
			    	}
			    	if(data.tests==data.category) {
						$(".Js-hhj-domain-tests-selectedpage-items").empty();
						
						$.each(data.selectedPage.result,function(i,item){
							$(".Js-hhj-domain-tests-selectedpage-items")
								.append('<div class="checkbox Js-hhj-domain-tests-selectedpage-item" data-id="'+item.id+'">'
									+'<label class="checkbox-inline">'
									+(!Boolean(item.name)?'""':item.name)
									+'</label>'
									+'</div>');
							selectedIds = selectedIds + item.id + ",";
						});
						selectedIds = selectedIds.substring(0,selectedIds.length-1);
						var selectedPageString = "";
							selectedPageString += '<table class="table table-striped">';
							selectedPageString += data.selectedPageStr;
							selectedPageString += '</table>';
						$(".Js-hhj-domain-tests-selectedpage-page").html(selectedPageString);
			    	}
					
					//window.location.reload();
				}
			});
		});
		
		/**
		 * 搜索框
		 * */
		$(document).on("click",".Js-hhj-domain-search-button",function(){
			var searchByName=$(".Js-hhj-domain-search-text").val();
			location.href="${request.contextPath}/admin/domain/domainList?inname="+searchByName;
		});
		
		/**
		 * 新增域名称验证
		 */
		jQuery.validator.addMethod("nameEditValidate", function(value, element) { 
			var editName= $(".Js-hhj-domain-add-name").val();
			var tmp = $.ajax({ //一个Ajax过程 
				type : "post",
				url : "${request.contextPath}/admin/domain/validateName",
				async:false,
				data : {
					"inname" : editName
				},
				success : function(data) {
				},
				error:function(){
					return false;
				}
			}).responseText;
			tmp = eval('(' + tmp + ')').errCode;
			if(tmp=="0") {
                return false;
			} else {
                return true;
			}
		}, "亲，域名称重复了，请换个名称");

    /**
     * 新增域账号验证
     */
    jQuery.validator.addMethod("usernameEditValidate", function (value, element) {
        var editName = $(".Js-hhj-insert-domain-user-username").val();
        var tmp = $.ajax({ //一个Ajax过程
            type: "post",
            url: "${request.contextPath}/admin/domain/validateUsername",
            async: false,
            data: {
                "inname": editName
            },
            success: function (data) {
            },
            error: function () {
                return false;
            }
        }).responseText;
        tmp = eval('(' + tmp + ')').errCode;
        if (tmp == "0") {
            return false;
        } else {
            return true;
        }
    }, "亲，域账号重复了，请换个名称");

    /**
     * 修改域名称验证
     */
    jQuery.validator.addMethod("nameEditUpdateValidate", function (value, element) {
        var editName = $(".Js-hhj-domain-edit-name").val();
        var orgId = $(".Js-hhj-domain-edit-update-id").val();
        var tmp = $.ajax({ //一个Ajax过程
            type: "post",
            url: "${request.contextPath}/admin/domain/validateName",
            async: false,
            data: {
                "inname": editName,
                "id":orgId
            },
            success: function (data) {
            },
            error: function () {
                return false;
            }
        }).responseText;
        tmp = eval('(' + tmp + ')').errCode;
        if (tmp == "0") {
            return false;
        } else {
            return true;
        }
    }, "亲，域名称重复了，请换个名称");

    /**
     * 修改域账号验证
     */
    jQuery.validator.addMethod("usernameUpdateEditValidate", function (value, element) {
        var editName = $(".Js-hhj-domain-edit-username").val();
        var orgId = $(".Js-hhj-domain-edit-update-id").val();
        var tmp = $.ajax({ //一个Ajax过程
            type: "post",
            url: "${request.contextPath}/admin/domain/validateUsername",
            async: false,
            data: {
                "inname": editName,
                "id": orgId
            },
            success: function (data) {
            },
            error: function () {
                return false;
            }
        }).responseText;
        tmp = eval('(' + tmp + ')').errCode;
        if (tmp == "0") {
            return false;
        } else {
            return true;
        }
    }, "亲，域账号重复了，请换个名称");
		
		//校验方法 
		$(function() {
			$("#saveDomainForm").validate({ 
				rules: { 
					username:{required:true,minlength:1,maxlength:20, specialCharValidate:true, usernameEditValidate: true},
					domainNumber:{required:true,minlength:1,maxlength:100}, 
					name:{required:true,minlength:1,maxlength:100,specialCharValidate:true,nameEditValidate:true},
					password:{required:true,minlength:5,specialCharValidate:true},
					url:{required:true,url:true}
				}, 
				messages: {  //提示信息
					username:{required: "请输入账号",minlength: "账号至少需要1个字符",maxlength:"账号不能超过20个字符", specialCharValidate:"有非法字符，请重新输入"},
					domainNumber:{required: "请输入编号",minlength: "编号至少需要1个字符",maxlength:"编号不能超过100个字符"}, 
					name:{required: "请输入名称",minlength: "名称至少需要1个字符",maxlength:"名称不能超过100个字符",
                        specialCharValidate: "有非法字符，请重新输入"},
					password:{required: "请输入密码",minlength: "密码至少需要5个字符", specialCharValidate: "有非法字符，请重新输入"},
					url:{required: "请输入网址",url:"请输入合法的网址"}
				}
			});
			$("#editDomainForm").validate({ 
				rules: { 
					username:{required:true,minlength:1,maxlength:20,specialCharValidate:true, usernameUpdateEditValidate: true},
					domainNumber:{required:true,minlength:1,maxlength:100}, 
					name:{required:true,minlength:1,maxlength:100,specialCharValidate:true,nameEditUpdateValidate:true},
					password:{required:true,minlength:5,maxlength:100,specialCharValidate:true},
					url:{required:true, url: true}
				}, 
				messages: {  //提示信息
					username:{required: "请输入账号",minlength: "账号至少需要1个字符",maxlength:"账号不能超过20个字符",
                        specialCharValidate: "有非法字符，请重新输入"},
					domainNumber:{required: "请输入编号",minlength: "编号至少需要1个字符",maxlength:"编号不能超过100个字符"}, 
					name:{required: "请输入名称",minlength: "名称至少需要1个字符",maxlength:"名称不能超过100个字符",
                        specialCharValidate: "有非法字符，请重新输入"},
					password:{required: "请输入密码",minlength: "密码至少需要5个字符",maxlength:"密码不能超过100个字符"
                        ,specialCharValidate: "有非法字符，请重新输入"},
					url:{required: "请输入网址", url:"请输入合法的网址"}
				}
			});
		});
		
		$(document).on("click",".Js-hhj-insert-domain-user-button",function(){
			$(".Js-hhj-insert-domain-user-username").val("");
			$(".Js-hhj-insert-domain-user-password").val("")
		});
	</script>
</body>
</html>
