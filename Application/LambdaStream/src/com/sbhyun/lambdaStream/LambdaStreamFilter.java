package com.sbhyun.lambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.sbhyun.data.EmployeeVO;
import com.sbhyun.data.Employees;
import com.sbhyun.data.EmployeeRepository;

/*
 * map() 은 원래 스트림의 각 요소들에 함수를 적용한 후에 새로운 스트림을 생성한다. 새로운 스트림은 다른 타입일 수 있다.
 * 다음 예제는 Integer 스트림을 Employees 스트림으로 변환한다.
 */
public class LambdaStreamFilter {

	public static <T> void main(String[] args) {

		// 불변 리스트로 변환.
		List<EmployeeVO> empList = Arrays.asList(Employees.arrayOfEmps);
	
		EmployeeRepository employeeRepository = new EmployeeRepository(empList);
		
		// Map 에서 쓰일 Input 데이터. Integer 타입이다.
		Integer[] empIds = { 1, 2, 3 };
		
		// Integer 타입의 스트림 생성.
		Stream<Integer> empIdsSt = Stream.of(empIds);

		// Anonymous 를 이용한 구현.
		/*
		 * Stream<Employee> employeeFilter =
		 * empIdsSt.map(employeeRepository::findById).filter(new Predicate<Employee>() {
		 * 
		 * @Override public boolean test(Employee t) { // TODO Auto-generated method
		 * stub return t.getSalary() > 200000; }
		 * 
		 * });
		 */
		
		// Lambda 표현식.
		Stream<EmployeeVO> employeeFilter = empIdsSt
				.map(id -> employeeRepository.findById(id))
				.filter(e -> e != null)
				.filter(e -> e.getSalary() > 200000);
		
		employeeFilter.forEach(System.out::println);
		
	}
}
