package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class T01ArrayListTest {
	public static void main(String[] args) {
		
		// 기본 용량(Default Capacity)은 10이다.
		List list1 = new ArrayList();//new LinkedList();
		
		//add()메서드를 사용하여 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);	//==list1.add(new Integer(111));
		list1.add('k');
		list1.add(true);
		list1.add(12.34);
		
		//size() => 데이터 개수
		System.out.println("size => "+list1.size());
		System.out.println("list1 => "+list1);
		System.out.println("---------------------------------------------");
		
		//get()으로 데이터 꺼내오기
		System.out.println("1번째 자료 => "+list1.get(0));
		System.out.println("---------------------------------------------");
		
		//데이터 끼워넣기도 add() 사용한다.
		list1.add(0,"zzz");
		System.out.println("zzz끼워넣은 후 => "+list1);
		System.out.println("---------------------------------------------");
		
		//데이터 변경하기(set메서드 사용)
		String temp = (String) list1.set(0,"YYY");
		System.out.println("temp=> "+temp);
		System.out.println("데이터 변경 후 list1 => "+list1);
		System.out.println("---------------------------------------------");
		
		//데이터 삭제하기(remove메서드 이용)
		list1.remove(0);
		System.out.println("첫번째 데이터 삭제 후 => "+list1);
		System.out.println("---------------------------------------------");
		
		list1.remove("bbb");
		System.out.println("bbb 삭제 후 => "+list1);
		System.out.println("---------------------------------------------");
		
		//list1.remove(111) == 111번째 인덱스를 찾아서 지워라
		list1.remove(new Integer(111));
		System.out.println("111 삭제 후 => "+list1);
		System.out.println("---------------------------------------------");
		
		//////////////////////////////////////////////////////////////////////
		
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for(String str : list2) {
			System.out.println(str);
		}
		System.out.println("---------------------------------------------");
		
		//contains(비교객체) => 리스트에 '비교객체'가 있으면 true
		//										없으면 false반환
		System.out.println(list2.contains("DDD"));//true
		System.out.println(list2.contains("ZZZ"));//false
		System.out.println("---------------------------------------------");
		
		//indexOf(비교객체) => 리스트에서 '비교객체'를 찾아 '비교객체'가 존재하는 index값을 반환함.
		//					  없으면 -1 반환함
		System.out.println("DDD의 index값 : "+list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : "+list2.indexOf("ZZZ"));
		System.out.println("---------------------------------------------");
		
//		for (int i = 0; i < list2.size(); i++) {
//			list2.remove(i);
//			System.out.println(i+"삭제함");
//		}
//		//왜 0이 아니라 2가 나올까? -> [0]지우고 앞으로 땡기고, [1]지우고 앞으로 땡기고, [2]지우고 앞으로 땡긴다
//		System.out.println("list2의 크기 => "+list2.size());
//		System.out.println(list2);
//		System.out.println("---------------------------------------------");
		
		//다 지우고싶으면?
		for (int i = list2.size()-1; 0<=i; i--) {
			list2.remove(i);
			System.out.println(i+"삭제함");
		}
		System.out.println("list2의 크기 => "+list2.size());
		System.out.println(list2);
		
		
		
	}
	
}
