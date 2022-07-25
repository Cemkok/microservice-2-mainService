/**
 * Spring Boot version 2.6.2 Build Automation Tool is Maven. Java version 1.8
 */

package com.Bit.microservice2mainService.business.abstracts;

import com.google.gson.JsonElement;

/**
 * This is an interface of ExternalService class.
 * 
 * @author Cem Kok
 * @Date 13 Tem 2022
 * @Time 18:33:34
 * @see
 * 
 *      <p>
 *      This interface provides us with a template to implement,
 *      <strong>dependency injection </strong> for the concrete class, and
 *      communication between the data <strong>access</strong> layer and the
 *      <strong>presentation</strong> layer.
 */

public interface IExternalService {

	/**
	 * This method gets all customers.
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return DataResult { a customer list, message, success status}
	 */

	JsonElement getAllCustomers(int pageNo, int pageSize);

	/**
	 * This method adds a new customer.
	 * 
	 * @param requestBody
	 * @return DataResult { a list sorted by customer name, message, success status}
	 */

	JsonElement addCustomer(JsonElement requestBody);

	/**
	 * This method find customer by id
	 * 
	 * @param id
	 * @return DataResult { a customer by customer name, message, success status}
	 */
	JsonElement getByCustomerId(Long id);

	/**
	 * This method filters by company name
	 * 
	 * @param companyName
	 * @return DataResult { filtered list of customers by company name, message,
	 *         success status}
	 */
	JsonElement findAllFilteredByCompanyName(String companyName);

	/**
	 * This method returns a list of customers sorted by company name in descending
	 * order.
	 * 
	 * @param id
	 * @return DataResult { a list of customers sorted by company name, message,
	 *         success status}
	 */
	JsonElement getCustomerSortedDesc();

	/**
	 * This method deletes customer by id.
	 * 
	 * @param customerId
	 * @return If the customer exists and is deleted, successDataResult DataResult {
	 *         customer, message, true} is returned, if there is no customer,
	 *         ErrorResult { message, false} is returned.
	 */
	JsonElement deleteCustomer(Long customerId);

}
