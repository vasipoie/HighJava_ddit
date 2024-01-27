package kr.or.ddit.basic;

/**
 * 스레드 수행시간 체크해보기
 * @author PC-25
 *
 */
public class T03ThreadTest {
	public static void main(String[] args) {
		Thread th1 = new Thread(new MyRunner());
		
		//UTC(Universal Time Coordinated 세계 표준 협정시)를 사용하여 
		//1970년 1월 1일 0분 0초를 기준으로 경과한 시간을 밀리세컨드 단위로 나타낸다.
		long startTime = System.currentTimeMillis();
		
		th1.start();
		
		try {
			th1.join();	 //현재 실행 중인 스레드에서 작업중인 스레드가 종료될 때까지 기다린다
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간(ms) : "+(endTime - startTime));
		
	}
}


//1~1000000000(9) 까지의 합계를 구하는 스레드
class MyRunner implements Runnable {

	@Override
	public void run() {
		long sum = 0;
		for(int i=1; i<=1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : "+sum);
	}
}