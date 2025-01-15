package com.cts.Service;
import com.cts.DTO.UserDTO;

import org.springframework.http.ResponseEntity;

public interface UserService {


	ResponseEntity<String> signUp(UserDTO userDTO);
	
	
	ResponseEntity<String> login(UserDTO userDTO);
	
}
