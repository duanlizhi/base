<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	color: #428bca;
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
	background: #428bca;
	border-color: #2B2E33;
}

#main-nav.nav-tabs.nav-stacked>li.active>a,#main-nav.nav-tabs.nav-stacked>li>a:hover>span
	{
	color: #FFF;
	background-color: #428bca;
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
	color: #428bca;
	text-align: center;
	border-radius: 1px;
}

.secondmenu>li>a:hover {
	background-color: #428bca;
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

	</head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid"> 
        <div class="navbar-header">
          <a class="navbar-brand" href="#">Content Center</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">客户链接</a></li>
            <li><a href="#">公司网址</a></li>
            <li>
            	<a href='javascript:;' onclick='doUpdate()'><shiro:principal/></a> 
            	<form action="http://www.xcj.com:8081/page/tab1" target="_blank" method="POST" style="display: none;" name="updateCurrentUserform">
            		<input name="username" value="<shiro:principal/>">
            	</form>
            </li>
            <li><a href="#">退出</a></li>
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
      
        <div class="col-sm-3 col-md-2 sidebar">
          <ul id="main-nav" class="main-nav nav nav-tabs nav-stacked" style="width:300px;">
                    <li class="active">
                        <a href="#">
                            <i class="glyphicon glyphicon-th-large"></i>
                      		      首页 		
                        </a>
                    </li>
                    <li>
                        <a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-cog"></i>
                            	系统管理
                            <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                        </a>
                        <ul id="systemSetting" class="nav nav-list secondmenu collapse" style="height: 0px;">
                            <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>&nbsp;域管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>&nbsp;标签管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>&nbsp;账号管理</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#configSetting" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-credit-card"></i>
                            		课题卷
                                   <span class="pull-right glyphicon  glyphicon-chevron-toggle"></span>
                        </a> <!-- class="active"   in -->
                        <ul id="configSetting" class="nav nav-list secondmenu collapse">
                            <li><a href="#"><i class="glyphicon glyphicon-globe"></i>&nbsp;课程库</a></li>
                            <li><a href="<%=path%>/admin/question/index"><i class="glyphicon glyphicon-star-empty"></i>&nbsp;题库</a></li>
                            <li><a href="<%=path%>/admin/course/index"><i class="glyphicon glyphicon-star"></i>&nbsp;测评库</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="#disSetting" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-globe"></i>
                            		数据统计
							 <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                        </a>
                    </li>

                    <li>
                        <a href="#dicSetting" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-bold"></i>
                           	 知识体系
                            <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="glyphicon glyphicon-fire"></i>
                           		 关于系统
                            <span class="badge pull-right">1</span>
                        </a>
                    </li>

                </ul>
          
        </div>
		<!-- main  start-->
		
		<!-- main  end-->

      </div>
    </div>
    <script type="text/javascript">
    	function doUpdate(){
    		document.updateCurrentUserform.submit();
    	}
    </script>
  </body>
</html>
