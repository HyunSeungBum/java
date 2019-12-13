package com.sbhyun.functionalinterfaces;

import java.util.function.BinaryOperator;

/*
 * 동일한 타입의 인자 2개와 인자와 같은 타입의 리턴타입을 가진다.
 */
public class myBinaryOperatorImp implements BinaryOperator<String> {

	@Override
	public String apply(String t, String u) {
		// TODO Auto-generated method stub
		return t + u;
	}

}
