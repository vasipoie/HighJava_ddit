package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;

public class T04TcpChatClient {
	public static void main(String[] args) {
		try {
			//서버ip로 접속
			Socket socket = new Socket("192.168.143.17", 7777);
			System.out.println("서버에 연결되었습니다...");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
