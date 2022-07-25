package com.Bit.microservice2mainService.core.results;
/**
 *  This class is used to return a SuccessResult object {true, String message}.
 * @author Cem Kok
 * @Date   25 Tem 2022
 * @Time   21:15:26
*
 */
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
