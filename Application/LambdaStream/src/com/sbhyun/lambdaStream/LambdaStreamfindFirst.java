package com.sbhyun.lambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.sbhyun.data.EmployeeRepository;
import com.sbhyun.data.EmployeeVO;
import com.sbhyun.data.Employees;

/*
 * findFirst() 는 스트림에서 첫번째 엔트리에 대한 Optional 을 리턴 한다. 당연히 Optional 은 empty 일 수 있다.
 */
public class LambdaStreamfindFirst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] empIds = {1,2,3,4};
		
		// 불변 리스트로 변환.
		List<EmployeeVO> empList = Arrays.asList(Employees.arrayOfEmps);
	
		EmployeeRepository employeeRepository = new EmployeeRepository(empList);
		
		EmployeeVO empVO = Stream.of(empIds)
								.map(employeeRepository::findById)
								.filter(e -> e != null)
								.filter(e -> e.getSalary() > 100000)
								.findFirst().orElse(null);
		System.out.println(empVO);
	}

}
