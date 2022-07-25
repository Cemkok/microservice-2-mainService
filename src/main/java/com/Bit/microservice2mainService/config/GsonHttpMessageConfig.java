/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.converter.json.GsonHttpMessageConverter;


import com.Bit.microservice2mainService.core.constants.Logging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is the Gson configuration class.
 * 
 * @author Cem Kok
 * @Date 13 Tem 2022
 * @Time 17:06:19
 * @see *
 *      <table border="1">
 *      <body>
 *      <tr>
 *      <td><strong>Annotations </strong></td>
 *      <td></td>
 *      </tr>
 * 
 *      <tr>
 *      <td>@Slf4j</td>
 *      <td>* Causes lombok to generate a logger field.
 *      <p>
 *      Complete documentation is found at
 *      <a href="https://projectlombok.org/features/Log">the project lombok
 *      features page for lombok log annotations</a>.
 *      <p>
 *      </td>
 * 
 *      </tr> 
 *      <tr>
 *      <tr>
 *      <td>@Configuration</td>
 *      <td>* Indicates that a class declares one or more {@link Bean @Bean}
 *      methods and may be processed by the Spring container to generate bean
 *      definitions and service requests for those beans at runtime
 *
 *     </td>
 * 
 *      </tr>
 *      
 *      <tr>
 *      <td>@Bean</td>
 *      <td>
 *      Indicates that a method produces a bean to be managed by the Spring container.
 *      </td>
 *      </tr>
 * 
 *      </body>
 *      </table>
 *      <br>
 * 
 */

@Slf4j
@Configuration
public class GsonHttpMessageConfig {

	/**
	 * This method performs date serialization and deserialization.
	 * 
	 * @return serialized or deserialized date
	 */
	@Bean
	public GsonBuilder gsonBuilder() {
		log.info("[gsonBuilder method is called ]");
		Logging.internalLogDetail();

		return new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class,
						(JsonSerializer<LocalDateTime>) (date, type, context) -> date == null ? null
								: new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type,
						context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}

	/**
	 * This method create Gson object
	 * 
	 * @param gsonBuilder
	 * 
	 * @return Gson Object
	 * 
	 */
	@Bean
	public Gson gson(GsonBuilder gsonBuilder) {
		log.info("[gson method is called ]--" + "[input parameter = " + gsonBuilder + "]");

		Logging.internalLogDetail();

		return gsonBuilder.create();
	}

	/**
	 * This method provides custom message converter.
	 * 
	 * @param gson
	 * @return GsonHttpMessageConverter
	 */
	@Bean
	public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
		log.info("[gsonHttpMessageConverter method is called ]--" + "[input parameter = " + gson + "]");

		Logging.internalLogDetail();

		GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
		converter.setGson(gson);
		return converter;
	}

}
