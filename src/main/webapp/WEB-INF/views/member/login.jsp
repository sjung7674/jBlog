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
      <div class="col mx-auto mt-5 pt-5 text-center" style="max-width:500px">
      	<%-- <form class="form-signin" action="/member/login" method="post">
	  		<h2 class="form-signin-heading">Login</h2>
			<label for="inputEmail" class="sr-only">Email address</label>
			<input type="email" id="inputEmail" name = "inputEmail" class="form-control mb-1" placeholder="Email address" required="" autofocus="">
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" name="inputPassword" class="form-control mb-1" placeholder="Password" required="">
			<c:if test="${not empty error }">
				<div class='text-sm-left text-danger'>${error }</div>
			</c:if>
			<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
			<button class="btn btn-info btn-block mb-3" type="submit">로그인</button>
		</form> --%>
		<a href="${url }" onclick="window.open(this.href, 'naverloginpop', 'titlebar=1, resizable=1, scrollbars=yes, width=600, height=550'); return false" target="_blank"><img height="50" src="/img/naver_login_btn.PNG"/></a>
      </div>
  </div>

  <hr>
<jsp:include page="../include/footer.jsp"/>
  <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
<script>
$('#mainNav').addClass('is-visible is-fixed');
</script>
</body>

</html>
