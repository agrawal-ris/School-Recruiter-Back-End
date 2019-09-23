package com.example.java_project_server.models;


import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Student extends User {
    private String joiningYear;
    private String skills; 

    
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name="likes",joinColumns=
    @JoinColumn(name="STUDENT_ID", 
   referencedColumnName="ID"),
   inverseJoinColumns=@JoinColumn(name=
      "SCHOOL_ID", referencedColumnName="ID"))
    @JsonIgnore
    private List<School> enrolledSchool;
    
   
	public List<School> getEnrolledSchool() {
		return enrolledSchool;
	}

	public void setEnrolledSchool(List<School> enrolledSchool) {
		this.enrolledSchool = enrolledSchool;
	}

	public void enrollSchool(School school) {
		this.enrolledSchool.add(school);
		if(!school.getEnrolledStudents().contains(this)) {
		school.getEnrolledStudents().add(this);
}}
	
	public void deenrollSchool(School school) {
		if(school.getEnrolledStudents().contains(this)) {
			school.getEnrolledStudents().remove(this);

		}}
	
	
	@ManyToMany(mappedBy="followedStudents")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Recruiter> followedRecruiter;
    
	
	public List<Recruiter> getFollowedRecruiter() {
		return followedRecruiter;
	}

	public void setFollowedRecruiter(List<Recruiter> followedRecruiter) {
		this.followedRecruiter = followedRecruiter;
	}

	public void followRecruiter(Recruiter recruiters) {
		this.followedRecruiter.add(recruiters);
		if(!recruiters.getFollowedStudents().contains(this)) {
			recruiters.getFollowedStudents().add(this);
}}
	
	public void defollowRecruiter(Recruiter recruiters) {
	
		if(recruiters.getFollowedStudents().contains(this)) {
			recruiters.getFollowedStudents().remove(this);
}}
	
	
	

    public String getJoiningYear() {
        return joiningYear;
    }

    public void setJoiningYear(String joiningYear) {
        this.joiningYear = joiningYear;
    }
    public String getSkills() {
		return skills;
	}	

	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	 public void setStudent(Student user) {

	        this.skills = user.skills != null ? user.skills : this.skills;
	        this.joiningYear = user.joiningYear != null ? user.joiningYear : this.joiningYear;	    

	    }


	    
    
}