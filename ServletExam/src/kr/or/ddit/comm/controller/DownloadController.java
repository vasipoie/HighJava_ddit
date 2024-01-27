package kr.or.ddit.comm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVo;

@WebServlet("/fileDownload.do")
public class DownloadController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long atchFileId = req.getParameter("fileId") == null ? 
				0 : Long.parseLong(req.getParameter("fileId"));
		int fileSn = req.getParameter("fileSn") == null ?
				0 : Integer.parseInt(req.getParameter("fileSn"));
		
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVo atchFileVo = new AtchFileVo();
		atchFileVo.setAtchFileId(atchFileId);
		atchFileVo.setFileSn(fileSn);
		
		atchFileVo = fileService.getAtchFileDetail(atchFileVo);
		
	/*
	 	Content-Disposition 헤더에 대하여...
	 	첨부파일을 떨굴때 사용
	 	
	 	Response 헤더에서 사용되는 경우 ex) 파일 다운로드
	 	
	 	Content-Disposition: inline (default)
	 	Content-Disposition: attachment
	 	Content-Disposition: attachment; filename="filename.jpg"
	 */
		
		//contentType은 인라인으로 동작. 파일을 누르면 확장자에 맞춰서 알아서 열림
		//지금은 다운로드 하고싶으니까 바이너리데이터 라는 의미의 application/octet-stream 
		//Header정보 set해주기
		resp.setContentType("application/octet-stream");

		//URL에는 공백문자를 포함할 수 없다.
		//URLEncoding을 이용하여 인코딩 작업을 하면 공백은 +로 표시되기 때문에 
		//+문자를 공백문자인 %20으로 일괄변경한다. 
		resp.setHeader("Content-Disposition"
				, "attachment;"
				+ " filename=\""
				+ URLEncoder.encode(atchFileVo.getOrignlFileNm(), "utf-8")
					.replaceAll("\\+", "%20") +"\"");
		
		
		
		//FileInputStream은 바이트기반이니까 buffer이용한 스트림으로 감싸기
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(atchFileVo.getFileStreCours()));
		
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		//IO작업
		int data = 0;
		while((data=bis.read())!= -1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
