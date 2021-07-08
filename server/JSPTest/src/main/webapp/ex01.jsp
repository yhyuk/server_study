<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" import="java.util.*" %>

<!-- import는 독립적으로 써도 되고, 맨위에 써도된다. -->
<%@ page import="java.util.Random" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- ex01.jsp -->
	
	<div><%@ include file="inc/menu.jsp" %></div>
	<% 
		Random rnd = new Random();
	%>
	숫자: <%= 100 / rnd.nextInt(100) %>
	<div><%@ include file="inc/copyright.html" %>저작권</div>
	
</body>
</html>