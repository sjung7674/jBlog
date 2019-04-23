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
		}
}