package com.sbhyun.lambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sbhyun.data.EmployeeVO;
import com.sbhyun.data.Employees;
import com.sbhyun.data.EmployeeRepository;

/*
 * java.util.stream.Stream 에 collect 메소드에 예제가 잘 나와 있다. 참조하라.
 * Collectors 클래스를 익명으로 작성할 수는 없다. Interface 도 아니며 전부 static 메소들의 집합이다.
 * 많은 예제들을 찾아 익히는 수밖에 없는데, collect 메소드 설명에 왠만한 예제는 잘 나와 있다.
 * collect, Collectors 모두 stream 패키지에 속한다.
 */
public class LambdaStreamCollect {

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
				//.map(employeeRepository::findById)
				.filter(e -> e != null)
				.filter(e -> e.getSalary() > 200000);
		
		List<EmployeeVO> res = employeeFilter.collect(Collectors.toList());
		res.forEach(System.out::println);
		
	}
}
