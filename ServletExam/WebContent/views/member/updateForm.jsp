<%@page import="kr.or.ddit.comm.vo.AtchFileVo"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberVo mv = (MemberVo)request.getAttribute("mv");


	List<AtchFileVo> atchFileList = (List<AtchFileVo>) request.getAttribute("atchFileList");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 변경</title>
</head>
<body>
	<form action="./update.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="memId" value="<%=mv.getMemId() %>">
		<input type="hidden" name="atchFileId" value="<%=mv.getAtchFileId() %>">
		<table>
			<tr>
				<td>I D:</td>
				<td><%=mv.getMemId() %></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type="text" name="memName" value="<%=mv.getMemName() %>"></td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td><input type="text" name="memTel" value="<%=mv.getMemTel() %>"></td>
			</tr>
			<tr>
				<td>주소:</td>
				<td><textarea name="memAddr" ><%=mv.getMemAddr() %></textarea></td>
			</tr>
			<tr>
				<td>기존 첨부파일</td>
				<td>
					<%if(atchFileList != null){
						for(AtchFileVo fileVo : atchFileList) {
					%>
					<div>
						<a href="<%=request.getContextPath() %>
						/fileDownload.do?fileId
						=<%=fileVo.getAtchFileId()%>
						&fileSn=<%=fileVo.getFileSn()%>">
						<%=fileVo.getOrignlFileNm()%></a>
					</div>
					
					<%						
						}
					}
					%>
				</td>
			</tr>
			<tr>
				<td>신규 첨부파일</td>
				<td>
					<input type="file" name="atchFile" multiple="multiple"/>
				</td>
			</tr>
		</table>
		<input type="submit" value="회원정보 수정">

	</form>
</body>
</html>