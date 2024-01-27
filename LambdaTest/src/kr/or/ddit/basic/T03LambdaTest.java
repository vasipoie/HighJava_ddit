package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class T03LambdaTest {
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		
		list.add("권예은");
		list.add("김나혜");
		list.add("김영상");
		list.add("김태원");
		
		for(String str : list) {
			System.out.println(str);
		}
		
//		list.forEach(new Consumer<String>() { //익명객체타입
//
//			@Override
//			public void accept(String t) {
//				System.out.println(t);
//			}	
//		});
		
		list.forEach((str)->System.out.println(str));
	}
}
