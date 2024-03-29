package kr.or.ddit.reflection;

import java.lang.reflect.Modifier;

public class T02ClassMetadataTest {
	public static void main(String[] args) throws ClassNotFoundException {
		
		//클래스 오브젝트 생성하기
		Class<?> clazz = Class.forName("kr.or.ddit.reflection.T02_2SampleVo");
		
		System.out.println("심플클래스명 : "+clazz.getSimpleName());
		System.out.println("클래스명 : "+clazz.getName());
		System.out.println(clazz.getSuperclass());
		System.out.println("상위클래스명 : "+clazz.getSuperclass().getName());
		
		System.out.println();
		
		//패키지 정보 가져오기
		Package pkg = clazz.getPackage();
		System.out.println(clazz.getPackage());
		System.out.println("패키지정보 : "+pkg.getName());
		
		System.out.println();
		
		//해당 클래스에서 구현하고 있는 인터페이스 목록 가져오기
		 Class<?>[] interfaces = clazz.getInterfaces();
		 
		 System.out.println(clazz.getInterfaces());
		 
		 System.out.print("인터페이스 목록 : ");
		 for (Class<?> inf : interfaces) {
			System.out.println(inf.getName()+" | ");
		}
		System.out.println();
		
		//클래스의 접근제어자 정보 가져오기
		int modFlag = clazz.getModifiers();
		System.out.println(clazz.getModifiers()); //int값으로 flag형태의 정보를 줌
		//Modifier클래스의 static 메소드 toString으로 flag형태의 정보를 알 수있게 바꿈
		System.out.println("접근제어자 : "+Modifier.toString(modFlag)); 
		
	}
}
