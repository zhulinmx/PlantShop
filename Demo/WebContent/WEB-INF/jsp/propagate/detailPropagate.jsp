<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

	<head>
		<title>植物详细信息</title>
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
				<legend>新增宣传</legend>
			</fieldset>

			<form class="layui-form" action="addPropagate.do" enctype="multipart/form-data">
				<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="title" lay-verify="title" value="${propagate.title}" autocomplete="off" class="layui-input" readonly="readonly" >
					</div>
				</div>
				<div class="layui-inline">
						<label class="layui-form-label">有效日期</label>
						<div class="layui-input-block">
							<input type="text" name="title" lay-verify="title" value="${deadlinetime}" autocomplete="off" class="layui-input" readonly="readonly" >
						</div>
				</div>
				<div class="layui-inline">
						<label class="layui-form-label">创建日期</label>
						<div class="layui-input-block">
							<input type="text" name="title" lay-verify="title" value="${ceratetime}" autocomplete="off" class="layui-input" readonly="readonly" >
						</div>
				</div>
				<div class="layui-form-item" style="width: 200px;">
					<label class="layui-form-label"><font color="red">*</font>状态</label>
					<div class="layui-input-block ">
						<c:if test="${propagate.status == 0}">
						<input type="text" name="status" value="无效" class="layui-input" readonly>
						</c:if>
						<c:if test="${propagate.status != 0}">
						<input type="text" name="status" value="有效" class="layui-input" readonly>
						</c:if>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">内容</label>
					<div class="layui-input-block">
						<textarea class="layui-textarea" name="content" lay-verify="content">${propagate.content }</textarea>
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