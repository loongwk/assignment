package com.maybank.assignment.services.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserProfileDto implements Serializable {

	private static final long serialVersionUID = -1994990125523930252L;
	
	private String name;
	private String email;

}
