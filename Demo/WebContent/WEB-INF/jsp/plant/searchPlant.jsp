<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>植物管理</title>
		<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="css/global.css" media="all">
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/table.css" />
	</head>

	<body>
		<div class="admin-main">
			<blockquote class="layui-elem-quote">
				名称：<input type="text" id="plantname" value="${plantName }">
				状态：<select id="status">
					<option value="">全部</option>
					<option value="0"<c:if test="${status==0}">selected</c:if>>无效</option>
					<option value="1"<c:if test="${status==1}">selected</c:if>>有效</option>
				</select>
				<a href="#" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 搜索
				</a>
				<a href="#" class="layui-btn layui-btn-small" id="add">
					<i class="layui-icon">&#xe608;</i> 新增植物信息
				</a>
				<a href="#" class="layui-btn layui-btn-small" id="import">
					<i class="layui-icon">&#xe608;</i> 导出数据
				</a>
				
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>植物管理</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" id="selected-all"></th>
								<th>id</th>
								<th>植物名称</th>
								<th>创建时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${plantList}" var="plant">
								<tr>
									<td><input type="checkbox"></td>
									<td>${plant.plantid}</td>
									<td>
										<a href="detailPlant.do?plantid=${plant.plantid}">${plant.plantname}</a>
									</td>
									<td>${plant.ceratetime}</td>
									<c:if test="${plant.status ==0}"><td>无效</td></c:if>
									<c:if test="${plant.status ==1}"><td>有效</td></c:if>
									<td>
										<a href="detailPlant.do?plantid=${plant.plantid}" class="layui-btn layui-btn-normal layui-btn-mini">详细</a>
										<a href="toEditPlant.do?plantid=${plant.plantid}" class="layui-btn layui-btn-mini">修改</a>
										<input type="hidden" id="plantid" value="${plant.plantid}">
										<!-- <input name="file" class="layui-upload-file"  type="file"> -->
										<!-- <a href="toAddPlantPicture.do?plantid=${plant.plantid}" class="layui-btn layui-btn-mini">添加图片</a> -->
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

			layui.use(['icheck', 'laypage','layer','upload'], function() {
				
				 layui.upload({
				    url: '' //上传接口
				    ,success: function(res){ //上传成功后的回调
				      console.log(res)
				    }
				  }); 
			
				  layui.upload({
				    url:''
				    ,elem: '#test' //指定原始元素，默认直接查找class="layui-upload-file"
				    ,method: 'post' //上传接口的http类型
				    ,success: function(res){
				      LAY_demo_upload.src = res.url;
				    }
				  });
				
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
							var plantname = document.getElementById('plantname').value;
							var status = document.getElementById('status').value;
							var currentPage = obj.curr;//获取点击的页码      
							window.location.href ="searchPlant.do?page="+curr+"&plantname="+plantname+"&status="+status;
						}
					}
				});

				$('#search').on('click', function() {
					var plantname = document.getElementById('plantname').value;
					var status = document.getElementById('status').value;
					window.location.href="searchPlant.do?flag=1&page=1&plantname="+plantname+"&status="+status;
				});

				$('#add').on('click', function() {
					window.location.href="toAddPlant.do";
				});

				$('#import').on('click', function() {
					window.location.href="exportPlantInfo.do";
				});

				$('#selected-all').on('ifChanged', function(event) {
					var $input = $('.site-table tbody tr td').find('input');
					$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
				});
			});
		</script>
	</body>

</html>