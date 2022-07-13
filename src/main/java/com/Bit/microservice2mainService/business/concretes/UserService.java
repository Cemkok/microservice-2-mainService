/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Bit.microservice2mainService.business.abstracts.IUserService;
import com.Bit.microservice2mainService.dataAccess.UserDao;
import com.Bit.microservice2mainService.entities.User;

/**
 * @author cemko
 * @Date   12 Tem 2022
 * @Time   13:42:07
 * @See
 */	

@Service
public class UserService implements IUserService{
	
	private UserDao userDao;
	private PasswordEncoder passwordEncoder;
	
	/**
	 * @param userDao
	 * @param passwordEncoder
	 */
	@Autowired
	public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
		super();
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	
	@Override
	public User addUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreateTime(LocalDateTime.now());
		return  userDao.save(user);
		
	}
	
	
	@Override
	 public Optional<User> findByUsername(String username){
		 
		 return userDao.findByUsername(username);
	 }
	
	
	@Override
	public List<User> findAllUsers(){
		return userDao.findAll();
	}
	

}
