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
import com.Bit.microservice2mainService.core.constants.Logging;
import com.Bit.microservice2mainService.entities.User;

import lombok.extern.slf4j.Slf4j;

/**
 * This class was created to implement UserDetailsService, which is the default
 * user service of Spring Security. This core interface which loads
 * user-specific data.
 * <p>
 * It is used throughout the framework as a user DAO and is the strategy used by
 * the
 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider
 * DaoAuthenticationProvider}.
 *
 * <p>
 * The interface requires only one read-only method, which simplifies support
 * for new data-access strategies.
 * 
 * @author Cem Kok
 * @Date 12 Tem 2022
 * @Time 14:44:35
 * @see
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserService iUserService;

	/**
	 * This method finds users by username.
	 * @return UserPrincipal object
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("[loadUserByUsername method is called ]--" + "[input parameter =no args" + "]");

		Logging.internalLogDetail();

		User user = iUserService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with this username: " + username));

		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword());
	}

}
