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

		<a href="${pageContext.request.contextPath}/redis/login">로그인</a>
		<a href="${pageContext.request.contextPath}/redis/logout">로그아웃</a>
</body>
</html>