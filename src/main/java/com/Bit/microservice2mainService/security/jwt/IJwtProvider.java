/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.Bit.microservice2mainService.security.UserPrincipal;

/**
 * @author Cem Kök
 * @Date   12 Tem 2022
 * @Time   18:11:17
 * @See
 */
public interface IJwtProvider {

	/**
	 * @param authentication
	 * @return
	 */
	String generateToken(UserPrincipal authentication);

	/**
	 * @param request
	 * @return
	 */
	Authentication getAuhentication(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	boolean isTokenValid(HttpServletRequest request);

}
