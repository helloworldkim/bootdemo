$(function(){
	var checkbox = $('#checkbox');
	checkbox.hide();
	$('input[name=userId]').keyup(function(event){
		var id = $(this).val();
		if(id==''){
			checkbox.fadeOut();
		}
		if(id.length>0){
			
			$.ajax({
				type:'post',
					url:"http://localhost:8080/rest/idcheck.json",
					data:{"userId":id},
					dataType:'json',
					success:function(result){
						var data= result.data;
						if(data==0){
							checkbox.fadeIn();
							$('#chk').text("사용가능합니다");
							$('#chk').css("color","blue");
							//사용가능
						}else{
							//사용불가능
							checkbox.fadeIn();
							$('#chk').text("중복된 아이디입니다");
							$('#chk').css("color","red");
						}
					},
					error:function(e){
						console.log("오류:"+e);
					}
			});
			
			/*
			$.post('http://localhost:8080/rest/idcheck.json',{"userId":id},function(result){
				console.log(result);
				var data= result.data;
				if(data==0){
							checkbox.fadeIn();
							$('#chk').text("사용가능합니다");
							$('#chk').css("color","blue");
							//사용가능
						}else{
							//사용불가능
							checkbox.fadeIn();
							$('#chk').text("중복된 아이디입니다");
							$('#chk').css("color","red");
						}
				
			},'json');
			*/
			/*
			$.get('http://localhost:8080/rest/idcheck/'+id,function(result){
				console.log(result);
				var data= result.data;
				if(data==0){
							checkbox.fadeIn();
							$('#chk').text("사용가능합니다");
							$('#chk').css("color","blue");
							//사용가능
						}else{
							//사용불가능
							checkbox.fadeIn();
							$('#chk').text("중복된 아이디입니다");
							$('#chk').css("color","red");
						}
				
			},'json');
			*/
		}//if문
		//$('#chk').text(id);
	});
});