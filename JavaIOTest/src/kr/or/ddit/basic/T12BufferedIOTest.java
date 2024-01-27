package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 성능향상을 위한 보조스트림 예제
 * (문자기반 스트림을 위한 보조스트림)
 * @author PC-25
 *
 */
public class T12BufferedIOTest {
	public static void main(String[] args) {
		
		FileReader fr = null;
		BufferedReader br = null;
		
		//File관련된 스트림 사용 시, 예외가 발생해도 확실히 close()시키는게 좋으니까 finally쓰려고 try~catch 씀
		try {
			
			fr = new FileReader("src/kr/or/ddit/basic/T11BufferedIOTest.java");
			
			br = new BufferedReader(fr);
			
			
			int data = 0;
			
//			while((data = fr.read()) != -1) {
//				System.out.print((char) data);	//버퍼없을때는 한 문자씩 읽는다
//			}
			
			String tempStr = "";
			int cnt = 1; //줄번호
			
			while((tempStr = br.readLine()) != null) {
				System.out.printf("%d : %s \n",cnt++,tempStr);
//				System.out.print(tempStr);	//버퍼가 있을때는 한 줄씩 읽는다
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
