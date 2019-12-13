package com.sbhyun.functionalinterfaces;

import java.util.function.IntSupplier;

/*
 * IntSupplier 는 Function Interface 이다.
 * Lambda 표현식으로 바로 구현이 가능하다.
 */
public class myIntSupplierImp implements IntSupplier {

	@Override
	public int getAsInt() {
		// TODO Auto-generated method stub
		int num = (int) (Math.random() * 6) + 1;
		return num;
	}

}
