package com.sbhyun.functionalinterfaces;

import java.util.function.IntSupplier;

/*
 * Functional Interface 에 대한 설명
 * IntSupplier 외에 BooleanSupplier, DoubleSupplier, LongSupplier
 * 
 * Supplier 함수적 인터페이스는 매개값은 없고 리턴값이 있는 getXXX() 메소드를 가지고 있다.
 * 이 메소드들은 호출한 곳으로 데이터를 리턴(공급)하는 역할을 한다.
 */
public class myIntSupplierMain {

	public static void main(String[] args) {

		// Implement IntSupplier make by Anonymous Class
		IntSupplier intS2 = new IntSupplier() {

			@Override
			public int getAsInt() {
				// TODO Auto-generated method stub
				int num = (int) (Math.random() * 6) + 1;
				return num;
			}
			
		};
		
		System.out.println(intS2.getAsInt());
		
		// Usage custom IntSupplier
		IntSupplier intS = new myIntSupplierImp();
		int res = intS.getAsInt();
		System.out.println("Num::" + res);
		
		// Lambda expression
		IntSupplier intS3 = () -> (int) (Math.random() * 6) + 1;
		System.out.println(intS3.getAsInt());

	}


}
