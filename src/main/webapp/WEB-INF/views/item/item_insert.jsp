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
<form action="${pageContext.request.contextPath}/jpa/item_insert.do" method="POST">
	물품명:<input type="text" name="name"><br/>
	물품설명:<textarea rows="6" name="content"></textarea><br/>
	물품가격:<input type="text" name="price"><br/>
	<input type="submit" value="물품등록">
</form>
<a href="${pageContext.request.contextPath}/jpa/item_home.do">홈으로</a>
</body>
</html>