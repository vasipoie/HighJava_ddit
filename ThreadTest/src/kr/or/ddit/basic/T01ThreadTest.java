package kr.or.ddit.basic;

public class T01ThreadTest {
	public static void main(String[] args) {
		
		//싱글스레드 프로그램...-> 메인Thread가 main메소드를 찾아서 들어감
		
		for(int i=1; i<=200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for(int i=1; i<=200; i++) {
			System.out.print("$");
		}
	}
}
