<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!--root경로 지정이 되어야지만 aws(아마존)서버 등에서 돌아가는게 가능함 root경로 지정!!  -->
<div class="container">

<table class="table table-hover">
	<thead class="table table-dark">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일</th>			
		</tr>
	</thead>
	
	<c:forEach var="tmp" items="${list}">
	<c:set var="date" value="${tmp.brd_date}"/>
		<tr>
			<td>${tmp.brd_no}</td>
			<td>
				<a href="${pageContext.request.contextPath}/board/content/${tmp.brd_no}">${tmp.brd_title}</a>
			</td>
			<td>${tmp.brd_writer}</td>
			<td>${tmp.brd_hit}</td>
			<td>${fn:substring(date,0,10)} ${fn:substring(date,11,13)}시 ${fn:substring(date,14,16)}분</td>
		</tr>
	</c:forEach>
</table>
<ul class="pagination">
<c:forEach  var="i" begin="1" end="${cnt}">
	<c:if test="${txt eq ''}">
		<li class="page-item"><a href="${pageContext.request.contextPath}/board/list?page=${i}" class="page-link">${i}</a></li>
	</c:if>
	<c:if test="${txt ne ''}">
		<li class="page-item"><a href="${pageContext.request.contextPath}/board/list?page=${i}&txt=${txt}" class="page-link">${i}</a></li>
	</c:if>
	<%-- 다른방법:<li class="page-item"><a href="?page=${i}&txt=${txt}" class="page-link">${i}</a></li> --%>
</c:forEach>

</ul>
<form action="${pageContext.request.contextPath}/board/list" method="get">
<a class="btn btn-primary pull-right" href="${pageContext.request.contextPath}/board/write">게시글쓰기</a>
<a class="btn btn-primary pull-right" href="${pageContext.request.contextPath}/home">홈으로</a>
	<input type="text" name="txt" placeholder="제목검색">
	<input type="hidden" name="page" value="1">
	<input type="submit" class="btn btn-primary" value="검색">
</form>
</div>
</body>
</html>