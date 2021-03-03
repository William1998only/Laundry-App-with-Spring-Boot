package com.lawencon.laundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.service.UserService;

/**
 * 
 * @author WILLIAM
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody String body) {
		try {
			Users u = new ObjectMapper().readValue(body, Users.class);
			u = userService.getUserByPassword(u.getUserName(), u.getPassword());
			if(u == null) {
				return new ResponseEntity<>("Username atau password salah", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Login berhasil", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
