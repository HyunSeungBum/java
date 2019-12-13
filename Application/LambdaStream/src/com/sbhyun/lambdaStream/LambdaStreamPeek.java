package com.sbhyun.lambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sbhyun.data.EmployeeVO;
import com.sbhyun.data.Employees;

/*
 * forEach 는 Terminal 연산이라고 한다. 리턴값이 없다. 그래서 모든 연산을 끝내버리기 때문에 Pipeline 으로 연결될 수 없다.
 * peek 는 Intermediate 연산이라고 한다. 또 다른 Stream 을 생성해 리턴 한다. 그러기에 또 다른 연산을 Pipeline 으로 연결될 수 있다. 
 */
public class LambdaStreamPeek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<EmployeeVO> empList = Arrays.asList(Employees.arrayOfEmps);
		System.out.println(empList);
		
		List<EmployeeVO> empList2 = empList.stream()
									.peek(t -> t.salaryIncrement(10.0)) // Intermediate 연산이다.
									.peek(System.out::println)
									.collect(Collectors.toList()); // collect 는 Terminal 연산이다.
		System.out.println(empList2);
	}

}
