/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.request;



import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Cem Kok
 * @Date   13 Tem 2022
 * @Time   18:15:51
 * @see
 */


public interface IExternalServiceRequest {
	
	@POST("/api/customer/add")
	Call<JsonElement> saveCustomer(@Body JsonElement requestBody);
	
	
	@DELETE("/api/customer/deleteById/{customerId}")
	Call<JsonElement> deleteCustomer(@Path("customerId") Long customerId);
	
	@GET("/api/customer/getAllByPage/{pageNo}/{pageSize}")
	Call<JsonElement> getAllCustomers(@Path("pageNo") int pageNo, @Path("pageSize") int pageSize);
	
	@GET("/api/customer/getById/{id}")
	Call<JsonElement> getCustomerById(@Path("id") Long customerId );
	
	@GET("/api/customer/{companyName}")
	Call<JsonElement> findAllFilteredByCompanyName(@Path("companyName") String companyName);
	
	@GET("/api/customer/getAllSortedByCompanyName")
	Call<JsonElement> getCustomerSortedDesc();


	
	
	
	
	
	
	


}
