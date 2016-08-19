<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>情景式诊断技能培训</title>
<script src="${request.contextPath}/resources/admin/js/jquery-1.11.1.min.js"></script>
<script src="${request.contextPath}/resources/web/js/common.js"></script>
<link href="${request.contextPath}/resources/web/demo/css/layout.css" rel="stylesheet" />
</head>
<body>
<!-- <input type="button" value="退出" onclick="exitStudy()" style="float:left;"/>-->
<div id="historyData" style="display:none;">
${data}
</div>
<div id="xmljson" style="display:none;">
${xmljson}
</div>
	<div class="container">
		<sitemesh:write property='body' />
		<iframe src="${request.contextPath}/course/${coursePath}" style="border:none; width: 1024px;height: 768px; margin:-1px auto; display:black;"></iframe>
	</div>
</body>
</html>