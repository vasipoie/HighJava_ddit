package kr.or.ddit.basic;

public class T14ThreadShareDataTest {
/*
 	스레드에서 데이터를 공유하는 방법
 	
 	1. 공유데이터를 관리하기 위한 클래스를 정의한다.
 	2. 클래스를 이용하여 공유객체를 생성한다.
 	3. 이 인스턴스를 각각의 스레드에 제공한다.
 	4. 각각의 스레드는 이 인스턴스에 저장한 데이터를 공유하여 사용한다.
 	
 예) 원주율을 계산하는 스레드가 있고, 계산된 원주율을 출력하는 스레드가 있다.
	 원주율을 계산한 후 이 값을 출력하는 프로그램 작성하기.
 */
	
	public static void main(String[] args) {
		
		ShareData sd = new ShareData();	//공유객체
		
		CalcPIThread cTh = new CalcPIThread(sd);
		PrintPIThread pTh = new PrintPIThread(sd);	//둘 다 공유객체를 바라봄
		
		cTh.start();
		pTh.start();
		
		
	}
}

//원주율 데이터를 관리하기 위한 클래스(공유객체 생성용)
class ShareData {
	private double result;	//계산된 원주율 저장될 변수
	volatile private boolean isOk;	//원주율 계산 완료여부 확인용 변수
	/*
	 	volatile => 선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다.
	 				즉, 값이 변경되는 즉시 변수에 적용시킨다.
	 */
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
}

//원주율 계산을 위한 스레드
class CalcPIThread extends Thread {
	private ShareData sd;

	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		/*
		 	원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ......)*4;
		 			 1  -  3  +  5  -  7  +  9  => 분모
		 			 0     1     2     3     4  => 분모를 2로 나눈 몫
		 */
		
		double sum = 0.0;
		for(int i=1; i<=300000000; i+=2) {
			if(((i/2)%2)==0) {
				sum += (1.0/i);
			}else {
				sum -= (1.0/i);
			}
		}
		
		//계산된 원주율 정보 공유객체에 저장하기
		sd.setResult(sum*4);
		sd.setOk(true);
	}
}

//계산된 원주율을 출력하기 위한 스레드
class PrintPIThread extends Thread {
	private ShareData sd;

	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		
		while(true) {
			//원주율 계산이 완료되었는지 확인
			if(sd.isOk()) {
				break;
			}
		}
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.getResult());
		System.out.println("        PI : " + Math.PI);
	}
}