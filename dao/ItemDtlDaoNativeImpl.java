package com.lawencon.laundry.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ItemTypes;
import com.lawencon.laundry.model.ItemsDtl;
import com.lawencon.laundry.model.LaundryTypes;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="itemdtlhibernate")
public class ItemDtlDaoNativeImpl extends BaseDao implements ItemDtlDao {

	@Override
	public void insertItemDtl(Long idInvoiceHdr, ItemsDtl itemsDtl) throws Exception {
		String sqlBuilder = bBuilder("INSERT INTO t_r_dtl_items ", "(id_invoice, item_name, id_item_type, ",
				"id_laundry_type, expected_done_date) ",
				"values(?, ?, (select id from t_m_item_types where item_code = ?), ",
				"(select id from t_m_laundry_types where laundry_code = ?), ", "(NOW() + INTERVAL '2 DAY'))")
						.toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, idInvoiceHdr)
				.setParameter(2, itemsDtl.getItemName())
				.setParameter(3, itemsDtl.getIdItemType().getItemCode())
				.setParameter(4, itemsDtl.getIdLaundryType().getLaundryCode()).executeUpdate();
	}

	@Override
	public List<ItemsDtl> getAllItemsDtlByCashier(Long idUser) throws Exception{
		List<ItemsDtl> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(bBuilder("select di.item_name, ",
				"di.expected_done_date, it.item_type, lt.laundry_name, lt.service_cost",
				"from t_r_dtl_items di ",
				"inner join t_r_hdr_invoices hi on hi.id = di.id_hdr_invoice ",
				"inner join t_m_item_types it on it.id = di.id_item_type ",
				"inner join t_m_laundry_types lt on lt.id = di.id_laundry_type ",
				"inner join t_m_users u on u.id = hi.id_user ", 
				"inner join t_m_outlets o on o.id = u.id_outlets ",
				"where o.id = (select id_outlets from t_m_users where id = ?)").toString())
				.setParameter(1, idUser).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			ItemsDtl itemDtl = new ItemsDtl();
			itemDtl.setItemName((String) objArr[0]);
			itemDtl.setExpectedDoneDate((LocalDate) objArr[1]);
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
