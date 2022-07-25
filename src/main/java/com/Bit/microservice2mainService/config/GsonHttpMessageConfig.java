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
 * @author Cem Kok
 * @Date   13 Tem 2022
 * @Time   17:06:19
 * @see
 */
@Slf4j
@Configuration
public class GsonHttpMessageConfig {

	
	/**
	 * Thsi method performs date serialization and deserialization.
	 * @return serialized or deserialized date
	 */
    @Bean
    public GsonBuilder gsonBuilder()
    {
    	log.info("[gsonBuilder method is called ]");
				Logging.internalLogDetail();
    	
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (date, type, context) ->
                        date == null ? null : new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, context) ->
                        LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
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
    public Gson gson(GsonBuilder gsonBuilder)
    {
    	log.info("[gson method is called ]--" + "[input parameter = " + gsonBuilder + "]");

				

				Logging.internalLogDetail();

        return gsonBuilder.create();
    }
    
    /**
     * This method provides custom message converter.
     * @param gson
     * @return GsonHttpMessageConverter
     */
    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson)
    {
    	log.info("[gsonHttpMessageConverter method is called ]--" + "[input parameter = " + gson + "]");

		

		Logging.internalLogDetail();

        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }

}
