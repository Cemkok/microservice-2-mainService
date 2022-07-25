/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.Bit.microservice2mainService.core.results.DataResult;
import com.Bit.microservice2mainService.core.results.Result;
import com.Bit.microservice2mainService.entities.User;

/**
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   13:42:22
 * @see
 */
public interface IUserService {

	/**
	 * This method adds a user.
	 * @param user
	 * @return 
	 */
	Result addUser(User user);

	/**
	 * This method finds user by name
	 * @param username
	 * @return Optional<User> Object
	 */
	Optional<User> findByUsername(String username);
	
 
	/**
	 * This method returns all users.
	 * @return All users
	 */
	DataResult<List<User>> findAllUsers();

}
