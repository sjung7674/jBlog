/**
 * 
 */
var jBlog = {
		init : function(){
			$("#container").html('');
		},
		write : function(){
			var headers = {};
			headers["X-CSRF-TOKEN"] = $("input[name=_csrf]").val();
			$.ajax({
					url : "/write",
					type : "post",
					dataType : "html",
					headers : headers,
					success : function(html) {
						jBlog.init();
						$("#container").html(html);
					}
			});
		},
		save : function(){
			var category = $("#category").val();
			var title = $("#title").val();
			var content_text = Global_Smart_Editor_Get_Content_For_Editor("ir1").replace("<p><br></p>","");
			
			if(!category){
				alert('카테고리를 선택해주세요');
				$("#category").focus();
				return;
			}else if(!title){
				alert('제목을 입력해주세요');
				$("#title").focus();
				return;
			}else if(!content_text){
				alert('내용을 입력해주세요');
				$("#ir1").focus();
				return;
			}
			var headers = {};
			headers["X-CSRF-TOKEN"] = $("input[name=_csrf]").val();
			$.ajax({
					url : "/save",
					type : "post",
					data: {"category":category,"title":title, "content":Global_Smart_Editor_Get_Content_For_Editor("ir1")},
					headers : headers,
					success : function(obj) {
						console.log(obj);
						var json_obj = $.parseJSON(obj);
						var json_errors=json_obj.errors;
						var json_success=json_obj.success;
						var message="";
						if(json_errors){
							for(var i in json_errors){
								message+=json_errors[i].message+"\n";
							}
							alert(message);
						}else if(json_success){
							for(var i in json_success){
								message+=json_success[i].message+"\n";
							}
							alert(message);
						}
						
					},
					error: function(){
						alert("오류가 발생하였습니다.");
					}
			});
		},
		cancel:function(){
			if(confirm("취소 하시겠습니까?")){
				location.href="/";
			}
		}
}
function click_category(obj){
	$("#category_btn").html($(obj).text());
	$("#category").val($(obj).data("value"));
	
	
}