package com.HBA.controller.impl;

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

import com.HBA.controller.IStudentController;
import com.HBA.dto.DTOStudent;
import com.HBA.dto.DTOStudentIU;
import com.HBA.entities.Student;
import com.HBA.services.IStudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/student")
public class StudentControllerImpl implements IStudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@PostMapping(path = "/save")
	@Override
	public DTOStudent saveStudent(@RequestBody @Valid DTOStudentIU dtoStudentIU) { //Burdaki Valid anatasyonu alınan parametrenin kısıtlama içerdiğini onların aktif olduğunu belirtir.
		return studentService.saveStudent(dtoStudentIU);
	}
	
	@GetMapping(path = "/list")
	@Override
	public List<DTOStudent> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@GetMapping(path = "/list/{id}")
	@Override
	public DTOStudent getStudentById(@PathVariable(name = "id") Integer id) {
		return studentService.getStudentById(id);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteStudent(@PathVariable(name = "id") Integer id) {
		studentService.deleteStudent(id);
	}
	
	@PutMapping(path = "/update/{id}")
	@Override
	public DTOStudent updateStudent(@PathVariable(name = "id") Integer id,@RequestBody DTOStudentIU updateStudentIU) {
		return studentService.updateStudent(id, updateStudentIU);
	}
	
	

}
