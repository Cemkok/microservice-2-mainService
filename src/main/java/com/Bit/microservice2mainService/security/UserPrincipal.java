/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Bit.microservice2mainService.core.constants.Logging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class was created to implement the UserDetails interface of Spring Security.
 * This interface  provides core user information.
 * <p>
 * Implementations are not used directly by Spring Security for security purposes. They
 * simply store user information which is later encapsulated into {@link Authentication}
 * objects. This allows non-security related user information (such as email addresses,
 * telephone numbers etc) to be stored in a convenient location.
 * <p>
 * Concrete implementations must take particular care to ensure the non-null contract
 * detailed for each method is enforced. See
 * {@link org.springframework.security.core.userdetails.User} for a reference
 * implementation (which you might like to extend or use in your code).
 *
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   14:59:07
 * @see
 */
@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {


		private Long id;
		private String username;
		transient private String password; // don't show up on serialized places
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			return Collections.singletonList(new SimpleGrantedAuthority("USER"));
		}

		@Override
		public String getPassword() {
			log.info("[getPassword method is called ]--" + "[input parameter = no args"+"]");

			Logging.internalLogDetail();
			
			
			return password;
		}

		@Override
		public String getUsername() {
			log.info("[getUsername method is called ]--" + "[input parameter = no args"+"]");

			Logging.internalLogDetail();
			
			return username;
		}

		@Override
		public boolean isAccountNonExpired() {
			log.info("[isAccountNonExpired method is called ]--" + "[input parameter = no args"+"]");

			Logging.internalLogDetail();
			
			
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			log.info("[isAccountNonLocked method is called ]--" + "[input parameter = no args"+"]");

			Logging.internalLogDetail();
			
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			log.info("[isCredentialsNonExpired method is called ]--" + "[input parameter = no args"+"]");

			Logging.internalLogDetail();
			
		
			return true;
		}

		@Override
		public boolean isEnabled() {
			log.info("[isEnabled method is called ]--" + "[input parameter = no args"+"]");

			Logging.internalLogDetail();
			
			
			return true;
		}

	}



