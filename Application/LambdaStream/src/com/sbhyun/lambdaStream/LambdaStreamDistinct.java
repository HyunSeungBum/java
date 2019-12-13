package com.sbhyun.lambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaStreamDistinct {

	public static void main(String[] args) {
		
		List<Integer> intList = Arrays.asList(2,4,5,2,1,5,3,1);
		
		List<Integer> distinctIntList = intList.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println(distinctIntList);
	}
}
