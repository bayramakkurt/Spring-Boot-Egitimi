package com.HBA.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOCourse;
import com.HBA.dto.DTOStudent;
import com.HBA.dto.DTOStudentIU;
import com.HBA.entities.Course;
import com.HBA.entities.Student;
import com.HBA.repository.StudentRepository;
import com.HBA.services.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public DTOStudent saveStudent(DTOStudentIU dtoStudentIU) {
		DTOStudent response = new DTOStudent();
		Student student = new Student();
		BeanUtils.copyProperties(dtoStudentIU, student);  //Burada dtoStudentIU verilerini student değişkenine kopyaladık.
		
		Student dbStudent = studentRepository.save(student);
		BeanUtils.copyProperties(dbStudent, response);
		
		return response;
	}
	
	@Override
	public List<DTOStudent> getAllStudents(){
		List<DTOStudent> dtoList = new ArrayList<>();
		
		List<Student> studentList = studentRepository.findAll();
		
		for (Student student : studentList) {
			DTOStudent dto = new DTOStudent();
			BeanUtils.copyProperties(student, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public DTOStudent getStudentById(Integer id) {
		DTOStudent dtoStudent = new DTOStudent();
		Optional<Student> optional = studentRepository.findById(id);
		
		if (optional.isEmpty()) {
			return null;
		}
		Student dbStudent = optional.get();
		BeanUtils.copyProperties(dbStudent, dtoStudent);
		
		if (dbStudent.getCourses() != null && !dbStudent.getCourses().isEmpty()) {
			for (Course course : dbStudent.getCourses()) {
				DTOCourse dtoCourse = new DTOCourse();
				BeanUtils.copyProperties(course, dtoCourse);
				dtoStudent.getCourses().add(dtoCourse);
			}
		}
		return dtoStudent;
	}

	@Override
	public void deleteStudent(Integer id) {
		Optional<Student> optional = studentRepository.findById(id); //Burda DTO kullanamayız çünkü DB de silinecek veriyi DTO ile alırsak silemeyiz illa ana Entity olacak.
		if (optional.isPresent()) {
			studentRepository.delete(optional.get());
		}
	}

	@Override
	public DTOStudent updateStudent(Integer id, DTOStudentIU updateStudentIU) {
		DTOStudent dto = new DTOStudent();
		Optional<Student> optional= studentRepository.findById(id);
		if (optional.isPresent()) {
			Student dbStudent = optional.get();
			
			dbStudent.setFirstName(updateStudentIU.getFirstName());
			dbStudent.setLastName(updateStudentIU.getLastName());
			dbStudent.setBirthOfDate(updateStudentIU.getBirthOfDate());
			
			Student updatedStudent = studentRepository.save(dbStudent);
			
			BeanUtils.copyProperties(updatedStudent, dto);
			return dto;
		}
		return null;
		
//		Student tempStudent = getStudentById(id);
//		if (tempStudent != null) {
//			tempStudent.setFirstName(updateStudent.getFirstName());
//			tempStudent.setLastName(updateStudent.getLastName());
//			tempStudent.setBirthOfDate(updateStudent.getBirthOfDate());
//			
//			return studentRepository.save(tempStudent); //Buradaki amaç verileri güncellenmiş olan yeni Student örneğini yeniden DB kaydettirmek sonuç olarakta üzerine yazmış olacak.
//		}
//		return null;
	}
}
