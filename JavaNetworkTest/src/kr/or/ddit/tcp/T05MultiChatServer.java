package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class T05MultiChatServer {
	//대화명, 클라이언트의 소켓을 저장하기 위한 Map 변수 선언
	private Map<String, Socket> clients;
	
	public T05MultiChatServer() {
		//동기화 처리 가능하도록 처리
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	//시작 메서드
	public void serverStart() {
		
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			
			System.out.println("서버가 시작되었습니다...");
			
			while(true) {
				//클라이언트의 접속을 대기한다.
				socket = server.accept();
				System.out.println("[" + socket.getInetAddress()
						+" : "+socket.getPort()
						+"] 에서 접속하였습니다.");
				
				//메세지 수신 및 전송처리를 위한 스레드 생성 및 실행
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 안내메세지를 전송하기 위한 메서드
	 * @param msg 안내메서지
	 */
	public void sendMessage(String msg) {
		//Map에 저장된 모든 소켓을 꺼내 메세지 보내기
		Iterator<String> it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			String name = it.next(); //대화명 꺼내기
			Socket socket = clients.get(name); //소켓 꺼내기
			try {
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				
				dos.writeUTF(msg); //메세지 전송하기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 채팅메세지를 전송하기 위한 메서드
	 * @param msg 채팅메세지
	 * @param from 보내는 사람 대화명
	 */
	public void sendMessage(String msg, String from) {
		sendMessage("["+from+"]"+msg);
	}
	
	//Inner클래스로 정의(Inner 클래스는 Outer 클래스의 멤버들을 직접 접근할 수 있음)
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private String name; //대화명
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				//서버에서는 클라이언트가 보내는 최초의 메세지 즉, 대화명을 수신해야 한다.
				name = dis.readUTF();
				
				//대화명을 받아서 다른 모든 클라이언트에게 대화방 참여 메세지를 전송한다.
				sendMessage("#" + name + "님이 입장했습니다.");
				
				//대화명과 소켓정보를 Map에 저장한다.
				clients.put(name, socket);
				System.out.println("현재 접속자 수는 "+clients.size()+"명 입니다.");
				
				//이 후의 메세지(채팅내용) 처리는 반복문으로 처리한다.
				//클라이언트가 보낸 메시지를 다른 모든 클라이언트에게 전송해준다.
				while(dis != null) {
					sendMessage(dis.readUTF(), name);
				}
			} catch (IOException e) {
				
			}finally {
				//이 finally영역이 실행된다는 것은 클라이언트의 접속이 종료되었다는 것을 의미
				sendMessage(name+"님이 나가셨습니다.");
				
				//Map에서 해당 사용자를 삭제한다.
				clients.remove(name);
				
				System.out.println("[" + socket.getInetAddress()
				+" : "+socket.getPort()
				+"] 에서 접속을 종료하였습니다.");
				
				System.out.println("현재 접속자 수는 "+clients.size()+"명 입니다.");
			}
		}
	}
	
	public static void main(String[] args) {
		new T05MultiChatServer().serverStart();
	}
}