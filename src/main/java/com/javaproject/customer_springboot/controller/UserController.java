package com.javaproject.customer_springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javaproject.customer_springboot.dto.UserDTO;
import com.javaproject.customer_springboot.dto.UserAuthenticationRequestDTO;
import com.javaproject.customer_springboot.exception.UserException;
import com.javaproject.customer_springboot.service.UserAuthenticationService;
import com.javaproject.customer_springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    
	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@GetMapping
    public ResponseEntity<List<UserDTO>> listUser(){
    	log.info("List All user");
    	return ResponseEntity.ok(userService.listUser());
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<UserDTO>> listActiveUser(){
    	log.info("List Active user");
    	return ResponseEntity.ok(userService.listActiveUser());
    }
    
    @GetMapping("/inactive")
    public ResponseEntity<List<UserDTO>> listInactiveUser(){
    	log.info("List Inactive user");
    	return ResponseEntity.ok(userService.listInactiveUser());
    }
    
    @GetMapping("/pageable")
    public ResponseEntity<Page<UserDTO>> listUserPageable(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
    	log.info("List All user pageable");
        return ResponseEntity.ok(userService.listUserPageable(page, size));
    }

    @GetMapping("/pageable/{status}")
    public ResponseEntity<Page<UserDTO>> listUserStatusPageable(
    		@PathVariable("status") String status, 
	        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
	        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
    	log.info("List user by status pageable");
        return ResponseEntity.ok(userService.listUserByStatusPageable(status, page, size));
    }
	
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable("id") Long id) throws UserException {
    	log.info("Search user ID");
    	return ResponseEntity.ok(userService.findUser(id));
    }

    @GetMapping("/name/{userName}")
    public ResponseEntity<UserDTO> findUserName(
    		@PathVariable("userName") String userName) throws UserException {
    	log.info("Search user name");
    	return ResponseEntity.ok(userService.findUserName(userName));
    }

    @GetMapping("/login/{userLogin}")
    public ResponseEntity<UserDTO> findUserLogin(
    		@PathVariable("userLogin") String userLogin) throws UserException {
    	log.info("Search user login");
    	return ResponseEntity.ok(userService.findUserLogin(userLogin));
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<UserDTO> findUserEmail(
    		@PathVariable("userEmail") String userEmail) throws UserException {
    	log.info("Search user email");
    	return ResponseEntity.ok(userService.findUserEmail(userEmail));
    }

    @GetMapping("/sigin{userEmail}{userPassword}")
    public ResponseEntity<UserDTO> findUserEmailPassword(
    		@PathVariable("userEmail") String userEmail, @PathVariable("userPassword") String userPassword) throws UserException {
    	log.info("Search user email and password email");
    	return ResponseEntity.ok(userService.findUserEmailPassword(userEmail, userPassword));
    }
    
    @PostMapping("/add")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) throws UserException{
    	log.info("Create user");
    	return ResponseEntity.ok(userService.createUser(userDTO));
    }
    
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) throws UserException {
    	log.info("Update user");
    	return ResponseEntity.ok(userService.updateUser(userDTO));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) throws UserException {
    	log.info("Delete user");
    	userService.deleteUser(id);
		return ResponseEntity.noContent().build();
    }
    
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserAuthenticationRequestDTO authDto) throws UserException {
		log.info("Login user");
		return ResponseEntity.ok(userAuthenticationService.login(authDto));
	}
}
