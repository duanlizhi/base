<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>tab切换页面</title>
		<meta name="Author" content="">
		<meta name="Keywords" content="">
		<meta name="Description" content="">
	  	<link href="<%=path%>/resources/admin/css/bootstrap.css" rel="stylesheet">
		<link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet">
		<link href="<%=path%>/resources/admin/css/tip.css" rel="stylesheet">
		<link href="<%=path%>/resources/web/css/layout.css" rel="stylesheet"/>
		<script src="<%=path%>/resources/admin/js/jquery-1.11.1.min.js"></script>
		<script src="<%=path%>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=path%>/resources/web/js/common.js"></script>
	</head>
<body>
	<input type="hidden" value="${domainUsername}" id="domainUsername"/>
	<input type="hidden" value="${token}" id="token"/>
	<input type="hidden" value="${userEmail}" id="userEmail"/>
	
	<!--  
	<input type="button" value="开始wbt学习" class="btn btn-primary"/>
	<c:if test="${courseWareList != null}">
	   	<c:forEach var="courseWare" items="${courseWareList}" varStatus="status">
	   		<c:if test="${courseWare.type == '2'}">
	   			<input type="button" value="${courseWare.courseNumber}" class="btn btn-info" onclick="studySubmit('${courseWare.courseNumber}')"/>
	   		</c:if>
	   	</c:forEach>
   	</c:if>
   	<br/><br/>
	<input type="button" value="开始sbt学习" class="btn btn-primary"/>
	<c:if test="${courseWareList != null}">
	   	<c:forEach var="courseWare" items="${courseWareList}" varStatus="status">
	   		<c:if test="${courseWare.type == '1'}">
	   			<input type="button" value="${courseWare.courseNumber}" class="btn btn-info" onclick="studySubmit('${courseWare.courseNumber}')"/>
	   		</c:if>
	   	</c:forEach>
   	</c:if>
   	<br/><br/>
	<input type="button" value="开始emu学习" class="btn btn-primary"/>
	<c:if test="${courseWareList != null}">
	   	<c:forEach var="courseWare" items="${courseWareList}" varStatus="status">
	   		<c:if test="${courseWare.type == '3'}">
	   			<input type="button" value="${courseWare.courseNumber}" class="btn btn-info" onclick="studySubmit('${courseWare.courseNumber}')"/>
	   		</c:if>
	   	</c:forEach>
   	</c:if>
   	<br/><br/>
	<input type="button" value="开始dmc学习" class="btn btn-primary"/>
	<c:if test="${courseWareList != null}">
	   	<c:forEach var="courseWare" items="${courseWareList}" varStatus="status">
	   		<c:if test="${courseWare.type == '4'}">
	   			<input type="button" value="${courseWare.courseNumber}" class="btn btn-info" onclick="studySubmit('${courseWare.courseNumber}')"/>
	   		</c:if>
	   	</c:forEach>
   	</c:if>
   	-->

		<div class="container">
			<div class="top">
				<div class="top-title">
					<span class="red">
						测评
					</span>
					体系
				</div>
				<div class="time">
					<div class="time-title">
						倒计时：
					</div>
					<div class="time-num">
						00:00:00
					</div>
				</div>
			</div>
			<div class="main">
				<div class="main-tab">
					<div class="main-tab-img">
						<img src="<%=path%>/resources/web/image/sbt.jpg" class="main-tab-imgc" width="231" height="150"
						class="" alt="">
					</div>
					<div class="main-tab-title">
						SBT
					</div>
					<div class="main-tab-con">
						<p class="main-tab-goal">
							目标呀目标
						</P>
						<p class="main-tab-point">
							知识点
						</P>
					</div>
				</div>
			</div>
		</div>
	
	<form method="post" action="" id="studyForm">
		<input type="hidden" value="" name="objData" id="objData"/>
		<input type="hidden" value="${token}" name="objToken" id="objToken">
	</form>
</body>
<script>
	$(".main-tab").hover(function() {
		$(this).find("img").attr("class", "main-tab-imgcc");
		$(this).attr("class", "main-tabb");
	},
	function() {
		$(this).find("img").attr("class", "main-tab-imgc");
		$(this).attr("class", "main-tab");
	})
</script>
<script type="text/javascript">
	function studySubmit(courseNumber){
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
	$(":button").css("width","100px");
</script>
</html>

