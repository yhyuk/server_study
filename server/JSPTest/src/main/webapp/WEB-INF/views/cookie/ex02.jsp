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
	<!-- cookie/ex01.jsp -->
	<div class="container">
		<h1 class="page-header">다른 페이지</h1>
		
		<h2>쿠키 읽기</h2>
		<button type="button" class="btn btn-default" id="btn2">읽기</button>
			
	
	</div>
	
	<script src="<%= request.getContextPath() %>/asset/js/cookie.js"></script>
	<script>
	
		$('#btn2').click(function() {
			
			// 쿠키 읽기
			// function getCookie(key)
			
			alert(getCookie('num'));
			alert(getCookie('name'));
		});


	</script>
</body>
</html>