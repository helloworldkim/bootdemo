<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/security/join" method="post">
<!--spring security넣고 해당 임의토큰으로 인증이 된 경우에만 form 을 동작하게됨! input hidden 꼭 들어가야함  -->
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<table class="table table-hover">
	<thead class="thead-dark">
		<tr>
			<th colspan="5" style="text-align: center">가입</th>
		</tr>
	</thead>
	<tr>
		<th>아이디:</th>
		<td><input type="text" name="username"></td>
	</tr>
	<tr>
		<th>비밀번호:</th>
		<td><input type="password" name="password"></td>
	</tr>
	<tr>
		<th>이름:</th>
		<td><input type="text" name="name"></td>
	</tr>
	<tr>
		<th>계정권한</th>
		<td>
		<select name="role">
			<option value="ADMIN">관리자</option>
			<option value="MANAGER">매니저</option>
			<option value="USER">회원</option>
		</select>
	</td>
	</tr>
</table>
	
	 
	
	<input type="submit" value="회원가입">
</form>
</body>
</html>