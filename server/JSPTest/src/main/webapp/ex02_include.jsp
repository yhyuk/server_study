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
	<h1>조각 페이지</h1>
	
	<div><%@ include file="inc/menu.jsp" %></div>
	<div style="padding: 100px 0;">본문</div>
	<div><%@ include file="inc/copyright.html" %>저작권</div>
	

</body>
</html>