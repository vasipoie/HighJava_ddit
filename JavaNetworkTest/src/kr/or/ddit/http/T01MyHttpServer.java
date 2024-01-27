package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;

/**
 * 간단한 웹서버 예제
 * @author PC-25
 *
 */
public class T01MyHttpServer {
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	public void start() {
		System.out.println("HTTP 서버가 시작되었습니다...");
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(this.port);
			
			while(true) {
				Socket socket = server.accept();
				
				HttpHandler handler = new HttpHandler(socket);
				handler.start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * HTTP 요청 처리를 위한 스레드 클래스
	 * @author PC-25
	 *
	 */
	class HttpHandler extends Thread {
		
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			
			BufferedOutputStream bos = null;
			BufferedReader br = null;
			
			try {
				bos = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				//요청 헤더 정보 파싱하기
				String reqLine = br.readLine();
				
				System.out.println("request Line : "+reqLine);
				
				//헤더정보 가져오기
				
				StringBuilder sb = new StringBuilder();
				while(true) {
					String str = br.readLine();
					
					//빈 문자열이 나오면 (=empty line이 나오면)
					if(str.equals("")) {
						break;
					}
					
					//복합대입연산자는 퍼포먼스가 떨어지므로 StringBuilder 쓰기
//					str += str + "\n"; 
					sb.append(str + "\n");
				}
				
//				System.out.println("요청 헤더 : ");
//				System.out.println(sb.toString());
				
				//요청 경로 가져오기
				String reqPath = "";
				StringTokenizer st = new StringTokenizer(reqLine);
				
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					
					if(token.startsWith("/")) {
						reqPath = token;
						break;
					}
				}
				
				System.out.println("요청 경로 => "+ reqPath);
				
				//URL 디코딩 처리하기(한글깨짐 문제...)
				reqPath = URLDecoder.decode(reqPath, "UTF-8");
				
				//파일경로 세팅
				String filePath = "./WebContent"+reqPath;
				
				//해당 파일 이름을 이용하여 Content-type 정보 추출하기
				String contentType = URLConnection.getFileNameMap()
						.getContentTypeFor(filePath);
				
				//CSS파일인 경우 인식이 안돼서 추가함.
				if(contentType == null && filePath.endsWith(".css")) {
					contentType = "text/css";
				}
				
				System.out.println("contentType => "+contentType);
				
				File file = new File(filePath);
				if(!file.exists()) {
					makeErrorPage(bos, 404, "Not Found");
					return;
				}
				
				byte[] body = makeResponseBody(filePath);
				
				byte[] header = makeResponseHeader(body.length, contentType);
						
				//////////////////////////////////////////////////////////////
				
				bos.write(header); //헤더정보 전송하기
				
				//응답내용 보내기 전에 반드시 Empty Line 보내준다...
				bos.write("\r\n\r\n".getBytes());
				
				bos.write(body); //응답내용(Body) 전송하기
				
				bos.flush(); //강제 버퍼 비우기...
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close(); //소켓 닫기(연결끊기)
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}

		/**
		 * 에러 페이지 생성
		 * @param bos 
		 * @param statusCode 
		 * @param errMsg 
		 */
		private void makeErrorPage(BufferedOutputStream bos, int statusCode, String errMsg) {
			
			String statusLine = "HTTP/1.1"+" "+statusCode+" "+errMsg;
			
			try {
				
				bos.write(statusLine.getBytes());
				bos.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 응답 헤더 생성하기
		 * @param length 응답내용 크기
		 * @param contentType 응답내용 마임타입
		 * @return
		 */
		private byte[] makeResponseHeader(int length, String contentType) {
			
			String headerStr = "HTTP/1.1 200 OK\r\n"
					+ "Server: MyHttpServer 1.0\r\n"
					+ "Content-length : "+length + "\r\n"
					+ "Content-type : "+contentType +"; charset = UTF-8";
			
			//String->byte 계열로
			return headerStr.getBytes();
		}

		/**
		 * 응답 내용 생성하기
		 * @param filePath 응답으로 사용할 파일경로 
		 * @return 파일내용을 담은 바이트 배열 데이터 
		 */
		private byte[] makeResponseBody(String filePath) {
			
			FileInputStream fis = null;			
			byte[] data = null;
			
			try {
				File file = new File(filePath);
				data = new byte[(int)file.length()];
				
				fis = new FileInputStream(file);
				fis.read(data);
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return data;
		}
	}
	
	public static void main(String[] args) {
		new T01MyHttpServer().start();
	}
	
}
