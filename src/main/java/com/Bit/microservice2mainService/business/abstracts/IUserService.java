/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.Bit.microservice2mainService.entities.User;

/**
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   13:42:22
 * @see
 */
public interface IUserService {

	/**
	 * @param user
	 * @return
	 */
	User addUser(User user);

	/**
	 * @param username
	 * @return
	 */
	Optional<User> findByUsername(String username);

	/**
	 * @return
	 */
	List<User> findAllUsers();

}
