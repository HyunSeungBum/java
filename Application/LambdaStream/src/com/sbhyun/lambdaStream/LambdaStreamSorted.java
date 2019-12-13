package com.sbhyun.lambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import com.sbhyun.data.EmployeeVO;
import com.sbhyun.data.Employees;

public class LambdaStreamSorted {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<EmployeeVO> empList = Arrays.asList(Employees.arrayOfEmps);
		
		Comparator<EmployeeVO> comparator = new Comparator<EmployeeVO>() {

			@Override
			public int compare(EmployeeVO o1, EmployeeVO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
			
		};
		
		Comparator<EmployeeVO> comparator2 = (o1, o2) -> o1.getSalary().compareTo(o2.getSalary());
		
		List<EmployeeVO> employees = empList.stream().sorted(comparator2).collect(Collectors.toList());
		System.out.println(employees);
	}

}
