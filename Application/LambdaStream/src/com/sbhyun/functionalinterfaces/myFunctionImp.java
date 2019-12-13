package com.sbhyun.functionalinterfaces;

import java.util.function.Function;

public class myFunctionImp implements Function<Integer, Double> {

	@Override
	public Double apply(Integer t) {
		// TODO Auto-generated method stub
		return t/2.0;
	}

}
