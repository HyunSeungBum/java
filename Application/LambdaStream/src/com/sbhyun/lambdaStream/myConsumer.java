package com.sbhyun.lambdaStream;

import java.util.function.Consumer;

public class myConsumer implements Consumer<Integer> {

	@Override
	public void accept(Integer t) {
		// TODO Auto-generated method stub
		System.out.println("Consumer anonymous class value::" + t);
	}

}
