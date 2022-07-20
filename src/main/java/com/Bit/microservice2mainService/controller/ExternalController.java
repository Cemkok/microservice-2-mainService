/**
 * Spring Boot version 2.6.2 Build Automation Tool is Maven. Java version 1.8
 */

package com.Bit.microservice2mainService.controller;



import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bit.microservice2mainService.business.abstracts.IExternalService;
import com.Bit.microservice2mainService.util.constants.Logging;
import com.google.gson.JsonElement;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cem Kok
 * @Date 14 Tem 2022
 * @Time 19:41:40
 * @see
 *   <p>
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
 *      <tr>
 *      <td>@DeleteMapping</td>
 *      <td>* Annotation for mapping HTTP {@code DELETE} requests onto specific
 *      handler methods.
 *
 *      <p>
 *      Specifically, {@code @DeleteMapping} is a <em>composed annotation</em>
 *      that acts as a shortcut for
 *      {@code @RequestMapping(method = RequestMethod.DELETE)}.</td>
 * 
 *      </tr>
 *      *
 *      <tr>
 *      <td>@GetMapping</td>
 *      <td>Annotation for mapping HTTP {@code GET} requests onto specific
 *      handler methods.
 *
 *      <p>
 *      Specifically, {@code @GetMapping} is a <em>composed annotation</em> that
 *      acts as a shortcut for
 *      {@code @RequestMapping(method = RequestMethod.GET)}.</td>
 * 
 *      </tr>
 *      *
 *      
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
 *      <tr>
 *      <td>@PathVariable</td>
 *      <td>* Annotation which indicates that a method parameter should be bound
 *      to a URI template variable. Supported for {@link RequestMapping}
 *      annotated handler methods.
 *
 *      <p>
 *      If the method parameter is {@link java.util.Map Map&lt;String,
 *      String&gt;} then the map is populated with all path variable names and
 *      values.</td>
 * 
 * 
 *      </tr>
 *      </body>
 *      </table>
 *      <br>
 * 
 */


@Slf4j
@RestController
@RequestMapping("gateway/customer")
public class ExternalController {

	@Autowired
	private IExternalService externalService;

	@PostMapping("/add")
	public ResponseEntity<?> addCustomer(@RequestBody JsonElement customer) {

		log.info("[addCustomer method is called ]--" + "[input parameter = " + customer + "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(new ResponseEntity<>(customer, HttpStatus.CREATED))
				+ "]");

				Logging.internalLogDetail();

		return ResponseEntity.ok(externalService.addCustomer(customer));
	}

	@DeleteMapping("/deleteCustomerById/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
		log.info("[deleteCustomer method is called ]--" + "[input parameter = " + customerId + "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(new ResponseEntity<>( HttpStatus.OK))
				+ "]");

				Logging.internalLogDetail();
		
		
		
		return new ResponseEntity<>(externalService.deleteCustomer(customerId), HttpStatus.OK);
	}
	


	@GetMapping("/getAll/{pageNo}/{pageSize}")
	public ResponseEntity<?> getAllCustomers(@PathVariable int pageNo, @PathVariable int pageSize) {
		
		log.info("[getAllCustomers method is called ]--" + "[input parameter = pageNo-pageSize" + pageNo + pageSize+ "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(ResponseEntity.ok(externalService.getAllCustomers(pageNo, pageSize)))
				+ "]");

				Logging.internalLogDetail();
		
		return ResponseEntity.ok(externalService.getAllCustomers(pageNo, pageSize));
	}
	@GetMapping("/getByCustomerId/{id}")
	public ResponseEntity<?>getByCustomerId(@PathVariable Long id) {
		log.info("[getByCustomerId method is called ]--" + "[input parameter = " +id+ "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(ResponseEntity.ok(externalService.getByCustomerId(id) + "for logging getByCustomerId()"))
				+ "]");

				Logging.internalLogDetail();
		
		
		
		return ResponseEntity.ok(externalService.getByCustomerId(id));
	}
	@GetMapping("/{companyName}")
	public ResponseEntity<?>findAllFilteredByCompanyName(@PathVariable String companyName) {
		log.info("[findAllFilteredByCompanyName method is called ]--" + "[input parameter = " +companyName+ "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(ResponseEntity.ok(externalService.findAllFilteredByCompanyName(companyName))+"for logging getByCustomerId()")
				+ "]");

				Logging.internalLogDetail();
		
		return ResponseEntity.ok(externalService.findAllFilteredByCompanyName(companyName));
	}
}
