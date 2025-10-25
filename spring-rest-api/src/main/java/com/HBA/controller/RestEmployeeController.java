package com.HBA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.model.Employee;
import com.HBA.model.UpdateEmployeeRequest;
import com.HBA.services.EmployeeService;

@RestController //Controller sınıfından olduğunu belirtir
@RequestMapping("/rest/api/employee") //Bütün pathlerin başına bu path ekler
public class RestEmployeeController {

	@Autowired //Daha önce oluşturulan service context içinden alınır. (Dependency İnjection)
	private EmployeeService employeeService;
	
	@GetMapping(path = "/list") //Altındaki fonksiyonunun bu path karşılayacak fonksiyon olduğunu belirtir.
	public List<Employee> getAllEmployeeList(){
		return employeeService.getAllEmployeeList();
		
	}
	
	@GetMapping(path = "/list/{id}") //Kullanıcıdan değişken almak için {} içine değişken yazılır.
	public Employee getEmployeeById(@PathVariable(name = "id", required = true) String id) {  //Pathvariable ile URL içinde değişken alınır alınan değişken adı name, required ise zorunlu olup olmadığını belirtir. String id ise fonksiyona gönderilir.
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping(path = "/with-params")
	public List<Employee> getEmployeeWithParams(@RequestParam(name = "firstName", required = false) String firstName,
										  @RequestParam(name = "lastName", required = false) String lastName) {
		return employeeService.getEmployeeWithParams(firstName, lastName);
	}
	
	@PostMapping(path = "/save-employee")
	public Employee saveEmployee(@RequestBody Employee newEmployee) {
		return employeeService.saveEmployee(newEmployee);
	}
	
	@DeleteMapping(path = "/delete-employee/{id}")
	public boolean deleteEmployee(@PathVariable(name = "id", required = true) String id) {
		return employeeService.deleteEmployee(id);
	}
	
	@PutMapping(path = "/update-employee/{id}")
	public Employee updateEmployee(@PathVariable(name = "id") String id, 
								   @RequestBody UpdateEmployeeRequest updateEmployee) {
		return employeeService.updatEmployee(id, updateEmployee);
	}
}
