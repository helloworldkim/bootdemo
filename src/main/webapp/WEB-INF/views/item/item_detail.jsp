<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<!--제이쿼리-->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<!--root경로 지정이 되어야지만 aws(아마존)서버 등에서 돌아가는게 가능함 root경로 지정!!  -->
<div class="container">

<a class="btn btn-primary float-right" href="${pageContext.request.contextPath}/jpa/item_home.do">홈으로</a>
<form action="${pageContext.request.contextPath}/jpa/item_image_insert.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="${obj.no}">
<table class="table table-hover">
	<thead class="table table-dark">
		<tr>
			<td colspan="4">물품상세</td>		
		</tr>
	</thead>
	<tbody>
		<tr>
		<th>물품번호:</th>
		<td>${obj.no}</td>
	</tr>
	<tr>
		<th>이름:</th>
		<td>${obj.name}</td>
	</tr>
	<tr>
		<th>내용:</th>
		<td>${obj.content}</td>
	</tr>
	<tr>
		<th>가격:</th>
		<td>${obj.price}</td>
	</tr>
	<tr>
		<th>등록일:</th>
		<td>${obj.date}</td>
	</tr>
	<tr>
		<th>이미지1:</th>
		<td><input type="file" name="img1"></td>
	</tr>
	<tr>
		<th>이미지2:</th>
		<td><input type="file" name="img1"></td>
	</tr>
	</tbody>
</table>
	<input type="submit" class="btn btn-success" value="iamge_insert">
</form>
	<c:forEach var="tmp" items="${list}">
		<form action="${pageContext.request.contextPath}/jpa/item_image_delete.do" method="get">
		<input type="hidden" name="no" value="${tmp.no}">
			<a href="${pageContext.request.contextPath}/jpa/imgview?no=${tmp.no}" target="_blink">
				<img src="data:image/png;base64,${tmp.strimg}" width="200px" height="100px">
			</a>
		<input type="submit" value="삭제"> 
		</form>
	</c:forEach>
	<br/>
	<a href="${pageContext.request.contextPath}/jpa/itm_img_view?no=${obj.no}">전체이미지보기</a>
	<br/>

</div>
</body>
</html>