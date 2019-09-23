package com.example.java_project_server.controllers;

import com.example.java_project_server.models.Recruiter;
import com.example.java_project_server.models.School;
import com.example.java_project_server.models.Student;
import com.example.java_project_server.repositories.RecruiterRepository;
import com.example.java_project_server.repositories.SchoolRepository;
import com.example.java_project_server.repositories.StudentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class SchoolController {
  @Autowired
  SchoolRepository repository;
  
  @Autowired
  StudentRepository  studentrepository;
  
  @Autowired
  RecruiterRepository recruiterRep;
  
  // Create school
  
  @PostMapping("/api/school")
  public School createSchool(@RequestBody School school) {
      return repository.save(school);
  }
  
 // Create school wrt Recruiter
  
  @PostMapping("/api/school/add/{RecruiterID}")
  public School createSchoolByRecruiter(@PathVariable("RecruiterID") int rid, @RequestBody School school) {
	  System.out.println("Recruiss" +  rid);

	  Recruiter r = recruiterRep.findById(rid).get();

	  school.setRecruiters(r);
	  System.out.println("Second Recruiter ");
      return repository.save(school);
  }
  
  
  // Find school
  
  @GetMapping("/api/school")
  public List<School> findAllSchool() {
      return (List<School>) repository.findAll();
  }
  
  @GetMapping("/api/school/likes/{id}")
  public List<Student> findSchoolLikes(@PathVariable("id") int schoolid) {
	  School s1 = repository.findById(schoolid).get();
	  return s1.getEnrolledStudents(); 
  }
  
 
  
  // 
 
  @GetMapping("/api/school/{schoolId}")
  public School findSchoolById(@PathVariable("schoolId") String schoolId) {
      int id;
      try {
          id = Integer.parseInt(schoolId);
      }catch (Exception e){
          return null;
      }
      if (repository.findById(id).isPresent())
          return repository.findById(id).get();
      return null;
  }

  // Update school


  @PutMapping("/api/school/{schoolId}")
  public School updateSchool(@PathVariable("schoolId") String recruiterId, @RequestBody School school) {
      int id;
      try {
          id = Integer.parseInt(recruiterId);
      }catch (Exception e){
          return null;
      }
      if (repository.findById(id).isPresent()) {
    	  School school1 = repository.findById(id).get();
    	  school1.setSchoolDetails(school);
          return repository.save(school1);
      }
      return null;
  }

  // Delete school
  @DeleteMapping("/api/school/{schoolId}")
  public void deleteSchool(@PathVariable("schoolId") int schoolId) {
		
	  repository.deleteById(schoolId);
  }
}

