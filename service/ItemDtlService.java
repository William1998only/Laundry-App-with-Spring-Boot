package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.InvoicesHdr;
import com.lawencon.laundry.model.ItemsDtl;

/**
 * 
 * @author WILLIAM
 *
 */
public interface ItemDtlService {

	void insertItemDtl(Long idInvoiceHdr, InvoicesHdr invoicesHdr) throws Exception;

	List<ItemsDtl> getAllItemsDtlByCashier(Long idUser) throws Exception;

}
