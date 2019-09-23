package com.example.java_project_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name="schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String post_content;
    private String School_api_id;

    
  
    
    @ManyToMany(mappedBy="enrolledSchool" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    	private List<Student> enrolledStudents;
    
    	

		public List<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(List<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

		public void enrollStudent(Student student) {
		this.enrolledStudents.add(student);
		if(!student.getEnrolledSchool().contains(this)) {
			student.getEnrolledSchool().add(this);

		}}
		public void deenrollStudent(Student student) {
			
			if(student.getEnrolledSchool().contains(this)) {
				student.getEnrolledSchool().remove(this);

			}}
		
	
		 
	@ManyToOne
    @JsonIgnore
    private Recruiter recruiters;

    public Recruiter getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(Recruiter recruiters) {
        this.recruiters = recruiters;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

    public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public String getSchool_api_id() {
		return School_api_id;
	}

	public void setSchool_api_id(String school_api_id) {
		School_api_id = school_api_id;
	}

	public void setSchoolDetails(School school) {
        this.title = school.title != null ? school.title : this.title;
        this.post_content = school.post_content != null ? school.post_content : this.post_content;
        this.School_api_id = school.School_api_id != null ? school.School_api_id : this.School_api_id;

    }
  


}