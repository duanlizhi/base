<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.xiaochejiang.com/tags/pager" prefix="w"%>
<%
	String path = request.getContextPath();
%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>Metronic | Data Tables - Editable Tables</title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />
	
	<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="<%=path%>/resources/admin/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/resources/admin/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/resources/admin/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	
	

	<link href="<%=path%>/resources/admin/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/resources/admin/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/resources/admin/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/resources/admin/css/default-main.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="<%=path%>/resources/admin/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	 

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/admin/css/select2_metro.css" />

	<link rel="stylesheet" href="<%=path%>/resources/admin/css/DT_bootstrap.css" />

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="resources/image/favicon.ico" />
	
	
	
	</head>
	
	
	<body class="page-header-fixed">
	<!-- BEGIN PAGE TITLE & BREADCRUMB-->

						<h3 class="page-title">

						 

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="###">用户管理</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">用户列表</a></li>

							 

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->
	
	 					<div class="row-fluid">
<div class="span12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->

						<div class="portlet box blue">

							<div class="portlet-title">
								
									
								<div class="caption">
								
								<!--<i class="icon-globe"></i>-->
								
							用户管理 
								
								
								
								
								</div>
								<div class="actions">
								 
								 <div class="input-prepend">
								 
								 <div class="btn-group">

													<button class="btn dropdown-toggle" data-toggle="dropdown">
													分类信息
													<span class="caret"></span></button>
													<ul class="dropdown-menu">

														<li><a href="#">乐活</a></li>

														<li><a href="#">养生</a></li>

														<li><a href="#">唯美</a></li>

														<li class="divider"></li>

														<li><a href="#">创业</a></li>

													</ul>

											</div>
											&nbsp;&nbsp;&nbsp;
											<div class="btn-group">

													<button class="btn dropdown-toggle" data-toggle="dropdown">

													用户级别 

													<span class="caret"></span></button>

													<ul class="dropdown-menu">

														<li><a href="#">一级</a></li>

														<li><a href="#">二级</a></li>

														<li><a href="#">三级级</a></li>

														<li class="divider"></li>

														<li><a href="#">1级</a></li>

													</ul>

											</div>
												&nbsp;&nbsp;&nbsp;
												<input class="m-wrap medium" type="text" style="background:#fff"/>
												&nbsp;&nbsp;&nbsp;
												<button class="btn green" type="button" >搜索</button>
												 
										</div>			
								 
								</div>
								<!-- 
									<div class="actions">

									<div class="btn-group">

										<a class="btn" href="#" data-toggle="dropdown">

										选择列

										<i class="icon-angle-down"></i>

										</a>

										<div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

											<label><input type="checkbox" checked data-column="0">ID</label>

											<label><input type="checkbox" checked data-column="1">用户名</label>

											<label><input type="checkbox" checked data-column="2">姓名</label>

											<label><input type="checkbox" checked data-column="3">邮箱</label>

											<label><input type="checkbox" checked data-column="4">地址</label>

										</div>

									</div>

								</div>
								
								
								-->

							</div>

							<div class="portlet-body">

								<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">

									<thead>

										<tr>

											<th>ID</th>

											<th>用户名</th>

											<th class="hidden-480">姓名</th>

											<th class="hidden-480">邮箱</th>

											<th class="hidden-480">地址</th>
											
											<th class="hidden-480">操作</th>

										</tr>

									</thead>

									<tbody>

										<tr>

											<td>1</td>

											<td> sujiansoft

											</td>

											<td class="hidden-480">苏建</td>

											<td class="hidden-480">sujiansoft@163.com</td>

											<td class="hidden-480">北京市西二旗</td>
											
										 <td class="hidden-480"> 
										  <a href="<%=path%>/admin/demo/usersave">添加</a>&nbsp;
										 |&nbsp;<a href="<%=path%>/admin/demo/userupdate">编辑</a>&nbsp;
										 |&nbsp;<a href="###">删除</a>&nbsp;
										 |&nbsp;<a href="###">查看</a> 
										 </td>
										</tr>

										<tr >

											<td>2</td>

											<td>luguosoft

											</td>

											<td class="hidden-480">路过</td>

											<td class="hidden-480">luguosoft@163.com</td>

											<td class="hidden-480">北京市沙河地铁</td>
										 <td class="hidden-480"> 
										    <a href="<%=path%>/admin/demo/usersave">添加</a>&nbsp;
										 |&nbsp;<a href="<%=path%>/admin/demo/userupdate">编辑</a>&nbsp;
										 |&nbsp;<a href="###">删除</a>&nbsp;
										 |&nbsp;<a href="###">查看</a> 
										 
										 </td>
										</tr>

										<tr >

											<td>3</td>

											<td> fangting

											</td>

											<td class="hidden-480">方婷</td>

											<td class="hidden-480">fangting@163.com</td>

											<td class="hidden-480">江西省</td>
										 <td class="hidden-480">  
										   <a href="<%=path%>/admin/demo/usersave">添加</a>&nbsp;
										 |&nbsp;<a href="<%=path%>/admin/demo/userupdate">编辑</a>&nbsp;
										 |&nbsp;<a href="###">删除</a>&nbsp;
										 |&nbsp;<a href="###">查看</a> 
										 </td>
										</tr>

										<tr >

											<td>4</td>

											<td> liuxuetao@164.com

											</td>

											<td class="hidden-480">刘学涛</td>

											<td class="hidden-480">liuxuet@163.com</td>

											<td class="hidden-480">河北省张家口市</td>
										 <td class="hidden-480">
										   <a href="<%=path%>/admin/demo/usersave">添加</a>&nbsp;
										 |&nbsp;<a href="<%=path%>/admin/demo/userupdate">编辑</a>&nbsp;
										 |&nbsp;<a href="###">删除</a>&nbsp;
										 |&nbsp;<a href="###">查看</a> 
										 </td>
										</tr>

										<tr>

											<td>1</td>

											<td> sujiansoft

											</td>

											<td class="hidden-480">苏建</td>

											<td class="hidden-480">sujiansoft@163.com</td>

											<td class="hidden-480">北京市西二旗</td>
										 <td class="hidden-480"> 
										  <a href="<%=path%>/admin/demo/usersave">添加</a>&nbsp;
										 |&nbsp;<a href="<%=path%>/admin/demo/userupdate">编辑</a>&nbsp;
										 |&nbsp;<a href="###">删除</a>&nbsp;
										 |&nbsp;<a href="###">查看</a> 
										 </td>
										</tr>

										<tr >

											<td>2</td>

											<td>luguosoft

											</td>

											<td class="hidden-480">路过</td>

											<td class="hidden-480">luguosoft@163.com</td>

											<td class="hidden-480">北京市沙河地铁</td>
										 <td class="hidden-480"> 
										   <a href="<%=path%>/admin/demo/usersave">添加</a>&nbsp;
										 |&nbsp;<a href="<%=path%>/admin/demo/userupdate">编辑</a>&nbsp;
										 |&nbsp;<a href="###">删除</a>&nbsp;
										 |&nbsp;<a href="###">查看</a> 
										 </td>
										</tr>

										<tr >

											<td>3</td>

											<td> fangting

											</td>

											<td class="hidden-480">方婷</td>

											<td class="hidden-480">fangting@163.com</td>

											<td class="hidden-480">江西省</td>
										 <td class="hidden-480">
										  <a href="<%=path%>/admin/demo/usersave">添加</a>&nbsp;
										 |&nbsp;<a href="<%=path%>/admin/demo/userupdate">编辑</a>&nbsp;
										 |&nbsp;<a href="###">删除</a>&nbsp;
										 |&nbsp;<a href="###">查看</a> 
										 </td>
										</tr>

										<tr >

											<td>4</td>

											<td> liuxuetao@164.com

											</td>

											<td class="hidden-480">刘学涛</td>

											<td class="hidden-480">liuxuet@163.com</td>

											<td class="hidden-480">河北省张家口市</td>
										 <td class="hidden-480"> 
										    <a href="<%=path%>/admin/demo/usersave">添加</a>&nbsp;
										 |&nbsp;<a href="<%=path%>/admin/demo/userupdate">编辑</a>&nbsp;
										 |&nbsp;<a href="###">删除</a>&nbsp;
										 |&nbsp;<a href="###">查看</a> 
										 </td>
										</tr>
										
									</tbody>
										
								</table>
								<table class="table  table-full-width" id="sample_2">
								<w:pager pageSize="5" currentPage="1" url="./userlist" totalsCount="18" />
								</table>
							</div>
												
							
				</div>

						<!-- END EXAMPLE TABLE PORTLET-->
			</div>	
			</div>
	 
	 <script src="<%=path%>/resources/admin/js/jquery-1.10.1.min.js" type="text/javascript"></script>
    <!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
   <script src="<%=path%>/resources/admin/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="<%=path%>/resources/admin/js/excanvas.min.js"></script>

	<script src="<%=path%>/resources/admin/js/respond.min.js"></script>  

	<![endif]-->                    

	<script src="<%=path%>/resources/admin/js/jquery.slimscroll.min.js" type="text/javascript"></script>

   <!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script type="text/javascript" src="<%=path%>/resources/admin/js/select2.min.js"></script>

	<script type="text/javascript" src="<%=path%>/resources/admin/js/jquery.dataTables.min.js"></script>

	<script type="text/javascript" src="<%=path%>/resources/admin/js/DT_bootstrap.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="<%=path%>/resources/admin/js/app.js"></script>

	<script src="<%=path%>/resources/admin/js/table-advanced.js"></script>     
	
 <script>

		jQuery(document).ready(function() {       

		   App.init();

		   //TableAdvanced.init();

		});

	</script>

				
<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>

<!-- END BODY -->
				
</html>