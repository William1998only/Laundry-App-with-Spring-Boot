package com.lawencon.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.laundry.model.Customers;
import com.lawencon.laundry.service.CustomerService;

/**
 * 
 * @author WILLIAM
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customersService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllCustomer(){
		try {
			List<Customers> customers = customersService.getAllCustomer();
			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/nik/{nik}")
	public ResponseEntity<?> getCustomerByNik(@PathVariable("nik") String nik){
		try {
			Customers customer = customersService.searchCustomerByNik(nik);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertCustomer(@RequestBody String body) {
		try {
			Customers customer = new ObjectMapper().readValue(body, Customers.class);
			customersService.insertCustomer(customer);
			return new ResponseEntity<>("Input berhasil", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateCustomer(@RequestBody String body) {
		try {
			Customers customer = new ObjectMapper().readValue(body,  Customers.class);
			customersService.updateCustomer(customer);
			return new ResponseEntity<>("update berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{nik}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("nik") String nik) {
		try {
			Long id = customersService.getCustomerbyNik(nik);
			customersService.deleteCustomer(id);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("NIK tidak terdaftar", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
}
