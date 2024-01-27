package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	public static void main(String[] args) {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		/*
		// 직접 복사하는 방법
		outSrc = new byte[inSrc.length];	//공간 확보
		
		for(int i=0; i<inSrc.length; i++) {
			outSrc[i] = inSrc[i];
		}
		
		System.out.println("직접 복사 후 outSrc => "+Arrays.toString(outSrc));
		*/
		
		
		/*
		//arraycopy() 메서드 이용
		outSrc = new byte[inSrc.length];
		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
		
		System.out.println("arraycopy() 메서드 이용 후 outSrc => "+Arrays.toString(outSrc));
		*/
		
		
		//스트림 객체 생성하기
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data = 0;	//읽어온 바이트 데이터 저장용
		
		//read()메서드 => byte단위로 데이터를 읽어와 int형으로 반환한다.
		//				 더 이상 읽어올 데이터가 없으면 -1을 반환한다.
		while((data = bais.read()) != -1) {
			baos.write(data);	//출력하기
		}
		outSrc = baos.toByteArray();	//복사하고 싶은 대상에 넣기
		
		System.out.println("inSrc => " +Arrays.toString(inSrc));
		System.out.println("outSrc => " +Arrays.toString(outSrc));
	}
}
