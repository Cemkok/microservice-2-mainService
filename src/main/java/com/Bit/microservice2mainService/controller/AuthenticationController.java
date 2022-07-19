/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.Bit.microservice2mainService.business.abstracts.IAuthenticationService;
import com.Bit.microservice2mainService.business.abstracts.IUserService;
import com.Bit.microservice2mainService.entities.User;
import com.Bit.microservice2mainService.util.constants.Logging;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cem Kok
 * @Date   13 Tem 2022
 * @Time   15:21:46
 * @see
 */
@Slf4j
@RestController
@RequestMapping ("api/authentication")
public class AuthenticationController {
	
	@Autowired
	private IAuthenticationService authenticationService;
	@Autowired
	private IUserService userService;
	
	
	@PostMapping("sign-up")
	public ResponseEntity<?> signUp(@RequestBody User user){
		
		if (userService.findByUsername(user.getUsername()).isPresent()) 
		{
			log.info("[signUp method is called ]--" + "[input parameter = " + user + "]--"+ "[output parameter = "
					+ ToStringBuilder.reflectionToString(new ResponseEntity<>(HttpStatus.CONFLICT))
					+ "]");

					

					Logging.internalLogDetail();

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		log.info("[signUp method is called ]--" + "[input parameter = " + user + "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(new ResponseEntity<>(user,HttpStatus.CREATED))
				+ "]");

				Logging.internalLogDetail();
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
			
	}
	
	@PostMapping ("sign-in")
		public ResponseEntity<?>  signIn(@RequestBody User user){
		
		log.info("[signIn method is called ]--" + "[input parameter = " + user + "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(new ResponseEntity<>(user,HttpStatus.OK))
				+ "]");

				Logging.internalLogDetail();
		
		return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
		
	}
	
	
	
	

}
