<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<h4 style="text-align: center;">게시판 수정</h4>
<form action="${pageContext.request.contextPath}/board/update" method="POST">
<table class="table table-hover">
	<thead class="thead-dark">
		<tr>
			<th colspan="2">게시글</th>
		</tr>
	</thead>
	<tr>
		<th>글번호:</th>
		<td><input type="text" name="brd_no" value="${obj.brd_no}" readonly="readonly"></td>
	</tr>
	<tr>
		<th>글제목:</th>
		<td><input type="text" name="brd_title" value="${obj.brd_title}"></td>
	</tr>
	<tr>
		<th>내용:</th>
		<td><textarea rows="10" cols="50" name="brd_content">${obj.brd_content}</textarea></td>
	</tr>
	<tr>
		<th>작성자:</th>
		<td><input type="text" name="brd_writer" value="${obj.brd_writer}"></td>
	</tr>
	<tr>
		<th>조회수:</th>
		<td><input type="text" name="brd_hit" value="${obj.brd_hit}" readonly="readonly"></td>
	</tr>
	<tr>
		<th>작성일:</th>
		<td><input type="text" name="brd_date" value="${obj.brd_date}" readonly="readonly"></td>
	</tr>
	</table>
		<input type="submit" value="수정하기">
</form>
</div>
</body>
</html>