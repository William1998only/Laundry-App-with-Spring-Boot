package com.lawencon.laundry.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ItemTypes;
import com.lawencon.laundry.model.ItemsDtl;
import com.lawencon.laundry.model.LaundryTypes;
import com.lawencon.laundry.repo.ItemDtlRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="itemdtljpa")
public class ItemDtlDaoNativeJpaImpl extends BaseDao implements ItemDtlDao {
	
	@Autowired
	ItemDtlRepo itemDtlRepo;

	@Override
	public void insertItemDtl(Long idInvoiceHdr, ItemsDtl itemsDtl) throws Exception {
		itemDtlRepo.insertItemDtl(idInvoiceHdr, itemsDtl.getItemName(), 
				itemsDtl.getIdItemType().getItemCode(), 
				itemsDtl.getIdLaundryType().getLaundryCode());
	}

	@Override
	public List<ItemsDtl> getAllItemsDtlByCashier(Long idUser) throws Exception{
		List<ItemsDtl> listResult = new ArrayList<>();
		List<Object[]> listObj = itemDtlRepo.getAllItemsDtlByCashier(idUser);
		listObj.forEach(objArr -> {
			ItemsDtl itemDtl = new ItemsDtl();
			itemDtl.setItemName((String) objArr[0]);
			java.sql.Date doneDate = (java.sql.Date) objArr[1];
			itemDtl.setExpectedDoneDate(doneDate.toLocalDate());
			ItemTypes itemType = new ItemTypes();
			itemType.setItemType((String) objArr[2]);
			itemDtl.setIdItemType(itemType);
			LaundryTypes laundryType = new LaundryTypes();
			laundryType.setLaundryName((String) objArr[3]);
			laundryType.setLaundryCost((BigDecimal) objArr[4]);
			itemDtl.setIdLaundryType(laundryType);
			listResult.add(itemDtl);
		});
		return listResult;
	}
}
