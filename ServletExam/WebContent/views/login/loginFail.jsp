<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
		alert("잘못입력했습니다. 다시 로그인해주세요");
<% 
 response.sendRedirect(request.getContextPath()+"/login.do");
%>

</script>
</body>
</html>