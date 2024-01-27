package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T04ErrorHandler extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("name", "사과는빨개");
		
		///////////////////////////////////////
		
		System.out.println("name => "+req.getAttribute("name"));
		
		Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
		String message = (String) req.getAttribute("javax.servlet.error.message");
		String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
		
		if(servletName == null) {
			servletName = "알 수 없는 서블릿 이름";
		}
		
		String reqURI = (String) req.getAttribute("javax.servlet.error.request_uri");
		
		if(reqURI == null) {
			reqURI = "알 수 없는 URI";
		}
		
		////////////////////////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "에러 및 예외 정보";
		
		out.print("<!doctype html><html><head><title>" + title + "</title>");
		out.print("</head><body>");
		if(throwable == null && statusCode == null) {
			out.print("<h2>에러정보없음</h2>");
		}else {
			out.print("<h2>에러 및 예외 정보</h2>");
			out.print("<p>상태코드 : " + statusCode + "</p><br><br>");
			out.print("<p>에러(예외)메세지 : " + message + "</p><br><br>");
			out.print("<p>서블릿네임 : " + servletName + "</p><br><br>");
			out.print("<p>요청 URI : " + reqURI + "</p><br><br>");
			
			if(statusCode != null) {
				out.print("<p>예외 타입 : " + throwable.getClass().getName() + "</p><br><br>");
			}
		}
		out.print("</body></html>");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
