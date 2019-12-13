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
public class LambdaStreamMap {

	public static void main(String[] args) {

		// 불변 리스트로 변환.
		List<EmployeeVO> empList = Arrays.asList(Employees.arrayOfEmps);
	
		EmployeeRepository employeeRepository = new EmployeeRepository(empList);
		
		// Map 에서 쓰일 Input 데이터. Integer 타입이다.
		Integer[] empIds = { 1, 2, 3 };
		for(Integer i: empIds) {
			System.out.println(i);
		}
		
		// Integer 타입의 스트림 생성.
		Stream<Integer> empIdsSt = Stream.of(empIds);
		/*
		 * Stream<Employee> employeeMap = empIdsSt.map(new Function<Integer, Employee>()
		 * {
		 * 
		 * @Override public Employee apply(Integer id) { // TODO Auto-generated method
		 * stub return employeeRepository.findById(id); }
		 * 
		 * });
		 */
		
		// Lambda 표현식.
		// Integer 스트림에서 Integer id 값을 employeeRepository.findById 에 전달해 Employee 스트림을 생성한다.
		Stream<EmployeeVO> employeeMap = empIdsSt.map(id -> employeeRepository.findById(id));
		/* Debug
		 * Stream<Employee> employeeMap = empIdsSt.map(id -> { System.out.println(id);
		 * return employeeRepository.findById(id); });
		 */
		
		// Lambda Reference
		//Stream<Employee> employeeMap2 = empIdsSt.map(employeeRepository::findById);
		
		// Consumer 이기 때문에 리턴값이 없다.
		employeeMap.forEach(e -> System.out.println(e));
		//employeeMap2.forEach(System.out::println);
	}
}
