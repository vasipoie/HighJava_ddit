package kr.or.ddit.reflection;

import java.io.Serializable;

import kr.or.ddit.basic.T01PrintAnnotation;

//interface를 implements시켜봄
public class T02_2SampleVo implements Serializable {
	private String id;
	protected String name;
	public int age;
	
	public T02_2SampleVo(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public T02_2SampleVo() {
		// TODO Auto-generated constructor stub
	}

	@Deprecated
	@T01PrintAnnotation
	public String getId() throws Exception {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "T02_2SampleVo [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
