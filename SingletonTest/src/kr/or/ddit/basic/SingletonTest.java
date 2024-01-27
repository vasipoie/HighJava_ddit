package kr.or.ddit.basic;

public class SingletonTest {
	public static void main(String[] args) {
		
		//getInstance()이용하여 객체생성하기
		MySingleton test1 = MySingleton.getInstance();
		test1.display();
		
		MySingleton test2 = MySingleton.getInstance();
		test2.display();
		
		System.out.println("test1 => "+test1);
		System.out.println("test2 => "+test2);
		
		
		
	}
}
