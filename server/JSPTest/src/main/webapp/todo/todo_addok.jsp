<%@ page import="com.test.jsp.jdbc.DBUtil"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//할일
	//1. 데이터 가져오기(request.getParameter())
	//2. DB 작업 -> insert
	//3. 페이지 이동하기 -> todo_list.jsp
	
	request.setCharacterEncoding("UTF-8");
	
	String todo = request.getParameter("todo");
	
	
	Connection conn = null;
	Statement stat = null;
	int result = -1;
	
	try {
		
		conn = DBUtil.open();
		stat = conn.createStatement();
		
		String sql = String.format("insert into tblTodo(seq, todo) values(seqTodo.nextval, '%s')", todo);
		
		result = stat.executeUpdate(sql); // 1(성공), 0(실패)
		
		// 까먹기 전에 close() 작성
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
	<!-- todo_addok.jsp -->
	
	
	<script>
	
		<% if (result == 1) { %>
			// 성공
			alert('추가 성공!!');
			location.href = '/jsp/todo/todo_list.jsp';
		<% } else { %>
			/// 실패
			alert('추가 실패');
			history.back();
		<% } %>
	
	</script>
	
</body>
</html>















