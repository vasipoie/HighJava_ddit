package kr.or.ddit.basic;

public class T20WaitNotifyTest {
	public static void main(String[] args) {
		
		DataBox dataBox = new DataBox();
		
		ProducerThread pTh = new ProducerThread(dataBox);
		ConsumerThread cTh = new ConsumerThread(dataBox);
		
		pTh.start();
		cTh.start();
		
	}
}

class DataBox{
	private String data;	//멤버변수 null = 데이터 없음
	
	//data가 null이 아닐 때 data값을 반환하는 메서드
	public synchronized String getData() {	//consumer가 호출 할 데이터 가져가는 메서드
		System.out.println(Thread.currentThread().getName()
				+" : getData() 메서드 진입...");
		
		if(this.data == null) {	//데이터가 없으면 wait으로
			
			try {
				System.out.println(Thread.currentThread().getName()
						+" : getData() 메서드 안에서 wait() 호출");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String returnData = this.data;	//데이터가 있으면 가져옴
		System.out.println("읽어온 데이터 : "+ returnData);
		this.data = null;	//데이터를 꺼내갔으니까 제거
		System.out.println();
		System.out.println(Thread.currentThread().getName()
				+" : getData() 메서드 안에서 notify() 호출");
		notify();	//세팅 못 한 producer가 있으면 깨우려고 notify
		
		System.out.println(Thread.currentThread().getName()
				+" : getData() 메서드 끝...");
		
		return returnData;
	}
	
	public synchronized void setData(String data) {	//producer가 쓸 데이터 세팅하는 메서드
		System.out.println(Thread.currentThread().getName()
				+" : setData() 메서드 진입...");
		
		if(this.data != null) {	//not null이면 데이터가 있으니까 기다리도록 wait
			try {
				System.out.println(Thread.currentThread().getName()
						+" : setData() 메서드 안에서 wait() 호출");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.data = data;	//data가 null이면 넣는다
		System.out.println("셋팅한 데이터 : "+this.data);
		System.out.println(Thread.currentThread().getName()
				+" : setData() 메서드 안에서 notify() 호출");
		notify();	//데이터를 못가져간 consumer가 있으면 깨우려고 notify
		
		System.out.println(Thread.currentThread().getName()
				+" : setData() 메서드 끝...");
	}
}

//데이터를 세팅만 하는 스레드
class ProducerThread extends Thread {
	private DataBox dataBox;

	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {	//세팅만 5번
			String data = "Data-"+i;
			System.out.println(this.getName()+"가 dataBox.setData("+data+") 호출하려고 함");
			dataBox.setData(data);
		}
	}
}

//데이터를 가져오기만 하는 스레드
class ConsumerThread extends Thread {
	private DataBox dataBox;

	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {	//가져가는것만 5번
			String data = dataBox.getData();
			System.out.println(this.getName()+"가 dataBox.getData() 호출 후 결과 받음 => "+data);
		}
	}
}