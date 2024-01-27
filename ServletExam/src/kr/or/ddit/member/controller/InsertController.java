package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVo;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVo;

@MultipartConfig
@WebServlet("/member/insert.do")
public class InsertController extends HttpServlet{
	
	//get방식으로 들어오면 form 보여주기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/views/member/insertForm.jsp").forward(req, resp);
	}
	
	
	
	//post방식으로 들어오면 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		
		//서비스 객체 생성
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVo atchFileVo = new AtchFileVo();
		
		//첨부파일 목록 저장하기(공통기능 사용)
		atchFileVo = fileService.saveAtchFileList(req.getParts());
		
		//회원정보 저장
		IMemberService memService = MemberServiceImpl.getInstance();
		
		MemberVo mv = new MemberVo(memId, memName, memTel, memAddr);
		if(atchFileVo != null) {
			mv.setAtchFileId(atchFileVo.getAtchFileId());
		}
		
		int cnt = memService.registMember(mv);
		
		
		String msg = "";
		
		if(cnt>0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		req.getSession().setAttribute("msg", msg);
		
		
		/*
		 	/views/member/list.jsp 와 member/list.do가 다른 점
		 	/member/insert.do 에서 요청을 날림
		 	insertController에서 응답을 바로 하지않고 list.jsp로 forward 하면 list가 없어서 오류
		 	forwarding은 똑같이 보냈으나 이번엔 ListController로 보낸 것. 다시 service이용해서 list조회하고 응답보냄
		 	forward는 백엔드(서버)에서 계속 전달할때 쓰는 기술 -> 화면그릴때 많이 쓰고 있음
		 */
		//목록 화면으로 포워딩처리 (url이 insert.do)
//		req.getRequestDispatcher("/member/list.do").forward(req, resp);
				
		//목록 화면으로 리다이렉팅 처리(string값으로 어디로 리다이렉팅할건지) (url이 list.do)
		//req.getContextPath()를 하면, 애플리케이션 이름을 호출할 수 있음
		resp.sendRedirect(req.getContextPath()+"/member/list.do");
				
	}
	
}
