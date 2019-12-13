package com.sbhyun.functionalinterfaces;

import java.util.function.Predicate;

public class myPredicateMain {

	public static void main(String[] args) {
		// Predicate 인터페이스 구현한 클래스로 이용.
		Predicate<String> pd1 = new myPredicateImp();
		boolean res1 = pd1.test("Hello");
		System.out.println(res1);
		
		// Anonymous 인터페이스를 이용.
		Predicate<String> pd2 = new Predicate<String>() {

			@Override
			public boolean test(String t) {
				// TODO Auto-generated method stub
				return t.isEmpty();
			}
			
		};
		
		boolean res2 = pd2.test("Hello");
		System.out.println(res2);
		
		Predicate<String> pd3 = (t) -> t.isEmpty();
		boolean res3 = pd3.test("");
		System.out.println(res3);
	}

}
