package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class T01UdpServer {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	//데이터를 주고받을 때 메세지를 담기위한 버퍼
	private byte[] msg;	//패킷송수신을 위한 바이트배열
	
	public T01UdpServer(int port) {
		try {
			ds = new DatagramSocket(port); //메세지 송수신을 위한 포트번호 설정
		} catch (SocketException e) {
			e.printStackTrace();
		} 
	}
	
	public void start() throws IOException {
		
		while(true) {
			
			//데이터를 수신하기 위한 패킷을 생성한다.
			msg = new byte[1]; //1바이트를 담을 수 있게 초기화시키기
			dp = new DatagramPacket(msg, msg.length);
			
			System.out.println("패킷 수신 대기중...");
			
			//패킷을 통해 데이터를 수신(receive)한다. : ip주소, port 알아내려고
			//상대방이 send를 해야 receive가 종료. 그 전까지는 멈춰있음
			ds.receive(dp);
			
			System.out.println("패킷 수신 완료...");
			
			//수신한 패킷으로부터 클라이언트의 IP주소 및 포트번호를 알아낸다.
			InetAddress addr = dp.getAddress();
			int port = dp.getPort();
			
			//서버의 현재시간을 시분초 형태[hh:mm:ss]로 변환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			msg = time.getBytes(); //시간문자열을 바이트배열로 변환한다.
			
			//패킷을 생성해서 클라이언트에게 전송(send)한다.
			dp = new DatagramPacket(msg, msg.length, addr, port);
			ds.send(dp); //전송시작
		}
	}
	
	public static void main(String[] args) throws Exception {
		new T01UdpServer(8888).start();
	}
	
	
	
	
}
