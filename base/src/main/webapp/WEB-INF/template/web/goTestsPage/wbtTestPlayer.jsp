<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>情景式诊断技能测评</title>
		<script src="<%=path%>/resources/admin/js/jquery-1.11.1.min.js"></script>
		<script src="<%=path%>/resources/web/js/commonTests.js"></script>
		<link href="${request.contextPath}/resources/web/demo/css/layout.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<sitemesh:write property='body' />
		<iframe src="${request.contextPath}/course/${coursePath}" style="border:none; width: 1024px;height: 768px; margin:-1px auto; display:black;" id="wbtFrame" name="wbtFrame"></iframe>
	</div>

	<div id="historyData" style="display:none;">${data}</div>
	<div id="xmljson" style="display:none;">${xmljson}</div>
	<div id="score" style="display:none;">${score}</div>
	<div id="testsPosted" style="display:none;">${testsPosted}</div>
	<div id="surplusTime" style="display:none;">${surplusTime}</div>
	
	<form method="post" action="" id="testsForm">
		<input type="hidden" value="${domainUsername}" name="domainUsername" id="domainUsername"/>
		<input type="hidden" value="${userEmail}" name="userEmail" id="userEmail"/>
		<input type="hidden" value="" name="objData" id="objData"/>
		<input type="hidden" value="${token}" name="token" id="token"/>
		<input type="hidden" value="" name="currentCourseId" id="currentCourseId"/>
		<input type="hidden" value="${paperId}" name="paperId" id="paperId"/>
		<input type="hidden" value="${testsPostedId}" name="testsPostedId" id="testsPostedId"/>
	</form>
		<c:forEach var="courseWare" items="${courseWare}" varStatus="status">
			<div style="display:none;" id="${courseWare.courseNumber}">${courseWare.id}</div>
		</c:forEach>
</body>
<script type="text/javascript">
	function initTests(courseNumber){
		var currentCourseId = $("#"+courseNumber).html();
		$("#currentCourseId").val(currentCourseId);
		
		var initTestsSessionIdObj = content.tests.getSessionId($("#domainUsername").val(),$("#userEmail").val(),$("#token").val());
		if(eval(initTestsSessionIdObj).errCode == 0){
			var sessionId = eval(eval(initTestsSessionIdObj).data).sessionId;
			var initTestsPublidIdObj = content.tests.getTestsPublicId($("#domainUsername").val(),$("#userEmail").val(),$("#token").val());
			if(eval(initTestsPublidIdObj).errCode == 0){
				var testsPublicId = eval(eval(initTestsPublidIdObj).data).testsPublicId;

				var historyTestsObj = content.tests.getHistoryData($("#token").val(),courseNumber,$("#userEmail").val(),$("#domainUsername").val(),sessionId,testsPublicId,"${testsPosted.id}");
				if(eval(historyTestsObj).errCode == 0){
					if(eval(eval(historyTestsObj).data).type == "1"){
						$("#objData").val(JSON.stringify(historyTestsObj));
						$("#testsForm").attr("action","<%=path%>/api/page/tests/gosbt");
						$("#testsForm").submit();
					}else if(eval(eval(historyTestsObj).data).type == "2"){
						$("#objData").val(JSON.stringify(historyTestsObj));
						$("#testsForm").attr("action","<%=path%>/api/page/tests/gotestwbt");
						$("#testsForm").submit();
					}else if(eval(eval(historyTestsObj).data).type == "3"){
						$("#testsFrame").attr("src","<%=path%>/course/emu/index.html");
					}else{
						$("#testsFrame").attr("src","<%=path%>/course/err.html");
					}
				}
			}else{
				console.log(eval(initTestsPublidIdObj).errCode+"--->"+eval(initTestsPublidIdObj).errMsg);
			}
		}else{
			console.log(eval(initTestsSessionIdObj).errCode+"--->"+eval(initTestsSessionIdObj).errMsg);
		}
	}
</script>
</html>