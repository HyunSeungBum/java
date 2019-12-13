package com.sbhyun.functionalinterfaces;

import java.util.function.Function;

/*
 * 인터페이스 명칭에서부터 알 수 있듯이 전형적인 함수를 지원한다고 보면 된다. 
 * 하나의 인자와 리턴타입을 가지며 그걸 제네릭으로 지정해줄수있다. 
 * 그래서 타입파라미터(Type Parameter)가 2개다.
 * T: denotes the type of the input argument
 * R: denotes the return type of the function
 */
public class myFunctionMain {

	public static void main(String[] args) {
		// Function 인터페이스를 이용한 구현
		Function<Integer, Double> f1 = new myFunctionImp();
		Double res1 = f1.apply(1);
		System.out.println("Result: " + res1);
		
		// Anonymous 클래스를 이용한 구현
		Function<Integer, Double> f2 = new Function<Integer, Double>() {

			@Override
			public Double apply(Integer t) {
				// TODO Auto-generated method stub
				return t/2.0;
			}
			
		};
		
		Double res2 = f2.apply(1);
		System.out.println("Result: " + res2);
		
		// Lambda 표현식
		Function<Integer, Double> f3 = (t) -> t/2.0;
		Double res3 = f3.apply(1);
		System.out.println("Result: " + res3);
	}

}
