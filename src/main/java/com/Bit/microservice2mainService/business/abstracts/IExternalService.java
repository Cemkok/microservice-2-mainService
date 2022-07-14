/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.business.abstracts;

import java.util.List;

import com.google.gson.JsonElement;

/**
 * @author Cem Kök
 * @Date   13 Tem 2022
 * @Time   18:33:34
 * @See
 */
public interface IExternalService {

	/**
	 * @return
	 */
	List<JsonElement> getAllCustomers();

	/**
	 * @param requestBody
	 * @return
	 */
	JsonElement saveCustomer(JsonElement requestBody);

	/**
	 * @param customerId
	 */
	void deleteCustomer(int customerId);

}
