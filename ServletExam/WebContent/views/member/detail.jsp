<%@page import="kr.or.ddit.comm.vo.AtchFileVo"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberVo mv = (MemberVo) request.getAttribute("mv");

	List<AtchFileVo> atchFileList = (List<AtchFileVo>) request.getAttribute("atchFileList");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 상세</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>I D</td>
			<td><%=mv.getMemId()%></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=mv.getMemName()%></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><%=mv.getMemTel()%></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><%=mv.getMemAddr()%></td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>
				<%if(atchFileList != null){
					for(AtchFileVo fileVo : atchFileList) {
				%>
				<div>
<%-- 					<a href='#'><%=fileVo.getOrignlFileNm() %></a> --%>
					<a href="/ServletExam/fileDownload.do?fileId=
											<%=fileVo.getAtchFileId()%>
									&fileSn=<%=fileVo.getFileSn()%> ">
											<%=fileVo.getOrignlFileNm() %></a>
				</div>
				
				<%						
					}
				}
				%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<a href="./list.do">[목록]</a>
			<a href="./update.do?memId=<%=mv.getMemId() %>">[회원정보 수정]</a>
			<a href="./delete.do?memId=<%=mv.getMemId() %>">[회원정보 삭제]</a>
			</td>
		</tr>
	</table>
</body>
</html>