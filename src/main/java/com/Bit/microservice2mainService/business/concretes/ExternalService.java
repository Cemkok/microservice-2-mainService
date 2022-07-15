/**
 * Spring Boot version 2.6.2 Build Automation Tool is Maven. Java version 1.8
 */

  package com.Bit.microservice2mainService.business.concretes;
  
  import java.util.List;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Service;
  
  import com.Bit.microservice2mainService.business.abstracts.IExternalService;
  import com.Bit.microservice2mainService.request.IExternalServiceRequest;
  import com.Bit.microservice2mainService.util.RetrofitUtils; import
  com.google.gson.JsonElement;
  
 /**
	 * @author Cem Kök
	 * @Date 13 Tem 2022
	 * @Time 18:33:57
	 * @See
	 */

  
  @Service 
  public class ExternalService implements IExternalService {
	  
	  @Autowired
	  private IExternalServiceRequest externalServiceRequest;
	  	
	  
		  
		  @Override public JsonElement saveCustomer(JsonElement requestBody) {
		  
		  return
		  RetrofitUtils.executeInBlock(externalServiceRequest.saveCustomer(requestBody)
		  );
		  
		  }
		  
		  
		  
		  @Override public void deleteCustomer(int customerId) {
		  RetrofitUtils.executeInBlock(externalServiceRequest.deleteCustomer(customerId
		  )); }
		  
		  
		  
		  @Override public List<JsonElement> getAllCustomers() {
		  System.out.println("ben çalıştım"); return
		  RetrofitUtils.executeInBlock(externalServiceRequest.getAllCustomers()); } }
		  
		  
		  
		  
		  
		 