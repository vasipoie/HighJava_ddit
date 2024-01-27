package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T18SyncCollectionTest {
/*
 	Vector, Hashtable 등의 예전부터 존재하던 Collection 클래스들은 내부에 동기화 처리가 되어있다
 	그런데, 최근에 새로 구성된 Collection클래스들은 동기화 처리가 되어있지 않다.
 	그래서 동기화가 필요한 경우에는 직접 동기화 처리를 한 후에 사용해야 한다.
 */
	// 동기화 처리를 하지 않은 경우...
	private static List<Integer> list1 = new ArrayList<Integer>();
	
	//동기화 처리를 한 경우...
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=10000; i++) {
					
//					//list1에 들어갈 때 순서대로 들어갈 수 있게 동기화 처리
//					synchronized (list1) {
//						list1.add(i);
//					}
					
					list2.add(i);
				}
			}
		};
		
		Thread[] ths = new Thread[] {
			new Thread(r), new Thread(r),
			new Thread(r), new Thread(r), new Thread(r)
		};
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("list1의 개수 : "+list1.size());
		
		System.out.println("list2의 개수 : "+list2.size());
		
		
		
//		list1.add(1);
//		list1.add(1);
//		list1.add(1);
//
//		System.out.println(list1);
//		list1.add(6, 1);	//하나씩 들어가는게 아니라 건너뛰고 들어가려니까 오류
		
		
	}
}
