<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	        
	        <div id="search_box">
	                <form action="searchPlan.do" method="get">
	                    <input type="text" value="Enter a keyword" name="key" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
	                </form>
           	</div>
	    
    		<div class="cleaner"></div>
	    </div> 
		
		</div> 
		<div id="templatemo_content_wrapper">
			<div id="templatemo_content">
		        <div class="content_section">
		            <h2>植物浏览</h2>
		            <c:forEach items="${plantList }" var="list">
		            	<div class="product_box margin_r10">
				            <h3>${list.plantname }</h3>
			                <img src="${list.picture }" alt="plant" />
			                <a href="searchPlanDetail.do?id=${list.plantid }">Detail</a>
		            	</div>
		            </c:forEach>
		            <div class="cleaner"></div>
		        </div> 
		        
		        <div class="cleaner_h40"></div>
		        <div class="content_section">
		        
		        <div id="templatmeo_sidebar">
			    	<div class="sidebar_section">
		            <h2>植物种类</h2>
		       		<div class="sidebar_section_content">
		                <ul class="categories_list">
		                	<c:forEach items="${categories }" var="clist">
		                		<li><a href="searchPlan.do?cid=${clist.categoryid }">${clist.categoryname }</a></li>
		                	</c:forEach>
		                </ul>
		            </div>
		        	</div>
			        <div class="cleaner_h30"></div>
			        <div class="sidebar_section"></div>
				</div> 
		        
		        <h2>宣传栏</h2>
	        	<c:forEach items="${propagateList }" var="pList"> 
	            	<li>
	            	<a href="topropagatedetail.do?id=${pList.propagateid }">
	            		<p class="em_text">
	            			${pList.title }
	            		</p>
	            	</a>
	            	</li>
	            	<p>
		            	<c:choose>
		            		<c:when test="${fn:length(pList.content) > 100}">
		            			${fn:substring(pList.content, 0, 100)}...	
		            		</c:when>
		            		<c:otherwise>${pList.content }</c:otherwise>
		            	</c:choose>
		            	
	          		</p>
	          		时间：${pList.ceratetime }-${pList.deadlinetime }
	        	</c:forEach>
	        	
	        </div>
		    </div> <!-- end of content -->
		  	<div class="cleaner"></div>    
		
		</div>
		<div id="templatemo_content_wrapper_bottom"></div> <!-- end of content_wrapper -->
	
		<div id="templatemo_footer">
			<ul class="footer_menu">
	              <li><a href="#">Home</a></li>
	         </ul>
	          Copyright Â© 2048 <a href="#">植物园系统 </a> | 
	    </div> 
	</body>
</html>