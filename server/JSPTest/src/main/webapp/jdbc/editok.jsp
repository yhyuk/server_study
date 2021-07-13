<%@ page import="java.sql.Statement"%>
<%@ page import="com.test.jsp.jdbc.DBUtil"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%

	// 이 페이지에서 할 일?
	// 1. 데이터 가져오기
	// 2. DB -> update 실행
	// 3. 페이지 이동하기 -> list.jsp
	
	request.setCharacterEncoding("UTF-8");
	
	// 1.
	String seq = request.getParameter("seq"); // edit.jsp에서 히든태그로 받은 seq
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	String gender = request.getParameter("gender");
	String address = request.getParameter("address");
	
	// 2. 
	Connection conn = null;
	Statement stat = null;
	int result = -1;
	
	try {
		
		conn = DBUtil.open();
		stat = conn.createStatement();
		
		String sql = String.format("update tblAddress set name = '%s', age = '%s', gender = '%s', address = '%s' " 
									+ "where seq = %s", name, age, gender, address, seq);
		
		result = stat.executeUpdate(sql); // 1(성공), 0(실패)
		
		stat.close();
		conn.close();
		
	} catch(Exception e) {
		System.out.println(e);
	}
	
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
	<!--editok.jsp -->
	<div class="container">
		<h1 class="page-header">Address Book</h1>	
	
	</div>
	
	<script>
		
		<% if (result == 1) { %>
			alert('수정 성공!!');
			location.href = '/jsp/jdbc/list.jsp';
		<% } else { %>
			alert('수정 실패..');
			history.back();
		<% } %>

	</script>
</body>
</html>