package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class T02UdpClient {
	private DatagramSocket ds;
	private DatagramPacket dp;
	//byte 배열 담기위한 객체변수
	private byte[] msg;
	
	public T02UdpClient() {
		try {
			msg = new byte[100];
			
			//소켓생성 시 포트번호를 명시하지 않으면 이용가능한 임의의 포트번호가 할당된다
			ds = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException {
		
		InetAddress serverAddr = InetAddress.getByName("192.168.143.3");
		
		//데이터를 send 할 때는 반드시 상대방의 addr, port가 있어야함
		//addr, port 알려주려고 보내는거
		dp = new DatagramPacket(msg, 1, serverAddr, 8888);
		ds.send(dp);
		
		//receive할 때는 addr, port는 필요없음. 받아내기만 하면 되니까
		dp = new DatagramPacket(msg, msg.length);
		ds.receive(dp);
		
		//바이트 배열 기반으로 String객체를 만듦
		System.out.println("현재 서버 시간 : "+new String(dp.getData()));
	}
	
	public static void main(String[] args) throws Exception {
		new T02UdpClient().start();
	}
}
