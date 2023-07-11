package com.sapient.attandanceApp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sapient.attandanceApp.entity.Employee;
import com.sapient.attandanceApp.exception.EmployeeNotExistException;
import com.sapient.attandanceApp.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplementationTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImplementation employeeServiceImplementation;

	@Test
	void getAllEmployee() {
		ArrayList<Employee> arrayList = new ArrayList<Employee>();
		when(employeeRepository.findAll()).thenReturn(arrayList);
		List<Employee> allEmployee = employeeServiceImplementation.getAllEmployee();
		assertEquals(arrayList.size(), allEmployee.size());
	}

	@Test
	void createEmployee() {
		Employee employee = new Employee();
		when(employeeRepository.save(ArgumentMatchers.any())).thenReturn(employee);
		Employee employee2 = employeeServiceImplementation.createEmployee(employee);
		assertEquals(Employee.class, employee2.getClass());
	}

	@Test
	void createEmployeeFail() {
		when(employeeRepository.save(ArgumentMatchers.any())).thenThrow(IllegalArgumentException.class);
		assertThrows(IllegalArgumentException.class, () -> employeeServiceImplementation.createEmployee(null));
	}

	@Test
	void getEmployee() throws EmployeeNotExistException {
		when(employeeRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Employee()));
		Employee employee = employeeServiceImplementation.getEmployee(1);
		assertEquals(Employee.class, employee.getClass());
	}

	@Test
	void getEmployeeFailNotExist()  {
		when(employeeRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
		assertThrows(EmployeeNotExistException.class, () -> employeeServiceImplementation.getEmployee(1));
	}

	@Test
	void deleteEmployee() {

		doNothing().when(employeeRepository).deleteById(ArgumentMatchers.anyInt());
		employeeServiceImplementation.deleteEmployee(1);
		verify(employeeRepository, times(1)).deleteById(ArgumentMatchers.anyInt());
		;
	}

}
