package com.lawencon.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.laundry.model.InvoicesHdr;
import com.lawencon.laundry.service.InvoiceHdrService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/invoicehdr")
public class InvoiceHdrController {

	@Autowired
	InvoiceHdrService invoiceHdrService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllInvoices() {
		try {
			List<InvoicesHdr> invoices = invoiceHdrService.getAllInvoiceHdr();
			return new ResponseEntity<>(invoices, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.OK);
		}
	}
	
	@GetMapping("/outlet/kode/{kode}")
	public ResponseEntity<?> getInvoicesByOutletCode(@PathVariable("kode") String kode) {
		try {
			List<InvoicesHdr> invoices = invoiceHdrService.getAllInvoiceHdrByOutletsCode(kode);
			return new ResponseEntity<>(invoices, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/kasir/id/{id}")
	public ResponseEntity<?> getInvoicesByCashier(@PathVariable("id") Long id) {
		try {
			List<InvoicesHdr> invoices = invoiceHdrService.getAllInvoiceHdrByCashier(id);
			return new ResponseEntity<>(invoices, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertInvoice(@RequestBody String body) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<InvoicesHdr> invoiceList = mapper.readValue(body, mapper.getTypeFactory().constructCollectionType(List.class, InvoicesHdr.class));
			invoiceHdrService.insertInvoiceHdr(invoiceList);
			return new ResponseEntity<>("Input berhasil", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
