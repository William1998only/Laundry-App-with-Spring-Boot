package com.lawencon.laundry.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ItemTypes;
import com.lawencon.laundry.repo.ItemTypeRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="itemtypesjpa")
public class ItemTypeDaoNativeJpaImpl extends BaseDao implements ItemTypeDao {

	@Autowired
	ItemTypeRepo itemTypeRepo;
	
	@Override
	public void insertItemType(ItemTypes itemType) throws Exception {
		itemTypeRepo.save(itemType);
	}

	@Override
	public List<ItemTypes> getListItemTypes() throws Exception {
		return itemTypeRepo.getListItemTypes();
	}

	@Override
	public Long getItemTypesByCode(String inputKode) throws Exception {
		Object listObj = itemTypeRepo.getItemTypesByCode(inputKode);
		return Long.valueOf(String.valueOf(listObj));
	}

	@Override
	public ItemTypes searchItemTypeByCode(String kode) throws Exception {
		return itemTypeRepo.searchItemTypeByCode(kode);
	}

	@Override
	public void updateItemType(ItemTypes itemType)  throws Exception{
		itemTypeRepo.updateItemType(itemType.getItemType(), itemType.getItemCode());
	}

	@Override
	public void deleteItemType(Long id) throws Exception {
		itemTypeRepo.deleteItemType(id);
	}
}
