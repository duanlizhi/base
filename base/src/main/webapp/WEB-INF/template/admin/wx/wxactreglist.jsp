<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.xiaochejiang.com/tags/pager" prefix="w"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Content Center</title>
		<link href="<%=path%>/resources/admin/css/bootstrap.css" rel="stylesheet"/>
		<link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet"/>
		<link href="<%=path%>/resources/admin/css/jquery.datetimepicker.css" rel="stylesheet">
		<link href="<%=path%>/resources/admin/css/tip.css" rel="stylesheet">
		<script src="<%=path%>/resources/admin/js/jquery-1.11.1.min.js"></script>
		<script src="<%=path%>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=path%>/resources/admin/js/jquery.validate.js" type="text/javascript"></script>
		<script charset="utf-8" src="<%=path%>/resources/admin/js/jquery.datetimepicker.js" type="text/javascript"></script>
		<script language="JavaScript">  
function showtip2(current,e,text)  
{  
if (document.readyState=="complete")  
{<!--如果是ie浏览器并且满足readystate标志位-->  
document.all.tooltip2.innerHTML='<marquee style="border:1px solidblue">'+text+'</marquee>'  
<!--循环播放链接-->  
document.all.tooltip2.style.pixelLeft=event.clientX+document.body.scrollLeft+10  
<!--链接的显示左边界的确定-->  
document.all.tooltip2.style.pixelTop=event.clientY+document.body.scrollTop+10  
<!--链接的显示上边界的确定-->  
document.all.tooltip2.style.visibility="visible"<!--方框设置为显示-->  
}  
}  
function hidetip2()  
{  
 (document.all)  
document.all.tooltip2.style.visibility="hidden"<!--设置链接不显示-->  
}  
</script>  
	</head>
	<body>
		<div class="container-fluid">
		<jsp:include page="../top.jsp" >
		<jsp:param value="w" name="MeauTag"/>
		</jsp:include>
			<div class="row">
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<ol class="breadcrumb">
							<li>
								<a href="javascript://">首页</a>
							</li>
							<li class="active">
								微信注册信息
							</li>
							<li>
								<div id="tooltip2" style="background-color: white; clip: rect(0px 850px 850px 0px); height: 0px; left: 200px; position: absolute; top: 28px; visibility: visible; width: 800px; z-index: 2">
								<LAYER name="nstip" width="500px" bgColor="white" left="374" top="164">  
								</LAYER>
								</div>  
							</li>
						</ol>
						
					</div>
					<div class="btn-toolbar" role="toolbar">
						<div class="btn-group" style="float: right; padding-right: 60px;">
						
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>序号</th>
									<th>姓名</th>
									<th>年龄</th>
									<th>职务</th>
									<th>电话</th>
									<th>手机标示</th>
									<th>创建时间</th>
								</tr>
							</thead>
							<tbody>
							
							<c:if test="${page != null}">
   							<c:forEach var="wxactreg" items="${page.result}" varStatus="status">
								<tr>
									<td>
										${wxactreg.id}
									</td>
									<td>
										${wxactreg.name}
									</td>
									<td>
										${wxactreg.age}
									</td>
									<td title="${wxactreg.poster}">
										<c:choose>             
											<c:when test="${fn:length(wxactreg.poster) > 10}">                 
											<c:out value="${fn:substring(wxactreg.poster, 0, 10)}..." />            
											</c:when>           
											<c:otherwise>               
											<c:out value="${wxactreg.poster}" />            
											</c:otherwise>        
										</c:choose> 
									</td>
									<td>
										${wxactreg.mobile}
									</td>
									<td title="${wxactreg.agent}">
										<c:choose>             
											<c:when test="${fn:length(wxactreg.agent) > 10}">                 
											<c:out value="${fn:substring(wxactreg.agent, 0, 10)}..." />            
											</c:when>           
											<c:otherwise>               
											<c:out value="${wxactreg.agent}" />            
											</c:otherwise>        
										</c:choose> 
									</td>
									<td>
										${wxactreg.createDate}
									</td>
								</tr>
								</c:forEach>
    							</c:if>	
							</tbody>
						</table>
 
					</div>
				</div>
			</div>
		</div>
		<w:pager pageSize="${page.pageSize}" currentPage="${page.currentPage}"
			url="./wxactreglist" totalsCount="${page.totalsCount}" formName="wxactregForm"/>
	</body>
</html>
