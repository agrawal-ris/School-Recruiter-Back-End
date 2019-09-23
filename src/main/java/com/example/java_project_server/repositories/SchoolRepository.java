package com.example.java_project_server.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.java_project_server.models.School;
import com.example.java_project_server.models.Student;

public interface SchoolRepository extends CrudRepository<School, Integer> { 
    
	
}