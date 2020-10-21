<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>admin</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
	<script>
$(function(){
	$("#updateBtn").click(function(){
		$("form").attr("action","${pageContext.request.contextPath}/admin/batchupdate")
	});
});
</script>
</head>
<body>
<h1 style="text-align: center">전체회원목록</h1>
<hr/>
<div class="container">
<form action="${pageContext.request.contextPath}/admin/update" method="post">
<table class="table table-hover">
	<thead class="table table-dark">
		<tr>
			<th>id</th>
			<th>pw</th>
			<th>name</th>
			<th>phone</th>
			<th>age</th>			
			<th>date</th>			
		</tr>
	</thead>
	
	<c:forEach var="tmp" items="${list}">
		<tr>
			<td><input type="text" name=userId value="${tmp.userId}" readonly="readonly"></td>
			<td><input type="text" name=userPw value="${tmp.userPw}"></td>
			<td><input type="text" name=userName value="${tmp.userName}"></td>
			<td><input type="text" name=userPhone value="${tmp.userPhone}"></td>
			<td><input type="text" name=userAge value="${tmp.userAge}"></td>
			<td><input type="text" name=userDate value="${tmp.userDate}" readonly="readonly"></td>
		</tr>
	</c:forEach>
</table>
	<input type="submit" class="btn btn-primary" name="btn" value="수정">
</form>
	
</div>
</body>
</html>