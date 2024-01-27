package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//인코딩 방식 바꿔서 저장하기
public class T10FileEncodingTest {
	/*
	 	OutputStreamWriter 객체 => 바이트기반 객체를 문자기반 객체로 변환해주는 보조스트림
	 						   => 이 객체도 '인코딩방식'을 지정하여 출력할 수 있다.
	 */
		/*
		 	키보드로 입력한 내용을 파일로 저장하는데...
		 	out_utf8.txt 파일은 'utf-8'인코딩 방식으로,
		 	out_ansi.txt 파일은 'ms949'인코딩 방식으로 저장한다.
		 */
	
	public static void main(String[] args) throws IOException {
		//입력용 스트림 객체
		InputStreamReader isr = new InputStreamReader(System.in);
		
		//출력용 스트림 객체
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");
		
		//출력용 보조스트림 이용하여 인코딩 방식 지정하기
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "utf-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "ms949");
		
		System.out.println("아무거나 입력하세요.(종료시 Ctrl+z 누르세요)");
		
		int data = 0;
		
		while((data = isr.read()) != -1) {
			osw1.write(data);
			osw2.write(data);
		}
		System.out.println("작업완료...");
		
		isr.close();
		osw1.close();
		osw2.close();
		
	}
}