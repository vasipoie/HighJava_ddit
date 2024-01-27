package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T13DataIOStreamTest {
	public static void main(String[] args) {
		
		DataOutputStream dos = null;
		
		try {
			dos = new DataOutputStream(new FileOutputStream("d:/D_Other/test.dat"));
			dos.writeUTF("나콩");	//문자열 데이터 출력(UTF-8)
			dos.writeInt(19);		//정수형으로 데이터 출력. 17을 4byte로
			dos.writeFloat(3.14f);	//실수형(Float)으로 출력
			dos.writeDouble(0);	//실수형(Double)으로 출력
			dos.writeBoolean(true);	//논리형으로 출력
			
			System.out.println("출력완료...");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/////////////////////////////////////////////////////////////////////////////
		
		//읽기시작
		
		DataInputStream dis = null;
		
		try {
			dis = new DataInputStream(new FileInputStream("d:/D_Other/test.dat"));

			//들어간 순서에 맞게 적절한 사이즈만큼, 적절한 타입으로 읽어와야 복원이 됨
			System.out.println("문자열 자료 : "+dis.readUTF());
			System.out.println("정수형 자료 : "+dis.readInt());
			System.out.println("실수형(Float) 자료 : "+dis.readFloat());
			System.out.println("논리형 자료 : "+dis.readBoolean());
			System.out.println("실수형(Double) 자료 : "+dis.readDouble());
			
			System.out.println("읽기 완료...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			
		}
		
		
	}
}
