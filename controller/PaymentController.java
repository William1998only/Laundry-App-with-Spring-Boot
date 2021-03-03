package com.lawencon.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.laundry.model.Payments;
import com.lawencon.laundry.service.PaymentService;

/**
 * 
 * @author WILLIAM
 *
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllPayment(){
		try {
			List<Payments> payments = paymentService.getPaymentList();
			return new ResponseEntity<>(payments, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getPaymentByCode(@PathVariable("kode") String kode){
		try {
			Payments payment = paymentService.searchPaymentByCode(kode);
			return new ResponseEntity<>(payment, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertPayment(@RequestBody String body) {
		try {
			Payments payment = new ObjectMapper().readValue(body, Payments.class);
			paymentService.insertPaymentType(payment);;
			return new ResponseEntity<>("Input berhasil", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping
	public ResponseEntity<?> updatePayment(@RequestBody String body) {
		try {
			Payments payment = new ObjectMapper().readValue(body,  Payments.class);
			paymentService.updatePayment(payment);
			return new ResponseEntity<>("update berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deletePayment(@PathVariable("kode") String kode) {
		try {
			paymentService.deletePayment(kode);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Delete gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}

}
