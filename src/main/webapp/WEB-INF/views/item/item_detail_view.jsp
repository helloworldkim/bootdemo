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
이미지를 n번 호출한다!
<input type="hidden" id="number" value="${param.no}">
<script>
    $(function(){
    	var number = $("#number").val();
    	console.log(number);
        $.ajax({
        type:"post",
        url:'/jpa/itm_img_view',
        data:{"no":number},
		    success:function(data){
                var result = data.result;
                console.log(result);
		    },
		    error:function(e){
		        //alert(e);
		    }
        });
    });
</script>
</div>
</body>
</html>