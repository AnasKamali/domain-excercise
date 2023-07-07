package com.sapient.firstkafka.service;

import java.util.List;

import com.sapient.firstkafka.entity.Employee;
import com.sapient.firstkafka.exception.EmployeeNotExistException;

public interface EmployeeService {
	
	public List<Employee> getAllEmployee();

	public Employee createEmployee(Employee employee);

	public Employee getEmployee(int employeeId) throws EmployeeNotExistException;

	public void deleteEmployee(int employeeId);

}
