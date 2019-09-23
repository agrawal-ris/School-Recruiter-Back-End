package com.example.java_project_server.repositories;
import com.example.java_project_server.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("SELECT user FROM User user WHERE user.username=:username AND user.password=:password")
    public User findUserByCredentials(@Param("username") String u,
                                        @Param("password") String p);

    @Query("SELECT user from User user WHERE user.username=:username")
    public User findUserByUsername(@Param("username") String u);
	    


}
