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
    <style type="text/css">
    	.jumbotron{
    	max-width: 1200px;
    	}
    </style>
  </head>
  <body>
  	<jsp:include page="include/nav.jsp"/>
  	<div class="container-fluid" id="container">
		<div class="jumbotron center-block">
			
		
		
		</div>  
  	</div>
  	<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jBlog.js"></script>
  </body>
</html>