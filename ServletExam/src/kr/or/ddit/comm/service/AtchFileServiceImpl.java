package kr.or.ddit.comm.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

import kr.or.ddit.comm.dao.AtchFileDaoImpl;
import kr.or.ddit.comm.dao.IAtchFileDao;
import kr.or.ddit.comm.vo.AtchFileVo;

public class AtchFileServiceImpl implements IAtchFileService {
	
	private static final String UPLOAD_DIR = "upload_files";
	
	private IAtchFileDao fileDao;
	
	private static IAtchFileService fileService;
	
	public AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance(){
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		return fileService;
	}

	@Override
	public AtchFileVo saveAtchFileList(Collection<Part> parts) {
		
		// 웹애플리케이션 루트 디렉토리(WebContent)를 기준으로 업로드 경로 설정하기
		String uploadPath = "d:/D_Other/" + UPLOAD_DIR;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		boolean isFirstFile = true; //첫번째 파일 여부 체크
		
		AtchFileVo atchFileVo = null;
		
		for(Part part : parts) {
			
			//part에서 파일명 가져오는 메서드
			String fileName = part.getSubmittedFileName();
			
			if(fileName != null && !fileName.equals("")) {
				
				if(isFirstFile) {
					isFirstFile = false;
					
					//파일 기본정보 저장하기
					atchFileVo = new AtchFileVo();
					fileDao.insertAtchFile(atchFileVo);
					
				}
				
				//파일 세부정보 저장하기
				String orignFileName = fileName; //원본파일명
				long fileSize = part.getSize();	 //파일 사이즈
				String saveFileName = "";		 //저장파일명
				String saveFilePath = "";		 //저장파일경로
				
				saveFileName = UUID.randomUUID().toString().replace("-", "");
				saveFilePath = uploadPath + "/" + saveFileName;
				
				
				try {
					//폴더에 업로드 파일 저장하기
					part.write(saveFilePath);
				} catch (IOException ex) {
					//RuntimeException 따로 예외처리를 안해도 된다
					throw new RuntimeException("업로드 파일 저장 중 예외 발생!!!",ex);
				}
				
				
				//확장자 추출
				//0보다 작으면 확장자가 없는 것
				String fileExt = orignFileName.lastIndexOf(".") < 0 ? "" 
										: orignFileName.substring(orignFileName.lastIndexOf(".")+1);
				
				//파일 세부정보 준비
				atchFileVo.setStreFileNm(saveFileName);
				atchFileVo.setFileSize(fileSize);
				atchFileVo.setOrignlFileNm(orignFileName);
				atchFileVo.setFileStreCours(saveFilePath);
				atchFileVo.setFileExtsn(fileExt);
				atchFileVo.setFileCn("");
				
				//파일 세부정보 저장하기
				fileDao.insertAtchFileDetail(atchFileVo);
				
				
//				try {
//					//폴더에 업로드 파일 저장하기
//					String name = saveFilePath +"." + atchFileVo.getFileExtsn();
////					String name = atchFileVo.getOrignlFileNm(); //안됨
//					part.write(name);
//				} catch (IOException ex) {
//					//RuntimeException 따로 예외처리를 안해도 된다
//					throw new RuntimeException("업로드 파일 저장 중 예외 발생!!!",ex);
//				}
				
				try {
					part.delete(); //임시 업로드 파일 삭제하기
				} catch (IOException e) {
					throw new RuntimeException("업로드 파일 삭제중 예외 발생", e);
				} 
			}
		}
		return atchFileVo;
	}


	@Override
	public List<AtchFileVo> getAtchFileList(AtchFileVo atchFileVo) {
		List<AtchFileVo> atchFileList = fileDao.getAtchFileList(atchFileVo);
		return atchFileList;
	}

	@Override
	public AtchFileVo getAtchFileDetail(AtchFileVo atchFileVo) {
		return fileDao.getAtchFileDetail(atchFileVo);
	}
	
	
//	public static void main(String[] args) {
//		System.out.println(UUID.randomUUID().toString()); //file객체 만들어서 do while문으로 중복 체크
//	}

}
