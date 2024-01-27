package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//클라이언트는 서버에 접속하여 원하는 파일명을 전송하여 다운받는다
public class T08TcpFileClient {
	private Socket socket;
	private FileOutputStream fos;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Scanner scan;
	
	public T08TcpFileClient() {
		scan = new Scanner(System.in);
	}
	
	public void clientStart() {
		
		//안전하게 파일 주고받으려고
		BufferedInputStream bis= null;
		BufferedOutputStream bos = null;
		
		try {
			
			socket = new Socket("192.168.143.3", 7777);
			
			System.out.println("파일 서버에 연결되었습니다...");
			
			//소켓접속이 성공하면 받고싶은 파일명을 전송한다.
			System.out.print("파일명 >> ");
			String fileName = scan.next();
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(fileName);
			
			//결과 메세지 받기
			dis = new DataInputStream(socket.getInputStream());
			String resultMsg = dis.readUTF();
			
			if(resultMsg.equals("OK")) { //요청 파일이 존재하는 경우...
				
				File downDir = new File("d:/D_Other/down_files/");
				
				if(!downDir.exists()) {
					downDir.mkdir(); //폴더 생성하기
				}
				
				File file = new File("d:/D_Other/down_files/"+fileName);
				
				bos = new BufferedOutputStream(new FileOutputStream(file));
				bis = new BufferedInputStream(socket.getInputStream());
				
				int data = 0;
				while((data = bis.read())!=-1) {
					bos.write(data);
				}
				System.out.println("파일 다운로드 완료...");
			}else {
				System.out.println(resultMsg);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				bos.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new T08TcpFileClient().clientStart();
	}
}
