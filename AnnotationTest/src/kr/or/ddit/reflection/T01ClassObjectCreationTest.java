package kr.or.ddit.reflection;

/**
 * Class 오브젝트(클래스 정보를 담고 있는) 생성 예제
 * @author PC-25
 *
 */
public class T01ClassObjectCreationTest {
/*
 	Java Reflection에 대하여...
 	
 	1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생성자 등에 대한 정보를 가져오거나 수정할 수 있고
 		새로운 객체를 생성하거나 메서드를 실행할 수 있다.
 		(컴파일 시점에 해당 정보를 알 수 없는 경우(소스코드 부재)에 유용하게 사용될 수 있다.)
 		
 	2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공된다
 	
 	3. java.lang.Class의 주요 메서드
 	- getName(), getSuperclass(), getInterfaces(), getModifiers() 등
 	
 	4. java.lang.reflect 패키지의 주요 클래스
 	- Field, Method, Constructor, Modifier 등.
 	
 */
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		//Class Object 만드는 방법 3가지 -> 메서드 접근하려고
		
		//클래스 이름을 알 때
		//첫번째 방법 : Class.forName() 이라는 static method 이용하기
		Class<?> klass = Class.forName("kr.or.ddit.reflection.T01ClassObjectCreationTest");
		
		//객체가 있을 때
		//두번째 방법 : getClass() 이라는 instance method 이용하기
		T01ClassObjectCreationTest coct = new T01ClassObjectCreationTest();
		
		klass = coct.getClass();
		
		//세번째 방법 : .class 라는 키워드 이용
		klass = kr.or.ddit.reflection.T01ClassObjectCreationTest.class;
	}
}
