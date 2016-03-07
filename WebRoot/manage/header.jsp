<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resource/main.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/resource/util.js"></script>

  </head>
  
  <body>
    <br/>
    <br/>
    <h1>后台管理</h1>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/manage/addCategory.jsp">添加分类</a>
    <a href="${pageContext.request.contextPath}/manage/ManageServlet?op=showAllCategory">查询分类</a>
    <a href="${pageContext.request.contextPath}/manage/ManageServlet?op=addBookUI">添加图书</a>
    <a href="${pageContext.request.contextPath}/manage/ManageServlet?op=showPageBooks">查询图书</a>
    <a href="">待处理订单</a>
    <a href="">已处理订单</a>
    <br/>
    <br/>
