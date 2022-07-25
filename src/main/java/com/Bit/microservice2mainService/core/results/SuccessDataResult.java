package com.Bit.microservice2mainService.core.results;

/**
 * This class is used to return a SuccessDataResult object {true, T data, String message}.
 * @author Cem Kok
 * @Date   25 Tem 2022
 * @Time   21:14:13
 *
 */
public class SuccessDataResult<T> extends DataResult<T> {
	/**
	 * 
	 * @param data
	 * @param message
	 */
	public SuccessDataResult(T data, String message) {
		super(data, true, message);
	}

	/**
	 * 
	 * @param data
	 */
	public SuccessDataResult(T data) {
		super(data, true);
	}

	/**
	 * 
	 * @param message
	 */
	public SuccessDataResult(String message) {
		super(null, true, message);
	}

	public SuccessDataResult() {
		super(null, true);
	}

}
