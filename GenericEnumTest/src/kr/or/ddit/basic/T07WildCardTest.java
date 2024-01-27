package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 와일드카드 예제
 * @author PC-25
 *
 */
public class T07WildCardTest {
	
	//장바구니 항목 조회를 위한 메서드(모든 항목을 담은 장바구니)
	public static void displayCartItemInfo(Cart<?> cart) {
		System.out.println(" = 장바구니 항목 리스트(모든 항목) = ");
		for(Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("-------------------------------------");
	}
	
	//장바구니 항목 조회를 위한 메서드(음료나 음료 하위항목을 담은 장바구니)
	public static void displayCartItemInfo2(Cart<? extends Drink> cart) {
		System.out.println(" = 장바구니 항목 리스트(음료류 항목) = ");
		for(Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("-------------------------------------");
	}
	
	//장바구니 항목 조회를 위한 메서드(고기류나 상위 항목을 담은 장바구니)
	public static void displayCartItemInfo3(Cart<? super Meat> cart) {
		System.out.println(" = 장바구니 항목 리스트(고기류나 상위 항목) = ");
		for(Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("-------------------------------------");
	}

	
	public static void main(String[] args) {
		Cart<Food> foodCart = new Cart<>();
		foodCart.addItem(new Meat("돼지고기", 5000));
		foodCart.addItem(new Meat("소고기", 100000));
		foodCart.addItem(new Juice("오렌지쥬스", 1000));
		foodCart.addItem(new Coffee("아메리카노", 2000));
		
		Cart<Meat> meatCart = new Cart<>();
		meatCart.addItem(new Meat("돼지고기", 5000));
		meatCart.addItem(new Meat("소고기", 100000));
//		meatCart.addItem(new Juice("오렌지쥬스", 1000));
//		meatCart.addItem(new Coffee("아메리카노", 2000));
		
		Cart<Drink> drinkCart = new Cart<>();
//		drinkCart.addItem(new Meat("돼지고기", 5000));
//		drinkCart.addItem(new Meat("소고기", 100000));
		drinkCart.addItem(new Juice("오렌지쥬스", 1000));
		drinkCart.addItem(new Coffee("아메리카노", 2000));
		
		////////////////////////////////////////////////////
		
		displayCartItemInfo(foodCart);
		displayCartItemInfo(meatCart);
		displayCartItemInfo(drinkCart);
		System.out.println("/////////////////////////////////////");
		
//		displayCartItemInfo2(foodCart);
//		displayCartItemInfo2(meatCart);
		displayCartItemInfo2(drinkCart);
		System.out.println("/////////////////////////////////////");
		
		displayCartItemInfo3(foodCart);
		displayCartItemInfo3(meatCart);
//		displayCartItemInfo3(drinkCart);
		
	}
}

class Food {
	private String name; //음식이름
	private int price;	 //음식가격
	
	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return name + "(" + price + "원)";
	}
}

class Meat extends Food {

	public Meat(String name, int price) {
		super(name, price);
	}
}

class Drink extends Food {

	public Drink(String name, int price) {
		super(name, price);
	}
}

class Juice extends Drink {

	public Juice(String name, int price) {
		super(name, price);
	}
}

class Coffee extends Drink {

	public Coffee(String name, int price) {
		super(name, price);
	}
}

class Cart<T> {
	private List<T> list;
	
	public Cart() {
		list = new ArrayList<T>();
	}
	
	public List<T> getList(){
		return list;
	}
	
	public void addItem(T item) {
		list.add(item);
	}
}

/*
  Food - Meat
       - Drink - Juice
               - Coffee
  Cart
 */
 