/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.request;

import java.util.List;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Cem Kök
 * @Date   13 Tem 2022
 * @Time   18:15:51
 * @See
 */
public interface IExternalServiceRequest {
	@POST("/api/customer")
	Call<JsonElement> saveCustomer(@Body JsonElement requestBody);
	
	@DELETE("/api/customer/{customerId}")
	Call<Void> deleteCustomer(@Path("customerId") int customerId);
	
	@GET("/api/customer")
	Call<List<JsonElement>> getAllCustomers();
	
	


}
