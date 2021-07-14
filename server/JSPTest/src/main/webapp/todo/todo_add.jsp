<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<%@ include file="/inc/asset.jsp" %>

<style>
	.container {
		width: 500px;
		box-shadow: 0px 0px 5px #777;
		height: 100vh;
	}
	
	.container .page-header {
		font-variant: small-caps;
		letter-spacing: -1px;
	}
	
	.container .table th {
		text-align: center;
		vertical-align: middle;
		font-variant: small-caps;
	}
	
	.container .table th:nth-child(1) {
		width: 100px;
	}
	
	.container .table td {
		text-align: center;
		vertical-align: middle;
	}
	
	td .glyphicon {
		font-size: 12px;
		display: flex;
		padding: 3px;
	}
	
	.btns {
		display: flex;
		justify-content: space-between;
	}
	
</style>

</head>
<body>
	<!-- todo_list.jsp -->
	<div class="container">
		<h1 class="page-header">Todo List <small>Add</small></h1>		
		
		<form method="POST" action="/jsp/todo/todo_addok.jsp">
			<table class="table table-bordered">
				<tr>
					<th>Todo</th>
					<td><input type="text" name="todo" class="form-control"></td>
				</tr>
			</table>
			<div class="btns">
				<input type="button" value="목록보기" class="btn btn-default" data-toggle="tooltip" title="목록으로 돌아갑니다." onclick="location.href = 'todo_list.jsp';">
				<button type="submit" class="btn btn-primary" data-toggle="tooltip" title="할일을 추가합니다.">추가하기</button>
			</div>
		</form>
	</div>	
	
	<script>
	  
		$('[data-toggle="tooltip"]').tooltip();
		
	</script>
</body>
</html>















