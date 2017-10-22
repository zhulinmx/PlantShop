<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>留言详细信息</title>
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
				<legend>留言详细信息</legend>
			</fieldset>

			<form class="layui-form" action="" enctype="multipart/form-data">
				<div class="layui-form-item">
					<label class="layui-form-label">主题</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="title" value="${message.title}" class="layui-input" readonly>
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">创建者</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="userid" value="${message.userid}" class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">留言</label>
					<div class="layui-input-block">
						<textarea class="layui-textarea" name="message" readonly>${message.message}</textarea>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">创建时间</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="ceratetime" value="${dateStr}" class="layui-input" readonly>
					</div>
				</div>
					<div class="layui-form-item">
						<label class="layui-form-label">回复</label>
						<div class="layui-input-block">
							<textarea class="layui-textarea" name="message" readonly>${mess.message}</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">处理时间</label>
						<div class="layui-input-block" style="width: 200px;">
							<input type="text" name="ceratetime" value="${dateStr2}" class="layui-input" readonly>
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