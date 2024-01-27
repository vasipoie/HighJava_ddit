package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileWriter(문자기반스트림) 테스트
 * @author PC-25
 *
 */
public class T07FileWriterTest {
	public static void main(String[] args) {
		
		//사용자가 입력한 내용을 그대로 파일로 저장하기
		
		//콘솔(표준 입출력장치)과 연결된 입력용 문자 스트림 생성
		//InputStreamReader 스트림 => 바이트기반 스트림을 문자기반 스트림으로 변환해 주는 보조 스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null;	//파일 출력용 문자기반 스트림
		
		try {
			//파일 출력용 문자기반 스트림 객체 생성
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			System.out.println("아무거나 입력하세요.(종료시 Ctrl+z 키 누르세요)"); 	//Ctrl + z : -1값으로 체크되어서 while문 종료
			
			int data = 0;
			
			while((data = isr.read()) != -1) {
				fw.write(data); //콘솔에서 입력받은 데이터를 파일에 저장하기
			}
			
			System.out.println("작업 끝...");
		} catch (IOException ex) {
			 ex.printStackTrace();
		}finally {
			try {
				isr.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
