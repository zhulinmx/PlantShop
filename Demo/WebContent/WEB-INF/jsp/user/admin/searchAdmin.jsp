<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>人事管理</title>
		<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="css/global.css" media="all">
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/table.css" />
	</head>

	<body>
		<div class="admin-main">
			<blockquote class="layui-elem-quote">
				用户名：<input type="text" id="username" value="${userName }">
				状态：<select id="status">
					<option value="">全部</option>
					<option value="0"<c:if test="${status==0}">selected</c:if>>无效</option>
					<option value="1"<c:if test="${status==1}">selected</c:if>>有效</option>
				</select>
				<a href="#" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 搜索
				</a>
				<a href="#" class="layui-btn layui-btn-small" id="add">
					<i class="layui-icon">&#xe608;</i> 新增人事信息
				</a>
				<a href="#" class="layui-btn layui-btn-small" id="import">
					<i class="layui-icon">&#xe608;</i> 导出数据
				</a>
				
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>人事管理</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" id="selected-all"></th>
								<th>用户Id</th>
								<th>部门</th>
								<th>姓名</th>
								<th>权限</th>
								<th>入职时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="user">
								<tr>
									<td><input type="checkbox"></td>
									<td>${user.userid}</td>
									<td>${user.dep}</td>
									<td>
										<a href="toDetailAdmin.do?id=${user.userid}">${user.username}</a>
									</td>
									<c:if test="${user.role ==0}"><td>超级管理员</td></c:if>
									<c:if test="${user.role ==1}"><td>管理员</td></c:if>
									<td>${user.jointime}</td>
									<c:if test="${user.status ==0}"><td>无效</td></c:if>
									<c:if test="${user.status ==1}"><td>有效</td></c:if>
									<td>
										<a href="toDetailAdmin.do?id=${user.userid}" class="layui-btn layui-btn-normal layui-btn-mini">详细</a>
										<a href="toEditAdmin.do?flag=1&id=${user.userid}" class="layui-btn layui-btn-mini">修改</a>
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
							var username = document.getElementById('username').value;
							var status = document.getElementById('status').value;
							var currentPage = obj.curr;//获取点击的页码      
                            window.location.href ="searchAdmin.do?flag=1&page="+curr+"&userName="+username+"&status="+status;
						}
					}
				});

				$('#search').on('click', function() {
					var username = document.getElementById('username').value;
					var status = document.getElementById('status').value;
					window.location.href="searchAdmin.do?flag=1&page=1&userName="+username+"&status="+status;
				});

				$('#add').on('click', function() {
					window.location.href="toAddAdmin.do";
				});

				$('#import').on('click', function() {
					window.location.href="exportAdmin.do";
				});

				$('.site-table tbody tr').on('click', function(event) {
					var $this = $(this);
					var $input = $this.children('td').eq(0).find('input');
					$input.on('ifChecked', function(e) {
						$this.css('background-color', '#EEEEEE');
					});
					$input.on('ifUnchecked', function(e) {
						$this.removeAttr('style');
					});
					$input.iCheck('toggle');
				}).find('input').each(function() {
					var $this = $(this);
					$this.on('ifChecked', function(e) {
						$this.parents('tr').css('background-color', '#EEEEEE');
					});
					$this.on('ifUnchecked', function(e) {
						$this.parents('tr').removeAttr('style');
					});
				});
				$('#selected-all').on('ifChanged', function(event) {
					var $input = $('.site-table tbody tr td').find('input');
					$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
				});
			});
		</script>
	</body>

</html>