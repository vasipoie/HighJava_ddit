package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03ServletParameterTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 	요청메세지로부터 파라미터 데이터 가져오는 방법
		 	
		 	- getParameter() - 파라미터값이 한 개인 경우에 가져올 때 사용
		 	- getParameterValues() - 파라미터값이 여러 개인 경우 사용 ex)checkbox
		 	- getParameterNames() - 요청메세지에 존재하는 모든 파라미터 정보를 가져올 때 사용
		 */
		
		//POST방식으로 넘어오는 Body 데이터를 인코딩 처리
		//반드시 req객체에서 가져오기 전에 설정
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String birthDay = req.getParameter("birthDay");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");
		String rlgn = req.getParameter("rlgn");
		
		String[] foods = req.getParameterValues("food");

		///////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
//		resp.setHeader("Content-Type", "text/html;charset=UTF-8");
		
		//response객체의 getWriter를 호출해서 받은 문자기반 데이터를 
		//상대방 브라우저로 출력기능 보조스트림을 이용해서 쏨
		//문자기반이 아니었으면 resp.getOutputStream() 사용
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>username : "+username +"</p>");
		out.println("<p>password : "+password +"</p>");
		out.println("<p>birthDay : "+birthDay +"</p>");
		out.println("<p>gender : "+gender +"</p>");
		out.println("<p>hobby : "+hobby +"</p>");
		out.println("<p>rlgn : "+rlgn +"</p>");
		
		if(foods != null) {
			for(String food : foods) {
				out.println("<p>food : "+food +"</p>");
			}
		}
		
		//모든 파라미터 정보 가져오기
		Enumeration<String> paramNames = req.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			out.println("<p>파라미터 이름 : "+paramName +"</p>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
