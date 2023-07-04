package com.maybank.assignment.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maybank.assignment.exception.RecordNotFoundException;
import com.maybank.assignment.repositories.UserProfileRepository;
import com.maybank.assignment.repositories.entities.UserProfile;
import com.maybank.assignment.services.UserProfileService;
import com.maybank.assignment.services.dto.UserProfileDto;
import com.maybank.assignment.util.ModelMapperUtils;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	private Logger log = LoggerFactory.getLogger(UserProfileServiceImpl.class);

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserProfileDto findByUserProfileId(Long userProfileId) throws RecordNotFoundException {
		log.info("Enter UserProfileServiceImpl.findByUserProfileId");
		Optional<UserProfile> userProfile = userProfileRepository.findById(userProfileId);
		if(userProfile.isPresent()) {
            return ModelMapperUtils.map(userProfile.get(), UserProfileDto.class);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
	}

	@Override
	@Transactional
	public UserProfileDto createUserProfile(UserProfileDto userProfileDto) throws RecordNotFoundException {
		log.info("Enter UserProfileServiceImpl.createUserProfile");
		UserProfile userProfile = ModelMapperUtils.map(userProfileDto, UserProfile.class);
		userProfileRepository.save(userProfile);
		return userProfileDto;
	}

	@Override
	@Transactional
	public UserProfileDto updateUserProfile(Long userProfileId, UserProfileDto userProfileDto) throws RecordNotFoundException {
		log.info("Enter UserProfileServiceImpl.updateUserProfile");
		Optional<UserProfile> userProfile = userProfileRepository.findById(userProfileId);
		if(userProfile.isPresent()) {
			UserProfile updatedUserProfile = ModelMapperUtils.map(userProfileDto, UserProfile.class);
			updatedUserProfile.setUserProfileId(userProfileId);
			userProfileRepository.save(updatedUserProfile);
			return ModelMapperUtils.map(updatedUserProfile, UserProfileDto.class);
		} else {
			throw new RecordNotFoundException("No user record exist for given id");
		}
	}

	@Override
	@Transactional
	public void deleteUserProfileById(Long userProfileId) throws RecordNotFoundException {
		log.info("Enter UserProfileServiceImpl.deleteUserProfileById");
		Optional<UserProfile> userProfile = userProfileRepository.findById(userProfileId);
		if(userProfile.isPresent()) {
			userProfileRepository.deleteById(userProfileId);
		} else {
			throw new RecordNotFoundException("No user record exist for given id");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserProfileDto> findAll(Pageable pageable) {
		log.info("Enter UserProfileServiceImpl.findAll");
		Page<UserProfile> userProfileChunk = userProfileRepository.findAll(pageable);
		if(userProfileChunk.hasContent()) {
			Page<UserProfileDto> userProfileDtoChunk = userProfileChunk.map(userProfile -> {
				return ModelMapperUtils.map(userProfile, UserProfileDto.class);
			});
			return userProfileDtoChunk.getContent();
		} else {
			return new ArrayList<UserProfileDto>();
		}
	}

}
