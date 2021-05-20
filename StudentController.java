package com.javatpoint.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.globalException.NotFoundException;
import com.javatpoint.model.Student;
import com.javatpoint.repository.StudentRepository;
import com.javatpoint.service.StudentService;
//creating RestController
@RestController
public class StudentController 
{
//autowired the StudentService class
@Autowired
StudentService studentService;

@Autowired
StudentRepository studentRepository;
//creating a get mapping that retrieves all the students detail from the database 
@GetMapping("/student")
private List<Student> getAllStudent() 
{
return studentService.getAllStudent();
}
//creating a get mapping that retrieves the detail of a specific student


@GetMapping("/student/{id}")
private ResponseEntity<Student> getStudent(@PathVariable("id") int id)  throws NotFoundException 
{
	Student student = studentRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Student not found for this id :: " + id));
return ResponseEntity.ok().body(student);
}

//creating a delete mapping that deletes a specific student
	@DeleteMapping("/student/{id}")
	private Map<String, Boolean> deleteStudent(@PathVariable("id") int id) throws NotFoundException {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Student not found for this id :: " + id));

		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


//creating post mapping that post the student detail in the database
@PostMapping("/student")
private int saveStudent(@RequestBody Student student) 
{
studentService.saveOrUpdate(student);
return student.getId();
}


@PatchMapping("/student/{id}")
private Student updateStudent(@PathVariable("id") int id, @RequestBody Student student) 
{
return studentService.getStudentById(id);
}
}
