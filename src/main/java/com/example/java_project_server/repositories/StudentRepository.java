package com.example.java_project_server.repositories;
import com.example.java_project_server.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends CrudRepository<Student, Integer> {
	

    @Query("SELECT user from User user WHERE user.id=:sId")
    public Student findByUsername(@Param("sId") int sId);
}
