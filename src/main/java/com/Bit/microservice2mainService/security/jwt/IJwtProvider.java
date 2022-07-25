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
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   18:11:17
 * @see
 */
public interface IJwtProvider {

	/**
	 * This method generates tokens using the UserPrincipal object.
	 * @param authentication
	 * @return a new token
	 */
	String generateToken(UserPrincipal authentication);

	/**
	 * This method authenticates a request from the client.
	 * @param request
	 * @return
	 */
	Authentication getAuhentication(HttpServletRequest request);

	/**
	 * This method checks the validity period of the token.
	 * @param request
	 * @return Returns false if the token is empty or out of date, and true if it is not expired.
	 */
	boolean isTokenValid(HttpServletRequest request);

}
