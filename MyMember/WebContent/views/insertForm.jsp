<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 입력</title>
</head>
<body>
	<form action="./insert.do" method="post" enctype="multipart/form-data">
		<h3>회원 정보 입력 폼</h3>
        <table border="1">
            <tr>
                <td>회원ID</td>
                <td><input type="text" name="memId"/> <button>중복확인</button></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="text" name="memPass"/></td>
            </tr>
            <tr>
                <td>비밀번호 확인</td>
                <td><input type="text"/></td>
            </tr>
            <tr>
                <td>회원이름</td>
                <td><input type="text" name="memName"/></td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td><input type="text" name="memTel"/></td>
            </tr>
            <tr>
                <td>회원주소</td>
                <td><input type="text" name="memAddr"/></td>
            </tr>
            <tr>
                <td>프로필 사진</td>
                <td><input type="file" name="memPhoto"/></td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <button type="submit">저장</button>
                    <button type="submit">취소</button>
                    <button type="submit">회원목록</button>
                </td>
            </tr>
        </table>
	</form>
</body>
</html>