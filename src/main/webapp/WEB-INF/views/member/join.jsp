<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
      <div class="col mx-auto" style="max-width:500px">
      	<form:form commandName="memberDTO" action="/member/join_proc" method="post">
      		<input type="hidden" name="userid" value="${memberDTO.userid }">
      		<input type="hidden" name="state" value="${param.state }">
      		<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
	      	<div class="form-group input-group">
				<div class="input-group-prepend">
				    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
				 </div>
		        <input name="nick_name" class="form-control" placeholder="nick name" type="text" value="${memberDTO.nick_name }"> 
		    </div> <!-- form-group// -->
		    <form:errors path="*" cssStyle="font-color:red;" cssClass="text-danger"/>
		    <div class="form-group">
		        <button type="submit" class="btn btn-primary btn-block"> Create Account  </button>
		    </div> <!-- form-group// --> 
	    </form:form>
      </div>
    </div>
  </div>

  <hr>
<jsp:include page="../include/footer.jsp"/>
  <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
<script>
<c:if test="${empty memberDTO.userid}">
	location.href="/error";
</c:if>
$('#mainNav').addClass('is-visible is-fixed');
function file_change(obj){
	$(obj).next().text(obj.files[0].name);
}
</script>
</body>

</html>
