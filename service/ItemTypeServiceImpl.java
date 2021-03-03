package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.ItemTypeDao;
import com.lawencon.laundry.model.ItemTypes;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class ItemTypeServiceImpl extends BaseService implements ItemTypeService {

	@Autowired
	@Qualifier(value="itemtypesjpa")
	private ItemTypeDao itemTypeDao;

	@Override
	public void insertItemType(ItemTypes data) throws Exception {
		ItemTypes itemTypeDb = new ItemTypes();
		if(inputCorrect(data)) {
			itemTypeDb = itemTypeDao.searchItemTypeByCode(data.getItemCode());
			if(null != itemTypeDb) {
				throw new Exception("Data sudah ada");
			}else {
				itemTypeDao.insertItemType(data);
			}
		}else {
			validasiKolom(data.getId());
		}
	}

	@Override
	public List<ItemTypes> getListItemTypes() throws Exception {
		List<ItemTypes> listResult = itemTypeDao.getListItemTypes();
		if(listResult.size() == 0) {
			throw new Exception("Data belum ada");
		}else {
			return listResult;
		}
	}

	@Override
	public Long getItemTypesByCode(String inputKode) throws Exception {
		return itemTypeDao.getItemTypesByCode(inputKode);
	}

	@Override
	public ItemTypes searchItemTypeByCode(String kode) throws Exception {
		ItemTypes data = itemTypeDao.searchItemTypeByCode(kode);
		if(null == data) {
			throw new Exception("Tipe barang belum terdaftar");
		}else return data;
	}

	@Override
	public void updateItemType(ItemTypes itemType) throws Exception {
		ItemTypes itemTypeDb = new ItemTypes();
		if(inputCorrect(itemType)) {
			itemTypeDb = itemTypeDao.searchItemTypeByCode(itemType.getItemCode());
			if(null != itemTypeDb) {
				itemTypeDao.updateItemType(itemType);
			}else {
				throw new Exception("Data tidak terdaftar");
			}
		}else {
			validasiKolom(itemType.getId());
		}
	}

	@Override
	public void deleteItemType(Long id)  throws Exception{
		itemTypeDao.deleteItemType(id);
	}
	
	public boolean inputCorrect(ItemTypes type) {
		return type.getId() == null && type.getItemType() != null 
				&& type.getItemCode() != null
				&& !type.getItemType().isEmpty() 
				&& !type.getItemCode().isEmpty();
	}
}
