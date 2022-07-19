/**
 * Spring Boot version 2.6.2 Build Automation Tool is Maven. Java version 1.8
 */

  package com.Bit.microservice2mainService.business.abstracts;
  
 



import com.google.gson.JsonElement;
  
 /**
	 * @author Cem Kok
	 * @Date 13 Tem 2022
	 * @Time 18:33:34
	 * @see
	 */

  public interface IExternalService  {
  
 /**
	 * @return
	 */

	  JsonElement getAllCustomers(int pageNo, int pageSize);
  
 /**
	 * @param requestBody
	 * @return
	 */

      JsonElement addCustomer(JsonElement requestBody);
  
 /**
	 * @param customerId
	 */
		  void deleteCustomer(int customerId);

/**
 * @param id
 * @return
 */	
		  JsonElement getByCustomerIdResult(Long id);
		  
		  }
		 