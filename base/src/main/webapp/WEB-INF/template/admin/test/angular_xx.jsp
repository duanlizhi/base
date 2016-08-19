<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<html ng-app="drag">
 <body>
I can add: {{ 1+2 }}.
		<script type="text/javascript"	src="<%=path%>/resources/admin/js/angular.js"></script>
		<script type="text/javascript"	src="<%=path%>/resources/admin/js/jquery-1.11.0.js"></script>

<script type="text/javascript">
angular.module('drag', []).
directive('draggable', function($document) {
var startX=0, startY=0, x = 0, y = 0;
return function(scope, element, attr) {
element.css({
position: 'relative',
border: '1px solid red',
backgroundColor: 'lightgrey',
cursor: 'pointer'
});
element.bind('mousedown', function(event) {
startX = event.screenX - x;
startY = event.screenY - y;
$document.bind('mousemove', mousemove);
$document.bind('mouseup', mouseup);
});
function mousemove(event) {
y = event.screenY - startY;
x = event.screenX - startX;
element.css({
top: y + 'px',
left: x + 'px'
});
}
function mouseup() {
$document.unbind('mousemove', mousemove);
$document.unbind('mouseup', mouseup);
}
}
});

	function GreetCtrl($scope) {
	$scope.name = 'World';
	}
	function ListCtrl($scope) {
	$scope.names = ['Igor', 'Misko', 'Vojta'];
	}
	
	
	function MyCtrl($scope) {
$scope.action = function() {
$scope.name = 'OK';
}
$scope.name = 'World';
}


function Ctrl1($scope) {
$scope.name = 'angular';
}


</script>

<style type="text/css">
.show-scope .doc-example-live.ng-scope,
.show-scope .doc-example-live .ng-scope {
border: 1px solid red;
margin: 3px;
}
</style>
<body>
<span draggable>Drag ME</span>
 <hr>
	<input ng-model="name">
<p>你好我是动态改变的： {{name}}!</p>


<hr>

<div ng-controller="GreetCtrl">
Hello {{name}}!
</div>
<div ng-controller="ListCtrl">
<ol>
<li ng-repeat="name in names">{{name}}</li>
</ol>
</div>





<div ng-controller="MyCtrl">
Hello {{name}}!
<button ng-click="action()">
OK
</button>
</div>



<hr>
<div ng-init="list = ['Chrome', 'Safari', 'Firefox', 'IE'] ">
<input ng-model="list" ng-list> <br>
<input ng-model="list" ng-list> <br>
<pre>list={{list}}</pre> <br>
<ol>
<li ng-repeat="item in list">

	{{item}}

</li>
</ol>
</div>

<hr>
<div ng-init="list = ['Chrome', 'Safari', 'Firefox', 'IE'] ">
Number formatting: {{ 1234567890 | number }} <br>
array filtering <input ng-model="predicate">
{{list|filter:predicate | json }}
</div>

<hr>
<div ng-controller="Ctrl1">
Hello <input ng-model='name'> <hr/>
&ltspan ng:bind="name"&gt <span ng:bind="name"></span> <br/>
&ltspan ng_bind="name"&gt <span ng_bind="name"></span> <br/>
&ltspan ng-bind="name"&gt <span ng-bind="name"></span> <br/>
&ltspan data-ng-bind="name"&gt <span data-ng-bind="name"></span> <br/>
&ltspan x-ng-bind="name"&gt <span x-ng-bind="name"></span> <br/>
</div>
<hr>


<button ng-click="show=true">show</button>
<dialog title="Hello {{username}}."
visible="show"
on-cancel="show = false"
on-ok="show = false; doSomething()">
Body goes here: {{username}} is {{title}}.
</dialog>

<hr>

<div class="example2" ng-controller="Cntl1">
Name: <input ng-model="name" type="text"/>
<button ng-click="greet()">Greet</button>
</div>
<script type="text/javascript">
function Cntl1($window, $scope){
$scope.name = 'World';
$scope.greet = function() {
($window.mockWindow || $window).alert('Hello ' + $scope.name);
}
}
</script>

{{123|number:2}}

</body>
</html>