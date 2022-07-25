package com.Bit.microservice2mainService.core.results;
/**
 *This class is used to return a DataResult object {T data, boolean success, String message}.
 * @author Cem Kok
 * @Date   18 Tem 2022
 * @Time   11:00:31
 */
public class DataResult<T> extends Result {
	
	private T data;
	/**
	 * 
	 * @param data
	 * @param success
	 * @param message
	 */
	public DataResult(T data, boolean success, String message) {
		super(success, message);
		this.data = data;
	}
	/**
	 * 
	 * @param data
	 * @param success
	 */
	public DataResult(T data, boolean success) {
		super(success);
		this.data = data;
	}
	/**
	 * 
	 * @return Type data
	 */
	public T getData() {
		return this.data;
	}
	/**
	 * @return
	 */
	public boolean isPresent() {
		
		return false;
	}

	

	
	
	

} 


