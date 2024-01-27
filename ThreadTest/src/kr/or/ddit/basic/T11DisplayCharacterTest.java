package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T11DisplayCharacterTest {
/*
 	3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데...
 	출력을 끝낸 순서대로 결과를 출력하는 프로그램 작성하기
 */
	
	static int CURR_RANK = 1; //현재 순위정보
	
	public static void main(String[] args) {
		
		List<DisplayCharacter> disCharList = new ArrayList<>();
		disCharList.add(new DisplayCharacter("김나혜"));
		disCharList.add(new DisplayCharacter("김영상"));
		disCharList.add(new DisplayCharacter("김태원"));
		disCharList.add(new DisplayCharacter("민경선"));
		
		for (Thread th : disCharList) {
			th.start();
		}
		for (Thread th : disCharList) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("경기 끝...");
		
		Collections.sort(disCharList); //오름차순으로 정렬 기능 사용
		
		System.out.println("--------------------------");
		System.out.println("==========================");
		System.out.println("     ====경기 결과====");
		System.out.println("==========================");
		System.out.println("              순위\s:\s이름");
		System.out.println("--------------------------");
		for(DisplayCharacter dc : disCharList) {
			System.out.println("         "+dc.getRank()+"\s:\s"+dc.getName());
		}
	}
}

//알파벳 대문자 출력을 위한 스레드
class DisplayCharacter extends Thread implements Comparable<DisplayCharacter> {
	private String name;
	private int rank; //순위
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public DisplayCharacter(String name) {
		super(name);	//스레드 이름 지정. 지정안하면 Thread-0 으로 만들어짐
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch='A'; ch<='C'; ch++) {
			System.out.println(name + "의 출력 문자 : "+ch);
			
			try {
				//sleep()메서드의 시간을 200~500 사이의 난수로 설정함
				Thread.sleep((int)(Math.random()*301+200));
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(name + "출력 끝...");
		
		this.setRank(T11DisplayCharacterTest.CURR_RANK++);
	}

	@Override
	public int compareTo(DisplayCharacter o) {
//		return new Integer(this.getRank()).compareTo(o.getRank());
		return Integer.compare(this.getRank(), o.getRank());
	}
}