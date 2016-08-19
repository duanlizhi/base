<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${paper.paperName}</title>
		<link href="<%=path%>/resources/web/css/layout.css" rel="stylesheet"/>
		<script src="<%=path%>/resources/admin/js/jquery-1.11.1.min.js"></script>
		<script src="<%=path%>/resources/web/js/commonTests.js"></script>
	</head>
<body class="indexBg">
	<input type="hidden" value="${domainUsername}" id="domainUsername"/>
	<input type="hidden" value="${userEmail}" id="userEmail"/>

   	
	 <div class="container">
		<div class="top">
			<div class="top-title">
				<span class="red">
					<!--测评体系 -->
					电子控制系统测评
				</span>
				<!--${testsPosted.testName}-->
			</div>
			<div class="time">
				<div class="time-title" id="timeTitle">
					倒计时：
				</div>
				<div class="time-num" id="timeNum">
				00:00:00
				</div>
			</div>
			<div class="quit" id="exitTests" onclick = "exitTests()">退出测评</div>
		</div>
		
		<div class="main">
   			<c:if test="${paper.courseWareList != null}">
   				<c:forEach var="courseWare" items="${paper.courseWareList}" varStatus="status">
					<div class="main-tab"  onclick="initTests('${courseWare.courseNumber}','${courseWare.id}')">
						<div class="main-tab-img">
							<c:if test="${courseWare.type == 1}">
							<img src="<%=path%>/resources/admin/image/tests/${courseWare.courseNumber}.png" class="main-tab-imgc" width="231" height="150"/>
							</c:if>
							<c:if test="${courseWare.type == 2}">
							<img src="<%=path%>/resources/admin/image/tests/${courseWare.courseNumber}.png" class="main-tab-imgc" width="231" height="150"/>
							</c:if>
							<c:if test="${courseWare.type == 3}">
							<img src="<%=path%>/resources/admin/image/tests/${courseWare.courseNumber}.png" class="main-tab-imgc" width="231" height="150"/>
							</c:if>
							<c:if test="${courseWare.type == 4}">
							<img src="<%=path%>/resources/admin/image/tests/${courseWare.courseNumber}.png" class="main-tab-imgc" width="231" height="150"/>
							</c:if>
						</div>
						<div class="main-tab-title">
							${courseWare.name}
						</div>
						<div class="main-tab-con">
							<p class="main-tab-goal">
								${courseWare.purpose}
							<p class="main-tab-point">
								<!--${courseWare.courseNumber}-->
							</P>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
   	
   	<form method="post" action="" id="testsForm" target="_blank">
   		<input type="hidden" value="${domainUsername}" name="domainUsername" id="domainUsername"/>
		<input type="hidden" value="${userEmail}" name="userEmail" id="userEmail"/>
		<input type="hidden" value="" name="objData" id="objData"/>
		<input type="hidden" value="${token}" name="token" id="token"/>
		<input type="hidden" value="" name="currentCourseId" id="currentCourseId"/>
		<input type="hidden" value="${paper.id}" name="paperId" id="paperId"/>
		<input type="hidden" value="${testsPosted.id}" name="testsPostedId" id="testsPostedId"/>
	</form>
</body>
<script>
	function closeWindow() 
	{ 
	　　top.window.opener = top;
	      top.window.open('','_self','');
	      top.window.close();
	}
	function exitTests(){
		$.post(
			"<%=path%>/api/oper/tests/exitTest",
			{"userEmail":$("#userEmail").val()},
			function(data){
				if(data == 1){
					closeWindow();
				}
			}
		)
	}
	
	$(".main-tab").hover(function() {
		$(this).find("img").attr("class", "main-tab-imgcc");
		$(this).attr("class", "main-tabb");
	},
	function() {
		$(this).find("img").attr("class", "main-tab-imgc");
		$(this).attr("class", "main-tab");
	})

	function MillisecondToDate(msd) {
	    var time = parseFloat(msd) / 1000;
	    if (null != time && "" != time) {
	        if (time > 60 && time < 60 * 60) {
	        	var mm = parseInt(time / 60.0);
	        	var ss = parseInt((parseFloat(time / 60.0) - parseInt(time / 60.0)) * 60);
	            if(/^\d{1}$/.test(mm)){
	            	mm = "0"+mm;
	            }
	            if(/^\d{1}$/.test(ss)){
	            	ss = "0"+ss;
	            }
	            time = "00:" + mm + ":" + ss ;
	        }
	        else if (time >= 60 * 60) {
	        	var hh = parseInt(time / 3600.0);
	        	var mm = parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60);
	        	var ss = parseInt((parseFloat((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) -
	                parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60)) * 60);
	        	if(/^\d{1}$/.test(hh)){
	            	hh = "0"+hh;
	            }
	        	if(/^\d{1}$/.test(mm)){
	            	mm = "0"+mm;
	            }
	        	if(/^\d{1}$/.test(ss)){
	            	ss = "0"+ss;
	            }
	            time = hh+":"+mm+":"+ss;
	        }
	        else {
	        	var ss = parseInt(time);
	        	if(/^\d{1}$/.test(ss)){
	        		ss = "0"+ss;
	        	}
	            time = "00:00:"+ss;
	        }
	    }
	    return time;
	}
	//倒计时
	var surplusTimeData =  "${surplusTime}";
	var surplusEndTimeData = "${surplusEndTime}";
	function surplusTime(){
		surplusTimeData -= 1000;
		$("#timeNum").html(MillisecondToDate(surplusTimeData));
		if(surplusTimeData <= 0){
			$("#timeNum").html("00:00:00");
			clearInterval(surplutTimeClear);
			//可以进入考试页面
			initTestsFlag = true;
			//距离考试结束
			$("#timeTitle").html("结束时间");
			clearInterval(endTimeClear);
			endTimeClear = setInterval("surplusEndTime()",1000);
		}
	}
	function surplusEndTime(){
		surplusEndTimeData -= 1000;
		$("#timeNum").html(MillisecondToDate(surplusEndTimeData));
		if(surplusEndTimeData <= 0){
			exitTests();
			$("#timeNum").html("00:00:00");
			clearInterval(endTimeClear);
			//不可以进入考试页面
			initTestsFlag = false;
		}
	}
	var surplutTimeClear = setInterval("surplusTime()",1000);
	var endTimeClear = setInterval("surplusEndTime()",1000);
</script>
<script type="text/javascript">
	//初始化开始标志(时间不到禁止进入考试页面)
	var initTestsFlag = false;
	//赋值地址1获取sessionId、2获取testsPublicId、3获取历史
	function initTests(courseNumber,currentCourseId){
		//时间不到禁止进入考试页面
		if(initTestsFlag == false){return false}
		
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

