package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class T04UdpFileReceiver {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] buffer;
	
	public T04UdpFileReceiver(int port) {
		try {
			ds = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}	
	}
	
	public void start() throws IOException {
		
		long fileSize = 0;
		long totalReadBytes = 0;
		
		int readBytes = 0;
		
		System.out.println("파일 수신 대기 중...");
		
		//강제로 String으로 변환하는 이유 : 제일 처음에는 start가 오니까
		//start 문자열 받기
		String str = new String(receiveData()).trim();
		
		if(str.equals("start")) {
			//파일명 받기
			str = new String(receiveData()).trim();
			
			FileOutputStream fos = new FileOutputStream("d:/D_Other/"+str);
			
			
			//파일 크기 받기
			str = new String(receiveData()).trim();
			
			fileSize = Long.parseLong(str);
			
			long startTime = System.currentTimeMillis();
			
			while(true) {
				byte[] data = receiveData();
				
				//몇 바이트 읽었는지 알 수 있음
				readBytes = dp.getLength();
				
				fos.write(data, 0, readBytes);
				
				totalReadBytes += readBytes;
				
				System.out.println("진행 상태 : "+totalReadBytes + " / "
						+ fileSize + " Byte(s) ("
						+ (totalReadBytes * 100 / fileSize) + " %)");
				
				if(totalReadBytes >= fileSize) {
					break;
				}
			}
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("수신 시간 : "+diffTime+" (ms)");
			System.out.println("평균 수신 속도 : "+transferSpeed+" (Bytes/ms)");
			System.out.println("수신 완료...");
			
			fos.close();
			ds.close();
			
		}else {
			System.out.println("비정상 데이터 발견!!!");
			ds.close();
		}
	}
	
	
	/**
	 * 데이터 수신하기
	 * @return 수신된 바이트 배열 데이터
	 * @throws IOException 
	 */
	private byte[] receiveData() throws IOException {
		buffer = new byte[1000]; //버퍼 초기화
		
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		
		return dp.getData();
	}
	
	
	public static void main(String[] args) throws IOException {
		new T04UdpFileReceiver(8888).start();
	}
}
