package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//인코딩 방식 적용 후 읽어오기
public class T09FileEncodingTest {
/*
 	문자 인코딩 방식에 대하여...
 	
 	한글 인코딩 방식은 크게 UTF-8과 EUC-KR방식 두 가지로 나누어 볼 수 있다.
	원래 한글 윈도우는 CP949방식을 사용했는데, 
	윈도우를 개발한 마이크로소프트사에서 EUC-KR방식 코드표를 확장했기 때문에 MS949라고도 부른다.
	한글윈도우의 메모장에서 말하는 ANSI인코딩이란, CP949(Code Page 949)를 말한다.
	  - MS949 => 한글 윈도우의 기본 한글 인코딩 방식(ANSI계열)
	  - US-ASCII => 영문 전용 인코딩 방식
	  
	참고)
	  ASCII => extended ASCII(ISO 8859-1) => 완성형(KSC 5601)
	  
	  => 윈도우 계열 : CP949(확장완성형)
	  => 유닉스 계열 : EUC-KR(확장유닉스 코드)
	  
	  => ANSI계열 => EUC-KR
	  -----------------------------------
	  => 유니코드(UTF-8)
 */
	
	public static void main(String[] args) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
//			fis = new FileInputStream("d:/D_Other/test_ansi.txt");
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			
			//파일 인코딩 정보를 이용하여 읽어오기
			//ex) new InputStreamReader(바이트기반스트림객체, 인코딩방식);
			isr = new InputStreamReader(fis, "utf-8");
			
			int data = 0;
			
			while((data = isr.read()) != -1) {
				System.out.print((char) data);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				isr.close(); //보조스트림만 닫아도 된다.
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	
}
