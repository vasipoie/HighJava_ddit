package kr.or.ddit.basic;

class Util2 {
	public static <T extends Number> int compare(T t1, T t2) {
		
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}

/**
 * 제한된 타입 파라미터 예제 -> extends
 * (Bounded Parameter)
 * @author PC-25
 *
 */
public class T05GenericMethodTest {
	public static void main(String[] args) {
		
		int result1 = Util2.compare(10, 20);
		System.out.println("result1 : "+result1);
		
		int result2 = Util2.compare(3.14, 3);
		System.out.println("result2 : "+result2);
		
		
		Flyable f = new MyPlane();
		f.fly();
		
	}
}


/*
 	interface는 추상메서드
 	implements를 하면 Flyable의 fly()메서드를 Override해야함
 	그러면 main()메서드에서 MyPlane 객체를 만들고 fly()메서드 사용 가능
 */
interface Flyable {
	public void fly();
}

class MyPlane implements Flyable {

	@Override
	public void fly() {
		// TODO Auto-generated method stub
	}
	
}