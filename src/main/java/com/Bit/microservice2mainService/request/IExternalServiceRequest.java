/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.request;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * This interface sends a request to the external service and receives a
 * response, thanks to the call method of the retrofit.
 * 
 * @author Cem Kok
 * @Date 13 Tem 2022
 * @Time 18:15:51
 * @see *
 *      <table border="1">
 *      <body>
 *      <tr>
 *      <td><strong>Annotations </strong></td>
 *      <td></td>
 *      </tr>
 *      <tr>
 *      <td>@DELETE</td>
 *      <td>Make a DELETE request.</td>
 *      </tr>
 * 
 *      <tr>
 *      <td>@POST</td>
 *      <td>Make a POST request.</td>
 * 
 *      </tr>
 *      <tr>
 *      <td>@GET</td>
 *      <td>Make a GET request.</td>
 *      </tr>
 *      <tr>
 *      <td>@Body</td>
 *      <td>Use this annotation on a service method param when you want to
 *      directly control the request body of a POST/PUT request (instead of
 *      sending in as request parameters or form-style request body). The object
 *      will be serialized using the {@link Retrofit Retrofit} instance
 *      {@link Converter Converter} and the result will be set directly as the
 *      request body.
 *
 *      <p>
 *      Body parameters may not be {@code null}.</td>
 * 
 *      </tr>
 * 
 *      <tr>
 *      <td>@Path</td>
 *      <td>Named replacement in a URL path segment. Values are converted to
 *      strings using {@link Retrofit#stringConverter(Type, Annotation[])} (or
 *      {@link Object#toString()}, if no matching string converter is installed)
 *      and then URL encoded.
 *
 *      </td>
 *      </tr>
 * 
 * 
 * 
 *      </body>
 *      </table>
 */

public interface IExternalServiceRequest {

	/**
	 * This method goes to the controller of the external service in the specified
	 * path and triggers the method in that path.
	 * 
	 * @param requestBody
	 * @return JsonElement { returns the response from the external service}.
	 */
	@POST("/api/customer/add")
	Call<JsonElement> saveCustomer(@Body JsonElement requestBody);

	/**
	 * This method goes to the controller of the external service in the specified
	 * path and triggers the method in that path.
	 * 
	 * @param customerId
	 * @return JsonElement { returns the response from the external service}
	 */
	@DELETE("/api/customer/deleteById/{customerId}")
	Call<JsonElement> deleteCustomer(@Path("customerId") Long customerId);

	/**
	 * This method goes to the controller of the external service in the specified
	 * path and triggers the method in that path.
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return JsonElement { returns the response from the external service}
	 */
	@GET("/api/customer/getAllByPage/{pageNo}/{pageSize}")
	Call<JsonElement> getAllCustomers(@Path("pageNo") int pageNo, @Path("pageSize") int pageSize);

	/**
	 * This method goes to the controller of the external service in the specified
	 * path and triggers the method in that path.
	 * 
	 * @param customerId
	 * @return JsonElement { returns the response from the external service}
	 */
	@GET("/api/customer/getById/{id}")
	Call<JsonElement> getCustomerById(@Path("id") Long customerId);

	/**
	 * This method goes to the controller of the external service in the specified
	 * path and triggers the method in that path.
	 * 
	 * @param companyName
	 * @return JsonElement { returns the response from the external service}
	 */
	@GET("/api/customer/filteredByCompanyName/{companyName}")
	Call<JsonElement> findAllFilteredByCompanyName(@Path("companyName") String companyName);

	/**
	 * This method goes to the controller of the external service in the specified
	 * path and triggers the method in that path.
	 * 
	 * @return JsonElement { returns the response from the external service}
	 */
	@GET("/api/customer/getAllSortedByCompanyName")
	Call<JsonElement> getCustomerSortedDesc();

}
