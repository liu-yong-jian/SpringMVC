<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>


<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/crud
 -->
<!-- 引入jQuery,必须在样式前面 -->
<!-- 引入css、js 样式 -->
<script type="text/javascript" src="${ APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
<link href="${ APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${ APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>

	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 内容 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>ID</th>
							<th>员工姓名</th>
							<th>性别</th>
							<th>电子邮箱</th>
							<th>部门名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						
					
					</tbody>
				
				</table>
			</div>
		</div>
		<!-- 分页 -->
		<div class="row">
			<div class="col-md-6" id="emps_page_info">
				
			</div>
			<div class="col-md-6">
			</div>
		</div>
	</div>
	
<body>

	<script type="text/javascript">
		$(function(){
			
			$.ajax({
				url:"${APP_PATH}/emps",
				data:"pn=1",
				type:"GET",
				success:function(result){
					//console.log(result);
					//alert("弹窗！");
					//构建table 表单
					build_emps_table(result);
					
					//构建分页信息
					build_emps_page(result);
					//构建分页条信息
				}
			});
			
		});
		//构建table 内容
		function build_emps_table(result){
			var emps = result.extend.pageInfo.list;
			$.each(emps, function(index, item){
				//alert(item.empName);//测试
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(item.gender=='M'?"男":"女");
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>").append(item.dept.deptName);
				
				/**
				<button class="btn btn-primary btn btn-primary btn-sm">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					编辑
				</button>
				<button class="btn btn-danger btn btn-primary btn-sm">
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				删除
				</button>
				**/
				var editBtn = $("<button></button>").addClass("btn btn-primary btn btn-primary btn-sm")
				.append($("<span></span>")).addClass("glyphicon glyphicon-pencil").append("编辑");
				var delBtn = $("<button></button>").addClass("btn btn-danger btn btn-primary btn-sm")
				.append($("<span></span>")).addClass("glyphicon glyphicon-trash").append("删除");
				
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				
				//append 返回<tr> 对象
				$("<tr></tr>").append(empIdTd).append(empNameTd)
				.append(genderTd).append(emailTd).append(deptNameTd)
				.append(btnTd)
				.appendTo("#emps_table tbody");//appendTo 添加到对应的表格中
				
			});
		}
		
		function build_emps_page(result){
			$("#emps_page_info").append("当前" + result.extend.pageInfo.pageNum + "页码,总" + result.extend.pageInfo.pages + "页," 
					+ "总" + result.extend.pageInfo.total + "条记录");
		}
		
	</script>
</body>
</html>