<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jBlog</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/member/member.css" rel="stylesheet">
  </head>
  <body>
  	<jsp:include page="../include/nav.jsp"/>
  	<div class="container-fluid" id="container">
  	<form class="form-signin" action="/member/login" method="post">
  		<h2 class="form-signin-heading">Login</h2>
		<label for="inputEmail" class="sr-only">Email address</label>
		<input type="email" id="inputEmail" name = "inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
		<label for="inputPassword" class="sr-only">Password</label>
		<input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required="">
		<c:if test="${not empty error }">
			<div class='error'>${error }</div>
		</c:if>
		
		<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
		<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
	</form>
  	</div>
  	<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jBlog.js"></script>
  </body>
</html>
