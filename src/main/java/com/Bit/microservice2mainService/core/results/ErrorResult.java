package com.Bit.microservice2mainService.core.results;
/**
 * This class is used to return a ErrorResult object {false, String message}.
 * @author Cem Kok
 * @Date   25 Tem 2022
 * @Time   21:09:58
 */
public class ErrorResult  extends Result{
	public ErrorResult() {
		super(false);
	} 
	/**
	 * 
	 * @param message
	 */
	public ErrorResult(String message) {
		super(false,message);
	} 



}