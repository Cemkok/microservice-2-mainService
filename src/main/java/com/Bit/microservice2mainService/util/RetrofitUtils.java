/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.util;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Cem Kök
 * @Date   14 Tem 2022
 * @Time   19:13:39
 * @See
 */
@Slf4j
public class RetrofitUtils {
	public static <T> T executeInBlock(Call<T> request)
	{
		try {
			Response<T> response = request.execute();
			if (!response.isSuccessful()) {
				log.error("Request completed unsuccessfully with statusCode:{}" + "and reason:{}", response.code(),
						response.errorBody().string());
			}
			return response.body();
		} catch (IOException e) {

			throw new RuntimeException(e.getCause());

		}
	}

}
