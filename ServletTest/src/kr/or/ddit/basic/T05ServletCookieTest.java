package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T05ServletCookieTest extends HttpServlet{
/*
 	http는 비연결성, 상태가 없는 프로토콜이니까 데이터를 Set-Cookie 명령에 따라 쿠키 저장소에 저장함
 	
 	쿠키정보 다루기 - 팝업창에 쓸 수 있음
 	(웹서버와 브라우저는 애플리케이션을 사용하는 동안 필요한 값을 쿠키를 통해 공유하여 상태정보를 유지함)
 	
 	1. 구성요소
 	- 이름
 	- 값
 	- 유효시간(초)
 	- 도메인 : ex) www.somehost.com, .somehost.com 등
 	- 경로 : 쿠키를 공유할 기준경로를 지정한다.
 	
 	2. 동작 방식
 	- 쿠키생성단계 : 생성한 쿠키를 응답헤더에 저장하여 브라우저(클라이언트)에 전송한다.
 	- 쿠키저장단계 : 브라우저는 응답헤더에 포함된 쿠키정보를 쿠키저장소에 보관한다.
 	- 쿠키전송단계 : 브라우저는 저장한 쿠키를 요청이 있을 때마다 웹서버에 전송한다.(삭제되기 전까지...)
 		                 웹서버는 브라우저가 전송한 쿠키를 사용해서 필요한 작업을 수행한다.
 */
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//쿠키 생성하기
//		setCookieExam(req, resp);
		
		//쿠키 읽기 예제
//		readCookieExam(req, resp);
		
		//쿠키 삭제 예제
		deleteCookieExam(req,resp);
		
		
		
		
		
		
		
	}
	
	
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//사용중인 쿠키정보를 삭제하는 방법
		
		//쿠키 지속시간을 0으로 설정 후 응답헤더에 해당쿠키객체를 추가하여 전송한다.
		
		Cookie[] cookies = req.getCookies();
		
		///////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 삭제 예제";
		
		out.print("<!DOCTYPE html><html><head><title>"
				+ title + "</title></head><body>");
		
		if(cookies != null) {
			out.print("<h2>" +title+"</h2>");
			
			for(Cookie cookie : cookies) {
				
				if(cookie.getName().equals("userId")) {
					//쿠키 제거하기
					cookie.setMaxAge(0);
					
					//response header에 삭제하라는 메세지를 추가
					resp.addCookie(cookie);
					
					out.print("<p>삭제한 쿠키 : "+cookie.getName()+"</p><br>");
				}
				
				out.print("<p>name : "+cookie.getName()+"</p><br>");
				out.print("<p>value : "+URLDecoder.decode(cookie.getValue(),"UTF-8")+"</p><br>");
				out.print("<hr>");
			}
		}else {
			out.print("<h2>쿠키 정보가 없습니다.</h2>");
		}
		
		out.print("</body></html>");
		
		
		
	}


	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Cookie[] cookies = req.getCookies();
		
		///////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 읽기 예제";
	
		out.print("<!DOCTYPE html><html><head><title>"
				+ title + "</title></head><body>");
		
		if(cookies != null) {
			out.print("<h2>" +title+"</h2>");
			
			for(Cookie cookie : cookies) {
				out.print("<p>name : "+cookie.getName()+"</p><br>");
				out.print("<p>value : "+URLDecoder.decode(cookie.getValue(),"UTF-8")+"</p><br>");
				out.print("<hr>");
			}
		}else {
			out.print("<h2>쿠키 정보가 없습니다.</h2>");
		}
		
		out.print("</body></html>");
	}


	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		//쿠키 생성하기(사용하고싶은 이름, value값)
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		
		//쿠키값에 한글을 사용 시 인코딩 처리를 해준다.
		Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "UTF-8"));
		
		//쿠키 소멸시간 설정(초단위) => 지정하지 않으면 브라우저 종료 시 쿠키도 함께 삭제한다
		userId.setMaxAge(60*60*24); //60초=1분*60=1시간*24=1일
		userId.setHttpOnly(true); //브라우저 콘솔에서 javascript를 이용한 직접 접근 방지
		
		name.setMaxAge(60*60*48);
		
		///////////////////////////////////////////////////////////////
		
		//응답헤더에 쿠키 추가하기
		resp.addCookie(userId);
		resp.addCookie(name);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키설정 예제";
		
		out.print("<!DOCTYPE html><html><head><title>"
				+ title + "</title></head><body>"
				+ "<h1 align=\"center\">"+title+"</h1>"
				+ "<ul><li><b>userID</b> :"
				+ req.getParameter("userId")+"</li>"
				+ "<li><b>이름</b> :"
				+ req.getParameter("name")+"</li></ul>"
				+ "</body></html>"
				);
		
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	

}
