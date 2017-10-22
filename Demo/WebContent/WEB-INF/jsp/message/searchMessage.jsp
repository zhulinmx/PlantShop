<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>留言管理</title>
		<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="css/global.css" media="all">
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/table.css" />
	</head>

	<body>
		<div class="admin-main">

			<blockquote class="layui-elem-quote">
				主题：<input type="text" id="title" value="${title }">
				状态：<select id="status">
					<option value="">全部</option>
					<option value="0"<c:if test="${status==0}">selected</c:if>>未处理</option>
					<option value="1"<c:if test="${status==1}">selected</c:if>>已处理</option>
				</select>
				<a href="#" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 搜索
				</a>
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>留言板管理</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" id="selected-all"></th>
								<th>主题</th>
								<th>创建者</th>
								<th>创建时间</th>
								<th>处理者</th>
								<th>处理时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${messageList}" var="list">
								<tr>
									<td><input type="checkbox"></td>
									<td>
										<a href="detailMessage.do?id=${list.id}">${list.title}</a>
									</td>
									<td>${list.userid}</td>
									<td>${list.ceratetime}</td>
									<td>${list.userid}</td>
									<c:if test="${list.status ==1}">
											<td>${list.ceratetime}</td>
									</c:if>
									<c:if test="${list.status ==0}">
											<td></td>
									</c:if>
									<c:if test="${list.status ==0}"><td>未处理</td></c:if>
									<c:if test="${list.status ==1}"><td>已处理</td></c:if>
									<td>
										<a href="detailMessage.do?id=${list.id}" class="layui-btn layui-btn-normal layui-btn-mini">详细</a>
										<c:if test="${list.status ==0}">
											<a href="responseMessage.do?flag=1&id=${list.id}" class="layui-btn layui-btn-mini">回复</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="page" class="page">
				</div>
			</div>
		</div>
		<script type="text/javascript" src="plugins/layui/layui.js"></script>
		<script>
			layui.config({
				base: 'plugins/layui/modules/'
			});

			layui.use(['icheck', 'laypage','layer'], function() {
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});
				
				//page
				laypage({
					cont: 'page',
					pages: '${pages}',
					curr: function(){ //通过url获取当前页，也可以同上（pages）方式获取  
	                    var page = location.search.match(/page=(\d+)/);
	                    return page ? page[1] : 1;  
	                }(), 
					groups: 5,
					jump: function(obj, first) {
						var curr = obj.curr;
						if(!first) {
							var title = document.getElementById('title').value;
							var status = document.getElementById('status').value;
							var currentPage = obj.curr;//获取点击的页码      
							window.location.href ="searchMessage.do?flag=1&page="+curr+"&status="+status+"&title="+title;
						}
					}
				});

				$('#search').on('click', function() {
					var title = document.getElementById('title').value;
					var status = document.getElementById('status').value;
					window.location.href="searchMessage.do?flag=1&page=1&status="+status+"&title="+title;
				});

				$('#selected-all').on('ifChanged', function(event) {
					var $input = $('.site-table tbody tr td').find('input');
					$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
				});
			});
		</script>
	</body>

</html>