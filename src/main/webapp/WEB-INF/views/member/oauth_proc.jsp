<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
<c:choose>
	<c:when test="${not empty error }">
		alert("에러가 발생하였습니다. 다시 시도해 주세요.");
		opener.parent.location.reload();
		self.close();
	</c:when>
	<c:otherwise>
		opener.parent.location.href="/member/login_proc?state=${state}";
		self.close();
	</c:otherwise>
</c:choose>
</script>
</body>
</html>