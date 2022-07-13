/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.request;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Cem Kök
 * @Date   13 Tem 2022
 * @Time   17:32:49 
 * @See
 */
public class RetrofitConfiguration {

	 
	  @Value("${retrofit.timeout}")
	  private Long TIMEOUT_IN_SECS;
	  @Bean
	  public OkHttpClient secureKeyClient(
			  @Value("${service.security.secure-key-username}") String secureKeyUsername, 
			  @Value("${service.security.secure-key-password}")	String secureKeyPassword) 
	  {
		  return createDefaultClientBuilder().addInterceptor(
				  interceptor -> interceptor.proceed(interceptor.request().newBuilder()
						  .header("Authorization", Credentials.basic(secureKeyUsername, secureKeyPassword))
						  .build()))
				  .build();
				  
		  
	  }
	  @Bean
	   public Retrofit.Builder secureKeyBuilder(OkHttpClient secureKeyClient, Gson gson){
		   return new Retrofit.Builder().client(secureKeyClient)
				   .addConverterFactory(GsonConverterFactory.create(gson));
		   
	   }
	  
	  private OkHttpClient.Builder createDefaultClientBuilder(){
	  
	  return new OkHttpClient.Builder()
			  .connectTimeout(TIMEOUT_IN_SECS,TimeUnit.SECONDS) 
			  .readTimeout(TIMEOUT_IN_SECS, TimeUnit.SECONDS)
	          .writeTimeout(TIMEOUT_IN_SECS, TimeUnit.SECONDS);
	  
	  
	  }
	  @Bean
	  public IExternalServiceRequest customerServiceRequest(Retrofit.Builder secureKeyBuilder,
	  
	  @Value("${external.service.url}")String baseUrl) 
	  {
	  
	  return secureKeyBuilder
			  .baseUrl(baseUrl)
			  .build().create(IExternalServiceRequest.class);
	  }
	   
	  
	
}
 