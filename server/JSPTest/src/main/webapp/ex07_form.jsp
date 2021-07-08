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
	
	.table { width: 400px; } 
	th { width: 30%; text-align: center; }
	
</style>

</head>
<body>
	<!-- -->
	<div class="container">
		<h2 class="page-header">버튼 만들기</h2>
		
		<form method="GET" action="ex07_ok.jsp">
			<table class="table table-bordered">
				<tr>
					<th>너비(px)</th>
					<td><input type="number" name="width" class="form-control" /></td>
				</tr>
				<tr>
					<th>높이(px)</th>
					<td><input type="number" name="height" class="form-control" /></td>
				</tr>
				<tr>
					<th>텍스트</th>
					<td><input type="text" name="txt" class="form-control"/></td>
				</tr>
				<tr>
					<th>배경색</th>
					<td><input type="color" name="backColor" class="form-control" style="width:70px;"/></td>
				</tr>
				<tr>
					<th>글자색</th>
					<td><input type="color" name="txtColor" class="form-control" style="width:70px;"/></td>
				</tr>
				<tr>
					<th>글자 크기(px)</th>
					<td><input type="number" name="px" class="form-control" /></td>
				</tr>
				<tr>
					<th>버튼 개수(ea)</th>
					<td><input type="number" name="ea" class="form-control" /></td>
				</tr>
				<tr>
					<th>버튼 간격</th>
					<td>
						좌우 간격: <input type="range" name="range1" class="form-control" />
						상하 간격: <input type="range" name="range2" class="form-control" />
					</td>
				</tr>
				<tr>
					<th></th>
					<td></td>
				</tr>
			</table>
			
			
			<div>
				<input type="submit" value="만들기" class="btn btn-default" style="background-color: coral;"/>			
			</div>
		</form>	
		
		<br />
		<br />
		<br />
	
	</div>
	
	<script>


	</script>
</body>
</html>