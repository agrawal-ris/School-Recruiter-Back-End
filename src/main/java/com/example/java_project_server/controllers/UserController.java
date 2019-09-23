package com.example.java_project_server.controllers;

import com.example.java_project_server.models.User;
import com.example.java_project_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class UserController {

    @Autowired
    UserRepository repository;
    
    // Create Users

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    // find users
    
    @GetMapping("/api/users/{userId}")
    public User findUserById(@PathVariable("userId") String userId) {
        int id;
        try {	
            id = Integer.parseInt(userId);
        }catch (Exception e){
            return null;
        }
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        return null;
    }


    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        return (List<User>) repository.findAll();
    }
    
    // update users

    @PutMapping("/api/users/{userId}")
    public User updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
        int id;
        try {
            id = Integer.parseInt(userId);
        }catch (Exception e){
            return null;
        }
        if (repository.findById(id).isPresent()) {
            User user1 = repository.findById(id).get();
            user1.setUser(user);
            return repository.save(user1);
        }
        return null;
    }
    // delete users
    @DeleteMapping("/api/users/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {
    	repository.deleteById(userId);
    }
    //
    @GetMapping("api/CheckLogin")
    public String checkLogin(HttpSession session) {
    	if (session.getAttribute("currentUser") == null) {
    		return "Anonymous";
    	}
    	
    	User currentUser = (User) session.getAttribute("currentUser");    	
    	
    	if (repository.findById(currentUser.getId()).isPresent()) {
    		return currentUser.getDtype();
    	}
    	else
    		return "Anonymous";
    }
    
    @GetMapping("/api/profile")
    public User profile(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
	      System.out.println("currentUser"+session.getAttribute("currentUser"));

        if (repository.findById(currentUser.getId()).isPresent()) {
            User user = repository.findById(currentUser.getId()).get();
            return user;
        }
        return null;
    }

    //LOGIN
    @PostMapping("/api/login")

    public String login(	@RequestBody User credentials,HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
    	User u1 = repository.findUserByCredentials(credentials.getUsername(), credentials.getPassword());
    	if(u1==null)
    	{
    	      System.out.println("No such user Exist");
	
    	}
    	else {
    		 session.setAttribute("currentUser", u1);
    		 System.out.println("currentUser in login"+session.getAttribute("currentUser"));
    	        return currentUser.getDtype();
    	}
		return "Anonymous";
      
     }
   


    List<User> users = new ArrayList<User>();
    @PostMapping("/api/register")
    public User register(@RequestBody User user,
    HttpSession session) {
    	session.setAttribute("currentUser", user);
    	users.add(user);
    	return user;
    }


    //LOGOUT
    @PostMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }


   


}