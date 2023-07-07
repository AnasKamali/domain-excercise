package com.sapient.attandanceApp.service;

import java.util.List;

import com.sapient.attandanceApp.entity.Employee;
import com.sapient.attandanceApp.exception.EmployeeNotExistException;

public interface EmployeeService {
	
	public List<Employee> getAllEmployee();

	public Employee createEmployee(Employee employee);

	public Employee getEmployee(int employeeId) throws EmployeeNotExistException;

	public void deleteEmployee(int employeeId);

}
