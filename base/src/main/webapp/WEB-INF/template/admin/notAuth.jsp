<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.xiaochejiang.com/tags/pager" prefix="w"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html class=''>
<head>
    <title>没有权限</title>
    <style class="cp-pen-styles">
        body
        {
            background-color: #0A7189;
            color: #fff;
            font: 100% "Lato" , sans-serif;
            font-size: 1.8rem;
            font-weight: 300;
        }
        
        a
        {
            color: #75C6D9;
            text-decoration: none;
        }
        
        h3
        {
            margin-bottom: 1%;
        }
        
        ul
        {
            list-style: none;
            margin: 0;
            padding: 0;
            line-height: 50px;
        }
        
        li a:hover
        {
            color: #fff;
        }
        
        .center
        {
            text-align: center;
        }
        
        /* Search Bar Styling */
        form > *
        {
            vertical-align: middle;
        }
        
        .srchBtn
        {
            border: 0;
            border-radius: 7px;
            padding: 0 17px;
            background: #e74c3c;
            width: 99px;
            border-bottom: 5px solid #c0392b;
            color: #fff;
            height: 65px;
            font-size: 1.5rem;
            cursor: pointer;
        }
        
        .srchBtn:active
        {
            border-bottom: 0px solid #c0392b;
        }
        
        .srchFld
        {
            border: 0;
            border-radius: 7px;
            padding: 0 17px;
            max-width: 404px;
            width: 40%;
            border-bottom: 5px solid #bdc3c7;
            height: 60px;
            color: #7f8c8d;
            font-size: 19px;
        }
        
        .srchFld:focus
        {
            outline-color: rgba(255, 255, 255, 0);
        }
        
        /* 404 Styling */
        .header
        {
            font-size: 4rem;
            font-weight: 700;
            margin: 2% 0 2% 0;
            text-shadow: 0px 3px 0px #7f8c8d;
        }
        
        /* Error Styling */
        .error
        {
            margin: -70px 0 2% 0;
            font-size: 7.4rem;
            text-shadow: 0px 3px 0px #7f8c8d;
            font-weight: 100;
        }
    </style>
   	<link href="<%=path%>/resources/admin/css/bootstrap.css" rel="stylesheet"/>
	<link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet"/>
	<!-- 校验CSS -->
	<link href="<%=path%>/resources/admin/css/tip.css" rel="stylesheet"/>
	<script src="<%=path%>/resources/admin/js/jquery-2.1.3.min.js"> </script>
	<script src="<%=path%>/resources/admin/js/bootstrap.min.js"> </script>
	<script src="<%=path%>/resources/admin/js/jquery.validate.js" type="text/javascript"></script>
	<script type='text/javascript' src='<%=path%>/resources/admin/js/messages_zh.js'></script>
	<script src="<%=path%>/resources/admin/js/common.js" type="text/javascript"></script>
</head>
<body>
		<div class="container-fluid">
			<div class="row">
				<jsp:include page="top.jsp">
					<jsp:param value="cc" name="MeauTag" />
				</jsp:include>
			</div>
			<section class="center">
			<article>
			<h1 class="header">
				呵呵！没有权限哦！
			</h1>
			</article>
			<article>
			<img src="<%=path%>/resources/admin/image/vovg1x.png"
				alt="Funny Face">
			</article>
			<p>
				by
				<a href="http://www.xiaochejiang.com">小车匠信息系统有限公司</a>
			</p>
			</section>
		</div>
	</body>
</html>
