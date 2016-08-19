<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	 

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

							<li><a href="#">用户添加</a></li>

							 

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->
	
	 					<div class="row-fluid">
 
		<!-- BEGIN EXAMPLE TABLE PORTLET-->

						<div class="portlet box blue">

							<div class="portlet-title">
								
									
								<div class="caption">
								
								<!--<i class="icon-globe"></i>-->
								
								用户添加 
								 
								</div>
								<div class="actions">
								 
								 <div class="input-prepend">
								 
											 
												 
											<!-- &nbsp;&nbsp;&nbsp;
												<button class="btn green" type="button" >返&nbsp;&nbsp;回</button>-->
												 
										</div>			
								 
								</div>
								 

							</div>

							<div class="portlet-body">
									 
									 	<table class="table table-bordered " id="sample_2">

								     <tbody>

										<tr>
											<th>用户名</th>

											<td>  
												<input class="m-wrap medium" size="10" type="text" placeholder="用户名" />
											</td>

											<th class="hidden-480">密码</th>

											<td class="hidden-480">
												<input class="m-wrap medium" size="10" type="text" placeholder="密码" />
											</td>

										</tr>
										<tr>
											<th>姓名</th>

											<td>  
												<input class="m-wrap medium" size="10" type="text" placeholder="姓名" />
											</td>

											<th class="hidden-480">邮箱</th>

											<td class="hidden-480">
												<input class="m-wrap medium" size="10" type="text" placeholder="邮箱" />
											</td>

										</tr>
										<tr>
											<th>姓名</th>
 
											<td  colspan="3" class="hidden-480">
												<input type="text" class="m-wrap span12" size="16" type="text" placeholder="邮箱" >
										 
											</td>

										</tr>
										<tr>
											<th>姓名</th>
 
											<td  colspan="3" class="hidden-480">
												<input type="text" class="m-wrap span12" size="16" type="text" placeholder="邮箱" >
										 
											</td>

										</tr>
										<tr>
											<th>用户级别</th>
												<td  colspan="3" class="hidden-480">
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
											</td>
										</tr>		
									</tbody>
									

								</table>
								
									<div class="form-actions">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<button class="btn blue" type="button">返&nbsp;&nbsp;回</button>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<button class="btn blue" type="button">重&nbsp;&nbsp;置</button>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<button class="btn blue" type="submit"><i class="icon-ok"></i> 确&nbsp;&nbsp;定</button>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													
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