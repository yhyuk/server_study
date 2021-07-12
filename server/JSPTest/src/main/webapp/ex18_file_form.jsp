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
	input[type=file].form-control {
		display: inline-block;
		width: 80%;
	} 
	
	td > input {
		float: left;
		margin-right: 5px;
	}
</style>

</head>
<body>
	<!-- ex18_file_form.jsp -->
	<div class="container">
		<h1 class="page-header">Gallery <small>Add Image</small></h1>
		
		<form method="POST" action="/jsp/ex18_file_ok.jsp" enctype="multipart/form-data">
		
			<table class="table table-bordered">
				<tbody>
					<tr>
						<th>이미지: </th>
						<td>
							<input type="file" name="attach1" class="form-control"  accept=".gif, .jpg, .png"/>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="btns">
				<input type="button" value="이미지 추가하기" class="btn btn-default" id="btnAdd" />
				<input type="submit" value="등록하기" class="btn btn-default" />
			</div>
			
		</form>	
	
	</div>
	
	<script>

		let index = 1;
	
		$('#btnAdd').click(function() {
			
			index++;
			
			$("table tbody").append("<tr><th>이미지: </th><td><input type=\"file\" name=\"attach" + index + "\" class=\"form-control\" /><input type=\"button\" value=\"delete\" class=\"btn btn-default\" onclick=\"delBtn();\"  accept=\".gif, .jpg, .png\" /></td></tr>")
		
		});
		
		function delBtn() {
			$(event.srcElement).parent().parent().remove();
		}
	
	</script>
</body>
</html>