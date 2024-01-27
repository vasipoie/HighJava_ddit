package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/member/update.do")
public class UpdateController extends HttpServlet{
	
	//데이터를 입력받아서 얻어내려고 할 때 get 사용
	//보내는 개념은 아니다
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		MemberVo mv = memService.getMember(memId);
		
		req.setAttribute("mv", mv);
		
		if(mv.getAtchFileId()>0) { //첨부파일이 존재하는 경우
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			AtchFileVo atchFileVo = new AtchFileVo();
			atchFileVo.setAtchFileId(mv.getAtchFileId());
			
			List<AtchFileVo> atchFileList = fileService.getAtchFileList(atchFileVo);
			
			req.setAttribute("atchFileList", atchFileList);
		}
		
		req.getRequestDispatcher("/views/member/updateForm.jsp").forward(req, resp);
	}
	
	
	//입력받은 데이터가 있어서 db를 수정해야하니까 post가 필요
	//필요한 정보 던져줄때 post 사용
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//갖고온다는건 화면에서 보내준다는 것
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		String atchFileId = req.getParameter("atchFileId"); //기존 첨부파일 ID
		
		
		//첨부파일 저장하기
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		AtchFileVo atchFileVo = new AtchFileVo();
		atchFileVo = fileService.saveAtchFileList(req.getParts());
		
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		MemberVo mv = new MemberVo(memId, memName, memTel, memAddr);
		
		if(atchFileVo == null) { //신규 첨부파일이 존재하지 않는 경우
			mv.setAtchFileId(Long.parseLong(atchFileId));
		}else { //신규 첨부파일이 존재하는 경우
			mv.setAtchFileId(atchFileVo.getAtchFileId());
		}
		
		int cnt = memService.modifyMember(mv);
		
		
		String msg = "";
		
		if(cnt>0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		req.getSession().setAttribute("msg", msg);
		
		resp.sendRedirect(req.getContextPath()+"/member/list.do");
				
	}

}
