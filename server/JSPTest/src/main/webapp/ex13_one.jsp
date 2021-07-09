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

</style>

</head>
<body>
	<!-- ex13_one.jsp -->
	<div class="container">
		<h1 class="page-header">첫번째 페이지</h1>	
	
		<% 
			// 내장 객체 + 값
			pageContext.setAttribute("num1", 100);
			request.setAttribute("num2", 200);
			session.setAttribute("num3", 300);
			application.setAttribute("num4", 400);
		%>
	
		<div>num1: <%= pageContext.getAttribute("num1") %></div>
		<div>num2: <%= request.getAttribute("num2") %></div>
		<div>num3: <%= session.getAttribute("num3") %></div>
		<div>num4: <%= application.getAttribute("num4") %></div>
		
		<a href="ex13_two.jsp">두번째 페이지</a>
		
		<%
			// response.sendRedirect("ex13_two.jsp");
			pageContext.forward("ex13_two.jsp");
		
		%>

	</div>
	
		
	
	<script>
		// location.href = "ex13_two.jsp";
	</script>
</body>
</html>