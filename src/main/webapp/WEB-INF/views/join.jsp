<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- jquery cdn  -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- jquery ui cdn -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/join.js"></script>
</head>
<body>
<!--root경로 지정이 되어야지만 aws(아마존)서버 등에서 돌아가는게 가능함 root경로 지정!!  -->
<form action="${pageContext.request.contextPath}/member/join" enctype="multipart/form-data" method="POST">
<table class="table table-hover">
	<thead class="table table-dark">
		<tr>
			<th colspan="5">회원가입</th>
		</tr>
	</thead>
	<tr>
		<th>아이디:</th>
		<td><input type="text" name="userid"></td>
	</tr>
	<tr id='checkbox'>
		<th>중복체크:</th>
		<td colspan="2"><div id="chk"></div></td>
	</tr>
	<tr>
		<th>비밀번호:</th>
		<td><input type="password" name="userpw"></td>
	</tr>
	<tr>
		<th>비밀번호 확인:</th>
		<td><input type="password"></td>
	</tr>
	<tr>
		<th>이름:</th>
		<td><input type="text" name="username"></td>
	</tr>
	<tr>
		<th>전화번호:</th>
		<td><input type="text" name="userphone"></td>
	</tr>
	<tr>
		<th>나이:</th>
		<td><input type="text" name="userage"></td>
	</tr>
</table>
	<input class="btn btn-primary" type="submit" value="회원가입">
</form>
</body>
</html>