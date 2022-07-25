package com.Bit.microservice2mainService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * This class is the main class of the project, project runs from this class
 * @author Cem Kok
 * @Date   25 Tem 2022
 * @Time   14:50:18
 * @see This class is the main class of the project, project runs from this class
 */

@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
@SpringBootApplication

public class Microservice2MainServiceApplication {

	/**
	 * This method provides us to run the project.
	 * 
	 *  <p>Class that can be used to bootstrap and launch a Spring
	 *                      application from a Java main method. Static helper that
	 *                      can be used to run a {@link SpringApplication} from the
	 *                      specified source using default settings. </p>
	 * 
	 * 
	 * @param args          the application arguments (usually passed from a Java
	 *                      main method)
	 * 
	 *                     
	
	 */
	public static void main(String[] args) {
		SpringApplication.run(Microservice2MainServiceApplication.class, args);
	}  
	
}
