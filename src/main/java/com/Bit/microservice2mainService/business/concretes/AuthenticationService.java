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
 * This class is used to generate tokens when the defined user sign-in.
 * 
 * @author Cem Kok
 * @Date   13 Tem 2022
 * @Time   14:48:19
 * @see 
 *   <table border="1">
 *      <body>
 *      <tr>
 *      <td><strong>Annotations </strong></td>
 *      <td></td>
 *      </tr>
 * 
 *      <tr>
 *      <td>@Slf4j</td>
 *      <td>* Causes lombok to generate a logger field.
 *      <p>
 *      Complete documentation is found at
 *      <a href="https://projectlombok.org/features/Log">the project lombok
 *      features page for lombok log annotations</a>.
 *      <p>
 *      </td>
 * 
 *      </tr>
 * 
 *      <tr>
 *      <td>@Autowired</td>
 *      <td>Marks a constructor, field, setter method, or config method as to be
 *      autowired by Spring's dependency injection facilities. This is an
 *      alternative to the JSR-330 {@link javax.inject.Inject} annotation,
 *      adding required-vs-optional semantics. *
 *      <h3>Autowired Constructors</h3>
 *      <p>
 *      Only one constructor of any given bean class may declare this annotation
 *      with the {@link #required} attribute set to {@code true}, indicating
 *      <i>the</i> constructor to autowire when used as a Spring bean.
 *      Furthermore, if the {@code required} attribute is set to {@code true},
 *      only a single constructor may be annotated with {@code @Autowired}. If
 *      multiple <i>non-required</i> constructors declare the annotation, they
 *      will be considered as candidates for autowiring. The constructor with
 *      the greatest number of dependencies that can be satisfied by matching
 *      beans in the Spring container will be chosen. If none of the candidates
 *      can be satisfied, then a primary/default constructor (if present) will
 *      be used. Similarly, if a class declares multiple constructors but none
 *      of them is annotated with {@code @Autowired}, then a primary/default
 *      constructor (if present) will be used. If a class only declares a single
 *      constructor to begin with, it will always be used, even if not
 *      annotated. An annotated constructor does not have to be public.
 *
 *      </td>
 * 
 *      </tr>
 *      <tr>
 *      <td>@Service</td>
 *      <td>* Indicates that an annotated class is a "Service", originally
 *      defined by Domain-Driven Design (Evans, 2003) as "an operation offered
 *      as an interface that stands alone in the model, with no encapsulated
 *      state."
 *
 *      <p>
 *      May also indicate that a class is a "Business Service Facade" (in the
 *      Core J2EE patterns sense), or something similar. This annotation is a
 *      general-purpose stereotype and individual teams may narrow their
 *      semantics and use as appropriate.
 *
 *      <p>
 *      This annotation serves as a specialization of
 *      {@link Component @Component}, allowing for implementation classes to be
 *      autodetected through classpath scanning.</td>
 * 
 *      </tr>
 *      <tr>
 *      <td>@Override</td>
 *      <td>Indicates that a method declaration is intended to override a method
 *      declaration in a supertype. If a method is annotated with this
 *      annotation type compilers are required to generate an error message
 *      unless at least one of the following conditions hold:
 *
 *      <ul>
 *      <li>The method does override or implement a method declared in a
 *      supertype.</li>
 *      <li>The method has a signature that is override-equivalent to that of
 *      any public method declared in {@linkplain Object}.</li>
 *      </ul>
 *      </td>
 *      </tr>
 * 
 *      </body>
 *      </table>
 *      <br>
 * 
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
		log.info("[signInAndReturnJWT method is called ]--" + "[input parameter = " + signInRequest + "]--"
				+ "[output parameter = JWT " + "]");
		;
		Logging.internalLogDetail();
		
 

		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
				);
		
		UserPrincipal userPrincipal =(UserPrincipal) authentication.getPrincipal();
		
		return jwtProvider.generateToken(userPrincipal);
		
	}

}
