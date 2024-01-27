package kr.or.ddit.basic;

//@T01PrintAnnotation -> 적용대상에 method만 되어있으니까 오류
public class T02Service {
	
	@T01PrintAnnotation
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다");
	}
	
	@T01PrintAnnotation(value = "%")
//	@T01PrintAnnotation("%") -> value만 있는 경우, value = 생략 가능
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다");
	}
	
	@T01PrintAnnotation(value = "#", count = 30)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다");
	}
}