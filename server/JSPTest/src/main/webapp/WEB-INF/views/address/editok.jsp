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
	<!-- editok.jsp -->
	<div class="container">
		<h1 class="page-header">Edit</h1>	
		
		<c:if test="${result == 1}">
			<div>
				<div>수정 성공</div>
				<button type="button" class="btn btn-default"
						onclick="location.href='/jsp/list.do';">목록보기</button>
			</div>
		</c:if>
		
		<c:if test="${result != 1}">
			<div>
				<div>수정 실패</div>
				<button type="button" class="btn btn-default"
						onclick="history.back();">돌아가기</button>
			</div>
		</c:if>
	
	</div>
	
	<script>


	</script>
</body>
</html>