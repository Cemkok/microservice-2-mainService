/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.request;

import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Bit.microservice2mainService.core.constants.Logging;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is the retrofit configuration class, OkHttpClient is configured for HTTP requests and defined as the infrastructure of the retrofit.
 * @author Cem Kok
 * @Date   13 Tem 2022
 * @Time   17:32:49 
 * @see
 */
@Slf4j
@Configuration
public class RetrofitConfiguration {

	 
	  @Value("${retrofit.timeout}")
	  private Long TIMEOUT_IN_SECS;
	  
	  /**
	   *This method creates a secure http client.
	   * @param secureKeyUsername
	   * @param secureKeyPassword
	   * @return Secure client
	   */
	  @Bean
	  public OkHttpClient secureKeyClient(
			  @Value("${service.security.secure-key-username}") String secureKeyUsername, 
			  @Value("${service.security.secure-key-password}")	String secureKeyPassword) 
	  {
		  log.info("[secureKeyClient method is called ]--" + "[input parameter ="+secureKeyUsername+secureKeyPassword+"]");
			
			Logging.internalLogDetail();
	 

		  return createDefaultClientBuilder().addInterceptor(
				  interceptor -> interceptor.proceed(interceptor.request().newBuilder()
						  .header("Authorization", Credentials.basic(secureKeyUsername, secureKeyPassword))
						  .build()))
				  .build();
				  
		  
	  }
	 
	  /**
	   * This method creates an OkHttp wrapper
	   * @param secureKeyClient
	   * @param gson
	   * @return Retrofit wrapper
	   */
	  @Bean
	   public Retrofit.Builder secureKeyBuilder(OkHttpClient secureKeyClient, Gson gson){
		  log.info("[secureKeyBuilder method is called ]--" + "[input parameter ="+secureKeyClient+"+"+gson+"]");
			
			Logging.internalLogDetail();
		   return new Retrofit.Builder().client(secureKeyClient)
				   .addConverterFactory(GsonConverterFactory.create(gson));
		   
	   }
	  /**
	   * This method builds OkHttp client. Sets the time-out for the connection.
	   * @return Sets the timeout for the connection.
	   */
	  private OkHttpClient.Builder createDefaultClientBuilder(){
		  log.info("[createDefaultClientBuilder method is called ]--" + "[input parameter =no args"+ "]");
			
			Logging.internalLogDetail();
	  
	  return new OkHttpClient.Builder()
			  .connectTimeout(TIMEOUT_IN_SECS,TimeUnit.SECONDS) 
			  .readTimeout(TIMEOUT_IN_SECS, TimeUnit.SECONDS)
	          .writeTimeout(TIMEOUT_IN_SECS, TimeUnit.SECONDS);
	  
	  
	  }
	  /**
	   *This method creates a request "@Bean" for the external service.
	   *
	   * @param secureKeyBuilder
	   * @param baseUrl
	   * @return Secure Key Builder
	   */
	  @Bean
	  public IExternalServiceRequest externalServiceRequest(Retrofit.Builder secureKeyBuilder,
	  
	  @Value("${external.service.url}")String baseUrl) 
	  {
		  
		  log.info("[externalServiceRequest method is called ]--" + "[input parameter =secureKeyBuilder , "+  baseUrl+ "]");
			
			Logging.internalLogDetail();
	  
	  return secureKeyBuilder
			  .baseUrl(baseUrl)
			  .build().create(IExternalServiceRequest.class);
	  }
	   
	  
	
}
 