<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!--root경로 지정이 되어야지만 aws(아마존)서버 등에서 돌아가는게 가능함 root경로 지정!!  -->
<div class="container">
<form action="${pageContext.request.contextPath}/jpa/item_update.do" method="post">
<table class="table table-hover">
	<thead class="thead-dark">
		<tr>
			<th colspan="5">물품수정</th>
		</tr>
	</thead>
		<tr>
			<th>물품번호</th><td><input type="text" name="no" value="${obj.no}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>물품명</th>
			<td><input type="text" name="name" value="${obj.name}" ></td>
		</tr>
		<tr>
			<th>물품내용</th>
			<td><input type="text" name="content" value="${obj.content}" ></td>
		</tr>
		<tr>
			<th>물품가격</th>
			<td><input type="text" name="price" value="${obj.price}" ></td>
		</tr>
		<tr>
			<th>등록일</th>	
			<td><input type="text" value="${obj.date}" readonly="readonly"></td>
		</tr>
</table>
<input type="submit" class="btn btn-primary" value="수정하기">
</form>
</div>
</body>
</html>