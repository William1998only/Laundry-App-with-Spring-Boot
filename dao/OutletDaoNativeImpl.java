package com.lawencon.laundry.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Outlets;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="outletshibernate")
public class OutletDaoNativeImpl extends BaseDao implements OutletDao {

	@Override
	public void insertOutletsData(Outlets data) throws Exception {
		String sqlBuilder = bBuilder("INSERT INTO t_m_outlets ", "(outlets_name, location, phone_number, outlet_code) ",
				"values(?, ?, ?, ?)").toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, data.getOutletsName()).setParameter(2, data.getLocation())
				.setParameter(3, data.getPhoneNumber()).setParameter(4, data.getOutletsCode()).executeUpdate();
	}

	@Override
	public Long getOutletsByCode(String outletsCode) throws Exception {
		String sqlBuilder = bBuilder("select id from t_m_outlets ", "where outlet_code = ? ").toString();
		Object listObj = em.createNativeQuery(sqlBuilder).setParameter(1, outletsCode).getSingleResult();
		return Long.valueOf(String.valueOf(listObj));
	}

	@Override
	public List<Outlets> getAllOutlets() throws Exception {
		String sqlBuilder = bBuilder("select * from t_m_outlets").toString();
		List<Outlets> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Outlets outlet = new Outlets();
			outlet.setLocation((String) objArr[1]);
			outlet.setOutletsCode((String) objArr[2]);
			outlet.setOutletsName((String) objArr[3]);
			outlet.setPhoneNumber((String) objArr[4]);
			listResult.add(outlet);
		});
		return listResult;
	}

	@Override
	public Outlets searchOutletsByCode(String inputKode) throws Exception {
		String sqlBuilder = bBuilder("select * from t_m_outlets ", "where outlet_code = ? ").toString();
		List<Outlets> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).setParameter(1, inputKode).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Outlets outlet = new Outlets();
			outlet.setLocation((String) objArr[1]);
			outlet.setOutletsCode((String) objArr[2]);
			outlet.setOutletsName((String) objArr[3]);
			outlet.setPhoneNumber((String) objArr[4]);
			listResult.add(outlet);
		});
		return listResult.get(0);
	}

	@Override
	public void updateOutlet(Outlets outlet) throws Exception {
		em.createNativeQuery(bBuilder("UPDATE t_m_outlets SET ", 
				"location = ?, outlets_name = ?, phone_number = ? ",
				"WHERE outlet_code = ?"
				).toString())
		.setParameter(1, outlet.getLocation())
		.setParameter(2, outlet.getOutletsName())
		.setParameter(3, outlet.getPhoneNumber())
		.setParameter(4, outlet.getOutletsCode())
		.executeUpdate();
	}

	@Override
	public void deleteOutlet(Outlets outlet) throws Exception {
		em.createNativeQuery(bBuilder("DELETE FROM t_m_outlets WHERE ",
				"outlet_code = ? "
				).toString())
		.setParameter(1, outlet.getOutletsCode())
		.executeUpdate();
	}
}
