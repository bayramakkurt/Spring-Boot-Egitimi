package com.HBA.controller;

import java.util.List;

import com.HBA.dto.DTOStudent;
import com.HBA.dto.DTOStudentIU;
import com.HBA.entities.Student;

public interface IStudentController {
	
	public DTOStudent saveStudent(DTOStudentIU dtoStudentIU);
	
	public List<DTOStudent> getAllStudents();
	
	public DTOStudent getStudentById(Integer id);
	
	public void deleteStudent(Integer id);
	
	public DTOStudent updateStudent(Integer id, DTOStudentIU updateStudentIU);
}
