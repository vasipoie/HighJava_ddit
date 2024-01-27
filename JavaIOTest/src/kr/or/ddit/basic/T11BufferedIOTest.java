package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 입출력 성능향상을 위한 보조스트림 예제
 * (바이트기반의 Buffered 스트림 예제)
 * @author PC-25
 *
 */
public class T11BufferedIOTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;		//기반스트림
		BufferedOutputStream bos = null;	//바이트 기반의 스트림을 위한 보조스트림
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192byte(8KB)로 설정됨
			
			//버퍼의 크기가 5byte인 보조스트림 생성
			//버퍼 기능을 쓸 수 있다 -> 안쓰면 기반에서는 1byte씩 읽음
			bos = new BufferedOutputStream(fos, 5);
			
			for(char ch='1'; ch<='9'; ch++) {
				bos.write(ch);	//보조스트림 write
				fos.write(ch);	//결과는 같음. 차이 : 버퍼링을 쓰면 입출력 성능향상이 있다
			}
			
//			bos.flush(); //방출. close()에 flush()가 동작하도록 되어있어 생략가능
			System.out.println("작업 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				bos.close();	//보조스트림만 닫아도 된다
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
