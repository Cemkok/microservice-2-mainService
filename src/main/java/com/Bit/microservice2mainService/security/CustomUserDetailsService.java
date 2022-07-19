/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Bit.microservice2mainService.business.abstracts.IUserService;
import com.Bit.microservice2mainService.entities.User;

/**
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   14:44:35
 * @see
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private  IUserService iUserService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = iUserService.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found with this username: " + username));
		
		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword());
	}
	
	
	

}
