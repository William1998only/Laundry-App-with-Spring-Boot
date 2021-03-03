package com.lawencon.laundry.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Promos;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="promoshibernate")
public class PromoDaoNativeImpl extends BaseDao implements PromoDao {

	@Override
	public void insertPromo(Promos promo) throws SQLException {
		String sqlBuilder = bBuilder("INSERT INTO t_m_promos ", "(promo_name, promo_code, discount) ",
				"VALUES(?, ?, ?)").toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, promo.getPromoName()).setParameter(2, promo.getPromoCode())
				.setParameter(3, promo.getDiscount()).executeUpdate();
	}

	@Override
	public List<Promos> getListPromo() throws Exception {
		String sqlBuilder = bBuilder("SELECT * FROM t_m_promos ", "WHERE promo_name != 'no_promo'").toString();
		List<Promos> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Promos promo = new Promos();
			BigInteger id = (BigInteger)objArr[0];
			promo.setId(id.longValue());
			promo.setDiscount((BigDecimal) objArr[1]);
			promo.setPromoCode((String) objArr[2]);
			promo.setPromoName((String) objArr[3]);
			listResult.add(promo);
		});
		return listResult;
	}

	@Override
	public void updatePromo(Promos promo) {
		em.createNativeQuery(bBuilder("UPDATE t_m_promos SET discount = ?, ",
				"promo_name = ? WHERE promo_code = ?").toString())
		.setParameter(1, promo.getDiscount())
		.setParameter(2, promo.getPromoName())
		.setParameter(3, promo.getPromoCode())
		.executeUpdate();
	}

	@Override
	public void deletePromo(String kode) {
		em.createNativeQuery("DELETE FROM t_m_promos WHERE promo_code = ?")
		.setParameter(1, kode)
		.executeUpdate();
	}

	@Override
	public Promos searchPromoByCode(String kode) {
		String sqlBuilder = bBuilder("SELECT * FROM t_m_promos ", "WHERE promo_code = ?").toString();
		List<Promos> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder)
				.setParameter(1, kode)
				.getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Promos promo = new Promos();
			BigInteger id = (BigInteger)objArr[0];
			promo.setId(id.longValue());
			promo.setDiscount((BigDecimal) objArr[1]);
			promo.setPromoCode((String) objArr[2]);
			promo.setPromoName((String) objArr[3]);
			listResult.add(promo);
		});
		return listResult.get(0);
	}
}
