package kr.or.ddit.basic;

public class MySingleton {
/*
 	- Singleton 클래스 생성하는 방법
 	1. 자기 자신 class 참조변수를 멤버변수로 선언한다.
 		(이 변수는 private static으로 지정한다.)
 	
 	2. 생성자를 private으로 한다.
 		(외부에서 생성자에 접근을 못하게 하기 위해서 즉, 외부에서 new명령을 사용하지 못하게 하기 위해)
 		
 	3. 객체(인스턴스)는 내부에서 생성해서 반환해줄 수 있는 메서드를 만든다.
 		(이 메서드는 static으로 선언하고 이름은 보통 getInstance()로 지정한다.)
 */
	
	//자기 자신의 클래스 참조변수를 선언한다.
	private static MySingleton single;
	
	//생성자를 private으로 지정한다.
	private MySingleton() {
		System.out.println("생성자 호출되었습니다.");
	}
	
	public static MySingleton getInstance() {
		if(single == null) {
			single = new MySingleton();
		}
		return single;
	}
	
	public void display() {
		System.out.println("안녕하세요. 싱글턴 객체입니다.");
	}
	
}
