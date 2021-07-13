<%@ page import="com.test.jsp.jdbc.DBUtil"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%

	// 이 페이지에서 할 일??
	// 1. 수정할 번호 가져오기
	// 2. DB -> select where seq = 수정할 번호
	// 3. ResultSet -> 폼 태그 기본값으로 설정
	
	String seq = request.getParameter("seq");
	
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	
	// ResultSet 받을 변수 지정
	String name = "";
	String age = "";
	String gender = "";
	String address = "";
	
	try {
		
		conn = DBUtil.open();	
		stat = conn.createStatement();
		
		String sql = "select * from tblAddress where seq = " + seq;
		
		rs = stat.executeQuery(sql);
		
		// 미리 만들어준 변수에 옮겨 담기
		if ( rs.next() ) {
			name = rs.getString("name");
			age = rs.getString("age");
			gender = rs.getString("gender");
			address = rs.getString("address");
		}
		
		rs.close();
		stat.close();
		conn.close();
		
	} catch (Exception e) {
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
	.contatiner { width: 600px; }
	
	.container .table th, .table td { 
		text-align: center; 
		vertical-align: middle; 
	}	
	.table th { width: 100px; }
	.table td input[name=name] { width: 100px; }
	.table td input[name=age] { width: 80px; }
	.table td select[name=gender] { width: 80px; }
</style>

</head>
<body>
	<!-- edit.jsp -->
	<div class="container">
		<h1 class="page-header">Address Book</h1>	
		
		<!-- 무조건 POST 임 -->
		<form method="POST" action="/jsp/jdbc/editok.jsp">
			<table class="table table-bordered">
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" class="form-control" autocomplete="off" value="<%= name %>"/></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="number" name="age" min="19" max="100" class="form-control" /></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<select name="gender" class="form-control" >
							<option value="m">남자</option>
							<option value="f">여자</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="address" class="form-control" /></td>
				</tr>
			</table>
			
			<div class="btns">
				<input type="submit" value="수정하기" class="btn btn-default" />
				<input type="button" value="목록보기" class="btn btn-default" 
							onclick="history.back();"/>
			</div>
			
			
			<!-- editok.jsp에 seq를 넘겨야 수정할 수 있다.. -->
			<!-- 하지만 사용자가 seq를 볼 필요는 없으므로 이럴때 hidden 태그를 사용한다. -->
			<input type="hidden" name="seq" value="<%= seq %>" />
		</form>
		
	
	</div>
	
	<script>
		
		// HTML에 db에서 가져온 name, age, gender, address를 각각 value 값으로 넣어도 되지만..
		// 가독성도 낮고, 자바스크립트를 이용해서 하면 쉽게 할 수 있다.
		$("input[name=age]").val("<%= age %>");
		$("input[name=gender]").val("<%= gender %>");
		$("input[name=address]").val("<%= address %>");


	</script>
</body>
</html>