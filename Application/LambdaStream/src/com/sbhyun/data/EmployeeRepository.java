package com.sbhyun.data;

import java.util.List;

public class EmployeeRepository {
	private List<EmployeeVO> empList;
	
	public EmployeeRepository(List<EmployeeVO> empList) {
		this.empList = empList;
	}
	
	public EmployeeVO findById(Integer id) {
		for (EmployeeVO emp : empList) {
			if (emp.getId() == id) {
				return emp;
			}
		}

		return null;
	}
}
