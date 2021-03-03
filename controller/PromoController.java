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
import com.lawencon.laundry.model.Promos;
import com.lawencon.laundry.service.PromoService;

/**
 * 
 * @author WILLIAM
 *
 */
@RestController
@RequestMapping("/promo")
public class PromoController {

	@Autowired
	PromoService promoService;

	@GetMapping("/getall")
	public ResponseEntity<?> getAllPromo() {
		try {
			List<Promos> promos = promoService.getListPromo();
			return new ResponseEntity<>(promos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getPromoByCode(@PathVariable("kode") String kode){
		try {
			Promos promo = promoService.searchPromoByCode(kode);
			return new ResponseEntity<>(promo, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> insertPromo(@RequestBody String body) {
		try {
			Promos promo = new ObjectMapper().readValue(body, Promos.class);
			promoService.insertPromo(promo);
			return new ResponseEntity<>("Input berhasil", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping
	public ResponseEntity<?> updatePromo(@RequestBody String body) {
		try {
			Promos promo = new ObjectMapper().readValue(body, Promos.class);
			promoService.updatePromo(promo);
			return new ResponseEntity<>("update berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deletePromo(@PathVariable("kode") String kode) {
		try {
			promoService.deletePromo(kode);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
