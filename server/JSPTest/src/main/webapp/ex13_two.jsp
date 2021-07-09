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
	<!-- ex13_two.jsp -->
	<div class="container">
		<h1 class="page-header">두번째 페이지</h1>	
		
		<div>num1: <%= pageContext.getAttribute("num1") %></div>
		<div>num2: <%= request.getAttribute("num2") %></div>
		<div>num3: <%= session.getAttribute("num3") %></div>
		<div>num4: <%= application.getAttribute("num4") %></div>
	
	</div>
	
	<script>


	</script>
</body>
</html>