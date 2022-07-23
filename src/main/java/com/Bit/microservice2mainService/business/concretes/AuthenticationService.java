/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.Bit.microservice2mainService.business.abstracts.IAuthenticationService;
import com.Bit.microservice2mainService.core.constants.Logging;
import com.Bit.microservice2mainService.entities.User;
import com.Bit.microservice2mainService.security.UserPrincipal;
import com.Bit.microservice2mainService.security.jwt.IJwtProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cem Kok
 * @Date   13 Tem 2022
 * @Time   14:48:19
 * @see
 */
@Slf4j
@Service
public class AuthenticationService implements IAuthenticationService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IJwtProvider jwtProvider;
	
	@Override

	public String signInAndReturnJWT(User signInRequest) {
		log.info("[signInAndReturnJWT method is called ]--" + "[input parameter ="+signInRequest+"]");
		
		Logging.internalLogDetail();
 

		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
				);
		
		UserPrincipal userPrincipal =(UserPrincipal) authentication.getPrincipal();
		
		return jwtProvider.generateToken(userPrincipal);
		
	}

}
