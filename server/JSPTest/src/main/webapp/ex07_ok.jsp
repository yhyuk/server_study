<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%
	
	String width = request.getParameter("width");
	String height = request.getParameter("height");
	
	String txt = request.getParameter("txt");
	
	String backColor = request.getParameter("backColor");
	String txtColor = request.getParameter("txtColor");
	
	String px = request.getParameter("px");
	
	int count = Integer.parseInt(request.getParameter("ea"));

	String range1 = request.getParameter("range1");
	String range2 = request.getParameter("range2");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<%@ include file="/inc/asset.jsp" %>

<style>

	.button {
	
		width: <%= width %>px;
		height: <%= height %>px;
		background-color: <%= backColor %>;
		color: <%= txtColor %>;
		font-size: <%= px %>px;
		margin: <%= range1%>px <%= range2 %>px;
		
	}

</style>

</head>
<body>
	<!-- -->
	<div class="container">
		<h1 class="page-header">결과</h1>	
		
		<table class="table table-bordered">
			<tr>
				<th style="text-align: center;">버튼</th>
			</tr>
			<tr>
				<td>
										<%-- 
					<button class="button" style="width:<%= width %>px;height:<%= height %>px;">Button</button> 
					--%>
					<%-- 
					<button class="button"><%= text %></button> 
					--%>
					<!-- 
					<button class="button">
						<span class="glyphicon glyphicon-heart"></span>
						Button
					</button> 
					-->
					<% for (int i=0; i < count; i++) { %>
						<button class="button">
							<%-- <% if (!icon.equals("none")) { %>
								<span class="<%= icon %>"></span>
							<% } %> --%>
							<%= txt %>
						</button>
					<% } %>
				</td>
			</tr>
		</table>
	
	</div>
	
	<script>


	</script>
</body>
</html>