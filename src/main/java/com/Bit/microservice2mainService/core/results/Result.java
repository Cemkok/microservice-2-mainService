package com.Bit.microservice2mainService.core.results;



public class Result {
   private boolean success;
   private String message;
   
   /**
    * 
    * @param success
    */
   public Result(boolean success) {
	   this.success = success;
   }
   /**
    * 
    * @param success
    * @param message
    */
   public Result(boolean success,String message) {
	   this(success);
	   this.message = message;
   }
   /**
    * 
    * @return boolean success status
    */
   public boolean isSuccess() {
	   return this.success;
   }
   
   /**
    * 
    * @return  message
    */
   public String getMessage() {
	   return this.message;
   }
}