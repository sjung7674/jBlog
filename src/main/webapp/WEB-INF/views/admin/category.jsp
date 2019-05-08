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

  <title>jBlog Admin - Dashboard</title>

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
            <h1 class="h3 mb-0 text-gray-800">Category</h1>
          </div>
          <div class="row">
       		<div class="card" style="width: 18rem;">
			  <img src="" class="card-img-top" alt="header_image" onerror="this.src='/img/thumbnail_error.jpg'" onclick="img_click();" id="img_preview">
			  <input type="file" name="header_image" onchange="img_change();" style="display:none;"/>
			  <div class="card-body text-center">
			    <p class="card-text"><input type="text" name="category_text" id="category_text"/></p>
				 <a href="javascript:;" class="btn btn-outline-primary btn-sm" onclick="save();">저장</a>
			  </div>
			</div>
			<c:forEach items="${category_list }" var="list">
         		<div class="card" style="width: 18rem;">
				  <img src="/getImg?imgName=${list.header_image_name }&path=category" class="card-img-top" alt="header_image" onerror="this.src='/img/thumbnail_error.jpg'">
				  <div class="card-body text-center">
				    <p class="card-text"><input type="text" name="category_text" value="${list.category }"/></p>
				    <div class="btn-group" role="group">
				     	<a href="javascript:;" class="btn btn-outline-primary btn-sm" onclick="">수정</a>
				     	<a href="javascript:;" class="btn btn-outline-danger btn-sm" onclick="">삭제</a>
					</div>
				  </div>
				</div>
       		</c:forEach>
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
function img_click(){
	$("input[name=header_image]").trigger("click");
}
function img_change(){
	var reader = new FileReader();
    reader.onload = function(e) {
        $('#img_preview').prop("src", e.target.result);
    }
    reader.readAsDataURL($("input[name=header_image]")[0].files[0]);
}
function save(){
	var img =$("input[name=header_image]")[0].files[0];
	var category_text = $("input[name=category_text]").val();
	if(!img){
		alert('이미지를 선택해주세요.');
		$("input[name=header_image]").focus();
		return;
	}
	if(!category_text){
		alert('카테고리명을 입력해주세요');
		$("input[name=category_text]").focus();
		return;
	}
	var headers = {};
	headers["X-CSRF-TOKEN"] = $("input[name=_csrf]").val();
	var formData = new FormData(); 
	formData.append("header_image",img);
	formData.append("category",category_text);
	$.ajax({
		url : "/admin/category/save",
		type : "post",
		data: formData,
		contentType: false,
		processData: false,
		headers : headers,
		success : function(result) {
			if(result=='success'){
				alert("저장하였습니다.");
				
			}else if(result=='over_size'){
				alert("사진은 10MB이하로 업로드하세요.");
			}else if(result=='not_JGPBImage'){
				alert("사진은 jpg,gif,png,bmp로 업로드하세요.");
			}else if(result=='error'){
				alert("오류가 발생하였습니다.");
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
