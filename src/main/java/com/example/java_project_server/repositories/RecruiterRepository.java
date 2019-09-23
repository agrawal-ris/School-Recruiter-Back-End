package com.example.java_project_server.repositories;
import org.springframework.data.repository.*;
import com.example.java_project_server.models.Recruiter;

public interface RecruiterRepository extends CrudRepository<Recruiter, Integer> {
	
}