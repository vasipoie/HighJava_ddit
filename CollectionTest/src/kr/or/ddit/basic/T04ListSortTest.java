package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04ListSortTest {
	public static void main(String[] args) {
		
		List<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-1111-2222"));
		memList.add(new Member(9, "성춘향", "010-1111-3333"));
		memList.add(new Member(3, "이순신", "010-1111-4444"));
		memList.add(new Member(6, "강감찬", "010-1111-5555"));
		memList.add(new Member(2, "일지매", "010-1111-6666"));
		
		System.out.println("for쓰기 전, 정렬 전 : "+memList);
		
		System.out.println();
		for(Member mem : memList) {
			System.out.println("정렬 전 :"+mem);
		}
		
		Collections.sort(memList);
		System.out.println();
		for(Member m : memList) {
			System.out.println("정렬 후 : "+m);
		}
		System.out.println("---------------------------------------------");
		
		Collections.shuffle(memList);

		//외부 정렬자를 이용한 정렬시작
		Collections.sort(memList, new SortNumDesc());
		for(Member me: memList) {
			System.out.println("외부정렬자를 이용한 정렬 후 : "+me);
		}
		
	}
}

//Member객체의 번호를 기준으로 내림차순 정렬
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member o1, Member o2) {
		return new Integer(o1.getNum()).compareTo(o2.getNum())*-1;
//		return Integer.compare(o1.getNum(), o2.getNum());
	}

	/*
	@Override
	public int compare(Member o1, Member o2) {
		if(o1.getNum()>o2.getNum()) {
			return -1;
		}else if(o1.getNum() == o2.getNum()){
			return 0;
		}else
			return 1;
	}
	*/
}


//회원정보를 담기위한 VO클래스 정의
class Member implements Comparable<Member>{
	private int num;	//번호
	private String name;//이름
	private String tel; //전화번호
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	//회원이름을 기준으로 오름차순 정렬하기
	@Override
	public int compareTo(Member mem) {
		return this.getName().compareTo(mem.getName());
	}
}