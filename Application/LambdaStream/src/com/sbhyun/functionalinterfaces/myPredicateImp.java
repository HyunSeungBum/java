package com.sbhyun.functionalinterfaces;

import java.util.function.Predicate;

/*
 * 하나의 인자와 리턴타입을 가진다. Function과 비슷해보이지만 리턴타입을 지정하는 타입파라미터가 안보인다. 
 * 반환타입은 boolean 타입으로 고정되어있다. Function<T, Boolean>형태라고 보면된다.
 */
public class myPredicateImp implements Predicate<String> {

	@Override
	public boolean test(String t) {
		// TODO Auto-generated method stub
		return t.isEmpty();
	}

}
