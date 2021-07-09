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
	<!-- ex14_one.jsp -->
	<div class="container">
		<h1 class="page-header">첫번째 페이지</h1>	
		
		<%-- <%@ include file="inc/menu.jsp" %> 랑 동일하다. --%>
		<jsp:include page="inc/menu.jsp"></jsp:include>
		
		<!-- pageContext.forward("ex14_two.jsp);랑 동일하다. -->
		<jsp:forward page="ex14_two.jsp"></jsp:forward>
	</div>
	
	<script>


	</script>
</body>
</html>