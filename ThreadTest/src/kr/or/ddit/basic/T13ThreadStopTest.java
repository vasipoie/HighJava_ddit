package kr.or.ddit.basic;

public class T13ThreadStopTest {
	public static void main(String[] args) {
		ThreadStopEx1 th1 = new ThreadStopEx1();
//		th1.start();
		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		th1.setStop(true);
//		th1.stop();	//강제종료
		
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt();  //인터럽트 걸기 -> try catch의 catch부분으로 이동
		
		
	}
	
}

class ThreadStopEx1 extends Thread {
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 작업 중...");
		}
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료");
	}
}

class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
		//방법1 => sleep()메서드나 join()메서드 등을 사용했을 때 
		//		  interrupt()메서드를 호출하면 Interrupted Exception이 발생한다.
		//		   이 예외 이용하기.
/*
		try {
			while(true) {
				System.out.println("스레드 작업 중...");
				Thread.sleep(1);
			}
		}catch (InterruptedException ex) { }
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료");
*/		
		
		//방법2 => interrupt()가 호출되었는지 검사하기
///*
		while(true) {
			System.out.println("스레드 처리 중...");
			
			//검사방법1 => 스레드의 인스턴스 메서드를 이용하는 방법
//			if(this.isInterrupted()) {
//				System.out.println("인스턴스 메서드 사용하는 방법");
//				break;
//			}
			
			//검사방법2 => 스레드의 정적 메서드를 이용하는 방법
			if(Thread.interrupted()) {
				System.out.println("정적 메서드를 이용하는 방법");
				break;
			}
		}
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료");
//*/
	}
}

