/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.Bit.microservice2mainService.business.abstracts.IAuthenticationService;
import com.Bit.microservice2mainService.business.abstracts.IUserService;
import com.Bit.microservice2mainService.core.constants.Logging;
import com.Bit.microservice2mainService.entities.User;

import lombok.extern.slf4j.Slf4j;

/**
 * This AuthenticationController class is a RestController. It accepts and directs requests for Authentication, and sends a response depending on the situation.
 * @author Cem Kok
 * @Date   13 Tem 2022
 * @Time   15:21:46
 * @see
 * <p>
 *      This CustomerController class is a RestController. RestController is
 *      used for making restful web services with the help of
 *      the @RestController annotation. This annotation is used at the class
 *      level and allows the class to handle the requests made by the client.
 *      </p>
 *      <table border="1">
 *      <body>
 *      <tr>
 *      <td><strong>Annotations </strong></td>
 *      <td></td>
 *      </tr>
 *      <tr>
 *      <td>@RestController</td>
 *      <td>A convenience annotation that is itself annotated with
 *      {@link Controller @Controller} and {@link ResponseBody @ResponseBody}.
 *      <p>
 *      Types that carry this annotation are treated as controllers where
 *      {@link RequestMapping @RequestMapping} methods assume
 *      {@link ResponseBody @ResponseBody} semantics by default.
 *
 *      <p>
 *      <b>NOTE:</b> {@code @RestController} is processed if an appropriate
 *      {@code HandlerMapping}-{@code HandlerAdapter} pair is configured such as
 *      the
 *      {@code RequestMappingHandlerMapping}-{@code RequestMappingHandlerAdapter}
 *      pair which are the default in the MVC Java config and the MVC namespace.
 *      <p>
 *      </td>
 *      </tr>
 *      <tr>
 *      <td>@RequestMapping</td>
 *      <td>Annotation for mapping web requests onto methods in request-handling
 *      classes with flexible method signatures.
 *      <p>
 *      <strong>Note:</strong> This annotation can be used both at the class and
 *      at the method level. In most cases, at the method level applications
 *      will prefer to use one of the HTTP method specific variants
 *      {@link GetMapping @GetMapping}, {@link PostMapping @PostMapping},
 *      {@link PutMapping @PutMapping}, {@link DeleteMapping @DeleteMapping}, or
 *      {@link PatchMapping @PatchMapping}.
 *      </p>
 *      </td>
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
 *      <td>@PostMapping</td>
 *      <td>* Annotation for mapping HTTP {@code POST} requests onto specific
 *      handler methods.
 *
 *      <p>
 *      Specifically, {@code @PostMapping} is a <em>composed annotation</em>
 *      that acts as a shortcut for
 *      {@code @RequestMapping(method = RequestMethod.POST)}.</td>
 * 
 *      </tr>
 *    
 *      <tr>
 *      <td>@RequestBody</td>
 *      <td>* Annotation indicating a method parameter should be bound to the
 *      body of the web request. The body of the request is passed through an
 *      {@link HttpMessageConverter} to resolve the method argument depending on
 *      the content type of the request. Optionally, automatic validation can be
 *      applied by annotating the argument with {@code @Valid}.
 *
 *      <p>
 *      Supported for annotated handler methods.</td>
 * 
 *      </tr>
 *      </body>
 *      </table>
 *      <br>
 * 
 */
@Slf4j
@RestController
@RequestMapping ("api/authentication")
public class AuthenticationController {
	
	@Autowired
	private IAuthenticationService authenticationService;
	@Autowired
	private IUserService userService;
	
	/**
	 * This method performs sign-up process. It directs the user object coming from the specified path to the UserService class.
	 * @param user
	 * @return Result { {user, message, success status}, HttpStatus.CREATED}
	 */
	@PostMapping("sign-up")
	public ResponseEntity<?> signUp(@RequestBody User user){
		
		if (userService.findByUsername(user.getUsername()).isPresent()) 
		{
			log.info("[signUp method is called ]--" + "[input parameter = " + user + "]--"+ "[output parameter = "
					+ ToStringBuilder.reflectionToString(new ResponseEntity<>(HttpStatus.CONFLICT))
					+ "]");

					

					Logging.internalLogDetail();

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		log.info("[signUp method is called ]--" + "[input parameter = " + user + "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(new ResponseEntity<>(user,HttpStatus.CREATED))
				+ "]");

				Logging.internalLogDetail();
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
			
	}
	/**
	 * This method directs the user object from the path to the AuthenticationService class.
	 * @param user
	 * @return JWT, HttpStatus.OK
	 */
	@PostMapping ("sign-in")
		public ResponseEntity<?>  signIn(@RequestBody User user){
		
		log.info("[signIn method is called ]--" + "[input parameter = " + user + "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(new ResponseEntity<>(user,HttpStatus.OK))
				+ "]");

				Logging.internalLogDetail();
		
		return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
		
	}
	
	
	
	

}
