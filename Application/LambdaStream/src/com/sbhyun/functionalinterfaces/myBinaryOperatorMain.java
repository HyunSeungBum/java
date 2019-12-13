package com.sbhyun.functionalinterfaces;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/*
 * 동일한 타입의 인자 2개와 인자와 같은 타입의 리턴타입을 가진다.
 */
public class myBinaryOperatorMain {

	public static void main(String[] args) {

		myBinaryOperatorMain mbom = new myBinaryOperatorMain();
		
		mbom.basic();
		mbom.maxBy();
		mbom.minBy();
		
	}
	
	public void basic() {
		// BinaryOperator 인터페이스를 구현한 클래스를 이용.
		BinaryOperator<String> bo = new myBinaryOperatorImp();
		String res = bo.apply("Hello", "World");
		System.out.println("Result: " + res);

		// Anonymous 클래스를 이용.
		BinaryOperator<String> bo2 = new BinaryOperator<String>() {

			@Override
			public String apply(String t, String u) {
				// TODO Auto-generated method stub
				return t + u;
			}

		};

		String res2 = bo2.apply("Hello", "World");
		System.out.println("Result: " + res2);

		// Lambda 표현식
		BinaryOperator<String> bo3 = (t, u) -> (t + u);
		String res3 = bo3.apply("Hello", "World");
		System.out.println("Result: " + res3);
	}
	
	public void maxBy() {
		Comparator<Integer> cpr = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(02);
			}
			
		};
		
		BinaryOperator<Integer> opMax = BinaryOperator.maxBy(cpr);
		System.out.println("Max: " + opMax.apply(5, 6));
		System.out.println("Max: " + opMax.apply(9, 6));
		
		// 
		BinaryOperator<Integer> op = BinaryOperator
											.maxBy(
													(a, b) -> (a > b) ? 1 : ((a == b) ? 0 : -1));
		System.out.println("Max: " + op.apply(98, 11)); 
		
	}
	
	public void minBy() {
		Comparator<Integer> cpr = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(02);
			}
			
		};
		
		BinaryOperator<Integer> opMin = BinaryOperator.minBy(cpr);
		System.out.println("Min: " + opMin.apply(5, 6));
		System.out.println("Min: " + opMin.apply(9, 6));
		
		BinaryOperator<Integer> op = BinaryOperator
											.minBy(
													(a, b) -> (a > b) ? 1 : ((a == b) ? 0 : -1));
		
		System.out.println("Min: " + op.apply(98, 11));
	}

}
