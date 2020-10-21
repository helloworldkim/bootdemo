$(function(){
			
			$.get('http://localhost:8080/rest/boardlist.json?page=1&txt=',function(result){
				var data = result.data;
				var cnt = result.cnt;
				console.log(result);
				$("#tbody").html(data.map(item=> createHTML(item)).join(''));
			},'json')

			
			$("#tbody").click(function(e){
				var target=e.target;
				
				if(target.dataset==null){
					return;
				}
				var no =target.dataset.number;
				//console.log(target.dataset.number);
				$.ajax({
					type:"delete",
					url:"/rest/boardlist.json",
					data:{"no":no},
					success:function(data){
						console.log("삭제결과:"+data);
						$.restcall(1);
					},
					error:function(e){
						console.log(e);
					}
				});
			});
			var restcall = function(idx) { 
				$.get('http://localhost:8080/rest/boardlist.json?page='+idx, function(result){
				var data = result.data;
				var cnt = result.cnt;
				console.log(result);
				$("#tbody").html(data.map(item=> createHTML(item)).join(''));
				},'json');
			}
			
			$('.href').click(function(){
				var idx = $(this).index('.href');
				restcall(idx+1);
			})
			 // 처음 로딩될때 1페이지를 표시
			restcall(1);
});
		
function createHTML(item){
	return `<tr>
		<td>${item.brd_no}</td>
		<td>
			<a href="/board/content/${item.brd_no}">${item.brd_title}</a>
		</td>
		<td>${item.brd_writer}</td>
		<td>${item.brd_hit}</td>
		<td>${item.brd_date}</td>
		<td><input type='button' data-number=${item.brd_no} value="삭제"></td>
	</tr>`
}