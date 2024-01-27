<%@page import="vo.MyMemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MyMemberVO> memList = (List<MyMemberVO>)request.getAttribute("memList");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
<h3>회원 목록 보기</h3>
	<table border="1">
		<tr align="right">
			<td colspan="5">
				<button onclick="location.href='<%=request.getContextPath()%>/insert.do'">
				회원추가
				</button>
			</td>
		</tr>
        <tr>
			<th>ID</th>
			<th>비밀번호</th>
			<th>이 름</th>
			<th>전 화</th>
			<th>주 소</th>
		</tr>
<%
	if(memList.size() == 0){
%>
		<tr>
			<td colspan="5">회원 목록이 존재하지 않습니다.</td>
		</tr>
<%
	}else {
		for(MyMemberVO mmv : memList){
%>
        <tr>
            <td><%=mmv.getMemId() %></td>
            <td><%=mmv.getMemPass() %></td>
            <td><%=mmv.getMemName() %></td>
            <td><%=mmv.getMemTel() %></td>
            <td><%=mmv.getMemAddr() %></td>
        </tr>
<%
		}
	}
%>
    </table>
</body>
</html>