<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
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
				<legend>植物详细信息</legend>
			</fieldset>

			<form class="layui-form" action="" enctype="multipart/form-data">
				<div class="layui-form-item">
					<label class="layui-form-label">植物Id</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="id" value="${plant.plantid}" class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">植物名</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="plantName" value="${plant.plantname}" class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item" style="width: 300px;">
					<label class="layui-form-label"><font color="red">*</font>植物分类</label>
					<div class="layui-input-block ">
						<c:forEach items="${categorylist}" var="list">
							<c:if test="${plant.categoryid==list.categoryid}">
								<input type="text" name="categoryname" value="${list.categoryname}" class="layui-input" readonly>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">植物描述</label>
					<div class="layui-input-block">
						<textarea class="layui-textarea" name="descript"  readonly>${plant.descript}</textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">状态</label>
					<div class="layui-input-block" style="width: 200px;">
						<c:if test="${plant.status == 0}">
						<input type="text" name="satus" value="无效" class="layui-input" readonly>
						</c:if>
						<c:if test="${plant.status == 1}">
						<input type="text" name="satus" value="有效" class="layui-input" readonly>
						</c:if>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">植物图片</label>
					<div class="layui-input-block">
						<img src="${fileUrl}">
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