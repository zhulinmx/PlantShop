<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<title>修改植物信息</title>
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
				<legend>回复留言</legend>
			</fieldset>

			<form class="layui-form" action="responseMessage.do" enctype="application/x-www-form-urlencoded;charset=UTF-8">
				<input type="hidden" name="userid" value="${sessionScope.user.userid }">
				<input type="hidden" name="parentid" value="${mess.id}">
				<input type="hidden" name="title" value="${mess.title}">
				<div class="layui-form-item">
					<label class="layui-form-label">主题</label>
					<div class="layui-input-block" style="width: 200px;">
						<input type="text" name="title" value="${mess.title}" class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">游客留言</label>
					<div class="layui-input-block">
						<textarea class="layui-textarea" name="parentmessage" readonly="readonly">${mess.message}</textarea>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">回复</label>
					<div class="layui-input-block">
						<textarea class="layui-textarea" name="message"></textarea>
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

				//创建一个编辑器
				var editIndex = layedit.build('LAY_demo_editor');
				//自定义验证规则
				form.verify({
					plantName: function(value) {
						if(value.length > 20) {
							return '植物名不能超过20字符！';
						}
						else if (value == "" || value == null) {
							return '植物名不能为空！';
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