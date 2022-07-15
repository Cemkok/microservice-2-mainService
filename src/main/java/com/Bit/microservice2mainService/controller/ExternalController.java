/**
 * Spring Boot version 2.6.2 Build Automation Tool is Maven. Java version 1.8
 */

package com.Bit.microservice2mainService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bit.microservice2mainService.business.abstracts.IExternalService;
import com.google.gson.JsonElement;

/**
 * @author Cem Kök
 * @Date 14 Tem 2022
 * @Time 19:41:40
 * @See
 */

@RestController

@RequestMapping("gateway/customer")
public class ExternalController {

	@Autowired
	private IExternalService externalService;

	@PostMapping("/add")
	public ResponseEntity<?> saveCustomer(@RequestBody JsonElement customer) {

		return ResponseEntity.ok(externalService.saveCustomer(customer));
	}

	@DeleteMapping("{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) {

		externalService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllCustomers() {

		return ResponseEntity.ok(externalService.getAllCustomers());
	}
}
