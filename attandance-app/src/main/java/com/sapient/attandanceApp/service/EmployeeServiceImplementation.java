package com.sapient.attandanceApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sapient.attandanceApp.entity.Employee;
import com.sapient.attandanceApp.exception.EmployeeNotExistException;
import com.sapient.attandanceApp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	EmployeeRepository employeeRepository;

	public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllEmployee() {

		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployee(int employeeId) throws EmployeeNotExistException {
		Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
		if (employeeOptional.isPresent()) {
			return employeeOptional.get();
		} else {
			throw new EmployeeNotExistException();
		}
	}

	@Override
	public void deleteEmployee(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}
