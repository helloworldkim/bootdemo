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
<h4>게시판 글쓰기</h4>
<hr/>
<form action="${pageContext.request.contextPath}/board/write" enctype="multipart/form-data" method="POST">
제목:<input type="text" name="brd_title"><br/>
내용:<textarea rows="6" name="brd_content"></textarea><br/>
작성자:<input type="text" name="brd_writer"><br/>
파일첨부:<input type="file" name="tmp_img"><br/>
	<input type="submit" value="글쓰기">
	<a href="${pageContext.request.contextPath}/board/list">홈으로</a>
</form>
</body>
</html>