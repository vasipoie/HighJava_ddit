package kr.or.ddit.basic;

public class T12ThreadYieldTest {
/*
 	yield() 메서드에 대하여...
 	
 	1. 현재 실행 대기 중인 동등한 우선순위 이상의 다른 스레드에게 실행기회를 제공한다.(양보)
 	2. 현재 실행 중인 스레드의 상태를 RUNNABLE 상태로 바꾼다.
 	3. yield()메서드를 실행한다고 해서 현재 실행 중인 스레드가 곧바로 RUNNABLE 상태로 전이된다고 확신할 수 없다.
 */
	
	public static void main(String[] args) {
		
		Thread th1 = new YieldThreadEx1();
		Thread th2 = new YieldThreadEx2();
		
		th1.start();
		th2.start();
	}
}

class YieldThreadEx1 extends Thread {
	public YieldThreadEx1() {
		super("양보 스레드");	//생성자에서 이름 부여
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			//현재 스레드의 객체를 가져오는데 스레드 이름찍고 현재 i 몇번 째인지도 찍기
			System.out.println(Thread.currentThread().getName() + " : " + i);

			//for문 돌리는거랑 sleep는 다름
			//sleep는 일을 안하는거 WAITING
			//for문은 일 하고있는거. RUNNABLE
			for (int j = 0; j <= 1000000000; j++) {}

			Thread.yield(); // 양보하기
		}
	}
}

class YieldThreadEx2 extends Thread {
	public YieldThreadEx2() {
		super("비양보 스레드");
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);

			for (int j = 0; j <= 1000000000; j++) {}

//			Thread.yield(); // 양보하기
		}
	}
}