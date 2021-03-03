package com.lawencon.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.laundry.model.ItemsDtl;
import com.lawencon.laundry.service.ItemDtlService;

/**
 * 
 * @author WILLIAM
 *
 */
@RestController
@RequestMapping("/itemdtl")
public class ItemDtlController {
	
	@Autowired
	ItemDtlService itemDtlService;
	
	@GetMapping("/kasir/id/{id}")
	public ResponseEntity<?> getInvoicesByOutletCode(@PathVariable("id") Long id) {
		try {
			List<ItemsDtl> itemList = itemDtlService.getAllItemsDtlByCashier(id);
			return new ResponseEntity<>(itemList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
