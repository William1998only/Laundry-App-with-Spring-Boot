package com.lawencon.laundry.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

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
import com.lawencon.laundry.model.Roles;
import com.lawencon.laundry.service.RoleService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService rolesService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getDisease() throws Exception{
		List<Roles> listResult = new ArrayList<>(); 
		try {
			listResult = rolesService.getRole();
			return new ResponseEntity<>(listResult, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getDiseaseByCode(@PathVariable("kode") int kode) {
		Roles role = new Roles();
		try {
			role = rolesService.getRolesByCode(kode);
			return new ResponseEntity<>(role, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertDisease(@RequestBody String body) {
		try {
			Roles role = new ObjectMapper().readValue(body, Roles.class);
			rolesService.insertRole(role);
			return new ResponseEntity<>(role, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@PatchMapping
	public ResponseEntity<?> updateDisease(@RequestBody String body) {
		try {
			Roles role = new ObjectMapper().readValue(body, Roles.class);
			rolesService.updateRoles(role);
			return new ResponseEntity<>(role, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deleteDisease(@PathVariable("kode") int kode) {
		try {
			rolesService.deleteRole(kode);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		}catch (PersistenceException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Data masih terpakai di tabel lain", HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
