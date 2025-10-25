package com.HBA.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.model.Employee;
import com.HBA.model.UpdateEmployeeRequest;
import com.HBA.repository.EmployeeRepository;

@Service 
public class EmployeeService {
	
	@Autowired //Daha önce oluşturulan DAO bean context içinden alınır
	private EmployeeRepository employeeRepository;
	
	
	public List<Employee> getAllEmployeeList(){
		return employeeRepository.getAllEmployeeList(); //Buradada diğer katmandaki fonksiyonu kullanıyoruz.
		
	}
	
	public Employee getEmployeeById(String id) {
		return employeeRepository.getEmployeeById(id);
	}
	
	public List<Employee> getEmployeeWithParams(String firstName, String lastName){
		return employeeRepository.getEmployeeWithParams(firstName, lastName);
	}
	
	public Employee saveEmployee(Employee newEmployee) {
		return employeeRepository.saveEmployee(newEmployee);
	}
	
	public boolean deleteEmployee(String id) {
		return employeeRepository.deleteEmployee(id);
	}
	
	public Employee updatEmployee(String id, UpdateEmployeeRequest updateEmployee) {
		return employeeRepository.updatEmployee(id, updateEmployee);
	}
}
