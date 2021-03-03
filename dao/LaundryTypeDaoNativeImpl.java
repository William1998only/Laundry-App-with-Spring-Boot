package com.lawencon.laundry.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.LaundryTypes;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="laundrytypeshibernate")
public class LaundryTypeDaoNativeImpl extends BaseDao implements LaundryTypeDao {

	@Override
	public void insertLaundryTypes(LaundryTypes laundryType) throws Exception {
		String sqlBuilder = bBuilder("INSERT INTO t_m_laundry_types", "(laundry_name, laundry_code, service_cost) ",
				"values(?, ?, ?)").toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, laundryType.getLaundryName())
				.setParameter(2, laundryType.getLaundryCode()).setParameter(3, laundryType.getLaundryCost()).executeUpdate();
	}

	@Override
	public List<LaundryTypes> getListLaundryTypes() throws Exception {
		String sqlBuilder = bBuilder("SELECT * FROM t_m_laundry_types ").toString();
		List<LaundryTypes> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			LaundryTypes laundryType = new LaundryTypes();
			laundryType.setLaundryName((String) objArr[3]);
			laundryType.setLaundryCost((BigDecimal) objArr[2]);
			laundryType.setLaundryCode((String) objArr[1]);
			listResult.add(laundryType);
		});
		return listResult;
	}

	@Override
	public void updateLaundryType(LaundryTypes laundryType) {
		em.createNativeQuery(bBuilder("UPDATE t_m_laundry_types ",
				"SET laundry_name = ?, service_cost = ? ",
				"WHERE laundry_code = ?"
				).toString())
		.setParameter(1, laundryType.getLaundryName())
		.setParameter(2, laundryType.getLaundryCost())
		.setParameter(3, laundryType.getLaundryCode())
		.executeUpdate();
	}

	@Override
	public void deleteLaundryType(Long id) {
		em.createNativeQuery("DELETE FROM t_m_laundry_types WHERE id = ?")
		.setParameter(1, id)
		.executeUpdate();
	}

	@Override
	public LaundryTypes searchLaundryTypeByCode(String kode) {
		String sqlBuilder = bBuilder("SELECT laundry_name, service_cost FROM t_m_laundry_types WHERE laundry_code = ?").toString();
		List<LaundryTypes> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder)
				.setParameter(1, kode)
				.getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			LaundryTypes laundryType = new LaundryTypes();
			laundryType.setLaundryName((String) objArr[0]);
			laundryType.setLaundryCost((BigDecimal) objArr[1]);
			listResult.add(laundryType);
		});
		return listResult.get(0);
	}

}
