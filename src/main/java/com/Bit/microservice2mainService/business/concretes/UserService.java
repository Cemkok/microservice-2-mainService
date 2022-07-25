/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Bit.microservice2mainService.business.abstracts.IUserService;
import com.Bit.microservice2mainService.core.constants.Logging;
import com.Bit.microservice2mainService.core.constants.Messages;
import com.Bit.microservice2mainService.core.results.DataResult;
import com.Bit.microservice2mainService.core.results.Result;
import com.Bit.microservice2mainService.core.results.SuccessDataResult;
import com.Bit.microservice2mainService.core.results.SuccessResult;
import com.Bit.microservice2mainService.dataAccess.UserDao;
import com.Bit.microservice2mainService.entities.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   13:42:07
 * @see
 */	

@Slf4j
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
	public Result addUser(User user) {
		log.info("[addUser method is called ]--" + "[input parameter = " +user+ "]");

				Logging.internalLogDetail();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreateTime(LocalDateTime.now());
		userDao.save(user);
		
		return  new SuccessDataResult<User>(user, Messages.addUser);
				
		
	}
	
	
	@Override
	 public Optional< User> findByUsername(String username){
		log.info("[findByUsername method is called ]--" + "[input parameter =" +username+ "]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(userDao.findByUsername(username))
				+ "]");

				Logging.internalLogDetail();
		 
		 return userDao.findByUsername(username);
	 }
	
	
	@Override
	public DataResult<List<User>> findAllUsers(){
		log.info("[findAllUsers method is called ]--" + "[input parameter =no args"+"]--"+ "[output parameter = "
				+ ToStringBuilder.reflectionToString(userDao.findAll())
				+ "]");

				Logging.internalLogDetail();
		 
		return new SuccessDataResult<List<User>>(userDao.findAll());
	}
	

}
