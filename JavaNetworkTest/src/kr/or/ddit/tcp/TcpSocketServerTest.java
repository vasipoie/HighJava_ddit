package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketServerTest {
	public static void main(String[] args) throws IOException {
		
		//소켓이란? 두 호스트 간 통신을 하기 위한 양 끝단(Endpoint)을 말한다.
		//TCP 소켓통신을 하기 위해 ServerSocket 객체 생성하기
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 기다립니다...");
		
		//accept()는 클라이언트에서 연결요청이 올 때까지 계속 기다린다.
		//연결요청이 오면 Socket 객체를 생성해서 Client의 Socket과 연결한다.
		Socket socket = server.accept();
		
		//---------------------------------------------------------
		//이 이후는 클라이언트와 연결된 후의 작업을 진행하면 된다.
		
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소 : "+socket.getInetAddress());
		
		//클라이언트에게 메세지 보내기
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		dos.writeUTF("어서오세요..."); //메세지 보내기
		System.out.println("메세지를 보냈습니다.");
		
		dos.close();
		server.close();
	}
}
