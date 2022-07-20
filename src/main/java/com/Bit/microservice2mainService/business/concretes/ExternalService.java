/**
 * Spring Boot version 2.6.2 Build Automation Tool is Maven. Java version 1.8
 */

  package com.Bit.microservice2mainService.business.concretes;
  

  

import org.springframework.beans.factory.annotation.Autowired;

import
  org.springframework.stereotype.Service;
  
  import com.Bit.microservice2mainService.business.abstracts.IExternalService;
  import com.Bit.microservice2mainService.request.IExternalServiceRequest;
  import com.Bit.microservice2mainService.util.RetrofitUtils;
import com.Bit.microservice2mainService.util.constants.Logging;
import
  com.google.gson.JsonElement;

import lombok.extern.slf4j.Slf4j;
  
 /**
	 * @author Cem Kok
	 * @Date 13 Tem 2022
	 * @Time 18:33:57
	 * @see
	 */

  @Slf4j
  @Service 
  public class ExternalService implements IExternalService {
	  
	  @Autowired
	  private IExternalServiceRequest externalServiceRequest;
	  	
	  
		  
		  @Override public JsonElement addCustomer(JsonElement requestBody) {
				log.info("[ExternalService addCustomer method is called ]--" + "[input parameter ="+requestBody+"]");
						
						Logging.internalLogDetail();
				 
		  
		  return
		  RetrofitUtils.executeInBlock(externalServiceRequest.saveCustomer(requestBody)
		  );
		  
		  }
		  
		  
		
		@Override public JsonElement deleteCustomer(Long customerId) {
			  log.info("[ExternalService deleteCustomer method is called ]--" + "[input parameter ="+customerId+"]");
				
				Logging.internalLogDetail();
		 
		 return RetrofitUtils.executeInBlock(externalServiceRequest.deleteCustomer(customerId )); }
		  
		  
		  
		  @Override public JsonElement getAllCustomers(int pageNo, int pageSize) {

			  log.info("[ExternalService getAllCustomers method is called ]--" + "[input parameter =pageNo-pageSize"+pageNo+pageSize+"]");
				
				Logging.internalLogDetail(); 
				return
		  RetrofitUtils.executeInBlock(externalServiceRequest.getAllCustomers(pageNo, pageSize)); } 
  
  
  	@Override public JsonElement getByCustomerId(Long id) {
		  log.info("[ExternalService getByCustomerId method is called ]--" + "[input parameter = "+id+"]");
			
			Logging.internalLogDetail(); 
	  
	  return RetrofitUtils.executeInBlock(externalServiceRequest.getCustomerById(id)); } 
 
  

@Override public JsonElement findAllFilteredByCompanyName(String companyName) {
	  log.info("[findAllFilteredByCompanyName  method is called ]--" + "[input parameter ="+companyName+"]");
		
		Logging.internalLogDetail(); 
  
  return RetrofitUtils.executeInBlock(externalServiceRequest.findAllFilteredByCompanyName(companyName)); } }
	
  	
  
		  
		  
		  
		  
		  
		 