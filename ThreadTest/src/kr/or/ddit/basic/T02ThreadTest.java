package kr.or.ddit.basic;

public class T02ThreadTest {
	public static void main(String[] args) {
		
		/*
		 	멀티 스레드 프로그램 방식...
		 */
		
		//방법1 : Thread클래스를 상속한 클래스의 인스턴스를 생성한 후
		//	        이 인스턴스의 start() 메서드를 호출한다.
		MyThread1 th1 = new MyThread1();
		th1.start(); //->start()메서드를 통해 메인이 아닌 별도의 Thread가 실행됨
		//여기까지 Thread 총 2개
		
		
		//방법2 : Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 후
		//		   이 인스턴스를 Thread클래스의 생성자 매개변수로 넘겨준다.
		//		   이렇게 생성된 Thread객체의 start()메서드를 호출한다.	-> 재사용 할 때, 다중상속이 안될 때
		Runnable r = new MyThread2();
		
		Thread th2 = new Thread(r);
		th2.start();
		
		
		//방법3 : 익명클래스를 이용하는 방법 	-> 한 번 쓸 때
		//		  Runnable 인터페이스를 구현한 익명클래스를 이용하는 방법
		//인터페이스의 구조를 이용한 익명클래스를 만듦
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("#");
					
					try {
						//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다
						//시간은 밀리세컨드 단위를 사용한다
						//즉, 1000은 1초를 의미한다
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		th3.start();
		//여기까지 Thread 총 4개
	}
}

//방법1
class MyThread1 extends Thread {
	
	@Override
	public void run() {
		
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			
			try {
				//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다
				//시간은 밀리세컨드 단위를 사용한다
				//즉, 1000은 1초를 의미한다
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


//방법2
class MyThread2 implements Runnable {

	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("$");
			
			try {
				//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다
				//시간은 밀리세컨드 단위를 사용한다
				//즉, 1000은 1초를 의미한다
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}