<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<h4 style="text-align: center;">게시판 글보기</h4>
<table class="table table-hover">
	<thead class="thead-dark">
		<tr>
			<th colspan="2">게시글내용</th>
		</tr>
	</thead>
	<tr>
		<th>제목:</th>
		<td>${obj.brd_title}</td>
	</tr>
	<tr>
		<th>내용:</th>
		<td>${obj.brd_content}</td>
	</tr>
	<tr>
		<th>조회수:</th>
		<td>${obj.brd_hit}</td>
	</tr>
	<tr>
		<th>작성자:</th>
		<td>${obj.brd_writer}</td>
	</tr>
	<tr>
		<th>작성일:</th>
		<td>${obj.brd_date}</td>
	</tr>
	<tr>
		<th>이미지:</th>
		<td><img alt="이미지" src="${pageContext.request.contextPath}/board/imgview?no=${obj.brd_no}" width="50px" height="50px"></td>
	</tr>
</table>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/board/list">글목록</a>
<c:if test="${prev ne 0}">
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/board/content?no=${prev}">이전글</a>
</c:if>
<c:if test="${next ne 0}">
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/board/content?no=${next}">다음글</a>
</c:if>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/board/update?no=${obj.brd_no}">글수정</a>
<form style="display: inline-block;" action="${pageContext.request.contextPath}/board/delete" method="POST">
	<input  type="hidden" name="no" value="${obj.brd_no}">
	<input class="btn btn-primary" type="submit" value="글삭제">
</form>

</div>
</body>
</html>