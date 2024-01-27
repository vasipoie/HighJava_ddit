package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * commons-fileupload 모듈을 이용한 파일 업로드 예제
 * @author PC-25
 *
 */
@WebServlet("/fileUpload.do")
public class FileUploadServlet extends HttpServlet{

	private static final String UPLOAD_DIR = "upload_files";
	//메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	private static final int MEMORY_THRESHOLD = 1024*1024*3;
	//파일 1개당 최대 크기(크기는 byte. 50MB)
	private static final long MAX_FILE_SIZE = 1024*1024*50;
	//요청파일 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024*1024*50;

	//파일 업로드 관련된 서블릿에서는 get방식으로 보낼 수 없음 -> body가 없으니까
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		System.out.println("============================================================");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		
		String readLine = "";
		while((readLine = br.readLine()) != null) {
			System.out.println(readLine);
		}
		
		System.out.println("============================================================");
		*/
		
		
		//Multipart Parsing 전 파라미터 정보 조회해보기
		System.out.println("멀티파트 파싱 전 : "+req.getParameter("sender"));
		
		if(ServletFileUpload.isMultipartContent(req)) {
			
			//폼필드 데이터 저장용
			Map<String , String >formMap = new HashMap<String, String>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			
			//웹애플리케이션 루트 디렉토리(WebContent)를 기준으로 업로드 경로 설정하기
			String uploadPath = getServletContext().getRealPath("/")+File.separator+UPLOAD_DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				List<FileItem> formItems = upload.parseRequest(req);
				
				if(formItems != null && formItems.size() > 0) {
					for(FileItem item : formItems) {
						
						//멀티파트 구성은 폼데이터 or 파일데이터 2개뿐
						if(!item.isFormField()) { //파일데이터 인 경우...
							String fileName = item.getName();
							String filePath = uploadPath + File.separator+fileName;
							File storeFile = new File(filePath);
							
							System.out.println(storeFile);
							
							//FileItem객체 write
							item.write(storeFile); //업로드 파일 저장
							System.out.println("업로드 완료됨. => "+fileName);
						}else { //폼데이터 인 경우...
							formMap.put(item.getFieldName(), item.getString("UTF-8"));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//Multipart Parsing 후 파라미터 정보 조회해보기
			//req.getParameter로 가져올 수 없음
			System.out.println("멀티파트 파싱 후 : "+formMap.get("sender"));
			
		}
		
		
		
		
		
	}
	
	
}
