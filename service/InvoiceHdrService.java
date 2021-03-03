package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.InvoicesHdr;

/**
 * 
 * @author WILLIAM
 *
 */
public interface InvoiceHdrService {

	void insertInvoiceHdr(List<InvoicesHdr> listInvoice) throws Exception;

	List<InvoicesHdr> getAllInvoiceHdr() throws Exception;

	List<InvoicesHdr> getAllInvoiceHdrByOutletsCode(String inputKodeOutlet) throws Exception;

	List<InvoicesHdr> getAllInvoiceHdrByCashier(Long idUser) throws Exception;
	
}
