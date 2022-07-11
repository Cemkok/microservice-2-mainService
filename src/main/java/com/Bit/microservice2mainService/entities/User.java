/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * @author cemko
 * @Date   11 Tem 2022
 * @Time   20:01:58
 * @See
 */

@Data
@Entity
@Table(name="users")

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (name= "username", unique=true, nullable=false, length=100)
	private String username;
	
	@Column (name= "password", nullable=false, length=100)
	private String password;
	
	@Column (name="name", nullable=false)
	private String name;
	
	@Column(name="create_time")
	private LocalDateTime createTime;
	
	
	

}
