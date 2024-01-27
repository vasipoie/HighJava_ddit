package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T03ListSortTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : "+list);
		System.out.println("---------------------------------------------");
		//정렬은 Collections.sort()메서드를 이용하여 정렬한다.
		//정렬은 기본적으로 '오름차순 정렬'을 수행한다.
		
		//Collections 클래스 안에 static method로 sort가 정의되어있음.
		//static method는 클래스명.메소드이름
		//static method <-> instance method
		//instance method는 객체를 만들어야함. new 클래스명().메소드이름
		//static method는 자주써야할 때 사용
		Collections.sort(list);//오름차순으로 정렬하기
		System.out.println("정렬 후 : "+list);
		System.out.println("---------------------------------------------");
		
		//섞기
		Collections.shuffle(list);
		System.out.println("섞은 후 : "+list);
		System.out.println("---------------------------------------------");
		
		Collections.sort(list, new Desc());
		System.out.println("외부정렬자를 이용한 정렬 후 : "+list);
		
	}
}

//정렬방식을 결정하는 클래스(외부 정렬자)는 Comparator를 구현해야 한다.
class Desc implements Comparator<String>{
	/*
	 	compare()메서드의 반환값을 결정하는 방법
	 	
	 	- 오름차순 정렬일 경우
	 	=> 앞의 값이 크면 양수, 같으면 0, 앞의 같이 작으면 음수를 반환한다
	 */

	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2)*-1;
	}
}
