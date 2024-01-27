package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력 예제
 * @author PC-25
 *
 */
public class T06FileStreamTest {
	public static void main(String[] args) {
		
		//파일에 출력하기
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/out.txt"); //FileOutputStream 클래스는 찾는 파일이 없으면 만들어줌
			
			for(char ch='a'; ch<='z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일에 쓰기 작업 완료...");
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fos.close();	//작업 후 스트림객체 닫기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//////////////////////////////////////////////////////////
		
		//저장한 파일 읽어오기
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/out.txt");
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				System.out.print((char)data);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fis.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
