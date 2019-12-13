package com.sbhyun.functionalinterfaces;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

/*
 * Generic Type 의 Supplier 예제.
 * Supplier 는 불변하는 값을 공급하는게 목적이다.
 */
public class myGenericSupplierMain {

	public static void main(String[] args) {
		// Supplier 를 구현한 클래스를 기반으로 작성
		Supplier<String> s = new myGenericSupplierImp();
		System.out.println(s.get());
		
		// Anonymous 클래스를 이용한 구현.
		Supplier<String> s1 = new Supplier<String>() {

			@Override
			public String get() {
				// TODO Auto-generated method stub
				return "Hello World";
			}
			
		};
		System.out.println(s1.get());
		
		// Lambda 표현식을 이용.
		Supplier<String> s2 = () -> "Hello World";
		System.out.println(s2.get());
		
		// DoubleSupplier 는 Generic Supplier 를 이용해서 구현할 수 있다.
		Supplier<Double> ob = () -> Math.random();
		System.out.println(ob.get());
		DoubleSupplier ob2 = () -> Math.random();
		System.out.println(ob2.getAsDouble());
	}

}
