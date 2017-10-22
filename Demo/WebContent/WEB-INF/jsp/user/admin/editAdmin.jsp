<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setCharacterEncoding("UTF-8");%>
<html>

	<head>
		<title>修改人事信息</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">

		<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	</head>

	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>修改人事信息</legend>
			</fieldset>

			<form class="layui-form" action="editAdmin.do" enctype="multipart/form-data">
				<div class="layui-form-item">
					<label class="layui-form-label">用户ID</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="userid" value="${user.userid}" class="layui-input" readonly="readonly" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>姓名</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="username" value="${user.username}" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">登录名</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="loginname" value="${user.loginname}" class="layui-input" readonly="readonly" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">部门</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="dep" value="${user.dep}" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">联系方式</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="tellphone" value="${user.tellphone}" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="email" value="${user.email}" class="layui-input" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">出生年月</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="birthday" value="${dateStr}" onclick="layui.laydate({elem: this})">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="remark" value="${user.remark}" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item" style="width: 200px;">
					<label class="layui-form-label"><font color="red">*</font>状态</label>
					<div class="layui-input-block ">
						<select name="status" >
							<option value="0" <c:if test="${user.status==0}">selected</c:if>>无效</option>
							<option value="1" <c:if test="${user.status==1}">selected</c:if>>有效</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<!-- <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button> -->
						<button class="layui-btn">立即提交</button>
						<button class="layui-btn layui-btn-primary" onclick="javascript:history.back(-1);">返回</button>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="plugins/layui/layui.js"></script>
		<script>
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer,
					layedit = layui.layedit,
					laydate = layui.laydate;

				//自定义验证规则
				form.verify({
					username: function(value) {
						if(value.length > 20) {
							return '姓名不能超过20字符！';
						}
						else if (value == "" || value == null) {
							return '姓名不能为空！';
						}
					}
				});

				/* //监听提交
				form.on('submit(demo1)', function(data) {
					layer.alert(JSON.stringify(data.field), {
						title: '是否确认提交！'
					})
					return false;
				}); */
			});
		</script>
	</body>

</html>