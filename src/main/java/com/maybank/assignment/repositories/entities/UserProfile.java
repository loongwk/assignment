package com.maybank.assignment.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "users", schema = "TESTDB")
public class UserProfile implements Serializable {
	
	private static final long serialVersionUID = 835870413198665157L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_profile_id", nullable = false)
    private Long userProfileId;
    
	@Column(name = "name")
    private String name;
	
	@Column(name = "email")
    private String email;
    
}

