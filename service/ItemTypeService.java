package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.ItemTypes;

/**
 * 
 * @author WILLIAM
 *
 */
public interface ItemTypeService {

	void insertItemType(ItemTypes it) throws Exception;

	List<ItemTypes> getListItemTypes() throws Exception;

	Long getItemTypesByCode(String inputKode) throws Exception;

	ItemTypes searchItemTypeByCode(String kode) throws Exception;

	void updateItemType(ItemTypes itemType) throws Exception;

	void deleteItemType(Long id) throws Exception;

}
