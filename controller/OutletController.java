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
import com.lawencon.laundry.model.Outlets;
import com.lawencon.laundry.service.OutletService;

/**
 * 
 * @author WILLIAM
 *
 */
@RestController
@RequestMapping("/outlet")
public class OutletController {
	
	@Autowired
	private OutletService outletService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllOutlet(){
		try {
			List<Outlets> outlets = outletService.getAllOutlets();
			return new ResponseEntity<>(outlets, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getOutletByCode(@PathVariable("kode") String kode){
		try {
			Outlets outlet = outletService.searchOutletsByCode(kode);
			return new ResponseEntity<>(outlet, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertOutlet(@RequestBody String body) {
		try {
			Outlets outlet = new ObjectMapper().readValue(body, Outlets.class);
			outletService.insertOutletsData(outlet);
			return new ResponseEntity<>("Input berhasil", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateOutlet(@RequestBody String body) {
		try {
			Outlets outlet = new ObjectMapper().readValue(body,  Outlets.class);
			outletService.updateOutlet(outlet);
			return new ResponseEntity<>("update berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deleteOutlet(@PathVariable("kode") String kode) {
		try {
			Outlets outlet = outletService.searchOutletsByCode(kode);
			outletService.deleteOutlet(outlet);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Delete gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
}
