package com.sbhyun.lambdaStream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * flatMap 은 Stream<List<String>> 과 같은 복잡한 데이터 구조를 다룰 수 있다.
 * 여기서는 Stream<List<string>> 를 단순한 Stream<String> 로 바꾼다.
 */
public class LambdaStreamflatMap {

	public static void main(String[] args) {
		List<List<String>> namesNested = Arrays.asList(
				Arrays.asList("Jeff", "Bezos"), 
				Arrays.asList("Bill", "Gates"),
				Arrays.asList("Mark", "Zuckerberg"));
		
		System.out.println("Arrays.asList: " + namesNested);
		
		List<String> namesFlatStream = namesNested.stream()
												.flatMap(Collection::stream)
												.collect(Collectors.toList());
		System.out.println("List: " + namesFlatStream);
		
	}
}
