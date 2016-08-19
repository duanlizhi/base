<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html ng-app="testServiceDetail">
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>系统</title>
 <!--[if lte IE 8]>
<script>
document.createElement('ng-include');
document.createElement('ng-pluralize');
document.createElement('ng-view');
// Optionally these for CSS
document.createElement('ng:include');
document.createElement('ng:pluralize');
document.createElement('ng:view');
</script>
<![endif]-->
<script type="text/javascript"	src="<%=path%>/resources/admin/js/jquery-1.11.0.js"></script>
<script type="text/javascript"	src="<%=path%>/resources/admin/js/angular.js"></script>
<script type="text/javascript" src="<%=path%>/resources/admin/js/angular-resource.js"></script>
<link href="<%=path%>/resources/admin/css/page.css" type="text/css" rel="stylesheet" />
 <script type="text/javascript">
	var projectName = '/' + window.location.pathname.split('/')[1];
	var save = angular.module('testServiceDetail', [ 'ngResource' ], angular.noop);
 	  
 	  
 	  
</script>
		<style type="text/css">
input.ng-invalid {
	border: 1px solid red;
}

input.ng-valid {
	border: 1px solid green;
}
.ng-pristine {}
.ng-dirty {}
.ng-valid {}
.ng-invalid {}
</style>
<body>
<div id="detail"
  style="display: block; margin-left: auto; margin-right: auto;">
  <table class="table table-hover table-nomargin table-bordered usertable dataTable">
   <tr>
    <td>id</td>
    <td>
    	 ${test.id}
     </td>
   </tr>
   
   <tr>
    <td>用户名</td>
    <td>
    	  ${test.username}
     </td>
   </tr>
   <tr>
    <td>密码</td>
    <td> 
    	 ${test.password}
     </td>
   </tr>
  </table>
 </div>

 
 	</body>
</html>