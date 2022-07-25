/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Bit.microservice2mainService.entities.User;

/**
 * This interface acts as the data access layer
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   13:23:21
 * @see
 * <p>This interface acts as the data access layer . It is a structural pattern that
 *      allows us to isolate the application/business layer from the persistence
 *      layer (usually a relational database but could be any other persistence
 *      mechanism) using an abstract API.
 * 
 *      The API hides from the application all the complexity of performing CRUD
 *      operations in the underlying storage mechanism. This permits both layers
 *      to evolve separately without knowing anything about each other.</p>
 */
public interface UserDao extends JpaRepository<User, Long> {
	
	/**
	 * This method finds users by username.
	 * @param username
	 * @return  Optional<User>
	 */
	Optional<User> findByUsername(String username);
	
	

}
