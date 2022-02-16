package ac.in.cutm.erp.std_input.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.in.cutm.erp.std_input.exception.ResourceNotFoundException;
import ac.in.cutm.erp.std_input.model.Students;
import ac.in.cutm.erp.std_input.repository.StudentsRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class StudentsController {

	@Autowired
	private StudentsRepository studentsRepository;
	
	// get all Students
	@GetMapping("/students")
	public List<Students> getAllStudents(){
		return studentsRepository.findAll();
	}		
	
	// create Students rest api
	@PostMapping("/students")
	public Students createStudents(@RequestBody Students students) {
		return studentsRepository.save(students);
	}
	
	// get Students by id rest api
	@GetMapping("/students/{id}")
	public ResponseEntity<Students> getStudentsById(@PathVariable Long id) {
		Students students = studentsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
		return ResponseEntity.ok(students);
	}
	
	// update Students rest api
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Students> updateEmployee(@PathVariable Long id, @RequestBody Students studentsDetails){
		Students students = studentsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student not exist with id :" + id));
		
		students.setSource(studentsDetails.getSource());
		students.setFirstName(studentsDetails.getFirstName());
		students.setLastName(studentsDetails.getLastName());
		students.setGender(studentsDetails.getGender());
		students.setEmailId(studentsDetails.getEmailId());
		students.setPhoneNumber(studentsDetails.getPhoneNumber());
		students.setPermanentAddress(studentsDetails.getPermanentAddress());
		students.setPresentAddress(studentsDetails.getPresentAddress());
		students.setAge(studentsDetails.getAge());
		students.setDateOfBirth(studentsDetails.getDateOfBirth());
		students.setMartialStatus(studentsDetails.getMartialStatus());
		students.setQualification(studentsDetails.getQualification());
		
		
		Students updatedStudents = studentsRepository.save(students);
		return ResponseEntity.ok(updatedStudents);
	}
	
	// delete Students rest api
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudents(@PathVariable Long id){
		Students students = studentsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("students not exist with id :" + id));
		
		studentsRepository.delete(students);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
