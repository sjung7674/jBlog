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
    <link href="/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/css/member/member.css" rel="stylesheet">
  </head>
  <body>
  	<jsp:include page="../include/nav.jsp"/>
  	<div class="container-fluid" id="container">
		<div style="margin-bottom:5px;">
		    <div class="input-group">
		      <div class="input-group-btn">
		        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="category_btn" aria-expanded="false">선택하세요. <span class="caret"></span></button>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="javascript:;" data-value="1" onclick="click_category(this)">맛딥</a></li>
		        </ul>
		      </div><!-- /btn-group -->
		      <input type="hidden" id="category" value="">
		      <input type="text" class="form-control" id="title" aria-label="제목">
		    </div><!-- /input-group -->
	    </div><!-- /.col-lg-6 -->
  		<textarea name="ir1" id="ir1" rows="10" cols="100"></textarea>
  		<div class="text-center">
  			<button class="btn btn-default" onclick="jBlog.save();">저장</button>
  			<button class="btn btn-default" onclick="jBlog.cancel();">취소</button>
  		</div>
  	</div>
  	<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jBlog.js"></script>
    <script type="text/javascript" src="/js/smart_editor.js"></script>
	<script type="text/javascript" src="/smarteditor2/js/service/HuskyEZCreator.js"></script>  

	<script type="text/javascript">
		var Editor_Obj_ID = "";
		
		$(document).ready(function () {
			Editor_Obj_ID = Global_Create_Smart_Editor("ir1");
		});

	</script>
  </body>
</html>
