package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 객체 입출력 스트림 예제(직렬화와 역직렬화)
 * @author PC-25
 *
 */
public class T15ObjectStreamTest {
	public static void main(String[] args) {
		
		//Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "경기");
		Member mem3 = new Member("이몽룡", 40, "광주");
		Member mem4 = new Member("성춘향", 50, "강원");
		
		ObjectOutputStream oos = null;
		
		try {
			//객체 출력용 스트림 객체 생성
			oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/memberObj.bin"));
			
			//객체 저장
			oos.writeObject(mem1); //직렬화
			oos.writeObject(mem2); //직렬화
			oos.writeObject(mem3); //직렬화
			oos.writeObject(mem4); //직렬화
			
			System.out.println("객체 저장 작업 완료...");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		 finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/////////////////////////////////////////////////////////////////////
		//읽기
		
		ObjectInputStream ois = null;
		
		try {
			//객체를 읽기위한 스트림 객체 생성하기
			ois = new ObjectInputStream(new FileInputStream("d:/D_Other/memberObj.bin"));
			
			Object obj = null;
			
			while((obj = ois.readObject()) != null) { //역직렬화가 일어남
				//읽어온 데이터를 원래의 객체타입으로 캐스팅 후 사용한다
				Member mem = (Member) obj;
				System.out.println("이름 : "+mem.getName());
				System.out.println("나이 : "+mem.getAge());
				System.out.println("주소 : "+mem.getAddr());
				System.out.println("--------------------------");
			}
			
			
		} catch (IOException ex) {
//			ex.printStackTrace();
			//더 이상 읽어올 객체(데이터)가 없으면 예외발생함
			System.out.println("읽기 작업 끝...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * 회원정보를 담기 위한 VO 클래스
 */
class Member implements Serializable{
	//transient => 직렬화하는 데이터에서 제외. 직렬화가 되지 않게 할 멤버변수에 지정한다.
	//			   (static변수도 직렬화 대상이 아니다)
	//				직렬화되지않은 멤버변수는 기본값으로 저장된다.
	//				(참조변수 : null, 숫자형변수 : 0)
	
	private transient String name;
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}