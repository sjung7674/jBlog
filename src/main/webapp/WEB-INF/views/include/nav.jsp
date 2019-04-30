<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand" href="/">jBlog</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
          </li> 
          <li class="nav-item" style="position:relative; cursor:pointer;">
				  <a class="dropdown-toggle nav-link"  id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
				  	<c:choose>
				  		<c:when test="${not empty param.category_no }">
				  			<c:forEach items="${categoryList }"  var="list">
				  				<c:if test="${list.idx== param.category_no }">
				  					${list.category }
				  				</c:if>
				        	</c:forEach>
				  		</c:when>
				  		<c:otherwise>
				  			 category
				  		</c:otherwise>
				  	</c:choose>
				   
				  </a>
				  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
				  	<c:forEach items="${categoryList }"  var="list">
			    		<a class="dropdown-item" href="/1?category_no=${list.idx }" data-value="${list.idx }">${list.category }</a>
		        	</c:forEach>
				  </div>
          </li>
          <sec:authorize access="!hasRole('ROLE_USER')">
			<li class="nav-item">
				<a class="nav-link" href="/member/login">Login</a>
			</li>
		  </sec:authorize>
		  <sec:authorize access="hasRole('ROLE_USER')">
			<li class="nav-item">
				<a class="nav-link" href="/member/logout">Logout</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/write">Write</a>
			</li>
          </sec:authorize>
        </ul>
      </div>
    </div>
  </nav>