package kr.or.ddit.basic;

public class T19WaitNotifyTest {
/*
 	wait() 메서드 -> 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별 존재)으로 이동시킨다.
 	
 	notify() 또는 notifyAll()메서드 -> Wait-Set영역에 있는 스레드를 깨워서 실행(RUNNABLE)될 수 있도록 한다.
 									(notify()는 하나, notifyAll()은 Wait-Set에 있는 모두를 깨운다)
 	
 	=> wait()과 notify(), notifyAll()은 동기화 영역에서만 의미가 있고,
 		Object클래스에서 제공하는 메서드이다.
 */
	
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		Thread thA = new ThreadA(workObj);
		Thread thB = new ThreadB(workObj);
		
		thA.start();	//스레드 10번 호출
		thB.start();
	}

}

//공유객체로 사용할 클래스
class WorkObject {
	
	public synchronized void methodA() {
		System.out.println("methodA()메서드에서 작업 중...");
		
		System.out.println(Thread.currentThread().getName()+" : notify() 호출");
		notify();
		
		try {
			System.out.println(Thread.currentThread().getName()+" : wait() 호출");
			wait(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()메서드에서 작업 중...");
		
		System.out.println(Thread.currentThread().getName()+" : notify() 호출");
		notify();
		
		try {
			System.out.println(Thread.currentThread().getName()+" : wait() 호출");
			wait(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//WorkObject의 methodA()만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		super("ThreadA");
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {	//스레드 10번 호출
			workObj.methodA();
		}
	}
}

class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		super("ThreadB");
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
		}
	}
}
