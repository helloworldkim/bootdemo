<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page session="true" %>  기본값 true--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
	<%-- <c:if test="${empty USERID_SESSION}"> --%>
	<c:if test="${sessionScope.USERID_SESSION eq null}">
		<a href="${pageContext.request.contextPath}/member/login">로그인</a>
		<a href="${pageContext.request.contextPath}/member/join">회원가입</a>
	</c:if>
	<%-- <c:if test="${not empty USERID_SESSION}"> --%>
	<c:if test="${sessionScope.USERID_SESSION ne null}">
		<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/board/list">게시판</a>
</body>
</html>