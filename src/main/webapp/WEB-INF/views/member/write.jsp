<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>jBlog</title>

  <!-- Bootstrap core CSS -->
  <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="/css/template/clean-blog.min.css" rel="stylesheet">

</head>

<body>
<jsp:include page="../include/nav.jsp"/>
  <!-- Main Content -->
  <div class="container mt-5 pt-5">
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
  		<textarea name="ir1" id="ir1" rows="10" cols="100">${post_dto.content }</textarea>
  		<div class="btn-group btn-group-sm float-right" role="group" aria-label="submit_or_cancel">
  			<c:choose>
  				<c:when test="${not empty post_dto }">
  					<button type="button" class="btn btn-outline-primary" onclick="jBlog.update();">수정</button>
  				</c:when>
  				<c:otherwise>
  					<button type="button" class="btn btn-outline-primary" onclick="jBlog.save();">저장</button>
  				</c:otherwise>
  			</c:choose>
		  <button type="button" class="btn btn-outline-danger" onclick="jBlog.cancel();">취소</button>
		</div>
  		<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
  		<input type="hidden"	id="idx" name="idx"	value="${post_dto.idx}"/>
      </div>
    </div>
  </div>

  <hr>
<jsp:include page="../include/footer.jsp"/>
  <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
	<script type="text/javascript" src="/js/smart_editor.js"></script>
	<script type="text/javascript" src="/smarteditor2/js/service/HuskyEZCreator.js"></script>  
	<script type="text/javascript" src="/js/jBlog.js"></script>
<script>
$('#mainNav').addClass('is-visible is-fixed');
var Editor_Obj_ID = "";

$(document).ready(function () {
	Editor_Obj_ID = Global_Create_Smart_Editor("ir1");
});
function imgclick(){
	$("input[type=file]").trigger("click");
	
}
</script>
</body>

</html>
