<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page session="true" %>  기본값 true--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<h1>security home</h1>
	<security:authorize access="!isAuthenticated()">
		<a href="${pageContext.request.contextPath}/security/login">로그인</a>
		<a href="${pageContext.request.contextPath}/security/join">회원가입</a>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<!--security에 사용된 객체  -->
		OBJECT:<security:authentication property="principal"/><br/>
		ROLE: <security:authentication property="authorities"/><br/>
		아이디: <security:authentication property="principal.username"/> <br/>
		이름: <security:authentication property="principal.name"/> 님 환영합니다<br/>
		가입일자: <security:authentication property="principal.userdate"/><br>
		
		<security:authorize access="hasAuthority('ADMIN')">
			관리자입니다dsadas
		</security:authorize>
		<security:authorize access="hasAuthority('MANAGER')">
			매니저입니다
		</security:authorize>
		<security:authorize access="hasAuthority('USER')">
			사용자입니다
		</security:authorize>
		<form action="${pageContext.request.contextPath}/security/logout" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="submit" value="로그아웃">
		</form>
	</security:authorize>

		

</body>
</html>