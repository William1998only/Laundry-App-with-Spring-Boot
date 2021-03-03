package com.lawencon.laundry.controller;
/**
 * 
 * @author WILLIAM
 *
 */

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
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService usersService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllUser(){
		try {
			List<Users> users = usersService.getAllCashier();
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/username/{name}")
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name){
		try {
			Users user = usersService.getUserByName(name);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertUser(@RequestBody String body) {
		try {
			Users user = new ObjectMapper().readValue(body, Users.class);
			usersService.insertCashier(user);
			return new ResponseEntity<>("Input berhasil", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping
	public ResponseEntity<?> updatePassword(@RequestBody String body) {
		try {
			Users user = new ObjectMapper().readValue(body,  Users.class);
			Users userDb = usersService.getUserByName(user.getUserName());
			if(userDb != null) {
			    usersService.updatePassword(user);
				return new ResponseEntity<>("update berhasil", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("username atau password salah", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("update gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<?> deleteUser(@PathVariable("name") String name) {
		try {
			Users user = usersService.getUserByName(name);
			usersService.deleteUser(user);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Delete gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
