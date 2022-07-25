/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.Bit.microservice2mainService.core.constants.Logging;
import com.Bit.microservice2mainService.security.jwt.JwtAuthorizationFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is the web security configuration class.
 *
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   15:16:24
 * @see This class was created to prevent anonymous access to endpoints. Spring
 *      Security provides this with Authentication Manager.Authentication
 *      Manager checks whether there is a basic authentication header by default
 *      in incoming http requests. Spring Security provides default
 *      configurations and these configurations can be extended with the
 *      WebSecurityConfigurerAdapter class. Also, WebSecurityConfigurerAdapter
 *      provides us with different authorizations for different roles.
 * 
 * 
 *      <table border="1">
 *      <body>
 *      <tr>
 *      <td><strong>Annotations </strong></td>
 *      <td></td>
 *      </tr>
 * 
 *      <tr>
 *      <td>@EnableWebSecurity</td>
 *      <td>* Add this annotation to an {@code @Configuration} class to have the
 *      Spring Security configuration defined in any
 *      {@link WebSecurityConfigurer} or more likely by extending the
 *      {@link WebSecurityConfigurerAdapter} base class and overriding
 *      individual methods:
 *
 *      <pre class="code">
 *      &#064;Configuration &#064;EnableWebSecurity public class
 *      MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
 *
 *      &#064;Override public void configure(WebSecurity web) throws Exception {
 *      web.ignoring() // Spring Security should completely ignore URLs starting
 *      with /resources/ .antMatchers(&quot;/resources/**&quot;); }
 *
 *      &#064;Override protected void configure(HttpSecurity http) throws
 *      Exception {
 *      http.authorizeRequests().antMatchers(&quot;/public/**&quot;).permitAll().anyRequest()
 *      .hasRole(&quot;USER&quot;).and() // Possibly more configuration ...
 *      .formLogin() // enable form based log in // set permitAll for all URLs
 *      associated with Form Login .permitAll(); }
 *
 *      &#064;Override protected void configure(AuthenticationManagerBuilder
 *      auth) throws Exception { auth // enable in memory based authentication
 *      with a user named &quot;user&quot; and &quot;admin&quot;
 *      .inMemoryAuthentication().withUser(&quot;user&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;)
 *      .and().withUser(&quot;admin&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;,
 *      &quot;ADMIN&quot;); }</td>
 *      </tr>
 * 
 *      <td>@Value</td>
 *      <td>Annotation used at the field or method/constructor parameter level
 *      that indicates a default value expression for the annotated element.
 *
 *      <p>
 *      Typically used for expression-driven or property-driven dependency
 *      injection. Also supported for dynamic resolution of handler method
 *      arguments &mdash; for example, in Spring MVC.
 *
 *      <p>
 *      A common use case is to inject values using
 *      <code>#{systemProperties.myProp}</code> style SpEL (Spring Expression
 *      Language) expressions. Alternatively, values may be injected using
 *      <code>${my.app.myProp}</code> style property placeholders.
 *
 *      <p>
 *      Note that actual processing of the {@code @Value} annotation is
 *      performed by a
 *      {@link org.springframework.beans.factory.config.BeanPostProcessor
 *      BeanPostProcessor} which in turn means that you <em>cannot</em> use
 *      {@code @Value} within
 *      {@link org.springframework.beans.factory.config.BeanPostProcessor
 *      BeanPostProcessor} or
 *      {@link org.springframework.beans.factory.config.BeanFactoryPostProcessor
 *      BeanFactoryPostProcessor} types. Please consult the javadoc for the
 *      {@link AutowiredAnnotationBeanPostProcessor} class (which, by default,
 *      checks for the presence of this annotation).</td>
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
 *      </body>
 *      </table>
 *      <br>
 */

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	  private static final String[] AUTH_WHITELIST = {
	 
	            // -- Swagger UI v3 (OpenAPI)
	            "/v3/api-docs/**",
	            "/swagger-ui/**",
	            "swagger-ui.html/**"
	            // other public endpoints of your API may be appended to this array
	    };

	  
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Override
	/**
	 * This method configures the authorization. It constrains different paths for different roles.
	 */
	protected void configure(HttpSecurity http) throws Exception {
		log.info("[configure method(HttpSecurity http) is called ]--" + "[input parameter =" +http+ "]");

		Logging.internalLogDetail();
	  http.csrf().disable();
	  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 
	  http.authorizeRequests()
	  .antMatchers(HttpMethod.OPTIONS).permitAll()
	  .antMatchers("/api/authentication/**").permitAll().antMatchers(AUTH_WHITELIST).permitAll() //login and register pre-path
	  
	  .anyRequest().authenticated();
	  
	  http.addFilterBefore(JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	  
	
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html/**",
                                   "/webjars/**","/v3/api-docs/**",
                   	            "/swagger-ui/**",
                	            "swagger-ui.html/**");
    }
	/**
	 * This method is used to set the authentication type.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("[configure method is called ]--" + "[input parameter =" +auth+ "]");

		Logging.internalLogDetail();
		
		auth.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());
		
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("[passwordEncoder method is called ]--" + "[input parameter ="+"]");

		Logging.internalLogDetail();
		return  new BCryptPasswordEncoder();
	}
		
	@Bean
	public JwtAuthorizationFilter JwtAuthorizationFilter() {
		log.info("[JwtAuthorizationFilter method is called ]--" + "[input parameter = no args"+"]");

		Logging.internalLogDetail();
		
		return new JwtAuthorizationFilter();
		
	}
	
					
	
	
	@Bean
	/**
	 * This method customizes the permissions granted to paths and methods.
	 * @return  WebMvcConfigurer()
	 */
	public WebMvcConfigurer corsConfigurer() {
		log.info("[corsConfigurer method is called ]--" + "[input parameter = no args"+"]");

		Logging.internalLogDetail();
		
			return new WebMvcConfigurer() {
				
				public void addCorsMapping (CorsRegistry registry) {
					registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("*");
					
				}
			
		};
		

	}}




