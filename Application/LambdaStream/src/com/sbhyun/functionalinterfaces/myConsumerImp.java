package com.sbhyun.functionalinterfaces;

import java.util.function.Consumer;

/*
 * Consumer 는 인자로 값을 받고 리턴값이 없다. Generic 타입이다.
 * 리턴값이 없이 오로지 소모만하기에 소비자(Consumer) 라고 한다.
 */
public class myConsumerImp implements Consumer<String> {

	@Override
	public void accept(String t) {
		// TODO Auto-generated method stub
		System.out.println("Hello World " + t);
	}

}
