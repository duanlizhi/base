<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.xiaochejiang.com/tags/top" prefix="t"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>小车匠内容中心</title>
    <link href="<%=path%>/resources/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet">
<!-- 此版本是黑色版本 -->

<style type="text/css">
body {
	font-family: 'Microsoft Yahei', '微软雅黑', '宋体', \5b8b\4f53, Tahoma, Arial,
		Helvetica, STHeiti;
	margin: 0;
}

.main-nav {
	margin-left: 1px;
}

.main-nav.nav-tabs.nav-stacked>li {
}

.main-nav.nav-tabs.nav-stacked>li>a {
	padding: 10px 8px;
	font-size: 12px;
	font-weight: 600;
	color: #4A515B;
	background: #E9E9E9;
	border: 1px solid #D5D5D5;
	border-radius: 1px;
}

.main-nav.nav-tabs.nav-stacked>li>a>span {
	color: #4A515B;
}

.main-nav.nav-tabs.nav-stacked>li.active>a,#main-nav.nav-tabs.nav-stacked>li>a:hover
	{
	color: #FFF;
	background: #3C4049;
	border-color: #2B2E33;
}

#main-nav.nav-tabs.nav-stacked>li.active>a,#main-nav.nav-tabs.nav-stacked>li>a:hover>span
	{
	color: #FFF;
}

.main-nav.nav-tabs.nav-stacked>li {
	margin-bottom: 4px;
}

.nav-header.collapsed>span.glyphicon-chevron-toggle:before {
	content: "\e114";
}

.nav-header>span.glyphicon-chevron-toggle:before {
	content: "\e113";
}

footer.duomi-page-footer {
	background-color: white;
}

footer.duomi-page-footer .beta-message {
	color: #a4a4a4;
}

footer.duomi-page-footer .beta-message a {
	color: #53a2e4;
}

footer.duomi-page-footer .list-inline a,footer.authenticated-footer .list-inline li
	{
	color: #a4a4a4;
	padding-bottom: 30px;
}

footer.duomi-page-footer {
	background-color: white;
}

footer.duomi-page-footer .beta-message {
	color: #a4a4a4;
}

footer.duomi-page-footer .beta-message a {
	color: #53a2e4;
}

footer.duomi-page-footer .list-inline a,footer.authenticated-footer .list-inline li
	{
	color: #a4a4a4;
	padding-bottom: 30px;
}

/*********************************************自定义部分*********************************************/
.secondmenu a {
	font-size: 12px;
	color: #4A515B;
	text-align: center;
	border-radius: 1px;
}

.secondmenu>li>a:hover {
	background-color: #6f7782;
	border-color: #428bca;
	color: #fff;
}

.secondmenu li.active {
	background-color: #6f7782;
	border-color: #428bca;
	border-radius: 1px;
}

.secondmenu li.active>a {
	color: #ffffff;
}

.navbar-static-top {
	background-color: #212121;
	margin-bottom: 5px;
}

.navbar-brand {
	background: url('http://static-res.ruyo.net/ruyo_net_w_32.png')
		no-repeat 10px 8px;
	display: inline-block;
	vertical-align: middle;
	padding-left: 50px;
	color: #fff;
}

.navbar-brand:hover {
	color: #fff;
}

.collapse.glyphicon-chevron-toggle,.glyphicon-chevron-toggle:before {
	content: "\e113";
}

.collapsed.glyphicon-chevron-toggle:before {
	content: "\e114";
}
</style>
<script type="text/javascript">
function goUrl(url){
	if(url.trim()!=null||url.trim()!=""){
	 	location.href="<%=path%>"+url;
	}else{
		return false;
	}
}
</script>
	</head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid"> 
        <div class="navbar-header">
          <a class="navbar-brand" href="#">小车匠内容中心</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">客户链接</a></li>
            <li><a href="#">公司网址</a></li>
            <li>
            	<a href='javascript:;' onclick='javascript:void(0);'><shiro:principal/></a> <!-- doUpdate() -->
            	<form action="http://www.xcj.com:8081/page/tab1" target="_blank" method="POST" style="display: none;" name="updateCurrentUserform">
            		<input name="username" value="<shiro:principal/>">
            	</form>
            </li>
            <li><a href="<%=path%>/admin/admin/logout">退出</a></li>
          </ul>
          <form class="navbar-form navbar-right">
			<select class="form-control">
				<option>全部内容范围</option>
				<option>WBT</option>
				<option>SBT</option>
				<option>拆装保养</option>
				<option>软教具</option>
				<option>试题</option>
				<option>测评</option>
				<option>其他</option>
			</select>
            <input type="text" class="form-control" placeholder="Search Content">
            <button type="submit" class="btn btn-default">搜索</button>
          </form>
        </div>
      </div>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar" >
			<t:top/>
        </div>
      </div>
    </div>
    <script type="text/javascript">
    	function doUpdate(){
    		document.updateCurrentUserform.submit();
    	}
    </script>
  </body>
</html>
