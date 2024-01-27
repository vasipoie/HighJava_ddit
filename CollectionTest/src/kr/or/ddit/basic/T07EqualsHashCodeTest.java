package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T07EqualsHashCodeTest {
/*
 	해시함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수.
 	해시함수에 의해 얻어지는 값을 해시 값, 해시코드 또는 간단히 해시라고 한다.
 	
 	HashSet, HashMap, Hashtable과 같은 객체를 사용할 경우
 	객체가 서로 같은지를 비교하기 위해 hashCode()와 equals()메서드를 사용한다.
 	그래서 객체가 서로 같은지 여부를 체크하기 위해 두 메서드를 재정의 해주어야 한다.
 	객체가 같은지 여부는 데이터를 추가할 때 검사한다.
 	
 		- equals()는 두 객체의 내용(값)이 같은지 비교하는 메서드이고,
 		  hashCode()는 객체에 대한 해시코드값을 가져오기 위한 메서드이다. 
 		  
 		- equals()와 hashCode()메서드에 관한 규칙
 		1. 두 객체가 같으면 반드시 같은 해시코드를 가져야 한다.
 		2. 두 객체가 같으면 equals()메서드를 호출했을 때 true를 반환해야 한다.
 		즉, 객체 a,b가 같다면 a.equals(b)와 b.equals(a) 모두 true 이어야 한다.
 		3. 두 객체의 해시값이 같다고 해서 두 객체가 반드시 같은 객체라고 할 수는 없다.
 		하지만, 두 객체가 같으면 반드시 해시값이 같아야 한다.
 		4. equals()를 재정의 해야 한다면, hashCode()도 재정의 해주어야 한다.
 		5. hashCode()는 기본적으로 힙메모리에 존재하는 각 객체에 대한 메모리 주소를 기반으로 한
 		    정수값을 반환한다. 그러므로, 클래스에서 hashCode()메서드를 재정의 하지 않으면
 		    절대로 두 객체가 같은 객체로 간주될 수 없다.
 		  
 */
	public static void main(String[] args) {
		System.out.println("abcd".hashCode());
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode());
		System.out.println();
		
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");
		
		System.out.println("p1.equals(p2) : "+p1.equals(p2));
		System.out.println("p1==p2 : "+ (p1==p2));
		System.out.println();
		
		Set<Person> pSet = new HashSet<Person>();
		
		System.out.println("add(p1) 성공여부 : "+pSet.add(p1));
		System.out.println("add(p2) 성공여부 : "+pSet.add(p2));
		System.out.println();
		
		System.out.println("p1, p2 등록 후 데이터");
		for (Person p : pSet) {
			System.out.println(p.getId() + " : "+p.getName());
		}
		System.out.println();
		
		System.out.println("add(p3) 성공여부 : "+pSet.add(p3));
		System.out.println("p3 등록 후 데이터");
		for (Person p : pSet) {
			System.out.println(p.getId() + " : "+p.getName());
		}
		System.out.println();
		
		System.out.println("remove(p2) 성공여부 : "+pSet.remove(p2));
		System.out.println("p2 삭제 후 데이터");
		for (Person p : pSet) {
			System.out.println(p.getId() + " : "+p.getName());
		}
	}
}

class Person {
	private int id;
	private String name;
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	/*
	@Override
	public boolean equals(Object obj) {
		Person p = (Person)	obj;
		return (this.getId() == p.getId()) && (this.getName().equals(p.getName()));
	}
	
	@Override
	public int hashCode() {
		
		return (name+id).hashCode();//문자열 hashCode
	}
	*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}