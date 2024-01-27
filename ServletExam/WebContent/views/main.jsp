<%@page import="kr.or.ddit.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그인 하는 시점에 LOGIN_USER로 저장해놨으니까 꺼내오기
	MemberVo mv = (MemberVo) session.getAttribute("LOGIN_USER");
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<%
	if(mv != null){
		
%>

	<p>반값습니다. <%=mv.getMemName() %> 님! &nbsp; <a href="./logout.do">로그아웃</a>
		
<%
	}else {
		
%>
	<p>아직 로그인을 하지 않으셨군요. 로그인 먼저 해주세요... &nbsp; <a href="./login.do">로그인</a>
<%		
	}
%>
</body>
</html>