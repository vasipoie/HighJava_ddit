package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02ServletTest extends HttpServlet{
/*
 	서블릿 동작 방식에 대하여...
 	
 	1. 사용자(클라이언트)가 URL을 클릭하면 HTTP 요청메세지를 서블릿 컨테이너로 전송한다.
 	2. 컨테이너는 web.xml에 정의된 URL패턴을 확인하여 어느 서블릿을 통해 처리할지를 검색한다.
 	(서블릿이 로딩이 안된 경우에는 로딩 후 init()메서드 호출된다.)
 	3. 서블릿 컨테이너는 요청을 처리할 개별 스레드 객체를 생성하여 해당 서블릿 객체의 service()메서드를 호출한다.
 	(HttpServletRequest 및 HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다.)
 	4. service()메서드는 메서드타입을 체크하여 적절한 메서드를 호출한다.
 	(doGet, doPost, doDelete 등)
 	5. 요청처리가 완료되면 HttpServletRequest 및 HttpServletResponse 객체는 소멸된다.
 	6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()가 호출된다.
 */
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청객체의 메서드 확인하기
		System.out.println("req.getCharacterEncoding() : " + req.getCharacterEncoding());
		System.out.println("req.getContentLength() : " + req.getContentLength());
		System.out.println("req.getQueryString() : " + req.getQueryString());
		System.out.println("req.getProtocol() : " + req.getProtocol());
		System.out.println("req.getMethod() : " + req.getMethod());
		System.out.println("req.getRequestURI() : " + req.getRequestURI());
		System.out.println("req.getHeaderNames() : " + req.getHeaderNames());
		System.out.println("req.getRemoteAddr() : " + req.getRemoteAddr());
		System.out.println("req.getRemotePort() : " + req.getRemotePort());
		
		///////////////////////////////////////////////////////////////////
		
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		System.out.println("name : "+name);
		System.out.println("age : "+age);
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		System.out.println("name : "+username);
		System.out.println("age : "+password);
		
		
		
	}
}
