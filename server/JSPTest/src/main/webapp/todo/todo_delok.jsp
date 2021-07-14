<%@page import="java.sql.PreparedStatement"%>
<%@ page import="com.test.jsp.jdbc.DBUtil"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//할일
	//1. 선택된 항목 가져오기
	//2. DB 작업 -> delete
	//3. 페이지 이동하기 -> todo_list.jsp
	
	String seq = request.getParameter("seq");
	seq = seq.substring(0, seq.length() - 1);
	String[] slist = seq.split(",");
	
	Connection conn = null;
	PreparedStatement stat = null;
	int result = -1;
	
	try {
		
		String sql = "delete from tblTodo where seq = ?";

		conn = DBUtil.open();
		stat = conn.prepareStatement(sql);
		
		for ( String s : slist ) {
			stat.setString(1, s);
			result = stat.executeUpdate();
		}
		
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
	<!-- todo_del_ok.jsp -->
	
	<script>
		
		<% if (result == 1) { %>
			// 성공
			alert('삭제 성공!!');
			location.href = '/jsp/todo/todo_list.jsp';
		<% } else { %>
			/// 실패
			alert('삭제 실패..');
			history.back();
		<% } %>

	
	</script>
	
</body>
</html>















