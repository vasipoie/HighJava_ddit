package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16NonSerializableParentTest {
/*
 	부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우...
 	부모클래스의 필드값 처리방법에 대하여...
 	
 	1. 부모클래스가 Serializable 인터페이스를 구현하도록 한다.
 	2. 자식클래스에 writObject()와 readObject()메서드를 구현하여 부모객체의 필드값을 직접 저장하고 읽어온다
 */
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/notSerializable.bin"));
		
		Child child = new Child();
		child.setParentName("부모명");
		child.setChildName("자식명");
		
		oos.writeObject(child); //직렬화
		
		oos.close();
		
		/////////////////////////////////////////////
		//저장한거 읽어오기
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/D_Other/notSerializable.bin"));
		
		child = (Child) ois.readObject(); //역직렬화
		
		System.out.println("parentName : "+child.getParentName());
		System.out.println("childName : "+child.getChildName());
		
		ois.close();
	}
}

// Serializable 구현하지 않은 부모클래스 -> parentName : null
class Parent {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

//Serializable을 구현한 자식 클래스
class Child extends Parent implements Serializable {
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
	 * 직렬화 될 때 자동으로 호출됨
	 * (접근제한자가 private이 아니면 자동 호출되지 않음)
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException{
		
		System.out.println("writeObject() 호출됨...");
		
		out.writeUTF(getParentName());
		
		out.defaultWriteObject(); //기본기능 호출
	}
	
	/**
	 * 역직렬화 될 때 자동으로 호출됨
	 * (접근제한자가 private이 아니면 자동 호출되지 않음)
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		System.out.println("readObject() 호출됨...");
		
		setParentName(in.readUTF());
		
		in.defaultReadObject(); //기본기능 호출
	}
}