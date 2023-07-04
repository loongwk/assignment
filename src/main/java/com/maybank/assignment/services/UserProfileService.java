package com.maybank.assignment.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.maybank.assignment.exception.RecordNotFoundException;
import com.maybank.assignment.services.dto.UserProfileDto;

public interface UserProfileService {
	
	UserProfileDto findByUserProfileId (Long userProfileId) throws RecordNotFoundException;
	
	UserProfileDto createUserProfile(UserProfileDto userProfileDto) throws RecordNotFoundException;
	
	UserProfileDto updateUserProfile(Long userProfileId, UserProfileDto userProfileDto) throws RecordNotFoundException;
	
	void deleteUserProfileById(Long userProfileId) throws RecordNotFoundException;
	
	List<UserProfileDto> findAll(Pageable pageable);

}
