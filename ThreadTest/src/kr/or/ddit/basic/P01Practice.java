package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class P01Practice {
	
	public static boolean INPUT_CHECK = false;
	
	public static void main(String[] args) {
		
//		Thread com = new Computer();
		Thread us = new User();
		Thread cd = new CountDown2();

		us.start();
		cd.start();
//		com.start();
		
	}
}

//class Computer extends Thread {
//	
//	@Override
//	public void run() {
//		
//	}
//}

class User extends Thread {
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		P01Practice.INPUT_CHECK = true;
		System.out.println("당신 : "+str);
		
		int random = (int) (Math.random()*3+1);
		if(random==1) {
			String ga = "가위";
			System.out.println("컴퓨터 : "+ga);
		}else if(random==2) {
			String ba = "바위";
			System.out.println("컴퓨터 : "+ba);
		}else {
			String bo = "보";
			System.out.println("컴퓨터 : "+bo);
		}
		
//		if(str.equals("보"))
		
	}
}

class CountDown2 extends Thread {
	
	@Override
	public void run() {
		for(int i=5; i>0; i--) {
			
			if(P01Practice.INPUT_CHECK) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("5초가 지났습니다. 당신이 졌습니다.");
		System.exit(0);
	}
}