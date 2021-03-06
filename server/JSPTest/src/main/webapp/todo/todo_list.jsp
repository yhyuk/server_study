<%@page import="com.test.jsp.jdbc.DBUtil"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	
	try {
		
		conn = DBUtil.open();
		stat = conn.createStatement();
		
		String sql = "select seq, todo, complete, to_char(regdate, 'yyyy.mm.dd') as regdate from tblTodo order by seq";
		
		rs = stat.executeQuery(sql);
		
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
	.container {
		width: 500px;
		box-shadow: 0px 0px 5px #777;
		height: 100vh;
	}
	.container .page-header {
		font-variant: small-caps;
		letter-spacing: -1px;
	}
	
	.container .table th {
		text-align: center;
		vertical-align: middle;
		font-variant: small-caps;
	}
	
	.container .table th:nth-child(1) {
		width: 50px;
	}
	.container .table th:nth-child(3) {
		width: 70px;
	}
	
	.container .table td {
		text-align: center;
		vertical-align: middle;
	}
	.container .table td:nth-child(2) {
		text-align: left;
	}
	
	td .glyphicon {
		font-size: 12px;
		display: flex;
		padding: 3px;
	}
	
	td:nth-child(2) {
		cursor: pointer;
	}
	
	td:nth-child(2):hover {
		background-color: #EEE;
	}
	
	td small {
		font-size: 10px;
		color: #AAA;
	}
	
	.btns {
		display: flex;
		justify-content: space-between;
	}
	
	.complete {
		text-decoration: line-through;
		opacity: .7;
	}
	
	ol {
	    counter-reset: section;
	    list-style-type: none;
	}
	
	li:before {
	    counter-increment: section;
	    content: counters(section, ".") ".";
	}
	
	#descicon span {
		margin-top: 50px;	
		font-size: 1.5em;
		opacity: .3;
		cursor: pointer;
	}
	
	#desc {
		margin-top: 20px;
		display: none;
	}
	
	#sel1 {
		width: 100px;
		margin-left: auto;
		margin-right: 0px;
		margin-bottom: 10px;
	}
	
</style>

</head>
<body>
	<!-- todo_list.jsp -->
	<div class="container">
		<h1 class="page-header">Todo List <small>List</small></h1>	
		
		<div class="header">
			<select id="sel1" class="form-control">
				<option value="2">??????</option>
				<option value="0">?????????</option>
				<option value="1">??????</option>
			</select>
		</div>	
		
		<table class="table table-bordered">
			<tr>
				<th><input type="checkbox" name="cbAll" id="cbAll"></th>
				<th>Todo</th>
				<th></th>
			</tr>
			<% 
				while ( rs.next() ) { 
			%>
			<tr>
				<td>
					<input type="checkbox" name="cbItem" class="cbItem" value="<%= rs.getString("seq") %>">
				</td>
				<td>
					<span
							data-seq="<%= rs.getString("seq") %>" 
							data-complete="<%= rs.getString("complete") %>" 
							<% if (rs.getString("complete").equals("1")) { out.print("class = 'complete'"); } %>
					>
						<%= rs.getString("todo") %>
					</span> 
					<small><%= rs.getString("regdate") %></small>
				</td>
				<td>
					<button type="button" 
							class="btn btn-success btn-xs" 
							data-toggle="tooltip" 
							title="????????? ???????????????." 
							onclick="edit(<%= rs.getString("seq") %>);">
							<span class="glyphicon glyphicon-edit"></span>
					</button>
				</td>
			</tr>
			<% 
				} 
			%>

		</table>
		<div class="btns">
			<input type="button" value="????????????" class="btn btn-danger" data-toggle="tooltip" title="????????? ????????? ???????????????." onclick="del();">
			<button type="button" class="btn btn-primary" data-toggle="tooltip" title="????????? ???????????????." onclick="add();">????????????</button>
		</div>
		
	</div>	
	
	<script>
	  
		$('[data-toggle="tooltip"]').tooltip();	
		
		//???????????? ?????? ???
		$("#cbAll").click(function() {
			if ( $(this).is(':checked') ) {
				//?????? ???????????? ??????
				$(".cbItem").attr("checked", true);
				
			} else {
				//?????? ???????????? ????????????
				$(".cbItem").attr("checked", false);
			}
		});
		
		//?????? ????????? ?????? ???????????? ???
		$('#cbAll').change(function() {
			if ( $(this).is(":checked") ) { //????????? ?????? ?????? ?????? ???
				// ?????? ????????? ?????? ???????????? ??????
				$("input:checkbox[name='cbItem']").prop("checked", true);
			} else {
				// ?????? ????????? ?????? ???????????? ????????????
				$("input:checkbox[name='cbItem']").prop("checked", false);
			}
		});
		
		$("#descicon span").click(function() {
			$("#desc").toggle();
		});
	
	  	function add() {
	  		location.href = '/jsp/todo/todo_add.jsp';
	  	}
	  	
	  	function edit(seq) {
	  		location.href = '/jsp/todo/todo_edit.jsp?seq=' + seq;
	  	}
	  	
	  	function del() {
	  		
	  		if ( $('.cbItem:checked').length > 0 ) {
	  			
		  		if (confirm('????????? ????????? ??????????????????????')) {
		  			let temp = "";
		  			$('.cbItem:checked').each(function(index, item) {
		  				temp += $(item).val() + ",";
		  			});
		  			location.href = '/jsp/todo/todo_delok.jsp?seq=' + temp;
		  		}
		  		
	  		} else {
	  			alert('????????? ????????? ???????????????.');
	  		}
	  		
	  		
	  	}
	  	
	  	$('td > span').click(function() {
	  		
	  		let seq = $(this).data('seq');
	  		let complete = $(this).data('complete');
	  		
	  		location.href = 'todo_complete.jsp?seq=' + seq + '&complete=' + complete;
	  		
	  	});
	  	
	  	$('#sel1').change(function() {
	  		
	  		$('tr').show();
	  		
	  		if ( $(this).val() == 1 ) {
	  			$('td > span').each(function(index, item) {
	  				if ( $(item).data('complete') == '0' ) {
	  					$(item).parents('tr').hide();
	  				}
	  			});
	  		} else if ( $(this).val() == 0 ) {
	  			$('td > span').each(function(index, item) {
	  				if ( $(item).data('complete') == '1' ) {
	  					$(item).parents('tr').hide();
	  				}
	  			});
	  		}
	  		
	  	});
		
	</script>
</body>
</html>


<%
	rs.close();
	stat.close();
	conn.close();
%>




