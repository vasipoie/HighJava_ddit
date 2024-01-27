package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class T03TcpChatServer {
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버 준비 완료...");
			socket = serverSocket.accept();
			
			System.out.println("클라이언트와 연결되었습니다!");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			//스레드
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
