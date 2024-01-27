package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];
		
		//스트림 객체 생성하기
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readBytes = 0;	//읽어온 바이트 데이터 저장용(읽은 바이트 수)
		
		//read()메서드 => byte단위로 데이터를 읽어와 int형으로 반환한다.
		//				 1byte씩 읽고 더 이상 읽어올 데이터가 없으면 -1을 반환한다.
		//bais.read(temp) : 읽을파일경로.읽는다(원하는 바이트 수로)
		while((readBytes = bais.read(temp)) != -1) {
			System.out.println("temp => "+Arrays.toString(temp));
			//baos.write(temp,0,readBytes)
			// : 쓸위치.쓴다(내용/원하는바이트수로 
			//				,기록할 내용의 시작위치(offset)
			//              , 기록할 내용의 끝위치(length) 
			baos.write(temp, 0, readBytes);	//출력하기
		}
		outSrc = baos.toByteArray();	//복사하고 싶은 대상에 넣기
		
		System.out.println("inSrc => " +Arrays.toString(inSrc));
		System.out.println("outSrc => " +Arrays.toString(outSrc));
	}
}
