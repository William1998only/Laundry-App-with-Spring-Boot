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
import com.lawencon.laundry.model.LaundryTypes;
import com.lawencon.laundry.service.LaundryTypeService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/laundrytype")
public class LaundryTypeController {
	
	@Autowired
	LaundryTypeService laundryTypeService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllType(){
		try {
			List<LaundryTypes> laundryTypes = laundryTypeService.getListLaundryTypes();
			return new ResponseEntity<>(laundryTypes, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getLaundryTypeByCode(@PathVariable("kode") String kode){
		try {
			LaundryTypes laundryType = laundryTypeService.searchLaundryTypeByCode(kode);
			return new ResponseEntity<>(laundryType, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertLaundryType(@RequestBody String body) {
		try {
			LaundryTypes laundryType = new ObjectMapper().readValue(body, LaundryTypes.class);
			laundryTypeService.insertLaundryTypes(laundryType);;
			return new ResponseEntity<>("Input berhasil", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping
	public ResponseEntity<?> updateLaundryType(@RequestBody String body) {
		try {
			LaundryTypes laundryType = new ObjectMapper().readValue(body,  LaundryTypes.class);
			laundryTypeService.updateLaundryType(laundryType);
			return new ResponseEntity<>("update berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deleteLaundryType(@PathVariable("kode") String kode) {
		try {
			LaundryTypes data = laundryTypeService.searchLaundryTypeByCode(kode);
			laundryTypeService.deleteLaundryType(data.getId());
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Delete gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
}
