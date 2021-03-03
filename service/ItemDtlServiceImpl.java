package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.ItemDtlDao;
import com.lawencon.laundry.model.InvoicesHdr;
import com.lawencon.laundry.model.ItemsDtl;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
public class ItemDtlServiceImpl extends BaseService implements ItemDtlService {

	@Autowired
	@Qualifier(value="itemdtljpa")
	private ItemDtlDao itemDtlDao;
	
	@Autowired
	ItemTypeService itemTypeService;
	
	@Autowired
	LaundryTypeService laundryTypeService;

	@Override
	public void insertItemDtl(Long idInvoiceHdr, InvoicesHdr invoicesHdr) throws Exception {
		if(null == invoicesHdr.getItems()) {
			throw new Exception("Harus menginput daftar barang");
		}
		for (int i = 0; i < invoicesHdr.getItems().size(); i++) {
			ItemsDtl itemDtl = invoicesHdr.getItems().get(i);
			if(inputCorrect(itemDtl)) {
				if(inputNotEmpty(itemDtl)) {
					itemTypeService.searchItemTypeByCode(itemDtl.getIdItemType().getItemCode());
					laundryTypeService.searchLaundryTypeByCode(itemDtl.getIdLaundryType().getLaundryCode());
					InvoicesHdr invoice = new InvoicesHdr();
					invoice.setId(idInvoiceHdr);
					itemDtl.setIdInvoice(invoice);
					itemDtlDao.insertItemDtl(idInvoiceHdr, itemDtl);
				}else {
					throw new Exception("Input tidak boleh kosong");
				}
			}else {
				throw new Exception("Input kolom harus lengkap \n"
						+ "Daftar kolom yang harus dimasukkan: \n" 
						+ "nama item, kode item, kode laundry");
			}
		}
	}

	@Transactional
	@Override
	public List<ItemsDtl> getAllItemsDtlByCashier(Long idUser) throws Exception{
		return itemDtlDao.getAllItemsDtlByCashier(idUser);
	}
	
	public boolean inputCorrect(ItemsDtl itemDtl) {
		return null == itemDtl.getId() && null != itemDtl.getIdItemType() 
				&& null != itemDtl.getIdLaundryType()
				&& null != itemDtl.getItemName()
				&& null != itemDtl.getItemName() 
				&& null != itemDtl.getIdItemType().getItemCode()
				&& null != itemDtl.getIdLaundryType().getLaundryCode();
	}
	
	public boolean inputNotEmpty(ItemsDtl itemDtl) {
		return !itemDtl.getItemName().isEmpty() &&
				!itemDtl.getIdItemType().getItemCode().isEmpty()&&
				!itemDtl.getIdLaundryType().getLaundryCode().isEmpty();
	}
}
