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
<!--제이쿼리-->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<!--root경로 지정이 되어야지만 aws(아마존)서버 등에서 돌아가는게 가능함 root경로 지정!!  -->
<div class="container">

<table class="table table-hover">
	<thead class="table table-dark">
		<tr>
			<th>물품번호</th>
			<th>물품명</th>
			<th>물품내용</th>
			<th>물품가격</th>
			<th>등록일</th>			
			<th>수정</th>			
			<th>삭제</th>			
		</tr>
	</thead>
	
	<c:forEach var="tmp" items="${list}">
		<tr>
			<td>${tmp.no}</td>
			<td>${tmp.name}</td>
			<td>${tmp.content}</td>
			<td>${tmp.price}</td>
			<td>${tmp.date}</td>
			<td><a href="#" class="btn btn-primary" data-no="${tmp.no}">수정</a></td>
			<td><a href="#" class="btn btn-primary" data-no="${tmp.no}">삭제</a></td>
		</tr>
		
	</c:forEach>
</table>
<script>
$(function(){
	$("a").click(function(event){
		//event.preventDefault();
		//수정,삭제 구분
		var a = $(this).text();
		//dataset에 있는정보 가져오기 물품번호
		var no = $(this).data('no');
		if(a=="삭제"){
			var result = confirm("정말 삭제하시겠습니까?");
			if(!result) return;
			
			//this.href='${pageContext.request.contextPath}/jpa/item_delete.do?no='+no;
			/*
			$.ajax({
			type:"post",
			url:'/jpa/item_delete.do',
			data:{"no":no},
			success:function(data){
				console.log(no);
				//주소변경
				location.href='/jpa/item_select.do';
			},
			error:function(e){
				alert(e);
			}
			
		});	*/
			console.log("삭제");
		}
		else if(a=="수정"){
			this.href='${pageContext.request.contextPath}/jpa/item_update.do?no='+no;
		}
		

	});
});
</script>

</div>
</body>
</html>