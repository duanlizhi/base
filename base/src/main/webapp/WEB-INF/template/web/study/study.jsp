<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>学习平台 </title>
	  	<link href="<%=path%>/resources/web/css/layout.css" rel="stylesheet"/>
		<script src="<%=path%>/resources/admin/js/jquery-1.11.1.min.js"></script>
		<script src="<%=path%>/resources/web/js/common.js"></script>
	</head>
<body>
	<input type="hidden" value="${domainUsername}" id="domainUsername"/>
	<input type="hidden" value="${token}" id="token"/>
	<input type="hidden" value="${userEmail}" id="userEmail"/>
	
   	<div class="container">
		<div class="top">
			<div class="top-title">
				<span class="red">
					学习平台
				</span>
				${paper.paperName}
			</div>
			<div class="time">
				<div class="time-title">
					时间：
				</div>
				<div class="time-num" id="timeNum">
				88:88:88
				</div>
			</div>
		</div>
		<div class="main">
   			<c:if test="${courseWareList != null}">
   				<c:forEach var="courseWare" items="${courseWareList}" varStatus="status">
					<div class="main-tab">
						<div class="main-tab-img" onclick="studySubmit('${courseWare.courseNumber}','${courseWare.id}')">
							<c:if test="${courseWare.type == 1}">
							<img src="<%=path%>/resources/web/image/sbt.jpg" class="main-tab-imgc" width="231" height="150"/>
							</c:if>
							<c:if test="${courseWare.type == 2}">
							<img src="<%=path%>/resources/web/image/wbt.jpg" class="main-tab-imgc" width="231" height="150"/>
							</c:if>
							<c:if test="${courseWare.type == 3}">
							<img src="<%=path%>/resources/web/image/cvt.jpg" class="main-tab-imgc" width="231" height="150"/>
							</c:if>
							<c:if test="${courseWare.type == 4}">
							<img src="<%=path%>/resources/web/image/cz.jpg" class="main-tab-imgc" width="231" height="150"/>
							</c:if>
						</div>
						<div class="main-tab-title">
							${courseWare.name}
						</div>
						<div class="main-tab-con">
							<p class="main-tab-goal">
								${courseWare.purpose}
							<p class="main-tab-point">
								${courseWare.courseNumber}
							</P>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
   	
	<form method="post" action="" id="studyForm" target="_blank">
		<input type="hidden" value="" name="objData" id="objData"/>
		<input type="hidden" value="${token}" name="token" id="token"/>
		<input type="hidden" value="" name="currentCourseId" id="currentCourseId"/>
	</form>
	
</body>
<script type="text/javascript">
	//haoliqiang
	$(".main-tab").hover(function() {
		$(this).find("img").attr("class", "main-tab-imgcc");
		$(this).attr("class", "main-tabb");
	},
	function() {
		$(this).find("img").attr("class", "main-tab-imgc");
		$(this).attr("class", "main-tab");
	})
	//显示当前时间
	function currentTime(){
		$("#timeNum").html((new Date()).format("hh:mm:ss"));
	}
	setInterval("currentTime()",1000);
	
	function studySubmit(courseNumber,currentCourseId){
		$("#currentCourseId").val(currentCourseId);
		var initStudyObj = content.study.initStudy($("#token").val(),courseNumber,$("#userEmail").val(),$("#domainUsername").val());
		if(eval(initStudyObj).errCode == 0){
			var sessionId = eval(eval(initStudyObj).data).sessionId;
			var startStudyObj = content.study.startStudy($("#token").val(),courseNumber,$("#userEmail").val(),$("#domainUsername").val(),sessionId);
			if(eval(startStudyObj).errCode ==  0){
				var startStudyData = eval(eval(startStudyObj).data);
				//window.location.href = "<%=path%>"+startStudyData.url+"?objData="+JSON.stringify(startStudyData);
				$("#objData").val(JSON.stringify(startStudyObj));
				$("#studyForm").attr("action","<%=path%>"+startStudyData.url);
				$("#studyForm").submit();
			}else{
				alert(eval(startStudyObj).errCode+"-->"+eval(startStudyObj).errMsg);
			}
		}else{
			alert(eval(initStudyObj).errCode+"--->"+eval(initStudyObj).errMsg);
		}
	}
</script>
</html>

