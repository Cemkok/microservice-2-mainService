/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author cemko
 * @Date   12 Tem 2022
 * @Time   14:59:07
 * @See
 */

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
			
			return password;
		}

		@Override
		public String getUsername() {
			
			return username;
		}

		@Override
		public boolean isAccountNonExpired() {
			
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
		
			return true;
		}

		@Override
		public boolean isEnabled() {
			
			return true;
		}

	}



