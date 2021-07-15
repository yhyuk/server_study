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
	<!-- hello.jsp -->
	<div class="container">
		<h1 class="page-header">Hello(WEB-INF views)</h1>	
		
		
		<div><%= request.getAttribute("msg") %></div>
	
	</div>
	
	<script>


	</script>
</body>
</html>