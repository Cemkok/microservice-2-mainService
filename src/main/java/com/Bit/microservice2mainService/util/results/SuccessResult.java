package com.Bit.microservice2mainService.util.results;
	
public class SuccessResult extends Result{
	
	public SuccessResult() {
		super(true);
	} 
		/**
		 * 
		 * @param message
		 */
	public SuccessResult(String message) {
		super(true,message);
	}  

}
