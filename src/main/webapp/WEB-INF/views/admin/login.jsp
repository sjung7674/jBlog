<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Login</title>
<!-- Bootstrap core CSS -->
<link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5 pt-5">
    <div class="row">
      <div class="col mx-auto" style="max-width:500px">
      	<form class="form-signin" action="/admin/login" method="post">
	  		<h2 class="form-signin-heading">Login</h2>
			<label for="inputEmail" class="sr-only">ID</label>
			<input type="text" id="admin_id" name = "admin_id" class="form-control mb-1" placeholder="ID" required="" autofocus="">
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="admin_pw" name="admin_pw" class="form-control mb-1" placeholder="Password" required="">
			<c:if test="${not empty error }">
				<div class='text-sm-left text-danger'>${error }</div>
			</c:if>
			<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
			<button class="btn btn-info btn-block mb-3" type="submit">로그인</button>
		</form>
      </div>
    </div>
  </div>
   <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>