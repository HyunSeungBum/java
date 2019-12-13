package com.sbhyun.lambdaStream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import com.sbhyun.data.EmployeeVO;
import com.sbhyun.data.Employees;

public class LambdaStreamMinMax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<EmployeeVO> empList = Arrays.asList(Employees.arrayOfEmps);
		System.out.println(empList);
		
		EmployeeVO firstEmp = empList.stream()
				//.min((o1, o2) -> o1.getId() - o2.getId())
				.min(Comparator.comparing(EmployeeVO::getId))
				.orElseThrow(NoSuchElementException::new);
		
		System.out.println(firstEmp);
		
		EmployeeVO maxSalEmp = empList.stream()
				.max(Comparator.comparing(EmployeeVO::getSalary))
				.orElseThrow(NoSuchElementException::new);
		
		System.out.println(maxSalEmp);
	}

}
