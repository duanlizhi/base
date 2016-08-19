<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html ng-app="testServiceList">
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
<script type="text/javascript"	src="<%=path%>/resources/admin/js/angular.js"></script>
<script type="text/javascript" src="<%=path%>/resources/admin/js/angular-resource.js"></script>
<script type="text/javascript"	src="<%=path%>/resources/admin/js/jquery-1.11.0.js"></script>
<link href="<%=path%>/resources/admin/css/page.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	var projectName = '/' + window.location.pathname.split('/')[1];
	var userUrl = {
    	'queryUrl' : projectName + '/admin/test/testList'
	};
	var test = angular.module('testServiceList', [ 'ngResource' ], angular.noop);
 	test.controller('testControllerList', function($scope, $resource) {
 		var actions = {
        'query' : {
            method : 'GET',
            isArray : true
        } 
    	};
 		var testList = $resource(userUrl.queryUrl, {
       		 
    	}, actions);
	 	 testList.query({}, function(data) {
	        subobj = data;
	        $scope.mydata = data;
	    });
 	  });
	

</script>
</head>
<body ng-controller="testControllerList">

 <div id="testList" style="margin-left: auto; margin-right: auto;">
	 <table border="1" class="table table-hover table-nomargin table-bordered usertable dataTable">
		   <tr>
	    <th>序号</th>
	    <th ng-click="f='id'; rev=!rev">用户id</th>
	    <th ng-click="f='username'; rev=!rev">用户名称</th>
	    <th ng-click="f='password'; rev=!rev">用户密码</th>
	    <th ng-click="f='id'; rev=!rev">用户操作</th>
	   </tr>
	   <tr ng-repeat="test in mydata  | orderBy: f : rev" ng-class-even="'even'"
	    ng-class-odd="'odd'">
	    <td>{{$index + 1}}</td>
	    <td>{{test.id}}</td>
	    <td>{{test.username}}</td>
	    <td>{{test.password}}</td>
	    <td>
	    	<a href="<%=path%>/admin/test/save" ng-click="">添加</a> &nbsp;
	    	<a href="<%=path%>/admin/test/{{test.id}}/update" ng-click="">修改</a> &nbsp;	
	    	<a href="<%=path%>/admin/test/{{test.id}}"  ng-click="">查看</a>
	    	<a href="<%=path%>/admin/test/{{test.id}}/delete" ng-click="">删除</a></td>
	   </tr>
	  </table>
 	</div>
	</body>
</html>