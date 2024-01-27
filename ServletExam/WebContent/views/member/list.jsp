<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<MemberVo> memList = (List<MemberVo>)request.getAttribute("memList");

	String msg = session.getAttribute("msg")==null ? "" : (String)session.getAttribute("msg");
	
	//"성공"이 계속 남아있어서 alert가 계속 뜨니까 지워준다
	session.removeAttribute("msg");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>첨부파일</th>
		</tr>
<%
	if(memList.size() == 0){
%>		
		<tr>
			<td colspan="5">회원 정보가 존재하지 않습니다.</td>
		</tr>
<%
	}else{
		for(MemberVo mv: memList){
%>
		<tr>
			<td><%out.print(mv.getMemId()); %></td>
			<td><a href="./detail.do?memId=<%=mv.getMemId() %>"><%out.print(mv.getMemName()); %></a></td>
			<td><%=mv.getMemAddr() %></td>
			<td><%=mv.getMemTel() %></td>
			<td><%=mv.getAtchFileId() %></td>
		</tr>
<%
		}
	}
%>		
		
	<tr align="center">
		<td colspan="5"><a href="./insert.do">[회원 등록]</a></td>
	</tr>

	</table>
<%
	if(msg.equals("성공")){

%>
<script>
		alert('정상적으로 처리되었습니다.');
</script>
<%
	}
%>
</body>
</html>