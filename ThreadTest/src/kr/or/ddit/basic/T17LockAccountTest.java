package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//락 클래스를 이용한 동기화 처리
public class T17LockAccountTest {
/*
 	락 기능을 제공하는 클래스
 	
 	- ReentrantLock : Read 및 Write 구분없이 사용하기 위한 락클래스로 동기화 처리를 위해 사용
 					  synchronized를 이용한 동기화 처리보다 부가적인 기능이 제공된다
 					  ex) Fairness 설정 등 => 가장 오래 기다린 스레드가 가장 먼저 락을 획득하게 함
 	
 	- ReentrantReadWriteLock : Read 및 Write 락을 구분하여 사용가능
 				여러 스레드가 동시에 Read작업은 가능하지만, Write작업은 단지 하나의 스레드만 가능함(exclusive) 
 				=> Write보다 Read위주의 작업이 많이 발생하는 경우에 사용하면 처리량(Throughput)이 좋아진다
 */
	
	public static void main(String[] args) {
		
		ReentrantLock lock = new ReentrantLock(true);
		
		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000);
		
		BankThread2 bth1 = new BankThread2(lAcc);
		BankThread2 bth2 = new BankThread2(lAcc);
		
		bth1.start();
		bth2.start();
	}
}

//입출금 처리를 위한 공유객체
class LockAccount {
	private int balance;
	//Lock객체 생성 => 되도록이면 private final로 설정한다
	private final Lock lock;

	public int getBalance() {
		return balance;
	}

	public LockAccount(Lock lock) {
		this.lock = lock;
	}

	public void deposit(int money) {
		//Lock객체의 lock()메서드가 동기화 시작이고, unlock()메서드가 동기화의 끝을 나타낸다.
		//lock()메서드로 동기화를 설정한 곳에서는 반드시 unlock()메서드로 해제해 주어야 한다.
		lock.lock(); //락 설정(락을 획득하기 전까지 BLOCKED 됨)
		
		balance += money;
		
		lock.unlock(); //락 해제
	}
	
	public boolean withdraw(int money) {
		
		boolean chk = false;
		
		//try~catch 블럭을 사용할 경우에는
		//unlock()메서드 호출은 finally 블럭에서 하도록 한다.
		
		try {
			lock.lock(); //동기화 시작
			
			if(balance >= money) {
				for(int i=1; i<=1000000000; i++) {}
				
				balance -= money;
				System.out.println("메서드 안에서 balance = "+getBalance());
				chk = true;
			}else {
				chk = false;
			}
			
		} catch (Exception ex) {
			
		}finally {
			lock.unlock();
		}
		
		return chk;
	}
}

class BankThread2 extends Thread {
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println(Thread.currentThread().getName()+" 스레드 안에서 = "+result
				+ ", balance = "+lAcc.getBalance());
	}
}