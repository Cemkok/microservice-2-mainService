/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.core;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

/**
 * This class is created so that the execute method that will be applied in more than one method is not repeated.
 * @author Cem Kok
 * @Date   14 Tem 2022
 * @Time   19:13:39
 * @See
 *  <table border="1">
 *      <body>
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
 *      </body>
 *      </table>
 *      <br>
 */
@Slf4j

public class RetrofitUtils {
	/**
	 * This method implements the execute method to execute the Call method synchronously.
	 * @param <T>
	 * @param request
	 * @return  request.execute().body()
	 */
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
