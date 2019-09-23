package com.example.java_project_server.models;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String address;
    private String contactNum;
    
    @Column(insertable = false, updatable = false) private String dtype;
   


	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNum() {
	        return contactNum;
	    }

	    public void setContactNum(String contactNum) {
	        this.contactNum = contactNum;
	    }


    public void setUser(User user) {

        this.firstName = user.firstName != null ? user.firstName : this.firstName;
        this.lastName = user.lastName != null ? user.lastName : this.lastName;
        this.username = user.username != null ? user.username : this.username;
        this.password = user.password != null ? user.password : this.password;
        this.email = user.email != null ? user.email : this.email;
        this.address = user.address != null ? user.address : this.address;
        this.contactNum = user.contactNum != null ? user.contactNum : this.contactNum;

    

    }

}