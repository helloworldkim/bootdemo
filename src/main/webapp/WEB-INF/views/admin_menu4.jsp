<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
전체 회원목록
<hr/>
<div class="container">
<form action="${pageContext.request.contextPath}/admin/batchupdatedelete" method="post">
<table class="table table-hover">
	<thead class="table table-dark">
		<tr>
			<th>체크</th>
			<th>id</th>
			<th>pw</th>
			<th>name</th>
			<th>phone</th>
			<th>age</th>			
			<th>date</th>			
		</tr>
	</thead>
	
	<c:forEach var="tmp" items="${list}">
		<c:set var="date" value="${tmp.userDate}"/>
		<tr>
			<td><input type="checkbox" name="chk[]" value="${tmp.userId}"></td>
			<td>${tmp.userId}</td>
			<td>${tmp.userPw}</td>
			<td>${tmp.userName}</td>
			<td>${tmp.userPhone}</td>
			<td>${tmp.userAge}</td>
			<td>${fn:substring(date,0,10)} ${fn:substring(date,11,13)}시 ${fn:substring(date,14,16)}분</td>
		</tr>
	</c:forEach>
</table>
	<input type="submit" class="btn btn-primary" name="btn" value="일괄삭제">
	<input type="submit" class="btn btn-primary" name="btn" value="일괄수정">
</form>
	
</div>