package com.maybank.assignment.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.maybank.assignment.exception.RecordNotFoundException;
import com.maybank.assignment.services.UserProfileService;
import com.maybank.assignment.services.dto.UserProfileDto;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {
	
	private Logger log = LoggerFactory.getLogger(UserProfileController.class);
	
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody UserProfileDto userProfileDto) throws RecordNotFoundException {
		log.info("Enter UserProfileController.addUser request: {}", userProfileDto);
        
		UserProfileDto userProfile = userProfileService.createUserProfile(userProfileDto);

        log.info("UserProfileController.addUser response: {}", userProfile);
        return ResponseEntity.ok(userProfile);
    }
	
	@PutMapping("/edit/{userProfileId}")
    public ResponseEntity<UserProfileDto> editUser(@PathVariable Long userProfileId, @RequestBody UserProfileDto userProfileDto) throws RecordNotFoundException {
		log.info("Enter UserProfileController.editUser request userProfileId: {} userProfileDto: {}", userProfileId, userProfileDto);
		UserProfileDto updatedUser = userProfileService.updateUserProfile(userProfileId, userProfileDto);
		log.info("UserProfileController.editUser response: {}", updatedUser);
        return ResponseEntity.ok(updatedUser);
    }
	
	@GetMapping("/retrieve/{userProfileId}")
    public ResponseEntity<UserProfileDto> getUser(@PathVariable Long userProfileId) throws RecordNotFoundException {
		log.info("Enter UserProfileController.getUser request userProfileId: {}", userProfileId);
		UserProfileDto userProfileDto = userProfileService.findByUserProfileId(userProfileId);
		
		log.info("UserProfileController.getUser response: {}", userProfileDto);
	    return ResponseEntity.ok(userProfileDto);
    }
	
	@GetMapping("/all/retrieve")
    public ResponseEntity<List<UserProfileDto>> getUsers(@RequestParam(name = "page") int page) {
		log.info("Enter UserProfileController.getUsers request page number: {}", page);
        PageRequest pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        List<UserProfileDto> userList = userProfileService.findAll(pageRequest);
        log.info("UserProfileController.getUsers response: {}", userList);
        return ResponseEntity.ok(userList);
    }
	
	@DeleteMapping("/delete/{userProfileId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userProfileId) throws RecordNotFoundException {
		log.info("Enter UserProfileController.deleteUser request userProfileId: {}", userProfileId);
        userProfileService.deleteUserProfileById(userProfileId);
        log.info("UserProfileController.deleteUser response");
        return ResponseEntity.ok("User deleted");
    }
	
	@PostMapping("/nested")
	 public ResponseEntity<String> nestedApi() {
		log.info("Enter UserProfileController.nestedApi");
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange("https://api.restful-api.dev/objects/7", HttpMethod.GET, null, String.class);
	    log.info("UserProfileController.nestedApi response: {}", response.getBody());
	    return ResponseEntity.ok(response.getBody());
	 }
	
	@ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
