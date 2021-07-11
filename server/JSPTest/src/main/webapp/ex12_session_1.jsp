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
	<!-- ex12_session_1.jsp -->
	<div class="container">
		<h1 class="page-header">두번째 페이지</h1>
		
		<div>pageContext: <%= pageContext.getAttribute("num1") %></div>
		<div>request: <%= request.getAttribute("num2") %></div>
		<div>session: <%= session.getAttribute("num3") %></div>
		<div>application: <%= application.getAttribute("num4") %></div>
	
	</div>
	
	<script>


	</script>
</body>
</html>