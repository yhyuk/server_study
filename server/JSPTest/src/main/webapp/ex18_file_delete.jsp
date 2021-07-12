<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%

	// location href = "ex18_file_delete.jsp?img=";
	String img = request.getParameter("img");
	
	String path = application.getRealPath("/files/" + img);
	
	File file = new File(path); // 현재 이미지를 참조하는 파일 객체
	
	file.delete(); // 파일 삭제(****)
	
	
	response.sendRedirect("ex18_view.jsp"); // 다시 목록으로 이동하기
	

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
	<!-- ex18_file_delete.jsp -->
	<div class="container">
		<h1 class="page-header"></h1>	
		
		<%= img %>
	
	</div>
	
	<script>


	</script>
</body>
</html>