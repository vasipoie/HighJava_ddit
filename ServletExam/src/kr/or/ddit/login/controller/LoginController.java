package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVo;

@WebServlet("/login.do")
public class LoginController extends HttpServlet{
	
	//get방식으로 오면 로그인 폼 열어주기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/views/login/loginForm.jsp").forward(req, resp);
		
	}
	
	
	
	//post방식으로 오면 로그인처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
		//서비스 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//DB로부터 해당 사용자 정보를 가져온 후 사용자 ID 및 패스워드 일치여부 확인한다.
		//사용자 ID 및 패스워드를 사용하여 DB로부터 해당 사용자 정보를 가져온다.
		MemberVo loginInfo = new MemberVo();
		loginInfo.setMemId(memId);
		loginInfo.setMemPass(memPass);
		
		MemberVo mv = memService.getLoginInfo(loginInfo);
		
		if(mv!=null) { //인증된 사용자인 경우
			
			//세션에 로그인 정보 설정하기
			req.getSession().setAttribute("LOGIN_USER", mv);
			
			String redirectURL = (String) req.getSession().getAttribute("redirectURL");
			
			if(redirectURL != null) {
				
				//세션에서 요청했던 URL 삭제처리
				req.getSession().removeAttribute("redirectURL");
				
				//요청했던 URL로 이동시키기
				resp.sendRedirect(redirectURL);
			}else {
				
			//메인 페이지로 이동하기
			resp.sendRedirect(req.getContextPath() + "/main.do");
			
			}
		}else { //사용자 인증실패
			//로그인 페이지로 이동하기
			
			req.getRequestDispatcher("/views/login/loginFail.jsp").forward(req, resp);
			
			
			
		}
	}
}


