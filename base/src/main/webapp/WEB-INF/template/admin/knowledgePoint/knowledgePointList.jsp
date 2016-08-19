<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.xiaochejiang.com/tags/tree" prefix="tree"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<meta name="keywords" content="关键字">
		<meta name="description" content="描述信息">
		<title>小车匠内容中心</title>
       <link href="<%=path%>/resources/admin/css/bootstrap.css" rel="stylesheet"/>
		<link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet"/>
		<!-- 校验CSS -->
		<link href="<%=path%>/resources/admin/css/tip.css" rel="stylesheet"/>
		<script src="<%=path%>/resources/admin/js/jquery-2.1.3.min.js"> </script>
		<script src="<%=path%>/resources/admin/js/bootstrap.min.js"> </script>
		<script src="<%=path%>/resources/admin/js/jquery.validate.js" type="text/javascript"></script>
		<script type='text/javascript' src='<%=path%>/resources/admin/js/messages_zh.js'></script>
		<script src="<%=path%>/resources/admin/js/common.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="<%=path%>/resources/admin/css/knowledge-tree.css" />
        <script type="text/javascript" src="<%=path%>/resources/admin/js/knowledge-tree.js"></script>
		<script type="text/javascript">
		//校验知识点编号唯一方法 
	    jQuery.validator.addMethod("validateKpCode", function(value, element) {
		    if(editKnowledgeCodeFlag == value){
		    	return true;
		    }
			var flag=false;
			$.ajax( { //一个Ajax过程 
					type : "get",
					url : "<%=path%>/admin/knowledgePoint/validateKpCode",
					async:false,
					data : {
						"code":value.trim()
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
		}, "知识点编号重复");
		//校验方法 
		$().ready(function() {
			$("#saveKnowledgeForm").validate({ 
			 rules: { 
				code:{required:true,minlength:1,maxlength:10,digits:true,validateKpCode:true},
				name:{required:true,minlength:1,maxlength:100,specialCharValidate:true}
			 }, 
			messages: {  //提示信息
				code: { required: "请输入标签编码",minlength: "标签名称至少需要2个字符",digits:"只能是数字!"},
			    name: { required: "请输入标签名称",minlength: "标签名称至少需要2个字符",maxlength:"标签名称输入字符不能超过100个字符"}
				}
			 });
		  $("#editKnowledgeForm").validate({ 
			 rules: { 
				name:{required:true,minlength:1,maxlength:100,specialCharValidate:true}, 
				editKpCode:{required:true,minlength:2,maxlength:10,digits:true,validateKpCode:true}
			 }, 
		     messages: {  //提示信息
		     name: { required: "请输入标签名称",minlength: "标签名称至少需要2个字符",maxlength:"标签名称输入字符不能超过100个字符"},
		     editKpCode: { required: "请输入标签编码",minlength: "标签编码至少需要2个字符",maxlength:"标签编码输入字符不能超过10个字符",digits:"只能是数字!"}
			}
		 });
     });
		//编辑功能传参
var editKnowledgeCodeFlag="";
function editKnowledge() {
	var value=$("#edit_tree").attr("value");
	$.ajax( { //一个Ajax过程 
		type : "get",
		url :'<%=path%>/admin/knowledgePoint/updateById',
		data : {
		 "id" : value
		},
		success : function(data) {
		   var arr=data.split(",");
			$("#editId").val(arr[0]);
			$("#editName").val(arr[1]);
			$("#editKpCode").val(arr[2]);
			editKnowledgeCodeFlag=arr[2];
		},
	});
}

//   删除知识
function  deleteBatch(){
	var value=$("#delete_tree").attr("value");
	var pid=$("#hiddenKnowledge").val();
	$.ajax( { //一个Ajax过程 
		type : "get",
		url :'<%=path%>/admin/knowledgePoint/delete',
		data : {
		 "id" : value,
		 "pid":pid
		},
		success : function(data) {
			if(data.length>3){
				 $("#deleteModel").attr("style","display:none");
				 $("#msgModelContentBut").click();
				 $("#msgModelContent").html(data);
				return ;
			}
			if(data.trim()==''){
				location.href="<%=path%>/admin/knowledgePoint/knowledgePointList?id=${knowledgePoint.id}"
			}else{
				location.href="<%=path%>/admin/knowledgePoint/knowledgePointList";
			}
		},
	});
}

//添加父节点
function addParent(){
	var pid='${knowledgePointRoot.id}';
	$("#pid").val(pid);
}
</script>
    </head>
    
    <body>
  <div class="container-fluid">
			<div class="row">
				<jsp:include page="../top.jsp">
					<jsp:param value="j" name="MeauTag"/>
				</jsp:include>
                    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<ol class="breadcrumb">
							<li>
								<a href="<%=path%>/admin/">首页</a>
							</li>
							<li class="active">
								 知识体系
							</li>
						</ol>
					</div>
				<!--路径导航栏结束-->
				<!--功能导航栏开始-->
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="container-fluid">
                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">  
                                <ul class="nav navbar-nav">
                                    <li class="dropdown">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
													aria-expanded="false">
														<input type="hidden" id="hiddenKnowledge"  value="${knowledgePoint.id}">
		                                          		  ${knowledgePoint.name}
		                                            <span class="caret">
		                                            </span>
		                                        </a>
                                        <ul class="dropdown-menu" role="menu">
                                        <c:forEach items="${knowledgePointList}" var="knowledgePoint">
                                            <li>
                                                <a href="<%=path%>/admin/knowledgePoint/knowledgePointList?id=${knowledgePoint.id}">
                                                 ${knowledgePoint.name}
                                                </a>
                                            </li>
                                        </c:forEach>
                                        </ul>
                                    </li>
									
									 <li>
										&nbsp;&nbsp;<button type="button" class="btn btn-info navbar-btn  " id="add_parent" data-toggle="modal" data-target="#myModal" onclick="addParent()">父节点新增</button>
                                    </li>
                                    <li>
										&nbsp;&nbsp;<button type="button" class="btn btn-info navbar-btn" id="add_child" data-toggle="modal"    disabled="disabled" onclick="addChild()">子节点新增</button>
                                    </li>
                                    <li>
										&nbsp;&nbsp;<button type="button" class="btn btn-info navbar-btn" id="edit_tree"  data-toggle="modal"   disabled="disabled" onclick="editKnowledge()">  编辑</button>
                                    </li> 
									<li>
										&nbsp;&nbsp;<button type="button" class="btn btn-info navbar-btn " id="delete_tree" data-toggle="modal"   disabled="disabled"  >    删除</button>
                                    </li>
                                </ul>
                            </div>
                             
                        </div>	 
                        
                    </nav>
					<!--功能导航栏结束-->
				
					<!-- 添加弹出框开始-->
					<form action="<%=path%>/admin/knowledgePoint/save" method="post" id="saveKnowledgeForm">
					<input type="hidden" name="hiddenKnowledge" value="${knowledgePoint.id}">
					<input type="hidden" name="knowledgePoint.id" value="" id="pid">
					<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-sm">
						<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<h4 class="modal-title" id="myModalLabel"><font color="#000000" >新增页面</font></h4>
						  </div>
						  <div class="modal-body">
						  <div id="usercodeDiv" class="form-group has-feedback">
							  <label class="control-label" for="code">编码</label>
							  <input type="text" class="form-control"   placeholder="请输入编码" name="code">
							  <span id="userNameSpan" class="glyphicon form-control-feedback"></span>
							</div>
							
							<div id="userNameDiv" class="form-group has-feedback">
							  <label class="control-label" for="name">名称</label>
							  <input type="text" class="form-control"   placeholder="请输入名称" name="name">
							  <span id="userNameSpan" class="glyphicon form-control-feedback"></span>
							</div>
						  </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<input type="submit" class="btn btn-primary" value="提交">
						  </div>
						</div>
					  </div>
					</div>
					</form>
					<!--弹出框结束-->
					
					<!-- 编辑弹出框开始-->
					<form action="<%=path%>/admin/knowledgePoint/update" method="post" id="editKnowledgeForm">
							<input type="hidden" name="hiddenKnowledge" value="${knowledgePoint.id}">
					<input type="hidden" name="id" value="" id="editId">
					<div class="modal fade bs-example-modal-sm" id="editKnowledgeModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-sm">
						<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<h4 class="modal-title" id="myModalLabel"><font color="#000000" id="contentText">编辑页面</font></h4>
						  </div>
						  <div class="modal-body">
							<div id="userNameDiv" class="form-group has-feedback">
							  <label class="control-label" for="name">编码</label>
							  <input type="text" class="form-control" id="editKpCode"  placeholder="请输入编码" name="editKpCode">
							  <span id="userNameSpan" class="glyphicon form-control-feedback"></span>
							</div>
							<div id="userNameDiv" class="form-group has-feedback">
							  <label class="control-label" for="name">名称</label>
							  <input type="text" class="form-control" id="editName"  placeholder="请输入名称" name="name">
							  <span id="userNameSpan" class="glyphicon form-control-feedback"></span>
							</div>
						  </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<input type="submit" class="btn btn-primary" value="提交">
						  </div>
						</div>
					  </div>
					</div>
					</form>
					<!--弹出框结束-->
					
					<!-- 弹出框开始-->
					<div class="modal fade bs-example-modal-sm" id="deleteModel"   tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-sm">
						<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<h4 class="modal-title" id="myModalLabel"><font color="#000000">你是否确定删除</font></h4>
						  </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<input type="button" class="btn btn-primary" value="确定" onclick="deleteBatch()">
						  </div>
						</div>
					  </div>
					</div>
					<!--弹出框结束-->
					<button type="button" style="display:none;" class="btn btn-info navbar-btn " id="msgModelContentBut" data-target="#msgModel"  data-toggle="modal" > ))))))))))</button>
					<!-- 弹出返回信息框开始-->
					<div class="modal fade bs-example-modal-sm" id="msgModel"   tabindex="-1" role="dialog" aria-labelledby="msgModel" aria-hidden="true">
					  <div class="modal-dialog modal-sm">
						<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<h5 class="modal-title" id="msgModelContent"><font color="#000000"></font></h5>
						  </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						  </div>
						</div>
					  </div>
					</div>
					<!--弹出框返回信息结束-->
                    <!-- 树的结构开始-->
                    <div class="tree well">
                   	 <tree:tree treeId="${knowledgePoint.id}"/>
                    </div>
                    <!--树的结构结束-->
                </div>
            </div>
        </div>
    </body>

</html>