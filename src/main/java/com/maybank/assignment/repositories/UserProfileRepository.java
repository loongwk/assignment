package com.maybank.assignment.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maybank.assignment.repositories.entities.UserProfile;

@Repository
@Transactional
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile, Long> {

}
