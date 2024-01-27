package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class T08FileReaderTest {
	public static void main(String[] args) {
		
		//문자기반 스트림을 이용한 파일 내용 읽기 -> 문자기반으로 써야 1byte가 아닌 한글도 문제없이 처리 가능
		FileReader fr = null;
		FileInputStream fis = null;
		
		try {
			fr = new FileReader("d:/D_Other/testChar.txt");
			fis = new FileInputStream("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			while((data = fr.read()) != -1) {
//			while((data = fis.read()) != -1) {
				System.out.print((char)data);
			}
			
			System.out.println();
			System.out.println("읽기작업 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fr.close();	
				fis.close();	//다 읽은 다음에 혹시 모르니 close
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
	}
}
