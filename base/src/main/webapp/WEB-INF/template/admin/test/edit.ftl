<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html ng-app="testServiceAdd">
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
 <script type="text/javascript">
	var projectName = '/' + window.location.pathname.split('/')[1];
	var save = angular.module('testServiceAdd', [ 'ngResource' ], angular.noop);
	
	
 	  save.controller('testControllerAdd', function($scope, $resource) {
 	 
	 	 	 
 	  });
 	  
 	  
 	  
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
<div id="save"
  style="display: block; margin-left: auto; margin-right: auto;">
  <form name="test_form" action="save" ng-controller="testControllerAdd" method="post" ng-submit="testForm()">
  <table>
   <tr>
    <td>用户名</td>
    <td><input type="text" name="username"
     ng-model="test.username"  required  ng-minlength="4" ng-maxlength=20 ensure-unique="username" />
  <div class="error" ng-show="test_form.username.$dirty && test_form.username.$invalid && !test_form.username.$focused"">
    <small class="error" ng-show="test_form.username.$error.required">请输入姓名</small>
    <small class="error" ng-show="test_form.password.$error.minlength">您输入的姓名不能小于3个字符！</small>
	<small class="error" ng-show="test_form.password.$error.maxlength">您输入的姓名不能大于20字符！</small>
    <small class="error" ng-show="test_form.username.$error.unique">That username is taken, please try another</small>
  </div>
     </td>
   </tr>
   <tr>
    <td>密码</td>
    <td><input type="password" name="password"
     ng-model="test.password"  required  required  ng-minlength="4" ng-maxlength=20/>
 	 <div class="error" ng-show="test_form.password.$dirty && test_form.password.$invalid">
	    <small class="error" ng-show="test_form.password.$error.required">请输入密码</small>
	    <small class="error" ng-show="test_form.password.$error.minlength">您输入的密码不能小于3个字符！</small>
	    <small class="error" ng-show="test_form.password.$error.maxlength">您输入的密码不能大于20字符！</small>
  	</div>
     </td>
   </tr>
   <tr>
    <td colspan="2">
      <input type="submit" ng-disabled="test_form.$invalid"  class="button" value="添加" />
    </td>
   </tr>
  </table>
  </form>
 </div>

 
 	</body>
</html>