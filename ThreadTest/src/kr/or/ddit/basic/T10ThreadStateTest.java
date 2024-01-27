package kr.or.ddit.basic;

public class T10ThreadStateTest {
/*
 	<스레드의 상태>
 	
 	- NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
 	- RUNNABLE : 실행 중 또는 실행 가능한 상태
 	- BLOCKED : 동기화블럭에 의해서 일시정지 된 상태(락이 풀릴때까지 기다리는 상태)
 	- WAITING, TIMED_WAITING : 스레드의 작업이 종료되지는 않았지만 실행가능 하지않은 일시정지 상태
 								TIMED_WAITING은 일시정지 시간이 지정된 경우임
 	- TERMINATED : 스레드의 작업이 종료된 상태
 */
	public static void main(String[] args) {
		
		Thread target = new TargetThread();
		
		Thread spt = new StatePrintThread(target);
		spt.start();
		//종료순서 : main->targetThread->StatePrintThread
		
	}
}
//스레드의 상태를 출력하기 위한 모니터링용 스레드
class StatePrintThread extends Thread{
	
	private Thread targetThread;	//모니터링 대상 스레드
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			//Thread의 상태 구하기
			Thread.State state = targetThread.getState();
			System.out.println("타겟스레드의 상태값 : "+state);
			
			//NEW 상태인지 검사
			if(state == Thread.State.NEW) {
				targetThread.start();
			}
			
			//타겟 스레드가 종료 상태인지 검사. 비가역적이라서 다시 new나 runnable로 돌아갈 수 없음
			if(state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//Target용 스레드
class TargetThread extends Thread{
	@Override
	public void run() {
		
		for(long i=1; i<=10000000000L; i++) {}	//시간 지연용
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for(long i=1; i<=10000000000L; i++) {}	//시간 지연용
	}
}