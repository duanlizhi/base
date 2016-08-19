<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<html>
  <head>
  	<meta charset="UTF-8">
  	<title>小车匠内容中心</title>
    <link href="<%=path%>/resources/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/resources/admin/css/cover.css" rel="stylesheet">
    <link href="<%=path%>/resources/admin/css/tip.css" rel="stylesheet">
    <script src="<%=path%>/resources/admin/js/jquery-2.1.3.min.js"></script>
    <script src="<%=path%>/resources/admin/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    function loginFailed(){
    	$("#userNameDiv").addClass("has-success");
    	$("#userNameSpan").addClass("glyphicon-ok");
    	$("#passwordDiv").addClass("has-error");
    	$("#passwordSpan").addClass("glyphicon-remove");
    }
    
    function initModal(){
    	$("#userNameDiv").removeClass("has-success");
    	$("#userNameSpan").removeClass("glyphicon-ok");
    	$("#passwordDiv").removeClass("has-error");
    	$("#passwordSpan").removeClass("glyphicon-remove");
    }
    
    $(document).ready(function() {
    	$("#userNameDiv").css('text-align','left');
    	$("#userNameDiv").css('color','#000000');
    	$("#passwordDiv").css('text-align','left');
    	$("#passwordDiv").css('color','#000000');
    });
    </script>
  </head>
  <body>
    <div class="site-wrapper">
      <div class="site-wrapper-inner">
        <div class="cover-container">
          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">小车匠</h3>
              <ul class="nav masthead-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">客户链接</a></li>
                <li><a href="#">公司网址</a></li>
              </ul>
            </div>
          </div>
          <div class="inner cover">
            <h1 class="cover-heading">Content Center</h1>
            <p class="lead"></p>
            <p class="lead">
				<button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#loginModal" onclick="initModal()"> 登录</button>

				<div class="modal fade bs-example-modal-sm" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-sm">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel"><font color="#000000">请输入您的用户名和密码</font></h4>
				      </div>
				      <form id="loginForm" action="<%=path%>/admin/admin/sublogin" method="post">
				      <div class="modal-body">
				      	
					      	<div id="userNameDiv" class="form-group has-feedback">
							  <label class="control-label" for="userName">用户名：</label>
							  <input type="text" class="form-control"  placeholder="请输入用户名" placeholder="用户名" id="username" name="username">
							  <span id="userNameSpan" class="glyphicon form-control-feedback"></span>
							</div>
							<div id="passwordDiv" class="form-group has-feedback">
							  <label class="control-label" for="password">密码：</label>
							  <input type="password" class="form-control" id="password" type="password" placeholder="密码" name="password" placeholder="请输入密码">
							  <span id="passwordSpan" class="glyphicon form-control-feedback"></span>
							</div>
				       
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				        <button type="submit" class="btn btn-primary">登录</button>
				        <!--  
				        <button type="button" class="btn btn-primary" onclick="loginFailed()">登录失败</button>
				        -->
				      </div>
				       </form>
				    </div>
				  </div>
				</div>
				
            </p>
            <!-- p class="lead"><a href="test.html" class="btn btn-lg btn-default">测试</a></p-->
          </div>
          <div class="mastfoot">
            <div class="inner">
              <p>by <a href="http://www.xiaochejiang.com">小车匠信息系统有限公司</a></p>
            </div>
          </div>
        </div>
      </div>
    </div>



	<script src="<%=path%>/resources/admin/js/jquery.validate.js" type="text/javascript"></script>
	
	<script type='text/javascript' src='<%=path%>/resources/admin/js/messages_zh.js'></script>


<script type="text/javascript">
		 //身份证校验
		$.validator.addMethod("passwordNew", function (value, element) {
			var length = value.length;
			//var qh =  /^(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;
			 var qh=/^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{4,20}$/;
			 return this.optional(element) || qh.test(value);  
		 }, "密码格式不正确"); 
	
			 //校验方法
		 $().ready(function() {
 		   $("#loginForm").validate({ 
					 	 // 定义验证规则    
			    rules: {  
			        username:{ required:true,minlength:2,maxlength:30}, 
			        password: {required: true,  passwordNew: true, rangelength: [4, 20]}
			    }, 
			     messages: {  //提示信息
			        username:{required: "请输入用户名",minlength: "用户名至少两个字符",maxlength:"用户名不能超过80个字符"},  
			        password: { required: "请输入密码",passwordNew:"密码的格式不正确",rangelength:"密码的长度为8到15位"}
			     }
		  });
		 });
</script>
</body>
</html>
