package com.lawencon.laundry.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ItemTypes;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="itemtypeshibernate")
public class ItemTypeDaoNativeImpl extends BaseDao implements ItemTypeDao {

	@Override
	public void insertItemType(ItemTypes itemType) throws Exception {
		String sqlBuilder = bBuilder("INSERT INTO t_m_item_types", "(item_type, item_code) ", "values(?, ?)")
				.toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, itemType.getItemType()).setParameter(2, itemType.getItemCode())
				.executeUpdate();
	}

	@Override
	public List<ItemTypes> getListItemTypes() throws Exception {
		String sqlBuilder = bBuilder("SELECT * FROM t_m_item_types ").toString();
		List<ItemTypes> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			ItemTypes itemType = new ItemTypes();
			itemType.setItemType((String) objArr[2]);
			itemType.setItemCode((String) objArr[1]);
			listResult.add(itemType);
		});
		return listResult;
	}

	@Override
	public Long getItemTypesByCode(String inputKode) throws Exception {
		String sqlBuilder = bBuilder("select id from t_m_item_types ", "where item_code = ? ").toString();
		Object listObj = em.createNativeQuery(sqlBuilder).setParameter(1, inputKode).getSingleResult();
		return Long.valueOf(String.valueOf(listObj));
	}

	@Override
	public ItemTypes searchItemTypeByCode(String kode) throws Exception {
		String sqlBuilder = bBuilder("SELECT * FROM t_m_item_types WHERE item_code = ?").toString();
		List<ItemTypes> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder)
				.setParameter(1, kode)
				.getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			ItemTypes itemType = new ItemTypes();
			itemType.setItemType((String) objArr[2]);
			itemType.setItemCode((String) objArr[1]);
			listResult.add(itemType);
		});
		return listResult.get(0);
	}

	@Override
	public void updateItemType(ItemTypes itemType) throws Exception {
		em.createNativeQuery(bBuilder("UPDATE t_m_item_types SET item_type = ? ",
				"WHERE item_code = ?"
				).toString())
		.setParameter(1, itemType.getItemType())
		.setParameter(2, itemType.getItemCode())
		.executeUpdate();
	}

	@Override
	public void deleteItemType(Long id) throws Exception {
		em.createNativeQuery(bBuilder("DELETE FROM t_m_item_types ",
				"WHERE id = ?"
				).toString())
		.setParameter(1, id)
		.executeUpdate();
	}
}
