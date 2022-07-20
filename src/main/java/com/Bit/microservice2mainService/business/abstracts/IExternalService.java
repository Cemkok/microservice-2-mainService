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
 * @param id
 * @return
 */	
		  JsonElement getByCustomerId(Long id);

/**
 * @param companyName
 * @return
 */
JsonElement findAllFilteredByCompanyName(String companyName);

/**
 * @param customerId
 * @return
 */
JsonElement deleteCustomer(Long customerId);
		  
		  }
		 