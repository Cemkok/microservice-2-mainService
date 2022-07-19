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
 * @author Cem Kok
 * @Date   13 Tem 2022
 * @Time   18:15:51
 * @see
 */


public interface IExternalServiceRequest {
	@POST("/api/customer")
	Call<JsonElement> saveCustomer(@Body JsonElement requestBody);
	
	@DELETE("/api/customer/delete/{customerId}")
	Call<Void> deleteCustomer(@Path("customerId") int customerId);
	
	@GET("/api/customer/getAllByPage/{pageNo}/{pageSize}")
	Call<JsonElement> getAllCustomers(@Path("pageNo") int pageNo, @Path("pageSize") int pageSize);
	
	@GET("/api/customer/getById/{id}")
	Call<JsonElement> getCustomerById(@Path("id") Long customerId );
	@GET("/api/customer/getByIdResult/{id}")
	Call<JsonElement> getCustomerByIdResult(@Path("id") Long customerId );
	
	@GET("/api/customer/{companyName}")
	Call<List<JsonElement>> getCustomerFilteredByCompanyName(@Path("companyName") String companyName);
	
	@GET("/api/customer/getAllSortedDesc")
	Call<List<JsonElement>> getCustomerSortedDesc();

	/**
	 * @param id
	 * @return
	 */
	@GET("/api/customer/getByIdResult/{id}")
	Call <JsonElement> getByCustomerIdResult(@Path("id") Long customerId );
	
	
	
	
	
	


}
