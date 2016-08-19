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
		<link href="<%=path%>/resources/admin/css/bootstrap.css" rel="stylesheet">
		<link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet">
		<link href="<%=path%>/resources/admin/css/tip.css" rel="stylesheet">
		<script src="<%=path%>/resources/admin/js/jquery-1.11.1.min.js"></script>
		<script src="<%=path%>/resources/admin/js/jquery.validate.js" type="text/javascript"></script>
		<script src="<%=path%>/resources/admin/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			//全选全不选
			function selectAllNullorReserve(){
				var userids = document.getElementsByName("courseWareCheckbox");
				if($("#checkboxId").get(0).checked == true){
					for(var i=0;i<userids.length;i++){  
					      userids[i].checked = true;  
					} 
				}else{
					for(var i=0;i<userids.length;i++){   
					      userids[i].checked = false;  
					} 
				}
			}
			
			//弹出层是否显示标志打开
			function myModelAddStatusOpen(){
				$("#myModelAddStatus").val("1");
				$("#search").val("");
				document.getElementById("searchPagerForm").submit();
			}
			//弹出层是否显示标志关闭
			function myModelAddStatusClose(){
				$("#myModelAddStatus").val("0");
			}
			
			//选择试卷
			function addCourse(){
				if(!Boolean($("#cids").val())){
					return false;
				}
				$.post(
					"<%=path%>/admin/tests/getCourseWareByIds",
					{"ids":$("#cids").val()},
					function(data){
						//课题的ids放到提交表单
						$("#courseWareIds").val($("#cids").val());
						//关闭弹出层
						$("#myModelAddStatusClose").click();
						$("#courseWareList").empty();
						var htmlFild="";
						var obj = eval(data.data);
						for(var i=0;i<obj.length;i++){
							htmlFild +="<tr>"
							htmlFild +="<td>"+(i+1)+"</td>";
							if(obj[i].name.length>10){
								htmlFild +="<td>"+obj[i].name.substring(0,8)+"...</td>";
							}else{
								htmlFild +="<td>"+obj[i].name+"</td>";
							}
							htmlFild +="<td>"+obj[i].type+"</td>";
							htmlFild +="<td>"+obj[i].difficulty+"</td>";
							htmlFild +="<td>"+new Date(obj[i].createDate).toLocaleDateString();+"</td>";
							htmlFild +="<td>"+new Date(obj[i].modifyDate).toLocaleDateString();+"</td>";
							//htmlFild +="<td>"+obj[i].passScore+"</td>";
							htmlFild +="<td><input type='text' size='3' name='courseWareScore'><input type='hidden' value='"+obj[i].id+"' name='paperCourseWareScoreId'> </td>";
							htmlFild +='<td><button type="button" class="btn btn-primary btn-xs" onclick="delCourseWare(this,'+obj[i].id+')">删除</button>&nbsp;</td>';
							htmlFild +="</tr>"
						}
						$("#courseWareList").append(htmlFild);
						//内容是null的去掉显示空
						for(var i=0;i<$("td").length;i++){
							if($("td").eq(i).text() == "null"){
								$("td").eq(i).text("");
							}
						}
						
						$("[name=courseWareScore]").keyup(function(){
							var scoreSum=0;
							for(var i=0;i<$("[name=courseWareScore]").length;i++){
								scoreSum += parseInt($("[name=courseWareScore]").eq(i).val());
								if(!(/^\d+$/.test($("[name=courseWareScore]").eq(i).val()))){
									$("#score").val("");
									return false;
								}
							}
							if(!isNaN(scoreSum)){
								$("#score").val(scoreSum);
							}else{
								$("#score").val("");
							}
							$("#scoreHidden").val($("#score").val());
						})
							
					}
				)
			}
			
			//删除已经选好的
			function delCourseWare(obj,id){
				var tempStr="";
				var courseWareStr = $("#cids").val();
				if(courseWareStr.length>0){
					var courseWareSplit = courseWareStr.substring(0,courseWareStr.length-1).split(",");
					for(var i=0;i<courseWareSplit.length;i++){
						if(courseWareSplit[i] == id){
							courseWareSplit.splice(i,1);
							i--;
						}
					}
					for(var i=0;i<courseWareSplit.length;i++){
						tempStr+=courseWareSplit[i];
						tempStr+=",";
					}
				}
				$("#cids").val(tempStr);
				//同时删除并赋值给试卷表单
				$("#courseWareIds").val(tempStr);
				$("[name=courseForm]").nextAll("[name=cids]").val(tempStr);
				$(obj).parent().parent().remove();
				//算出总分数
				var scoreSum=0;
				for(var i=0;i<$("[name=courseWareScore]").length;i++){
					scoreSum += parseInt($("[name=courseWareScore]").eq(i).val());
					if(!(/^\d+$/.test($("[name=courseWareScore]").eq(i).val()))){
						$("#score").val("");
						return false;
					}
				}
				if(!isNaN(scoreSum)){
					$("#score").val(scoreSum);
				}else{
					$("#score").val("");
				}
				$("#scoreHidden").val($("#score").val());
				//end算出总分数
				if(!Boolean($("#courseWareList").html())){
					$("#search").val("");
					document.getElementById("searchPagerForm").submit();
				}
			}
			
			function checkSearch(){
				if($("#search").val() == "请输入搜索题组名称"){
					return false;
					$("#search").val("");
				}
			}
   		</script>
	</head>
	<body>
		<div class="container-fluid">
		<jsp:include page="../top.jsp" >
		<jsp:param value="l" name="MeauTag"/>
		</jsp:include>
			<form method="post" action="<%=path%>/admin/tests/savePaper" name="pagerForm" id="pagerForm">
				<div class="row">
						<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
							<div class="row">
								<ol class="breadcrumb">
									<li>
										<a href="javascript://">首页</a>
									</li>
									<li class="active">
										课题卷/测评新增  (先选择题组在填写表单)
									</li>
								</ol>
							</div>
							<div class="btn-toolbar" role="toolbar">
								<div class="btn-group">
									<button type="button" class="btn btn-primary" id="myModelAddButton"  data-toggle="modal"
										data-target="#myModalAdd" onclick="myModelAddStatusOpen()">
										新增
									</button>
									<button type="button" style="display:none;" class="btn btn-primary" id="myModelAddButtonHiden"  data-toggle="modal"
										data-target="#myModalAdd">
										新增
									</button>
									<button type="button" class="btn btn-info" onclick="window.location.href='<%=path%>/admin/tests/index'">
										返回测评库
									</button>
								</div>
							</div>
							&nbsp;&nbsp;
						<table width="100%" >
							<tr>
								<td style="display:block;margin-right:10px;margin-bottom:10px;">
									试卷名称：<input class="form-controls"  name="paperName" type="text" value="" placeholder="请输入试卷名称" style="width:300px;"  /> 
									<input type="hidden" value="" name="courseWareIds" id="courseWareIds" /> 
								</td>
							</tr>
							<tr>
								<td style="display:block;margin-right:10px;margin-bottom:10px;">
									描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：<input class="form-controls"  name="description" type="text" value="" placeholder="请输入描述" style="width:300px" />
								</td>
							</tr>
							<tr>
								<td style="display:block;margin-right:10px;margin-bottom:10px;">
									答题次数：<input class="form-controls"  name="answerNumber" type="text" value="" placeholder="请输入答题次数" style="width:300px" />  
								</td>
							</tr>
							<tr>
								<td style="display:block;margin-right:10px;margin-bottom:10px;">
									分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：
									<input class="form-controls" id="score" type="button"  name="scoreButt"  value="" placeholder="请输入已选择测评课件的分数" style="width:50px" />
									<input type="hidden" value=""  id="scoreHidden" name="score"/>
									分
								</td>
							</tr>
							<tr>
								<td style="display:block;margin-right:10px;margin-bottom:10px;">
									状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
									<select class="form-controls" name="state"  style="width:110px">
										<option selected="selected" value="1">正常</option>
										<option value="0">禁用</option>
									</select>
									试卷类型：
									<select class="form-controls"  name="papersType" style="width:110px">
										<option selected="selected" value="1">入门级</option>
										<option value="2">达人级</option>
										<option value="3">专家级</option>
									</select>
								</td>
							</tr>
							<tr>
								<td style="display:block;margin-right:10px;margin-bottom:10px;">
									版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本：<input class="form-controls"  name="version" type="text" value="" placeholder="请输入版本" style="width:300px" />  
								</td>
							</tr>
							<tr>
								<td style="display:block;margin-right:10px;margin-bottom:10px;">
									时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：<input class="form-controls"  name="totalTime" type="text" value="" placeholder="请输入时长" style="width:300px" />
								</td>
							</tr>
						</table>
						&nbsp;&nbsp;
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>
												序号
											</th>
											<th>
												题组名称
											</th>
											<th>
												题组类型
											</th>
											<th>
												难度系数
											</th>
											<th>
												发布时间
											</th>
											<th>
												修改时间
											</th>
											<th>
												通过分数
											</th>
											<th>
												操作
											</th>
										</tr>
									</thead>
									<tbody id="courseWareList">
									</tbody>
									
								</table>
								<table class="table table-striped">
									<input type="submit" value="提交" class="btn btn-info">
								</table>
							</div>
						</div>
				</div>
			</form>
		</div>
		<!--编辑窗口开始-->
		<div class="modal fade" id="myModalAdd" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							选择题组
						</h4>
					</div>
					<form method="post" action="<%=path%>/admin/tests/groupTitleInit" name="searchPagerForm" id="searchPagerForm">
						<c:if test='${search == null || search == ""}'>
						<input class="form-controls" name="search" type="text" id="search" onfocus="this.value=''" onblur="if(this.value==''){this.value='请输入搜索题组名称'}"
							value="请输入搜索题组名称" placeholder="请输入搜索题组名称" style="background:#fff;width:200px"/>
						</c:if>
						<c:if test='${search != null && search != ""}'>
						<input class="form-controls" name="search" type="text" id="search"
							value="${search}" style="background:#fff;width:200px" style="width:200px"/>
						</c:if>
						
						<input type="hidden" value="${cids}" name="cids" id="cids" />
						<input type="hidden" value="${status}" name="status" id="myModelAddStatus" />
						<input type="hidden" value="${page.currentPage}" name="currentPage" id="currentPage" />
						<input type="submit" class="btn btn-info" value="搜索" onclick="return checkSearch()"/>
					</form>
					
					<div class="modal-body">
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
										题组编号
									</th>
									<th>
										题组名称
									</th>
									<th>
										难度系数
									</th>
									<th>
										题组类型
									</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${page != null}">
   								<c:forEach var="courseWare" items="${page.result}" varStatus="status">
								<tr>
									<td>
										<input name="courseWareCheckbox" type="checkbox" value="${courseWare.id}">
									</td>
									<td>
										<!--${courseWare.id}-->
										${status.count}
									</td>
									<td>
										${courseWare.courseNumber}
									</td>
									<td>
										<c:choose>             
											<c:when test="${fn:length(courseWare.name) > 10}">                 
											<c:out value="${fn:substring(courseWare.name, 0, 10)}..." />            
											</c:when>           
											<c:otherwise>               
											<c:out value="${courseWare.name}" />            
											</c:otherwise>        
										</c:choose> 
									</td>
									<td>
										${courseWare.difficulty}
									</td>
									<td>
										${courseWare.type}
									</td>
								</tr>
								</c:forEach>
    							</c:if>	
								<tr>
									<table class="table table-striped">
										<w:pager pageSize="${page.pageSize}" currentPage="${page.currentPage}"
											url="./groupTitleInit" totalsCount="${page.totalsCount}" formName="courseForm"/>
									</table>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="myModelAddStatusClose" class="btn btn-default" data-dismiss="modal" onclick="myModelAddStatusClose()">
							关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="addCourse()"/>
							提交
						</button>
					</div>
				</div>
			</div>
		</div>
		<!--编辑窗口结束-->

	</body>
	<script>
	$().ready(function(){
		if($("#myModelAddStatus").val() == ""){
			$("#myModelAddStatus").val("0");
			$("#search").val("");
			document.getElementById("searchPagerForm").submit();
		}else{
			$("#myModelAddButtonHiden").click();
		}
		//分页如果如果提交已经选中的这把复选框勾选
		if($("#cids").val().length >0){
			var courseWareSplit = $("#cids").val().split(",");
			for(var i=0;i<$("[name=courseWareCheckbox]").length;i++){
				for(var j=0;j<courseWareSplit.length-1;j++){
					if($("[name=courseWareCheckbox]").eq(i).val() == courseWareSplit[j]){
						$("[name=courseWareCheckbox]").eq(i).get(0).checked = true;
					}
				}
			}
		}
		
		$("[name=courseWareCheckbox]").click(function(){
			if($(this).get(0).checked){
				var tempStr="";
				var courseWareStr = $("#cids").val();
				
				if(courseWareStr.length>0){
					var courseWareSplit = courseWareStr.substring(0,courseWareStr.length-1).split(",");
					for(var i=0;i<courseWareSplit.length;i++){
						if(courseWareSplit[i] == $(this).val()){
							courseWareSplit.splice(i,1);
							i--;
						}
					}
					for(var i=0;i<courseWareSplit.length;i++){
						tempStr+=courseWareSplit[i];
						tempStr+=",";
					}
				}
				$("#cids").val(tempStr+$(this).val()+",");
				if(navigator.appName == "Microsoft Internet Explorer"){
					$("[name=courseForm]").find("[name=cids]").eq(0).val(tempStr+$(this).val()+",");
				}else{
					$("[name=courseForm]").nextAll("[name=cids]").val(tempStr+$(this).val()+",");
				}
			}else{
				var tempStr="";
				var courseWareStr = $("#cids").val();
				if(courseWareStr.length>0){
					var courseWareSplit = courseWareStr.substring(0,courseWareStr.length-1).split(",");
					for(var i=0;i<courseWareSplit.length;i++){
						if(courseWareSplit[i] == $(this).val()){
							courseWareSplit.splice(i,1);
							i--;
						}
					}
					for(var i=0;i<courseWareSplit.length;i++){
						tempStr+=courseWareSplit[i];
						tempStr+=",";
					}
				}
				$("#cids").val(tempStr);
				if(navigator.appName == "Microsoft Internet Explorer"){
					$("[name=courseForm]").find("[name=cids]").val(tempStr);
				}else{
					$("[name=courseForm]").nextAll("[name=cids]").val(tempStr);
				}
			}
		})
		
		$("#checkboxId").click(function(){
			if($(this).get(0).checked){
				var tempStr="";
				var courseWareStr = $("#cids").val();
				if(courseWareStr.length>0){
					var courseWareSplit = courseWareStr.substring(0,courseWareStr.length-1).split(",");
					
					for(var j=0;j<$("[name=courseWareCheckbox]").length;j++){
						for(var i=0;i<courseWareSplit.length;i++){
							if(courseWareSplit[i] == $("[name=courseWareCheckbox]").eq(j).val()){
								courseWareSplit.splice(i,1);
								i--;
							}
						}
					}
					for(var i=0;i<courseWareSplit.length;i++){
						tempStr+=courseWareSplit[i];
						tempStr+=",";
					}
				}
				for(var j=0;j<$("[name=courseWareCheckbox]").length;j++){
					tempStr += $("[name=courseWareCheckbox]").eq(j).val()+",";
				}
				$("#cids").val(tempStr);
				if(navigator.appName == "Microsoft Internet Explorer"){
					$("[name=courseForm]").find("[name=cids]").val(tempStr);
				}else{
					$("[name=courseForm]").nextAll("[name=cids]").val(tempStr);
				}
			}else{
				var tempStr="";
				var courseWareStr = $("#cids").val();
				if(courseWareStr.length>0){
					var courseWareSplit = courseWareStr.substring(0,courseWareStr.length-1).split(",");
					for(var j=0;j<$("[name=courseWareCheckbox]").length;j++){
						for(var i=0;i<courseWareSplit.length;i++){
							if(courseWareSplit[i] == $("[name=courseWareCheckbox]").eq(j).val()){
								courseWareSplit.splice(i,1);
								i--;
							}
						}
					}
					for(var i=0;i<courseWareSplit.length;i++){
						tempStr+=courseWareSplit[i];
						tempStr+=",";
					}
				}
				$("#cids").val(tempStr);
				if(navigator.appName == "Microsoft Internet Explorer"){
					$("[name=courseForm]").find("[name=cids]").val(tempStr);
				}else{
					$("[name=courseForm]").nextAll("[name=cids]").val(tempStr);
				}
			}
		})
		
	   //校验试卷名唯一方法 
	    jQuery.validator.addMethod("validatePaperName", function(value, element) { 
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
		
		//发布校验
		$("#pagerForm").validate({ 
		 rules: { 
			paperName:{required:true,minlength:3,maxlength:50,validatePaperName:true}, 
			answerNumber:{required:true,isInteger:true}, 
			scoreButt:{required:true,isInteger:true,minlength:1,maxlength:3},
			courseWareScore:{required:true,isInteger:true,minlength:1,maxlength:3},
			description:{required:true,minlength:3,maxlength:50},
			version:{required:true,minlength:2,maxlength:10},
			totalTime:{required:true,isInteger:true}
		 }, 
		messages: {  //提示信息
		    paperName: {required: "请输入考试名称   ",minlength: "考试名称至少需要3个字符   ",maxlength:"考试名称输入字符不能超过50个字符  ",validatePaperName:"试卷名已经重复"},
		    answerNumber: {required: "请输入答题次数   ",isInteger:"答题次数必须为数字   "},
		    scoreButt:{required:"注意输入的分数不能为空",isInteger:"注意输入的分数为数字",minlength:"注意输入的分数至少1位数",maxlength:"注意输入的分数最多3位数"},
		    courseWareScore:{required:"",isInteger:"",minlength:"",maxlength:""},
		    description: {required: "请输入描述   ",minlength: "描述至少需要3个字符   ",maxlength:"描述输入字符不能超过50个字符   "},
		    version:{required: "请输入版本   ",minlength: "版本至少需要2个字符   ",maxlength:"版本输入字符不能超过10个字符    "},
		    totalTime:{required:"答题时间为空    ",isInteger:"答题次数必须为数字   "}
			},
		errorPlacement : function(error, element) { 
			if (element.is("input")) 
				error.appendTo(element.parent()); 
			} 
		});
	})
	</script>
</html>