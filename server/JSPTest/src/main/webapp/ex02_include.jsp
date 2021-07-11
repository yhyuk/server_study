<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- ex02_include.jsp -->
	<h1>include 예제</h1>
	
	<div><%@ include file="inc/menu.jsp" %></div>
	<div><%@ include file="inc/copyright.html" %>저작권</div>
	

</body>
</html>