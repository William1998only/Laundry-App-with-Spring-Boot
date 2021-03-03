package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.InvoicesHdr;

/**
 * 
 * @author WILLIAM
 *
 */
public interface InvoiceHdrDao {

	void updateTotalCost(Long idInvoiceHdr, String kodePromo) throws Exception;

	List<InvoicesHdr> getAllInvoiceHdr() throws Exception;

	List<InvoicesHdr> getAllInvoiceHdrByOutletsCode(String inputKodeOutlet) throws Exception;

	List<InvoicesHdr> getAllInvoiceHdrByCashier(Long idUser) throws Exception;

	Long insertInvoiceHdr(InvoicesHdr invoicesHdr) throws Exception;

}
