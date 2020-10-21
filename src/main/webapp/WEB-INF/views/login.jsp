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
<!--root경로 지정이 되어야지만 aws(아마존)서버 등에서 돌아가는게 가능함 root경로 지정!!  -->
<form action="${pageContext.request.contextPath}/member/login" method="POST">
<table class="table table-hover">
	<thead class="thead-dark">
		<tr>
			<th colspan="4">로그인</th>
			<th><a href="${pageContext.request.contextPath}/home">홈으로</a></th>
	</thead>
	<tr>
		<td>아이디:</td>
		<td><input type="text" name="userId"></td>
	</tr>
	<tr>
		<td>비밀번호:</td>
		<td><input type="password" name="userPw"></td>
	</tr>
</table>
	<input class="btn btn-primary" type="submit" value="로그인">
</form>
</body>
</html>