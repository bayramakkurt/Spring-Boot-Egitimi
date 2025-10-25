package com.HBA.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.HBA.model.Employee;
import com.HBA.model.UpdateEmployeeRequest;

@Repository
public class EmployeeRepository {

	@Autowired
	private List<Employee> employeeList;
	
	public List<Employee> getAllEmployeeList(){
		
		return employeeList;
	}
	
	public Employee getEmployeeById(String id) {
		Employee findEmployee = null;
		for (Employee employee : employeeList) {
			if (id.equals(employee.getId())) {
				findEmployee = employee;
				break;
			}
		}
		return findEmployee;
	}
	
	public List<Employee> getEmployeeWithParams(String firstName, String lastName){
		List<Employee> employeeWithParams = new ArrayList<>();
		
		if (firstName==null && lastName==null) {
			return employeeList;
		}
		for (Employee employee : employeeList) {
			if (firstName!=null && lastName!=null) {
				if (employee.getFirstName().equalsIgnoreCase(firstName) && employee.getLastName().equalsIgnoreCase(lastName)) {
					employeeWithParams.add(employee);
				}
			}
			if (firstName!=null && lastName==null) {
				if (employee.getFirstName().equalsIgnoreCase(firstName)) {
					employeeWithParams.add(employee);
				}
			}
			if (firstName==null && lastName!=null) {
				if (employee.getLastName().equalsIgnoreCase(lastName)) {
					employeeWithParams.add(employee);
				}
			}
		}
		return employeeWithParams;
	}
	
	public Employee saveEmployee(Employee newEmployee) {
		employeeList.add(newEmployee);
		return newEmployee;
	}
	
	public boolean deleteEmployee(String id) {
		Employee tempEmployee = null;
		for (Employee employee : employeeList) {
			if (id.equals(employee.getId())) {
				tempEmployee = employee;
				break;
			}
		}
		if (tempEmployee== null) {
			return false;
		}
		employeeList.remove(tempEmployee);
		return true;
	}
	
	public Employee findEmployee(String id) {
		Employee findEmployee = null;
		for (Employee employee : employeeList) {
			if (id.equals(employee.getId())) {
				findEmployee = employee;
				break;
			}
		}
		return findEmployee;
	}
	
	public Employee updatEmployee(String id, UpdateEmployeeRequest updateEmployee) {
		Employee findEmployee = findEmployee(id);
		if (findEmployee!= null) {
			deleteEmployee(id);
			
			Employee updatEmployee = new Employee(id, updateEmployee.getFirstName(), updateEmployee.getLastName());
			employeeList.add(updatEmployee);
			
			return updatEmployee;
		}
		return null;
	}
}
