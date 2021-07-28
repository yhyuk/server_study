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
		<h1 class="page-header">쿠키, Cookie</h1>	
		
		<!--  
			쿠키
			- 세션과 같은 데이터 저장소
			
			쿠키 읽기/쓰기
			- 직접 구현 안함
			- 다른 사람들이 만들어 놓은 기능을 가져와서 사용
		-->
		
		<h2>쿠키 쓰기</h2>
		<button type="button" class="btn btn-default" id="btn1">쓰기</button>
		
		<h2>쿠키 읽기</h2>
		<button type="button" class="btn btn-default" id="btn2">읽기</button>
		
		<hr />
		
		<a href="<%= request.getContextPath() %>/cookie/ex02.do">다른 페이지이동</a>
	</div>
	
	<script src="<%= request.getContextPath() %>/asset/js/cookie.js"></script>
	<script>
	
		$('#btn1').click(function () {
			
			// 쿠키에 데이터 저장하기
			//	- 주의!1: 문자열만 저장할 수 있다. > 쿠키가 텍스트 파일이기 때문이다.
			// function setCookie (key, value, exdays)
			
			setCookie('num', 100); // 100 -> "100"
			setCookie('name', '홍길동');
			setCookie('age', 20);
			setCookie('now', new Date()); // 객체 저장(X) -> 객체의 toString()값이 저장(O)
			
		});
		
		$('#btn2').click(function() {
			
			// 쿠키 읽기
			// function getCookie(key)
			
			alert(getCookie('num'));
			alert(getCookie('name'));
		});


	</script>
</body>
</html>