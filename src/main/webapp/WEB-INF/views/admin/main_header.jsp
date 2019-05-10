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

  <title>jBlog Admin - MainHeader</title>

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
            <h1 class="h3 mb-0 text-gray-800">Main Header</h1>
          </div>
          <div class="row">
          		<div class="input-group input-group-sm mb-1">
				  	<div class="input-group-prepend">
				    	<span class="input-group-text text-center" id="inputGroup-sizing-sm" style="min-width:100px">제목</span>
				  	</div>
				  		<input type="text" id="title" class="form-control" aria-label="제목" aria-describedby="inputGroup-sizing-sm" value="${main_header.title }" onblur="binding_text(this);">
				</div>
				<div class="input-group input-group-sm mb-1">
				  	<div class="input-group-prepend">
				    	<span class="input-group-text text-center" id="inputGroup-sizing-sm" style="min-width:100px">부제목</span>
				  	</div>
				  		<input type="text" id="sub_title" class="form-control" aria-label="부제" aria-describedby="inputGroup-sizing-sm" value="${main_header.sub_title }" onblur="binding_text(this);">
				</div>
				<div class="input-group input-group-sm mb-1">
				  <div class="input-group-prepend">
				    <span class="input-group-text text-center" style="min-width:100px">헤더 이미지</span>
				  </div>
				  <div class="custom-file" style="height:37.99px">
				    <input type="file" class="custom-file-input" id="inputGroupFile01" style="height:unset;" onchange="file_change(this);">
				    <label class="custom-file-label" for="inputGroupFile01" style="font-size:medium;">
				   		  파일을 선택하세요
				    </label>
				  </div>
				</div>
			 <!-- Page Header -->
			  <header class="masthead" id="header_image" style="background-image: url('/getImg?imgName=${main_header.header_image }');width:100%">
			    <div class="overlay"></div>
			    <div class="container">
			      <div class="row">
			        <div class="col mx-auto">
			          <div class="site-heading">
			          	<h1 id="title_main">${main_header.title }</h1>
	        			 <span class="subheading" id="subtitle_main">${main_header.sub_title }</span>
			          </div>
			        </div>
			      </div>
			    </div>
			  </header>
			  <div class="mx-auto mb-1">
				<button type="button" class="btn btn-outline-primary" onclick="save();">저장</button>
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
<script type="text/javascript">
$(document).ready(function(){
	$(".nav-item").removeClass("active");
	$(".nav-item").eq(1).addClass("active");
})
function binding_text(obj){
	if(obj.id=='title'){
		$("#title_main").text(obj.value);
	}else if(obj.id=='sub_title'){
		$("#subtitle_main").text(obj.value);
	}
}
function file_change(obj){
	var reader = new FileReader();
    reader.onload = function(e) {
        $('#header_image').css('background-image', "url('"+e.target.result+"')");
    }
    reader.readAsDataURL(obj.files[0]);
    
	$(obj).next().text(obj.files[0].name);
}
function save(){
	var title = $("#title").val();
	var sub_title = $("#sub_title").val();
	var file =$("#inputGroupFile01")[0].files[0];
	if(!title){
		alert('제목을 입력해주세요');
		$("#title").focus();
		return;
	}else if(!file){
		alert('이미지를 추가해 주세요.');
		$("#inputGroupFile01").focus();
		return;
	}
	
	
	var headers = {};
	headers["X-CSRF-TOKEN"] = $("input[name=_csrf]").val();
	var formData = new FormData(); 
	formData.append("sub_title",sub_title);
	formData.append("title",title);
	formData.append("header_image_file",$("#inputGroupFile01")[0].files[0]);
	
	$.ajax({
		url : "/admin/saveMainHeader",
		type : "post",
		data: formData,
		contentType: false,
		processData: false,
		headers : headers,
		success : function(result) {
			if(result=='success'){
				alert("저장하였습니다.");
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
