package com.sapient.firstkafka.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.firstkafka.entity.Employee;
import com.sapient.firstkafka.exception.EmployeeNotExistException;
import com.sapient.firstkafka.service.EmployeeService;

@RestController
public class EmployeeController {
	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		logger.info("getting all Employee");
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployee());
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int employeeId) throws EmployeeNotExistException {
		logger.info("getting Employee by id:{}",employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee(employeeId));
	}

	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		logger.info("creating {}",employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));
	}

	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") int employeeId) {
		logger.info("deleting Employee by id:{}",employeeId);
		employeeService.deleteEmployee(employeeId);
	}
}
