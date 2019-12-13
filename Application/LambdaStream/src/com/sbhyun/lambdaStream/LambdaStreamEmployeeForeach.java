package com.sbhyun.lambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.sbhyun.data.EmployeeVO;
import com.sbhyun.data.Employees;

/*
 * forEach() Terminal 연산이다. 
 * 이것은 연산을 실행한 후에 스트림 파이프라인은 소비된것으로 여긴다. 더 이상 사용되지 않는다.
 */
public class LambdaStreamEmployeeForeach {

	public static void main(String[] args) {

		List<EmployeeVO> empList = Arrays.asList(Employees.arrayOfEmps);
		
		empList.stream().forEach(new Consumer<EmployeeVO>() {

			@Override
			public void accept(EmployeeVO t) { 
				t.salaryIncrement(10.0);
			}

		});
		 
		// Lambda 표현식
		//empList.stream().forEach(t -> t.salaryIncrement(10.0));
		
		empList.forEach(e -> System.out.println(e.getSalary()));
	}

}
