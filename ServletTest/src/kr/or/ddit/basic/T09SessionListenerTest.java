package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T09SessionListenerTest extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//HttpSession 생성
		HttpSession session = req.getSession();
		
		session.setAttribute("ATTR1", "속성1"); //속성 생성
		session.setAttribute("ATTR1", "속성11"); //속성 변경
		session.setAttribute("ATTR2", "속성2"); //속성 생성
		session.removeAttribute("ATTR1"); //속성 삭제
		
		
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
