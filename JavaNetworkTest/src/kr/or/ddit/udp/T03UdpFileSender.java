package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class T03UdpFileSender {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private InetAddress receiverAddr;
	private int receiverPort;
	
	private File file; //전송할 파일
	
	public T03UdpFileSender(String receiverIp, int port) throws IOException {
		ds = new DatagramSocket();
		
		this.receiverPort = port;
		receiverAddr = InetAddress.getByName(receiverIp);
		
		file = new File("d:/D_Other/싱긋.jpg");
		
		if(!file.exists()) {
			System.out.println("전송할 파일이 존재하지 않습니다.");
			System.exit(0);
		}
	}
	
	public void start() throws Exception{
		
		long fileSize = file.length();
		long totalReadBytes = 0;
		
		long startTime = System.currentTimeMillis();
		
		//전송시작을 알려주기 위한 문자열 전송
		sendData("start".getBytes()); 
		
		//전송할 파일명 전송
		sendData(file.getName().getBytes()); 
		
		//총 파일 크기 정보를 전송
		sendData(String.valueOf(fileSize).getBytes());
		
		///////////////////////////////////////////////////////
		
		FileInputStream fis = new FileInputStream(file);
		
		byte[] buffer = new byte[1000];
		while(true) {
			Thread.sleep(10); //패킷전송간의 간격을 주기 위해서...
			int readBytes = fis.read(buffer,0,buffer.length);
			
			if(readBytes == -1) { //더 이상 읽을 데이터가 없는 경우...
				break;
			}
			
			sendData(buffer, readBytes); //읽은 바이트 배열 전송하기
			
			totalReadBytes += readBytes;
			
			System.out.println("진행 상태 : "+totalReadBytes + " / "
					+ fileSize + " Byte(s) ("
					+ (totalReadBytes * 100 / fileSize) + " %)");
		}
		long endTime = System.currentTimeMillis();
		long diffTime = endTime - startTime;
		double transferSpeed = fileSize / diffTime;
		
		System.out.println("전송 시간 : "+diffTime+" (ms)");
		System.out.println("평균 전송 속도 : "+transferSpeed+" (Bytes/ms)");
		System.out.println("전송 완료...");
		
		fis.close();
		ds.close();
	}
		
	/**
	 * 바이트배열 데이터 전송하기
	 * 
	 * @param data 전송할 바이트 배열
	 * @throws IOException 
	 */
	private void sendData(byte[] data) throws IOException {
		sendData(data, data.length);
	}

	/**
	 * 바이트배열 데이터 전송하기
	 * @param data
	 * @param length
	 * @throws IOException 
	 */
	private void sendData(byte[] data, int length) throws IOException {
		dp = new DatagramPacket(data, length, receiverAddr, receiverPort);
		ds.send(dp);
	}

	public static void main(String[] args) throws Exception {
		new T03UdpFileSender("192.168.143.17", 8888).start();
	}
}
