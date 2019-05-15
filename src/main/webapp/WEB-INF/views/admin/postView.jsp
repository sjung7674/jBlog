<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>jBlog Admin - PostView</title>

  <!-- Custom fonts for this template-->
  <link href="/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="/admin/css/sb-admin-2.css" rel="stylesheet"> 

</head>

<body id="page-top">
  <!-- Page Wrapper -->
  <div id="wrapper">
	<jsp:include page="./include/nav.jsp"/>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
          <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-primary" type="button">
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>
          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Post</h1>
          </div>
          <div class="row">
		      <div class="col mx-auto">
		      	<div style="margin-bottom:5px;">
		      		<div class="input-group input-group-sm mb-1">
					  <div class="input-group-prepend">
					    <button class="btn btn-outline-secondary dropdown-toggle" id="category_btn" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="min-width:100px">
					    	<c:choose>
						  		<c:when test="${not empty post_dto.category }">
						  			<c:forEach items="${categoryList }"  var="list">
						  				<c:if test="${list.idx== post_dto.category }">
						  					${list.category }
						  				</c:if>
						        	</c:forEach>
						  		</c:when>
						  		<c:otherwise>
						  			선택하세요.
						  		</c:otherwise>
						  	</c:choose>
					    </button>
					    <div class="dropdown-menu">
					    	<c:forEach items="${categoryList }"  var="list">
					    		<a class="dropdown-item" href="javascript:;" data-value="${list.idx }" onclick="click_category(this)">${list.category }</a>
				        	</c:forEach>
					    </div>
					  </div>
					  <input type="hidden" id="category" value="${post_dto.category}">
					  <input type="text" id="title" class="form-control" aria-label="제목" value="${post_dto.title }">
					</div>
					<div class="input-group input-group-sm mb-1">
					  	<div class="input-group-prepend">
					    	<span class="input-group-text text-center" id="inputGroup-sizing-sm" style="min-width:100px">부제목</span>
					  	</div>
					  		<input type="text" id="sub_title" class="form-control" aria-label="부제" aria-describedby="inputGroup-sizing-sm" value="${post_dto.sub_title }">
					</div>
					<div class="input-group input-group-sm mb-1">
					  <div class="input-group-prepend">
					    <span class="input-group-text text-center" style="min-width:100px">헤더 이미지</span>
					  </div>
					  <div class="custom-file" style="height:37.99px">
					    <input type="file" class="custom-file-input" id="inputGroupFile01" style="height:unset;" onchange="file_change(this);">
					    <label class="custom-file-label" for="inputGroupFile01" style="font-size:medium;">
					   		 <c:choose>
						  		<c:when test="${not empty post_dto.header_image }">
						  			${post_dto.header_image }
						  		</c:when>
						  		<c:otherwise>
						  			 파일을 선택하세요
						  		</c:otherwise>
						  	</c:choose>
					    </label>
					  </div>
					</div>
						<img src="/getImg?imgName=${post_dto.header_image }" id="thumbnail" class="img-thumbnail" width="20%" onerror="this.src='/img/thumbnail_error.jpg'" onclick="imgclick();">
			    </div><!-- /.col-lg-6 -->
		  		<textarea name="ir1" id="ir1" rows="10" cols="100" style="height:400px;">${post_dto.content }</textarea>
		  		<div class="btn-group btn-group-sm float-right" role="group" aria-label="submit_or_cancel">
		  			<button type="button" class="btn btn-outline-primary" onclick="update();">수정</button>
					<button type="button" class="btn btn-outline-danger" onclick="delete_post();">삭제</button>
				</div>
		  		<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
		  		<input type="hidden"	id="idx" name="idx"	value="${post_dto.idx}"/>
		      </div>
		    </div>
		</div>
      </div>
      <!-- End of Main Content -->
      <jsp:include page="./include/footer.jsp"/>
    </div>
    <!-- End of Content Wrapper -->
  </div>
  <!-- End of Page Wrapper -->
  
<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
  <!-- Bootstrap core JavaScript-->
  <script src="/admin/vendor/jquery/jquery.min.js"></script>
  <script src="/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/admin/js/sb-admin-2.min.js"></script>
  <script type="text/javascript" src="/admin/js/smart_editor.js"></script>
  <script type="text/javascript" src="/admin/smarteditor2/js/service/HuskyEZCreator.js"></script>  	
<script type="text/javascript">
var Editor_Obj_ID = "";

$(document).ready(function () {
	Editor_Obj_ID = Global_Create_Smart_Editor("ir1");
  	$(".nav-item").removeClass("active");
	$(".nav-item").eq(4).addClass("active");
});
function file_change(obj){
	var reader = new FileReader();
    reader.onload = function(e) {
        $('#thumbnail').attr('src', e.target.result);
    }
    reader.readAsDataURL(obj.files[0]);
    
	$(obj).next().text(obj.files[0].name);
}
function imgclick(){
	$("input[type=file]").trigger("click");
	
}
function click_category(obj){
	$("#category_btn").html($(obj).text());
	$("#category").val($(obj).data("value"));
}
function update(){
	var category = $("#category").val();
	var title = $("#title").val();
	var sub_title = $("#sub_title").val();
	var content_text = Global_Smart_Editor_Get_Content_For_Editor("ir1").replace("<p><br></p>","");
	var idx = $("#idx").val();
	
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
	}else if(!idx){
		alert('오류가 발생하였습니다. 다시 시도해 주세요');
		location.reload();
		return;
	}
	var headers = {};
	headers["X-CSRF-TOKEN"] = $("input[name=_csrf]").val();
	var formData = new FormData(); 
	formData.append("category",category);
	formData.append("sub_title",sub_title);
	formData.append("title",title);
	formData.append("content",Global_Smart_Editor_Get_Content_For_Editor("ir1"));
	formData.append("idx",idx);
	if($("#inputGroupFile01")[0].files[0]){
		formData.append("header_image_file",$("#inputGroupFile01")[0].files[0]);
	}
	$.ajax({
			url : "/admin/post/update",
			type : "post",
			data: formData,
			contentType: false,
			processData: false,
			headers : headers,
			success : function(obj) {
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
					location.reload();
				}
				
			},
			error: function(){
				alert("오류가 발생하였습니다.");
			}
	});
}
function delete_post(){
	var idx = $("#idx").val();
	if(!idx){
		alert('오류가 발생하였습니다. 다시 시도해 주세요');
		location.reload();
		return;
	}
	var headers = {};
	headers["X-CSRF-TOKEN"] = $("input[name=_csrf]").val();
	$.ajax({
			url : "/admin/post/delete",
			type : "post",
			data: {"idx":idx},
			headers : headers,
			success : function(obj) {
				if(obj==1){
					alert("삭제되었습니다.");
					location.href="/admin/postList";
				}else{
					alert("오류가 발생하였습니다.");
					location.reload();
				}
			},
			error: function(){
				alert("오류가 발생하였습니다.");
			}
	});
}
</script>
</body>

</html>
