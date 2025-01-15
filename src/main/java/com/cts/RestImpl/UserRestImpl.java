package com.cts.RestImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Constants.CafeConstants;
import com.cts.DTO.UserDTO;
//import com.cts.Rest.UserRest;
import com.cts.Service.UserService;
import com.cts.Utils.CafeUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/user")
public class UserRestImpl{

	@Autowired
	UserService userService;
	//[SignUp Implemented]
	
	//@Override
	@PostMapping(path="/signup")
	public ResponseEntity<String> signUp(@Valid @RequestBody UserDTO userDTO) {
		try {
			//This line calls the signUp method of the UserService class, passing the requestMap as an argument. It returns the result of this method call, which is expected to be a ResponseEntity<String>.
			return userService.signUp(userDTO);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		//[CafeUtils and Something Went Wrong from constants]
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	 // @Override
	 @PostMapping(path = "/login")
	    public ResponseEntity<String> login( @RequestBody UserDTO userDTO) {
	        try {
	            return userService.login( userDTO);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	

}
