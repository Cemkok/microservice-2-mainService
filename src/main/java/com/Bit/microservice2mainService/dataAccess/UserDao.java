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
 * @author cemko
 * @Date   12 Tem 2022
 * @Time   13:23:21
 * @See
 */
public interface UserDao extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	

}
