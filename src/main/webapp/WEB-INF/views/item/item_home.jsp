<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
item home 입니다<br/>
<a href="${pageContext.request.contentType}/jpa/item_insert.do">물품등록</a>
<a href="${pageContext.request.contentType}/jpa/item_select.do">물품리스트</a><br/>
<a href="${pageContext.request.contentType}/jpa/item_insert_batch.do">batch insert</a>
<a href="${pageContext.request.contentType}/jpa/item_update_batch.do">batch update</a><br/>
<a href="${pageContext.request.contentType}/jpa/item_select_batch.do">batch list</a><br/>
</body>
</html>