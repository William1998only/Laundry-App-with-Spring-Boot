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
import com.lawencon.laundry.model.ItemTypes;
import com.lawencon.laundry.service.ItemTypeService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/itemtype")
public class ItemTypeController {
	
	@Autowired
	ItemTypeService itemTypeService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllItemType(){
		try {
			List<ItemTypes> itemTypes = itemTypeService.getListItemTypes();
			return new ResponseEntity<>(itemTypes, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getItemTypeByCode(@PathVariable("kode") String kode){
		try {
			ItemTypes itemType = itemTypeService.searchItemTypeByCode(kode);
			return new ResponseEntity<>(itemType, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertItemType(@RequestBody String body) {
		try {
			ItemTypes itemType = new ObjectMapper().readValue(body, ItemTypes.class);
			itemTypeService.insertItemType(itemType);
			return new ResponseEntity<>("Input berhasil", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping
	public ResponseEntity<?> updateItemType(@RequestBody String body) {
		try {
			ItemTypes itemType = new ObjectMapper().readValue(body,  ItemTypes.class);
			itemTypeService.updateItemType(itemType);
			return new ResponseEntity<>("update berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deleteItemType(@PathVariable("kode") String kode) {
		try {
			Long id = itemTypeService.getItemTypesByCode(kode);
			itemTypeService.deleteItemType(id);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Data tidak terdaftar", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	
	
}
