<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>故障诊断模拟器测评</title>
		<script src="<%=path%>/resources/admin/js/jquery-1.11.1.min.js"></script>
		<script src="<%=path%>/resources/web/js/commonTests.js"></script>
		<link href="${request.contextPath}/resources/web/demo/css/layout.css" rel="stylesheet" />
</head>
<body><body>
	<div class="container">
		<sitemesh:write property='body' />
		<iframe src="${request.contextPath}/course/${coursePath}" style="border:none; width: 1024px;height: 768px; margin:-1px auto; display:black;" id="wbtFrame" name="wbtFrame"></iframe>
	</div>
	<div id="historyData" style="display:none;">${data}</div>
	<div id="xmljson" style="display:none;">${xmljson}</div>
	<div id="score" style="display:none;">${score}</div>
	<div id="testsPosted" style="display:none;">${testsPosted}</div>
	<div id="surplusTime" style="display:none;">${surplusTime}</div>
	<form method="post" action="" id="backHomePageForm">
		<input type="hidden" value="" name="domainUsername" id="domainUsername"/>
		<input type="hidden" value="" name="userEmail" id="userEmail"/>
		<input type="hidden" value="" name="domainFlag" id="domainFlag"/>
	</form>
</body>
</html>