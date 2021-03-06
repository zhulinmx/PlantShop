<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
	<meta name="keywords" content="Greeny Template, free website templates, CSS, HTML" />
	<meta name="description" content="Greeny Template - Download free website templates from templatemo.com" />
	<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="plugins/layui/layui.js"></script>
	<script type="text/javascript">
		function openpage() {
			layer.prompt({
				  formType: 2,
				  value: '最大120字',
				  title: '回复',
				  area: ['800px', '350px'] //自定义文本域宽高
				}, function(value, index, elem){
				  alert(value); //得到value
				  layer.close(index);
				});
		}
	</script>
</head>
<body>
	<div id="templatemo_header_wrapper">
		<div id="templatemo_header">
	   		<div id="site_title">
	            <h1><a href="#" target="_parent">
	            	<img src="images/templatemo_logo.png" alt="CSS Templates" />
	                <span>植物园系统</span>
	            </a></h1>
	        </div>
	        
	         <div id="templatemo_menu">
	            <ul>
	                <li><a href="tohome.do" class="current">首页</a></li>
	                <li><a href="tomessage.do" target="_parent">留言板</a></li>
	                <li><a href="propagate.do" target="_parent">宣传栏</a></li>
	                <li><a href="toredister.do" target="_parent">注册</a></li>
	                <li><a href="customerlogin.do?flag=1">登录</a></li>
	            </ul>    	
	        </div> 
	    </div> 
	</div>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;margin-left: 100px; width: 80%;">
		<legend>留言板</legend>
	</fieldset>
	<table class="layui-table" lay-even lay-skin="nob" style="width: 80%; margin-left: 100px;" >
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	      <th>序号</th>
	      <th>时间</th>
	      <th>标题</th>
	      <th>回复</th>
	      <th>操作</th>
	    </tr> 
	  </thead>
	  <tbody>
	  	<c:forEach items="${infos}" varStatus="i" var="list">
			<td>${i.index + 1 }</td>
			<td>${list.ceratetime }</td>
			<td>${list.title }</td>
			<td>
				<c:choose>
            		<c:when test="${fn:length(list.message) > 10}">
            			${fn:substring(list.message, 0, 10)}...	
            		</c:when>
            		<c:otherwise>${list.message }</c:otherwise>
            	</c:choose>
			</td>
			<td>
	      	<c:if test="${sessionScope.user == null}">
				请先<a href="customerlogin.do?flag=1">登录</a>/<a href="toredister.do">注册</a>
		  	</c:if>
			<c:if test="${sessionScope.user != null}">
				<a href="toDetailMessage.do?id=${list.id }">回复</a>
			</c:if>
	 		</td>
	 		<tr>
	  	</c:forEach>
	  	<tr>
	  	<td>
	  	<td>
	  	<td>
	  	<td>
	  	<c:if test="${sessionScope.user == null}">
	  			登录后可留言！
				请先<a href="customerlogin.do?flag=1">登录</a>/<a href="toredister.do">注册</a>
	  	</c:if>
		<c:if test="${sessionScope.user != null}">
			<a href="toaddmess.do">留言</a>
		</c:if>
		</td>
		<td>
	  </tbody>
	  
	  
	</table>
	
	
</body>
</html>