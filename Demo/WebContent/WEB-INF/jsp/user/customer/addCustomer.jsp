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
		<title>注册</title>
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
				<legend>注册</legend>
			</fieldset>

			<form class="layui-form" action="addCustomer.do" enctype="application/x-www-form-urlencoded;charset=UTF-8">
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>姓名</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="username" lay-verify="username" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>登录名</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="loginname" lay-verify="loginname" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>密码</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="password" name="password1" lay-verify="password" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>再次确认</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="password" name="password2" lay-verify="password2" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">联系方式</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="tellphone" lay-verify="tellphone" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>邮箱</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input" >
					</div>
				</div>
				<div class="layui-inline">
						<label class="layui-form-label"><font color="red">*</font>出生年月</label>
						<div class="layui-input-block">
							<input type="text" name="birthday" id="birthday" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<button class="layui-btn layui-btn-primary" onclick="javascript:history.back(-2);">返回</button>
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
					},
					loginname: function(value) {
						if(value.length > 20) {
							return '登录名不能超过20字符！';
						}
						else if (value == "" || value == null) {
							return '登录名不能为空！';
						}
					},
					password2: function(value) {
						var value1=$("#password1").val();
						if(value != value1){
							return '确认密码与密码不一致';
						}
					},
					password1: function(value) {
						if(value.length > 20) {
							return '密码不能超过20字符！';
						}
						else if (value == "" || value == null) {
							return '密码不能为空！';
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