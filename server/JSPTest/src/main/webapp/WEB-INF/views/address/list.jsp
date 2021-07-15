<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<%@ include file="/inc/asset.jsp" %>

<style>

</style>

</head>
<body>
	<!-- list.jsp -->
	<div class="container">
		<h1 class="page-header">List</h1>	
		
		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
				<th>주소</th>
			</tr>
			<c:forEach items="${list}" var="map">
			<tr>
				<td>${ map.seq }</td>
				<td>${ map.name }</td>
				<td>${ map.age }</td>
				<td>${ map.gender }</td>
				<td>
					${ map.address }
					<span class="glyphicon glyphicon-trash" 
						onclick="location.href='/jsp/del.do?seq=${ map.seq }';"></span>
					<span class="glyphicon glyphicon-edit" 
						onclick="location.href='/jsp/edit.do?seq=${ map.seq }';"></span>
				</td>
			</tr>
			</c:forEach>
		</table>
		
		<div>
			<button type="button" class ="btn btn-default" onclick="location.href='/jsp/add.do';">추가하기</button>
		</div>
	
	</div>
	
	<script>


	</script>
</body>
</html>