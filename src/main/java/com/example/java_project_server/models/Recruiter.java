package com.example.java_project_server.models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
public class Recruiter extends User {
    private String yearsOfExp;
    private String domain; 
@OneToMany(mappedBy = "recruiters", cascade = CascadeType.ALL)

    private List<School> schools;

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }
    
    
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)

    @JoinTable(name="followers",joinColumns=
    @JoinColumn(name="Recruiter_ID", 
   referencedColumnName="ID"),
   inverseJoinColumns=@JoinColumn(name=
      "Student_ID", referencedColumnName="ID"))
    
	private List<Student> followedStudents;
    
    

		public List<Student> getFollowedStudents() {
		return followedStudents;
	}

	public void setFollowedStudents(List<Student> followedStudents) {
		this.followedStudents = followedStudents;
	}

		public void followStudent(Student student) {
		this.followedStudents.add(student);
		if(!student.getFollowedRecruiter().contains(this)) {
			student.getFollowedRecruiter().add(this);

		}}
		
		public void defollowStudent(Student student) {
			if(!student.getFollowedRecruiter().contains(this)) {
				student.getFollowedRecruiter().remove(this);

			}}

   
    public String getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(String yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

	

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	 public void setRecruiter(Recruiter user) {

	        this.domain = user.domain != null ? user.domain : this.domain;
	        this.yearsOfExp = user.yearsOfExp != null ? user.yearsOfExp : this.yearsOfExp;	    

	    }
    

  
}