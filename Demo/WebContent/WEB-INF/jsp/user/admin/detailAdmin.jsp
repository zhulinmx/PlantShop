<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

	<head>
		<title>管理员详细信息</title>
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
				<legend>管理员详细信息</legend>
			</fieldset>

			<form class="layui-form" action="" enctype="multipart/form-data">
				<div class="layui-form-item">
					<label class="layui-form-label">用户ID</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="userid" lay-verify="userid" value="${user.userid}" autocomplete="off" class="layui-input" readonly="readonly" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>姓名</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="username" lay-verify="username" value="${user.username}" autocomplete="off" class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">登录名</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="loginname" lay-verify="loginname" value="${user.loginname}" autocomplete="off" class="layui-input" readonly="readonly" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">部门</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="dep" lay-verify="dep" value="${user.dep}" autocomplete="off" class="layui-input" readonly="readonly">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">联系方式</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="tellphone" lay-verify="tellphone" value="${user.tellphone}" autocomplete="off" class="layui-input" readonly="readonly">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="email" lay-verify="email" value="${user.email}" autocomplete="off" class="layui-input" readonly="readonly">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"><font color="red">*</font>出生年月</label>
					<div class="layui-input-block">
						<input type="text" name="birthday" id="birthday" lay-verify="date" value="${dateStr}" autocomplete="off" class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="remark" lay-verify="remark" value="${user.remark}" autocomplete="off" class="layui-input" readonly="readonly">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">状态</label>
					<div class="layui-input-block" style="width: 200px;">
						<c:if test="${user.status == 0}">
						<input type="text" name="loginname" value="无效" class="layui-input" readonly>
						</c:if>
						<c:if test="${user.status != 0}">
						<input type="text" name="loginname" value="有效" class="layui-input" readonly>
						</c:if>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-primary" onclick="javascript:history.back(-1);">返回</button>
					</div>
				</div>
			</form>
		</div>
	</body>

</html>