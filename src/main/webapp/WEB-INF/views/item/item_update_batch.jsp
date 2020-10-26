<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/jpa/item_update_batch.do" method="post">
	<table>
		<tr>
			<th>물품번호</th>
			<th>물품명</th>
			<th>물품내용</th>
			<th>물품가격</th>
		</tr>
	<c:forEach var="i" begin="1" end="3" step="1">
		<tr>
			<td><input type="text" name="nos[]" placeholder="물품번호"></td>
			<td><input type="text" name="names[]" placeholder="물품명"></td>
			<td><input type="text" name="contents[]" placeholder="물품내용"></td>
			<td><input type="text" name="prices[]" placeholder="가격"></td>
		</tr>
	</c:forEach>
	</table>
	<input type="submit" value="일괄수정">
</form>
</body>
</html>