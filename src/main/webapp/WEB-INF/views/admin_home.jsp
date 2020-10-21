<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page session="true" %>  기본값 true--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>admin</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

		<a href="${pageContext.request.contextPath}/admin/home?menu=1">게시판관리</a>
		<a href="${pageContext.request.contextPath}/admin/home?menu=2">회원관리</a>
		<a href="${pageContext.request.contextPath}/admin/home?menu=3">물품등록관리</a>
		<a href="${pageContext.request.contextPath}/admin/home?menu=4">회원목록</a>
		<hr/>
		
		<c:if test="${param.menu eq 1}">
		게시판관리
		</c:if>
		
		<c:if test="${param.menu eq 2}">
		회원관리
			<form action="${pageContext.request.contextPath}/admin/batchinsert" method="post">
				<table>
				<c:forEach var="i" begin="1" end="5">
					<tr>
						<td><input type="text" name="id[]" value=""></td>
						<td><input type="text" name="pw[]" value="a"></td>
						<td><input type="text" name="name[]" value="홍길동"></td>
						<td><input type="text" name="phone[]" value="010-0000-1111"></td>
						<td><input type="text" name="age[]" value="11"></td>
					</tr>
				</c:forEach>
				</table>
				<input type="submit" value="일괄추가">
			</form>
		</c:if>
		
		<c:if test="${param.menu eq 3}">
		물품등록관리
		</c:if>
		
		<c:if test="${param.menu eq 4}">
			<jsp:include page="admin_menu4.jsp"></jsp:include>
		</c:if>

</body>
</html>