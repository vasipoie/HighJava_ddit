package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//서버는 접속한 클라이언트가 요청하는 파일을 전송해주는 예제
public class T07TcpFileServer {
	
	public void serverStart() {
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("파일 서버 준비 완료...");
			
			while(true) {
				Socket socket = server.accept();
				
				//파일전송 스레드 생성 및 구동하기
				FileSender sender = new FileSender(socket);
				sender.start();
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	class FileSender extends Thread {
		private Socket socket;
		private FileInputStream fis;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		
		public FileSender(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			
			try {
				String downDir = "D:\\D_setting\\A_TeachingMaterial\\05_JQuery\\vscode\\image/";
				
				System.out.println("요청파일 존재여부 체크 중...");
				
				File file = new File(downDir + dis.readUTF());
				
				if(!file.exists()) {
					System.out.println("요청파일(" + file.getName()
							+ ")이 존재하지 않습니다.");
					dos.writeUTF("요청파일(" + file.getName()
							+ ")이 존재하지 않습니다.");
				}else {
					dos.writeUTF("OK"); //요청파일 확인완료
					System.out.println("요청파일("+file.getName()
							+") 전송 시작...");
					fis = new FileInputStream(file);
					
					bis = new BufferedInputStream(fis);
					bos = new BufferedOutputStream(socket.getOutputStream());
					
					int data = 0;
					while((data = bis.read())!=-1) {
						bos.write(data);
					}
					
					System.out.println("요청파일("+file.getName()
							+ ") 전송완료...");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bis.close();
					bos.close();
					dis.close();
					dos.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new T07TcpFileServer().serverStart();
	}
}
