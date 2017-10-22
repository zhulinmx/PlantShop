<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>植物园系统</title>
		<meta name="keywords" content="Greeny Template, free website templates, CSS, HTML" />
		<meta name="description" content="Greeny Template - Download free website templates from templatemo.com" />
		<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript">
			function clearText(field)
			{
			    if (field.defaultValue == field.value) field.value = '';
			    else if (field.value == '') field.value = field.defaultValue;
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
	        
    		<div class="cleaner"></div>
	    </div> 
		
		</div> 
		<div id="templatemo_content_wrapper">
			<div id="templatemo_content">
		        <div class="content_section">
		            <div class="cleaner"></div>
		        </div> 
		        
		        <div class="cleaner_h40"></div>
		        <div class="content_section">
		        
		        <h2>宣传详情</h2>
	        	<c:forEach items="${list }" var="list"> 
	        		<div class="product_box margin_r10">
	        		<img src="images/image_05.jpg" alt="image" class="fl_image" />
	        		<p class="em_text">${list.title  }</p>
	        		</div>
	          		<p>${list.content }</p>
	        	</c:forEach>
	        	
	        </div>
		    </div> 
		  	<div class="cleaner"></div>    
		
		</div>
		<div id="templatemo_content_wrapper_bottom"></div> <!-- end of content_wrapper -->
	
		<div id="templatemo_footer">
			<ul class="footer_menu">
	              <li><a href="tohome.do">Home</a></li>
	         </ul>
	          Copyright Â© 2048 <a href="tohome.do">植物园系统 </a> | 
	    </div> 
	</body>
</html>