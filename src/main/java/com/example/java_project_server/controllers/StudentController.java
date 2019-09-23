package com.example.java_project_server.controllers;
import com.example.java_project_server.models.Recruiter;
import com.example.java_project_server.models.Student;
import com.example.java_project_server.models.School;
import com.example.java_project_server.repositories.RecruiterRepository;
import com.example.java_project_server.repositories.SchoolRepository;
import com.example.java_project_server.repositories.StudentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class StudentController {
  @Autowired
  StudentRepository repository;
  
  @Autowired
  SchoolRepository schrep;
  
  @Autowired
  RecruiterRepository recruiterRepository;
  // Create student
  
  @PostMapping("/api/student")
  public Student createStudent(@RequestBody Student student) {
	 Iterable<Student> s1 = repository.findAll(); 
	 int found=0;
	 for (Student student2 : s1) {
		 if(student2.getUsername().equalsIgnoreCase(student.getUsername()))
		 {
			 System.out.println("Found"+found);
			 found=1;
			 return null;
		 }
	}
	 if(found == 0)
	 {
		 System.out.println("NOt Found"+found);
		 return repository.save(student);
	 }
	 
	 
      return null;
  }
  // Find student
  
  @GetMapping("/api/student")
  public List<Student> findAllStudent() {
      return (List<Student>) repository.findAll();
  }

  @GetMapping("/api/student/likes/{id}")
  public List<School> findStudentLikes(@PathVariable("id") int sid) {
	  Student s1 = repository.findById(sid).get();
	  	return s1.getEnrolledSchool(); 
  }

  @GetMapping("/api/student/follows/{id}")
  public List<Recruiter> findStudentsFollows(@PathVariable("id") int studentid) {
	  Student s1 = repository.findById(studentid).get();
	  	  return s1.getFollowedRecruiter(); 
  }
  
  @GetMapping("/api/student/{studentId}")
  public Student findStudentById(@PathVariable("studentId") String studentId) {
      int id;
      try {
          id = Integer.parseInt(studentId);
      }catch (Exception e){
          return null;
      }
      if (repository.findById(id).isPresent())
          return repository.findById(id).get();
      return null;
  }
  // Update student
  @PutMapping("/api/student/{studentId}")
  public Student updateStudent(@PathVariable("studentId") String studentId, @RequestBody Student student) {
      int id;
      try {
          id = Integer.parseInt(studentId);
      }catch (Exception e){
          return null;
      }
      if (repository.findById(id).isPresent()) {
    	  Student student1 = repository.findById(id).get();
    	  student1.setUser(student);
    	  student1.setStudent(student);
          return repository.save(student1);
      }
      return null;
  }

  // Delete student
  @DeleteMapping("/api/student/{studentId}")
  public void deleteStudent(@PathVariable("studentId") int studentId) {
	  repository.deleteById(studentId);
  }

  //AddLike
  @PostMapping("/api/student/{sid}/school/{scid}")
  public List<Student> AddLikes(@PathVariable("sid") int sid,@PathVariable("scid") int scid ) {
	  System.out.println("Rishabh");
	  School sch1 = schrep.findById(scid).get();
	  Student s1 = repository.findById(sid).get();
	  System.out.println("School = " + sch1.getTitle());
      System.out.println("Student = " + s1.getFirstName());  
	  sch1.enrollStudent(s1);
	  schrep.save(sch1);
	  return sch1.getEnrolledStudents();
  }
  
  // Remove Likes 
  
  @DeleteMapping("/api/student/{sid}/likes/school/{scid}")
  public void RemoveLikes(@PathVariable("sid") int sid,@PathVariable("scid") int scid ) {
	  System.out.println("Rishabh");
	  School sch1 = schrep.findById(scid).get();
	  Student s1 = repository.findById(sid).get();
	  System.out.println("School = " + sch1.getTitle());
	  System.out.println("Student = " + s1.getFirstName());
	  sch1.deenrollStudent(s1);
	  s1.deenrollSchool(sch1);
	  schrep.save(sch1);
	  repository.save(s1);
  }
  
  // Add Follows
  
  @PostMapping("/api/student/{sid}/recruiter/{rid}")
  public List<Recruiter> AddFollow(@PathVariable("sid") int sid,@PathVariable("rid") int rid ) {
	  System.out.println("Rishabh");
	  Recruiter repR = recruiterRepository.findById(rid).get();
	  Student s1 = repository.findById(sid).get();
      System.out.println("Student = " + s1.getFirstName());
	  s1.followRecruiter(repR);
	  repository.save(s1);
	  return s1.getFollowedRecruiter();  
  }
  
  // Remove Follow
  
  @DeleteMapping("/api/student/{sid}/follows/recruiter/{rid}")
  public List<Recruiter> RemoveFollow(@PathVariable("sid") int sid,@PathVariable("rid") int rid ) {
	  System.out.println("Rishabh");
	  Recruiter repR = recruiterRepository.findById(rid).get();
	  Student s1 = repository.findById(sid).get();
	  System.out.println("Student = " + s1.getFirstName());
	  s1.defollowRecruiter(repR);
	  repR.defollowStudent(s1);
	  repository.save(s1);
	  recruiterRepository.save(repR);
	  return s1.getFollowedRecruiter();
  }
}
