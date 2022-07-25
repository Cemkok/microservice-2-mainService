/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.business.abstracts;

import com.Bit.microservice2mainService.entities.User;

/**
 * This is an interface of AuthenticationService class.
 * 
 * @author Cem Kok
 * @Date   13 Tem 2022
 * @Time   14:56:26
 * @see 
 *  <p>
 *      This interface provides us with a template to implement,
 *      <strong>dependency injection </strong> for the concrete class, and
 *      communication between the data <strong>access</strong> layer and the
 *      <strong>presentation</strong> layer.
	 */

public interface IAuthenticationService {

	/**
	 * This method returns a token to the authorized sign-in.
	 * @param signInRequest
	 * @return Json Web Token
	 */
	String signInAndReturnJWT(User signInRequest);

}
