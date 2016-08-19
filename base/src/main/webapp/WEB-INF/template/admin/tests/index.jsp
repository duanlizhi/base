<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.xiaochejiang.com/tags/pager" prefix="w"%>
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
		<script src="<%=path%>/resources/admin/js/jquery-2.1.3.min.js"></script>
		<script src="<%=path%>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=path%>/resources/admin/js/jquery.validate.js" type="text/javascript"></script>
		<script charset="utf-8" src="<%=path%>/resources/admin/js/jquery.datetimepicker.js" type="text/javascript"></script>
		<script src="<%=path%>/resources/web/js/commonTests_test.js"></script>
		<script type="text/javascript">
			//禁用/启用
			function Disable(id,state,obj){
				$.post(
					"<%=path%>/admin/tests/disable",
					{"id":id,"state":state},
					function(data){
						//window.location.reload();
						if($(obj).html() == "禁用"){
							$(obj).html("启用");
							$(obj).next().next().next().css("display","none");
						}else{
							$(obj).html("禁用");	
							$(obj).next().next().next().show();
						}
					}
				)
			}
			//全选全不选
			function selectAllNullorReserve(){
				var userids = document.getElementsByName("checkbox");
				if($("#checkboxId").get(0).checked == true){
					for(var i=0;i<userids.length;i++){  
						if($(userids[i]).attr("disabled") != "disabled"){
							userids[i].checked = true;  
						}
					} 
				}else{
					for(var i=0;i<userids.length;i++){   
					      userids[i].checked = false;  
					} 
				}
				//判断删除按钮是否禁用
				if($("[name=checkbox]:checked").length < 1){
					$("#myModalDel").attr("disabled",true);
				}else{
					$("#myModalDel").attr("disabled",false);
				}
			}
			
			//删除选中的试卷
			function del(){
				var userids = document.getElementsByName("checkbox");
				var ids="";
				for(var i=0;i<userids.length;i++){
					if(userids[i].checked == true){
						ids+=userids[i].value;
						ids+=",";
					}
				}
				if(ids != ""){
					$.post(
						"<%=path%>/admin/tests/deletePaper",
						{"ids":ids.substring(0,ids.length-1).trim()},
						function(data){
							window.location.reload();
						}
					)
				}
			}
			
			//如果没选中复选框则不弹出删除框
			function delCourse(obj){
				if($("[name=checkbox]:checked").length < 1){
					$(obj).attr("data-target","");
				}else{
					$(obj).attr("disabled",false);
				}
			}
			
			//编辑
			var updatePaperName="";
			function updatePaper(id,paperName,papersType,score,totalTime){
				updatePaperName = paperName;
				$("#paper_id").val(id);
				$("#paper_name").val(paperName);
				$("#papers_type").val(papersType);
				$("#score").val(score);
				$("#total_time").val(totalTime);
			}
			
			//编辑提交
			function updatePaperSubmit(){
				$.post(
					"<%=path%>/admin/tests/updatePaper",
					{"id":$("#paper_id").val(),
						"paperName":$("#paper_name").val(),
						"papersType":$("#papers_type").val(),
						"score":$("#score").val(),
						"totalTime":$("#total_time").val()
					},
					function(data){
						window.location.reload();
					}
				)
			}
			
			//发布
			function myModealFb(obj,id,papersType){
				$.post(
					"<%=path%>/admin/tests/issuePaper",
					{"id":id,"papersType":papersType},
					function(data){ 
						totalsPage = data.totalsPage;
						$("#courseList").empty();
						$("#testsPostedfy").next().remove();
						$("#testsPostedfy").next().remove();
						//方便发布测评提交test_id
						$("#testsPostedTestId").val(id);
						//试卷发布的时候需要的试卷id和type
						var htmlFiled = "<tr style='display:none;'><td>";
						htmlFiled += "<input type='hidden' value='"+id+"' id='paperTestsPostedId'>";
						htmlFiled += "<input type='hidden' value='"+papersType+"' id='paperTestsPostedType'>";
						htmlFiled += "</td></tr>";
						for(var i=0;i<eval(data.result).length;i++){
							htmlFiled += "<tr>";
							htmlFiled += "<td>";
							htmlFiled += i+1;
							htmlFiled += "</td>";
							if(eval(data.result)[i].testName.length>10){
								htmlFiled +="<td>"+eval(data.result)[i].testName.substring(0,8)+"...</td>";
							}else{
								htmlFiled +="<td>"+eval(data.result)[i].testName+"</td>";
							}
							htmlFiled += "<td>";
							htmlFiled += format(eval(data.result)[i].startTime, 'yyyy-MM-dd HH:mm');
							htmlFiled += "</td>";
							htmlFiled += "<td>";
							htmlFiled += format(eval(data.result)[i].answerTime, 'yyyy-MM-dd HH:mm');
							htmlFiled += "</td>";
							htmlFiled += "<td>";
							htmlFiled += format(eval(data.result)[i].endTime, 'yyyy-MM-dd HH:mm');
							htmlFiled += "</td>";
							htmlFiled += "<td>";
							htmlFiled += format(eval(data.result)[i].releaseTime, 'yyyy-MM-dd HH:mm');
							htmlFiled += "</td>";
							htmlFiled += "<td>";
							htmlFiled += '<button type="button" class="btn btn-primary btn-xs" onclick="goTestsView('+eval(data.result)[i].paperId+','+eval(data.result)[i].id+')">查看</button>';
							htmlFiled += "</td>";
							htmlFiled += "</tr>";
						}
						$("#courseList").append(htmlFiled);
						var htmlFiledFy = "";
						htmlFiledFy += '<table class="table table-striped">';
						htmlFiledFy += data.htmlFiled;
						htmlFiledFy += '</table>';
						$("#courseList").parent().after(htmlFiledFy);
						$(obj).prev().click();
					}
				)
			}
			
			//发布局部刷新分页
			var totalsPage = 0;//总页数
			function turnOverPage2(no){
			    if(no>totalsPage){no=totalsPage;}
			    if(no<1){no=1;}
			    var id = $("#paperTestsPostedId").val();
			    var papersType = $("#paperTestsPostedType").val()
			    $.post(
					"<%=path%>/admin/tests/issuePaper",
					{"id":id,"papersType":papersType,"currentPage":no},
					function(data){ 
						$("#courseList").empty();
						$("#testsPostedfy").next().remove();
						$("#testsPostedfy").next().remove();
						//方便发布测评提交test_id
						$("#testsPostedTestId").val(id);
						//试卷发布的时候需要的试卷id和type
						var htmlFiled = "<tr style='display:none;'><td>";
						htmlFiled += "<input type='hidden' value='"+id+"' id='paperTestsPostedId'>";
						htmlFiled += "<input type='hidden' value='"+papersType+"' id='paperTestsPostedType'>";
						htmlFiled += "</td></tr>";
						for(var i=0;i<eval(data.result).length;i++){
							htmlFiled += "<tr>";
							htmlFiled += "<td>";
							htmlFiled += i+1;
							htmlFiled += "</td>";
							if(eval(data.result)[i].testName.length>10){
								htmlFiled +="<td>"+eval(data.result)[i].testName.substring(0,8)+"...</td>";
							}else{
								htmlFiled +="<td>"+eval(data.result)[i].testName+"</td>";
							}
							htmlFiled += "<td>";
							htmlFiled += format(eval(data.result)[i].startTime, 'yyyy-MM-dd HH:mm');
							htmlFiled += "</td>";
							htmlFiled += "<td>";
							htmlFiled += format(eval(data.result)[i].answerTime, 'yyyy-MM-dd HH:mm');
							htmlFiled += "</td>";
							htmlFiled += "<td>";
							htmlFiled += format(eval(data.result)[i].endTime, 'yyyy-MM-dd HH:mm');
							htmlFiled += "</td>";
							htmlFiled += "<td>";
							htmlFiled += format(eval(data.result)[i].releaseTime, 'yyyy-MM-dd HH:mm');
							htmlFiled += "</td>";
							htmlFiled += "<td>";
							htmlFiled += '<button type="button" class="btn btn-primary btn-xs" onclick="goTestsView('+eval(data.result)[i].paperId+','+eval(data.result)[i].id+')">查看</button>';
							htmlFiled += "</td>";
							htmlFiled += "</tr>";
						}
						$("#courseList").append(htmlFiled);
						var htmlFiledFy = "";
						htmlFiledFy += '<table class="table table-striped">';
						htmlFiledFy += data.htmlFiled;
						htmlFiledFy += '</table>';
						$("#courseList").parent().after(htmlFiledFy);
					}
				)
			}
			//提交验证
			function checkSearch(){
				if($("#search").val() == "请输入试卷名称"){
					return false;
				}
			}
			//试卷详情
			function paperDetailed(paperId){
				$.post(
					"<%=path%>/admin/tests/getCourseWareByPaperId",
					{"paperId":paperId},
					function (data){
						$("#paperDetailContent").empty();
						var htmlStr = "";
						htmlStr += "<table class='table table-striped'>";
						htmlStr += "<tr><td>ID</td><td>name</td><td>courseNum</td></tr>";
						var dataLength = eval(data.courseWareList).length;
						for(var i=0;i<dataLength;i++){
							htmlStr += "<tr>";
							htmlStr += "<td>"+eval(data.courseWareList)[i].id+"</td>";
							htmlStr += "<td>"+eval(data.courseWareList)[i].name+"</td>";
							htmlStr += "<td>"+eval(data.courseWareList)[i].courseNumber+"</td>";
							htmlStr += "</tr>"
						}
						htmlStr += "</table>";
						$("#paperDetailContent").append(htmlStr);
						$("#paperDetailedButton").click();
					}
				)
			}

    	</script>
	</head>
	<body>
		<div class="container-fluid">
		<jsp:include page="../top.jsp" >
		<jsp:param value="l" name="MeauTag"/>
		</jsp:include>
			<div class="row">
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<ol class="breadcrumb">
							<li>
								<a href="javascript://">首页</a>
							</li>
							<li class="active">
								课题卷/测评库
							</li>
						</ol>
					</div>
					<div class="btn-toolbar" role="toolbar">
						<div class="btn-group">
							<a href="<%=path%>/admin/tests/groupTitleInit" class="btn btn-primary">新增题组</a>
							<button type="button" class="btn btn-info" data-toggle="modal" onclick="delCourse(this)"
									disabled="disabled" data-target="#myModalDelete" id="myModalDel">
								删除
							</button>
						</div>
						<div class="btn-group" style="float: right; padding-right: 60px;">
							<form method="post" action="<%=path%>/admin/tests/index" name="searchPagerForm" id="searchPagerForm">
								<c:if test='${search == null || search == ""}'>
								<input class="form-control" name="search" type="text" id="search" onfocus="this.value=''" onblur="if(this.value==''){this.value='请输入试卷名称'}"
									value="请输入试卷名称" placeholder="请输入试卷名称" style="background: #fff" />
								</c:if>
								<c:if test='${search != null && search != ""}'>
								<input class="form-control" name="search" type="text" id="search"
									value="${search}" style="background: #fff" />
								</c:if>
								<input name="accateid" type="hidden" id="accateid" value="" />
								<input type="submit" class="btn btn-info" value="搜索"
									style="position: absolute; right: 0px; top: 0px;" onclick="return checkSearch()"/>
							</form>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>
										<input type="checkbox" id="checkboxId" onclick="selectAllNullorReserve()">
									</th>
									<th>
										序号
									</th>
									<th>
										试卷名称
									</th>
									<th>
										试卷类型
									</th>
									<th>
										题组数量
									</th>
									<th>
										最高分数
									</th>
									<th>
										答题时间
									</th>
									<th>
										创建时间
									</th>
									<th>
										发布状态
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
							
							<c:if test="${page != null}">
   							<c:forEach var="paper" items="${page.result}" varStatus="status">
								<tr>
									<c:forEach var="issue" items="${issue}" varStatus="issueStatus">
										<c:if test="${status.index == issueStatus.index}">
											<c:if test="${issue > 0}">
												<td>
												<input name="checkbox" type="checkbox" value="${paper.id}" disabled="disabled">
												</td>
											</c:if>
											<c:if test="${issue == 0}">
												<td>
												<input name="checkbox" type="checkbox" value="${paper.id}" >
												</td>
											</c:if>
										</c:if>
									</c:forEach>
									<td>
										<!--${paper.id}-->
										${status.count}
									</td>
									<td>
										<c:choose>             
											<c:when test="${fn:length(paper.paperName) > 10}">                 
											<c:out value="${fn:substring(paper.paperName, 0, 10)}..." />            
											</c:when>           
											<c:otherwise>               
											<c:out value="${paper.paperName}" />            
											</c:otherwise>        
										</c:choose> 
									</td>
									<td>
										${paper.papersType}
									</td>
									<td>
										${paper.questionSize}
									</td>
									<td>
										${paper.score}
									</td>
									<td>
										${paper.totalTime}分钟
									</td>
									<td>
										${fn:substring(paper.createDate,0,10)}
									</td>
									
									<c:forEach var="issue" items="${issue}" varStatus="issueStatus">
										<c:if test="${status.index == issueStatus.index}">
											<c:if test="${issue > 0}">
												<td name="on">
												已发布
												</td>
												<td>
													<button type="button" class="btn btn-primary btn-xs" onclick="paperDetailed(${paper.id})">详细</button>
													<button type="button" style="display:none;" data-toggle="modal" data-target="#myModalFb" class="btn btn-primary btn-xs">发布</button>
													<button type="button"  class="btn btn-primary btn-xs" onclick="myModealFb(this,${paper.id},'${paper.papersType}')">发布</button>&nbsp;
												</td>
											</c:if>
											<c:if test="${issue == 0}">
												<td name="on">
												未发布
												</td>
												<td>
													<button type="button" data-toggle="modal" data-target="#myModalEdit" class="btn btn-info btn-xs" 
															onclick="updatePaper('${paper.id}','${paper.paperName}','${paper.papersType}','${paper.score}','${paper.totalTime}')">编辑</button>&nbsp;
													<c:if test="${paper.state == 1}">
														<button type="button" class="btn btn-primary btn-xs" onclick="Disable(${paper.id},${paper.state},this)">禁用</button>&nbsp;
														<button type="button" class="btn btn-info btn-xs" onclick="paperDetailed(${paper.id})">详细</button>&nbsp;
														<button type="button" style="display:none;" data-toggle="modal" data-target="#myModalFb" class="btn btn-primary btn-xs">发布</button>
														<button type="button"  class="btn btn-primary btn-xs" onclick="myModealFb(this,${paper.id},'${paper.papersType}')">发布</button>&nbsp;
													</c:if>
													<c:if test="${paper.state != 1}">
														<button type="button" class="btn btn-primary btn-xs" onclick="Disable(${paper.id},${paper.state},this)">启用</button>&nbsp;
														<button type="button" class="btn btn-info btn-xs" onclick="paperDetailed(${paper.id})">详细</button>&nbsp;
														<button type="button" style="display:none;" data-toggle="modal" data-target="#myModalFb" class="btn btn-primary btn-xs">发布</button>
														<button type="button" style="display:none;" class="btn btn-primary btn-xs" onclick="myModealFb(this,${paper.id},'${paper.papersType}')">发布</button>&nbsp;
													</c:if>
												</td>
											</c:if>
										</c:if>
									</c:forEach>
								</tr>
								</c:forEach>
    							</c:if>	
							</tbody>
						</table>
 
					</div>
				</div>
			</div>
		</div>
		
	<form method="post" action="/api/tests/getTestDemoPaperList" id="testForm" target="_blank">
		<input type="hidden" value="" name="userEmail" id="userEmail"/>
		<input type="hidden" value="" name="testsPostedId" id="testsPostedId"/>
	</form>
	
	<script>
		function goTestsView(paperId,testsPostedId){
			var tsp = '${tsp}';
			if(!Boolean(tsp)){
				return false;
			}
			
			var userEmail = JSON.parse(tsp).userEmail;
			$("#userEmail").val(userEmail);
			$("#testsPostedId").val(testsPostedId);
			$("#testForm").submit();
		}
	</script>
		
		<!--删除窗口开始-->
		<div class="modal fade" id="myModalDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" id="cancelCourse">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="myModalLabel">您是否要删除数据</h4>
			  </div>
			  <div class="modal-body" align="center">
					<button type="button"  class="btn btn-primary" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="del()">确定</button>
			  </div>
			  <div class="modal-footer">
				
			  </div>
			</div>
		  </div>
		</div>
		<!--删除窗口结束-->

		<!--编辑窗口开始-->
		<div class="modal fade" id="myModalEdit" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							编辑课程
						</h4>
					</div>
					<form action="" method="post"  id="editPageForm" >
						<div class="modal-body">
							<input type="hidden" id="paper_id" value=""/>
							<div id="paper_name_div" class="form-group has-feedback">
							  <label class="control-label" for="paper_name">试卷名称：</label>
							  <input type="text" class="form-control" id="paper_name" name="paperName" value="">
							  <span id="userNameSpan" class="glyphicon form-control-feedback"></span>
							</div>
							<div id="papers_type_div" class="form-group has-feedback">
							  <label class="control-label" for="papers_type">试卷类型：</label>
							  <!--<input type="text" class="form-control" id="papers_type" name="papersType" value=""> -->
							  <select class="form-controls"  id="papers_type" name="papersType" style="width:110px">
										<option>自测</option>
										<option>考试</option>
										<option>小测验</option>
										<option>大测验</option>
							  </select>
							  <span id="passwordSpan" class="glyphicon form-control-feedback"></span>
							</div>
							<div id="score_div" class="form-group has-feedback">
							  <label class="control-label" for="score">最高分数：</label>
							  <input type="text" class="form-control" id="score" name="score" value="">
							  <span id="passwordSpan" class="glyphicon form-control-feedback"></span>
							</div>
							<div id="total_time_div" class="form-group has-feedback">
							  <label class="control-label" for="total_time">答题时间：</label>
							  <input type="text" class="form-control" id="total_time" name="totalTime" value="">
							  <span id="passwordSpan" class="glyphicon form-control-feedback"></span>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">
								关闭
							</button>
							<!--  正常ajax提交
							<button type="button" class="btn btn-primary" onclick="updatePaperSubmit()">
								提交
							</button>
							-->
							<input type="submit" class="btn btn-primary" value="提交"/>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--编辑窗口结束-->
		
		<!--发布窗口开始-->
		<div class="modal fade" id="myModalFb" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content" style="width:900px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							 
						</h4>
					</div>
					<form action="<%=path%>/admin/tests/saveTestsPosted"   id="saveTestsPostedForm"  method="post">
						<div class="modal-body" align="center">
							<table>
								<tr>
									<td style="display:block;margin-right:10px;margin-bottom:10px;">
										<input type="hidden" name="currenPage" value="${page.currentPage}">
										<input type="hidden" name="totalsCount" value="${page.totalsCount}">
										<input type="hidden" id="testsPostedTestId" value="" name="paperId">
										考试名称：
										<input class="form-controls" type="text" value="" id="testName" name="testName" style="width:200px"/>
										限制次数：
										<input type="text" class="form-controls" value="" id="limitCount" name="limitCount" style="width:200px"/> 
									</td> 
								</tr>
								<tr>
									<td style="display:block;margin-right:10px;margin-bottom:10px;">
										版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本：
										<input type="text" value="" id="version" name="version" class="form-controls" style="width:200px"/>
										 计分方式：
										 <!--<input type="text" value="" id="scoreType" name="scoreType" class="form-controls" style="width:200px"/>-->
										 <select class="form-controls" id="scoreType" name="scoreType" style="width:200px">
											<option value="1" selected>方式一</option>
											<option value="2">方式二</option>
											<option value="3">方式三</option>
											<option value="4">方式四</option>
							  			</select>
									</td>
								</tr>
								<tr>
									<td style="display:block;margin-right:10px;margin-bottom:10px;">
										发&nbsp;&nbsp;布&nbsp;人：
										<input type="text" value="" id="publisher" name="publisher" class="form-controls" style="width:200px"/>
										结束时间：
										<input type="text" size="18" id="endTime" name="endTime" type="text" value="" class="form-controls" style="width:200px"/>
									</td>
								</tr>
								<tr>
									<td>
										开始时间：
										<input type="text" size="18" id="startTime" name="startTime"  type="text" value="" class="form-controls" style="width:200px"/>
										答题时间：
										<input type="text" id="answerTime" name="answerTime" type="text" value="" class="form-controls" style="width:200px"/>
									</td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							 <button type="button" class="btn btn-default" data-dismiss="modal">
								取消
							</button>
							<input type="submit" class="btn btn-primary" value="提交">
						</div>
					</form>
						发布记录：
						<div class="table-responsive">
							<table class="table table-striped" id="testsPostedfy">
								<thead>
									<tr>
										<th>
											序号
										</th>
										<th>
											考试名称
										</th>
										<th>
											开始时间
										</th>
										<th>
											答题时间
										</th>
										<th>
											结束时间
										</th>
										<th>
											发布时间
										</th>
										<th>
											详情页
										</th>
									</tr>
								</thead>
								<tbody id="courseList">
								</tbody>
							</table>
						</div>
				</div>
			</div>
		</div>
		<!--发布窗口结束-->
		
		<!--内容选项窗口开始-->
		<button type="button" style="display:none;" id="paperDetailedButton" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModalContent" >详细</button>&nbsp;
		<div class="modal fade" id="myModalContent" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							试卷中的课件
						</h4>
					</div>
					<div class="modal-body" align="center" id="paperDetailContent">
						 
						
					</div>
					<div class="modal-footer">
						 <button type="button" class="btn btn-default" data-dismiss="modal" >
							关闭
						</button>
						<!-- 
						<button type="button" class="btn btn-primary">
							取消
						</button>
						 -->
					</div>
				</div>
			</div>
		</div>
		<!--内容选项窗口结束-->
		<w:pager pageSize="${page.pageSize}" currentPage="${page.currentPage}"
			url="./index" totalsCount="${page.totalsCount}" formName="paperForm"/>
	</body>
	<script>
	   //时间控件开始
	   jQuery('#startTime').datetimepicker({ dateFormat: 'yy-mm-dd', timeFormat: 'HH:mm:ss',  
            beforeShow: getCurrentTime  
       });
       jQuery('#endTime').datetimepicker({ dateFormat: 'yy-mm-dd', timeFormat: 'HH:mm:ss',  
            beforeShow: getCurrentTime  
       });
       jQuery('#answerTime').datetimepicker({ dateFormat: 'yy-mm-dd', timeFormat: 'HH:mm:ss',  
            beforeShow: getCurrentTime  
       });
       function getCurrentTime(){$(".ui-datepicker-current").click();} 
       //时间空间结束
       
       //校验开始时间方法 
	   jQuery.validator.addMethod("validatorNewDate", function(value, element) { 
		   if((new Date(value.replace(/-/g,"/"))).getTime()<(new Date()).getTime()){
				return false;
		   }else{
			   return true;
		   }
		}, "开始时间应该大于当前时间_");
       
       //校验结束时间方法 
	   jQuery.validator.addMethod("validatorEndDate", function(value, element) { 
		   if((new Date(value.replace(/-/g,"/"))).getTime()<(new Date()).getTime() || (new Date(value.replace(/-/g,"/"))).getTime()<(new Date($("#startTime").val().replace(/-/g,"/"))).getTime()){
				return false;
		   }else{
			   return true;
		   }
		}, "结束时间应该大于当前时间并大于开始时间_"); 
       //校验答题时间方法 
	   jQuery.validator.addMethod("validatorAnswerDate", function(value, element) { 
		   if((new Date(value.replace(/-/g,"/"))).getTime()<(new Date()).getTime() || 
				(new Date(value.replace(/-/g,"/"))).getTime()<(new Date($("#startTime").val().replace(/-/g,"/"))).getTime()||
				(new Date(value.replace(/-/g,"/"))).getTime()>(new Date($("#endTime").val().replace(/-/g,"/"))).getTime()){
				return false;
		   }else{
			   return true;
		   }
		}, "答题时间应该大于开始时间并小于结束时间_"); 
       
		$().ready(function() {
			
			//点击复选框的时候判断删除键是否可用			
			$("[name=checkbox]").click(function(){
				if($("[name=checkbox]:checked").length < 1){
					$("#myModalDel").attr("disabled",true);
				}else{
					$("#myModalDel").attr("disabled",false);
				}
			})
			
			//发布校验
			$("#saveTestsPostedForm").validate({ 
			 rules: { 
				testName:{required:true,minlength:3,maxlength:50}, 
				limitCount:{required:true,isInteger:true,maxlength:5}, 
				version:{required:true,minlength:1,maxlength:50},
				publisher:{required:true,minlength:2,maxlength:10},
				answerTime:{required:true,validatorAnswerDate:true},
				startTime:{required:true,validatorNewDate:true},
				endTime:{required:true,validatorEndDate:true}
			 }, 
			messages: {  
			    testName: {required: "请输入考试名称_",minlength: "考试名称至少需要3个字符_",maxlength:"考试名称输入字符不能超过50个字符_"},
			    limitCount: {required: "请输入限制次数_",isInteger:"限制次数必须为数字-",maxlength:"限制次数太大-"},
			    version:{required: "请输入版本_",minlength: "版本至少需要2个字符_",maxlength:"版本输入字符不能超过50个字符_"},
			    publisher:{required: "请输入发布人_",minlength: "发布人至少需要2个字符-",maxlength:"发布人输入字符不能超过10个字符_"},
			    answerTime:{required:"答题时间为空_",validatorAnswerDate:"答题时间应该大于开始时间并小于结束时间_"},
				startTime:{required:"开始时间为空_",validatorNewDate:"开始时间应该大于当前时间_"},
				endTime:{required:"结束时间为空_",validatorEndDate:"结束时间应该大于当前时间并大于开始时间_"}
				},
			errorPlacement : function(error, element) { 
					if (element.is("input")) 
						error.appendTo(element.parent().parent().parent().parent().parent()); 
				} 
			 });
			
			//校验试卷名唯一方法 
		    jQuery.validator.addMethod("validatePaperName", function(value, element) {
		    	if(updatePaperName == value){
		    		return true;
		    	}
			var flag=false;
			$.ajax( { //一个Ajax过程 
					type : "get",
					url : "<%=path%>/admin/tests/validatePaperName",
					async:false,
					data : {
						"name":value.trim()
					},
					success : function(data) {
						if(data=="0"){
							flag=false;
						}else{
							flag=true;
						}
					}
				});
			return flag;
			}, "试卷名已经重复_"); 
			
			//编辑异步提交
			$("#editPageForm").validate({
				onsubmit: true,
				// 是否在提交是验证
				onfocusout: false,
				// 是否在获取焦点时验证
				onkeyup: false,
				// 是否在敲击键盘时验证
				rules: {
					paperName:{required:true,minlength:3,maxlength:50,validatePaperName:true}, 
					papersType:{required:true}, 
					score:{required:true,isInteger:true}, 
					totalTime:{required:true,isInteger:true}
				},
				messages: {
					paperName: {required: "请输入考试名称",minlength: "考试名称至少需要3个字符 ",maxlength:"考试名称输入字符不能超过50个字符 ",validatePaperName:"试卷名已经重复"},
					papersType: {required: "请输入考试类型"},
					score: {required: "请输入成绩",isInteger:"限制成绩为数字"},
					totalTime: {required: "请输入答题时间",isInteger:"限制答题时间为数"}
				},
				submitHandler: function(form) { //通过之后回调
							$.post(
								"<%=path%>/admin/tests/updatePaper",
								{"id":$("#paper_id").val(),
									"paperName":$("#paper_name").val(),
									"papersType":$("#papers_type").val(),
									"score":$("#score").val(),
									"totalTime":$("#total_time").val()
								},
								function(data){
									window.location.reload();
								}
							)
				},
				invalidHandler: function(form, validator) { //不通过回调
					return false;
				}
			});
		 });//end--$().ready(function()
		//转换时间格式毫秒转标准格式
		var format = function(time, format) {
			var t = new Date(time);
			var tf = function(i) {
				return (i < 10 ? '0': '') + i
			};
			return format.replace(/yyyy|MM|dd|HH|mm|ss/g,
			function(a) {
				switch (a) {
				case 'yyyy':
					return tf(t.getFullYear());
					break;
				case 'MM':
					return tf(t.getMonth() + 1);
					break;
				case 'mm':
					return tf(t.getMinutes());
					break;
				case 'dd':
					return tf(t.getDate());
					break;
				case 'HH':
					return tf(t.getHours());
					break;
				case 'ss':
					return tf(t.getSeconds());
					break;
				};
			});
		};
	</script>
</html>
