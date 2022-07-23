package com.Bit.microservice2mainService.core.results;

public class ErrorDataResult<T> extends DataResult<T> {
 /**
  * 
  * @param data
  * @param message
  */
	public ErrorDataResult(T data, String message) {
		super(data, false ,message);
	}
	
	/**
	 * 
	 * @param data
	 */
	public ErrorDataResult(T data) {
		super(data,false);
	}
	
	/**
	 * 
	 * @param message
	 */
	public ErrorDataResult(String message) {
		super(null, false ,message);
	}
	
	public ErrorDataResult() {
		super(null, false);
	}
}
