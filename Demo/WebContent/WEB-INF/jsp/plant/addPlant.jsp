<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html>

	<head>
		<title>植物数据管理</title>
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
				<legend>新增植物</legend>
			</fieldset>
			<form class="layui-form" action="addPlant.do" enctype="multipart/form-data">
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>植物名</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="plantname" class="layui-input" lay-verify="plantname">
					</div>
				</div>
				<div class="layui-form-item" style="width: 200px;">
					<label class="layui-form-label"><font color="red">*</font>状态</label>
					<div class="layui-input-block ">
						<select name="status" lay-filter="status">
							<option value="0">无效</option>
							<option value="1">有效</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item" style="width: 500px;">
					<label class="layui-form-label"><font color="red">*</font>植物分类</label>
					<div class="layui-input-block ">
						<select name="categoryid" lay-filter="categoryid">
							<c:forEach items="${categorylist}" var="list">
								<option value="${list.categoryid}">${list.categoryname}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">植物描述</label>
					<div class="layui-input-block">
						<textarea class="layui-textarea" name="descript" lay-verify="content"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
					plantname: function(value) {
						if(value.length > 20) {
							return '植物名不能超过20字符！';
						}
						else if (value == "" || value == null) {
							return '植物名不能为空！';
						}
					},
					categoryid: function(value) {
						if (value == "" || value == null) {
							return '分类不能为空！';
						}
					}
				});

				//监听提交
				/* form.on('submit(demo1)', function(data) {
					layer.alert(JSON.stringify(data.field), {
						title: '是否确认提交！'
					})
					return false;
				}); */
			});
		</script>
	</body>

</html>