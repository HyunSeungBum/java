package com.sbhyun.stream.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import com.sbhyun.data.EmployeeVO;
import com.sbhyun.data.Employees;

public class LambdaStreamInit {

	private static ArrayList<Integer> myList;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LambdaStreamInit st = new LambdaStreamInit();
		st.init1();
		st.init2();
	}
	
	public void init1() {
		myList = new ArrayList<Integer>();	
		for (int i=0; i< 10; i++) {
			myList.add(i);
		}
		
		// 초기화1
		Stream<ArrayList<Integer>> st1 = Stream.of(myList);
		
		// 초기화2
		Stream<Integer> st2 = myList.stream();	
		
		// 초기화3
		Builder<Integer> t3 = Stream.builder();
		//t3.accept(myList.get(0));
		//t3.accept(myList.get(1));
		for(Integer i: myList) {
			t3.accept(myList.get(i));
		}
		Stream<Integer> st3 = t3.build();
	}
	
	public void init2() {
		// 초기화1
		Stream<EmployeeVO> st1 = Stream.of(Employees.arrayOfEmps);
		
		// 초기화2
		List<EmployeeVO> empList = Arrays.asList(Employees.arrayOfEmps);
		Stream<EmployeeVO> st2 = empList.stream();
		
		// 초기화3
		Builder<EmployeeVO> empStreamBuilder = Stream.builder();
//		empStreamBuilder.accept(Employees.arrayOfEmps[0]);
//		empStreamBuilder.accept(Employees.arrayOfEmps[1]);
//		empStreamBuilder.accept(Employees.arrayOfEmps[2]);
		for(EmployeeVO empVO: Employees.arrayOfEmps) {
			empStreamBuilder.accept(empVO);
		}
		Stream<EmployeeVO> empStream = empStreamBuilder.build();
		
	}

}
