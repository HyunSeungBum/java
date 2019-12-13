package com.sbhyun.functionalinterfaces;

import java.util.function.Consumer;

/*
 * Consumer 는 인자로 값을 받고 리턴값이 없다. Generic 타입이다.
 * 리턴값이 없이 오로지 소모만하기에 소비자(Consumer) 라고 한다.
 */
public class myConsumerMain {

	public static void main(String[] args) {
		// Consumer 인터페이스를 구현한 클래스를 이용.
		Consumer<String> c1 = new myConsumerImp();
		c1.accept("Cat!!");
		
		// Anonymous 클래스 이용.
		Consumer<String> c2 = new Consumer<String>() {

			@Override
			public void accept(String t) {
				// TODO Auto-generated method stub
				System.out.println("Hello World " + t);
			}
			
		};
		c2.accept("Dog!!");
		
		// Lambda 표현식
		Consumer<String> c3 = (t) -> System.out.println("Hello World " + t);
		c3.accept("Bird!!");
	}

}
