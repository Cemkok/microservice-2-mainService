/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Bit.microservice2mainService.core.constants.Logging;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   18:47:42
 * @see
 */
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter{
	
	 
	@Autowired
	private IJwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		 log.info("[doFilterInternal method is called ]--" + "[input parameter =no args"+ "]");
			
			Logging.internalLogDetail();
		Authentication authentication = jwtProvider.getAuhentication(request);
		
		if(authentication != null && jwtProvider.isTokenValid(request)) 
		{
		 SecurityContextHolder.getContext().setAuthentication(authentication);	
		}
		
		filterChain.doFilter(request, response);
		
	}
	}


