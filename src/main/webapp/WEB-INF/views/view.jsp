<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">
<head>
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
<jsp:include page="include/nav.jsp"/>
<sec:authentication property="principal" var="loginID" />
  <!-- Page Header -->
  <header class="masthead" style="background-image: url('/getImg?imgName=${post_dto.header_image }')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="post-heading">
            <h1>${post_dto.title }</h1>
            <h2 class="subheading">${post_dto.sub_title }</h2>
            <span class="meta">Posted by
              <a href="#">${post_dto.nick_name }</a>
               on <fmt:formatDate value="${post_dto.reg_date}" pattern="yyyy-MM-dd"/>
             </span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Post Content -->
  <article>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
        	${post_dto.content }
        	<c:if test="${loginID != 'anonymousUser' }">
	        	<c:if test="${loginID.username == post_dto.userid}">
	        		<form name="view_form" method="post">
	        			<input type="hidden" name="idx" value="${post_dto.idx }"/>
	        			<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
	        		</form>
					<div class="btn-group btn-group-sm" role="group" aria-label="submit_or_cancel">
					  <button type="button" class="btn btn-outline-primary" onclick="modify();">수정</button>
					  <button type="button" class="btn btn-outline-danger" onclick="remove();">삭제</button>
					</div>
				</c:if>
			</c:if>
        </div>
      </div>
    </div>
  </article>
  <hr>

 <jsp:include page="include/footer.jsp"/>
  <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="/js/template/clean-blog.min.js"></script>
	<script type="text/javascript">
		function modify(){ 
			document.view_form.action="/modify";
			document.view_form.submit();
		}
		function remove(){ 
			document.view_form.action="/remove";
			document.view_form.submit();
		}
	</script>
</body>

</html>
