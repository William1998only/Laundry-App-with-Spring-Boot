package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.ItemTypes;

/**
 * 
 * @author WILLIAM
 *
 */
public interface ItemTypeDao {

	void insertItemType(ItemTypes itemType) throws Exception;

	List<ItemTypes> getListItemTypes() throws Exception;

	Long getItemTypesByCode(String inputKode) throws Exception;

	ItemTypes searchItemTypeByCode(String kode) throws Exception;

	void updateItemType(ItemTypes itemType) throws Exception;

	void deleteItemType(Long id) throws Exception;

}
