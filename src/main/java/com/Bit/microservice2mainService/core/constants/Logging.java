/**
 * Spring Boot version 2.4.5
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.core.constants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cem Kok
 * @Date   18 Tem 2022
 * @Time   16:37:13
 * @see
 */
@Slf4j
public class Logging {
	/**
	 * This method delays log processing by 1 seconds.
	 * 
	 */
	public static String internalLogDetail() {
		try {
			log.info("log delayed by 0.1 sec. ");
			Thread.sleep(100);
			
		} catch (InterruptedException e) {
			log.error("Hata : {}", e);
		}
		return "";
	}

}
