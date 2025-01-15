package com.cts.ServiceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.Dao.UserDao;
import com.cts.JWT.CustomerUserDetailsService;
import com.cts.JWT.JwtFilter;
import com.cts.JWT.JwtUtil;
import com.cts.POJO.User;
import com.cts.Service.UserService;

import lombok.extern.slf4j.Slf4j;

import com.cts.Utils.CafeUtils;
import com.cts.Constants.CafeConstants;
import com.cts.DTO.UserDTO;

//For Loggers
@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	 @Autowired
	  private PasswordEncoder passwordEncoder;
	
	//used in login.The authenticationManager is a Spring Security component responsible for authenticating users.
	@Autowired
	AuthenticationManager authenticationManager;
	
	//CustomerUserDetailsService: Retrieves user details for authentication login.
	@Autowired
	CustomerUserDetailsService customerUserDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	JwtFilter jwtFilter;
	
	//ResponseEntity is the return type. it is a class in Spring that represents the complete HTTP response
	//requestMap is used to map request to controller
	public ResponseEntity<String> signUp(UserDTO userDTO) {
		
		//Loggers
		log.info("Inside signup{}", userDTO);
		try {
			//if email exist in our database during signup then it will return Bad Request if not then the credentials will be saved in userDao and returns Successfully registered
			//getting the object from userDao
			User user1 = userDao.findByEmail(userDTO.getEmail());
			if(Objects.isNull(user1)) 
			{
				userDao.save(getUser(userDTO));
				return CafeUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
			}
			else
			{
				return CafeUtils.getResponseEntity("email already exists.", HttpStatus.BAD_REQUEST);
			}
		
	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR); 
		
	}
    
	  //Extract the value from the UserDTO and set it in the user and return the user object. After returning the user object it is saved in
	    private User getUser(UserDTO userDTO) {
	    	 User user = new User();
	         user.setName(userDTO.getName());
	         user.setContactNumber(userDTO.getContactNumber());
	         user.setEmail(userDTO.getEmail());
	         user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	         user.setStatus("true");
	         user.setRole(userDTO.getRole());
	         return user;
	    }
	
	    
	    
	    //login
	    @Override
	    public ResponseEntity<String> login(UserDTO userDTO) {
	        log.info("Inside login"); //requestMap);
	        try {
	        	//we extract the email and password and check that it is authenticate or not
	        	//The UsernamePasswordAuthenticationToken wraps the credentials provided by the user into an object that can be processed by the AuthenticationProvider
	            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
	            
	            if (auth.isAuthenticated()) {
	            	//if the user is authenticated by the admin (means if status is true) then only token is generated and given otherwise it shows wait for admin approval
	                if (customerUserDetailsService.getUserDatails().getStatus().equalsIgnoreCase("true")) {
	                	log.info("Login is Succesfully done"); //**
	                    return new ResponseEntity<String>("{\"token\":\"" + jwtUtil.generateToken(
	                            customerUserDetailsService.getUserDatails().getEmail(), customerUserDetailsService.getUserDatails().getRole()) + "\"}",
	                            HttpStatus.OK);
	                } else {
	                    return new ResponseEntity<String>("{\"message\":\"" + "Wait for Admin Approval." + "\"}",
	                            HttpStatus.BAD_REQUEST);
	                }
	            }
	        } catch (Exception ex) {
	            log.error("{}", ex);
	        }
	        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials." + "\"}",
	                HttpStatus.BAD_REQUEST);
	    }

	    
	    

}
