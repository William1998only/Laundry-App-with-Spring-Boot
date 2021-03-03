package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.ItemsDtl;

/**
 * 
 * @author WILLIAM
 *
 */
public interface ItemDtlDao {

	void insertItemDtl(Long idInvoiceHdr, ItemsDtl itemDtl) throws Exception;

	List<ItemsDtl> getAllItemsDtlByCashier(Long idUser) throws Exception;

}
