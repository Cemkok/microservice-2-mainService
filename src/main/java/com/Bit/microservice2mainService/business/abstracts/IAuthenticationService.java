/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.business.abstracts;

import com.Bit.microservice2mainService.entities.User;

/**
 * @author Cem Kök
 * @Date   13 Tem 2022
 * @Time   14:56:26
 * @See
 */
public interface IAuthenticationService {

	/**
	 * @param signInRequest
	 * @return
	 */
	String signInAndReturnJWT(User signInRequest);

}
