package com.example.java_project_server.controllers;

import com.example.java_project_server.models.Recruiter;
import com.example.java_project_server.models.School;
import com.example.java_project_server.models.Student;
import com.example.java_project_server.models.User;
import com.example.java_project_server.repositories.RecruiterRepository;
import com.example.java_project_server.repositories.SchoolRepository;
import com.example.java_project_server.repositories.StudentRepository;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class RecruiterController {
  @Autowired
  RecruiterRepository repository;
  
  @Autowired
  StudentRepository studentrep;
  
  @Autowired
  SchoolRepository schoolrep;
  // Create recruiter 
  
  @PostMapping("/api/recruiter")
  public Recruiter createRecruiter(@RequestBody Recruiter recruiter) {
	 Iterable<Recruiter> s1 = repository.findAll(); 
	 int found=0;
	 for (Recruiter student2 : s1) {
		 if(student2.getUsername().equalsIgnoreCase(recruiter.getUsername()))
		 {
			 System.out.println("Found"+found);
			 found=1;
			 return null;
		 }
	}
	 if(found == 0)
	 {
		 System.out.println("NOt Found"+found);
		 return repository.save(recruiter);
	 }
	 
	 
      return null;
  }
  
  // Find recruiter
  
  @GetMapping("/api/recruiter")
  public List<Recruiter> findAllRecruiter() {
      return (List<Recruiter>) repository.findAll();
  }
  
  @GetMapping("/api/school/{id}/findRecruiter")
  public Recruiter findSchoolByRecruiter(@PathVariable("id") int schoolid) {
	  	
	  School s1 =  schoolrep.findById(schoolid).get();
	  Recruiter r1 = s1.getRecruiters();
	  
      return r1;

  }
  
  
 
  @GetMapping("/api/recruiter/{recruiterId}")
  public Recruiter findRecruiterById(@PathVariable("recruiterId") String recruiterId) {
      int id;
      try {
          id = Integer.parseInt(recruiterId);
      }catch (Exception e){
          return null;
      }
      if (repository.findById(id).isPresent())
          return repository.findById(id).get();
      return null;
  }

  // Update recruiter


  @PutMapping("/api/recruiter/{recruiterId}")
  public Recruiter updateRecruiter(@PathVariable("recruiterId") String recruiterId, @RequestBody Recruiter recruiter) {
      int id;
      try {
          id = Integer.parseInt(recruiterId);
      }catch (Exception e){
          return null;
      }
      if (repository.findById(id).isPresent()) {
    	  Recruiter recruiter1 = repository.findById(id).get();
    	  recruiter1.setUser(recruiter);
    	  recruiter1.setRecruiter(recruiter);
          return repository.save(recruiter1);
      }
      return null;
  }

  // Delete recruiter
  @DeleteMapping("/api/recruiter/{recruiterId}")
  public void deleteRecruiter(@PathVariable("recruiterId") int recruiterId) {
	  repository.deleteById(recruiterId);
  }
  
 // find recruiters that follow student 
  
  @GetMapping("/api/recruiter/follows/{id}")
  public List<Student> findStudentFollows(@PathVariable("id") int studentid) {
	  Recruiter s1 = repository.findById(studentid).get();
	  System.out.println("Student : " +  s1.getFirstName());	  
	  System.out.println("Student : " + s1.getId());
	  System.out.println("Student : " +  s1.getFollowedStudents());
	  return s1.getFollowedStudents();
  }
  
  

 
}
