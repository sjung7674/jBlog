<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<jsp:include page="include/nav.jsp"/>
  <!-- Page Header -->
  <header class="masthead" style="background-image: url('/img/home-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading">
            <h1>Clean Blog</h1>
            <span class="subheading">A Blog Theme by Start Bootstrap</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
      	<c:forEach items="${post_list}" var="list">
      		<div class="post-preview">
	          <a href="/view/${list.idx }">
	            <h2 class="post-title">
	              ${list.title }
	            </h2>
	            <h3 class="post-subtitle">
	              ${list.sub_title }
	            </h3>
	          </a>
	          <p class="post-meta">Posted by
	            <a href="#">${list.userid }</a>
	            on <fmt:formatDate value="${list.reg_date}" pattern="yyyy-MM-dd"/></p>
	        </div>
	        <hr>
      	</c:forEach>
        <!-- Pager -->
        <div class="clearfix">
	        <c:if test="${page.previousPageGroup }">
	        	<c:choose>
	        		<c:when test="${not empty param.category_no }">
	        			<a class="btn btn-primary float-left" href="/${currentPageNum-1}?category_no=${param.category_no}">&larr; Newest Posts</a>
	        		</c:when>
	        		<c:otherwise>
	        			<a class="btn btn-primary float-left" href="/${currentPageNum-1}">&larr; Newest Posts</a>
	        		</c:otherwise>
	        	</c:choose>
	        </c:if>
	        <c:if test="${page.nextPageGroup }">
	        	<c:choose>
	        		<c:when test="${not empty param.category_no }">
	        			<a class="btn btn-primary float-right" href="/${currentPageNum+1}?category_no=${param.category_no}">Older Posts &rarr;</a>
	        		</c:when>
	        		<c:otherwise>
	        			<a class="btn btn-primary float-right" href="/${currentPageNum+1}">Older Posts &rarr;</a>
	        		</c:otherwise>
	        	</c:choose>
	        </c:if>
        </div>
      </div>
    </div>
  </div>

  <hr>
<jsp:include page="include/footer.jsp"/>
  <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="/js/template/clean-blog.min.js"></script>
</body>

</html>
