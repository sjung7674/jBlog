<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<link rel="canonical" href="" />
	<title>Inprogress...</title>
</head>
<%
String ReturnU = (String)request.getParameter("url");
%>
<body>
	<script type="text/javascript">
	<!--
		var ReturnU = "<%= ReturnU %>" ;
		/* 업로드 완료시 호출 */
		parent.File_Upload_Complete_Func(ReturnU);
		
		/* 업로드 실패시 호출 */
		//parent.File_Upload_Error_Func("Error_Code");
	//-->
	</script>
</body>
</html>


