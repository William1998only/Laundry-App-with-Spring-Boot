package com.lawencon.laundry.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Promos;
import com.lawencon.laundry.repo.PromoRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="promosjpa")
public class PromoDaoNativeJpaImpl extends BaseDao implements PromoDao {
	
	@Autowired
	PromoRepo promoRepo;

	@Override
	public void insertPromo(Promos promo) throws SQLException {
		promoRepo.save(promo);
	}

	@Override
	public List<Promos> getListPromo() throws Exception {
		return promoRepo.getListPromo();	
	}

	@Override
	public void updatePromo(Promos promo) throws Exception {
		promoRepo.updatePromo(promo.getDiscount(), promo.getPromoName(), promo.getPromoCode());
	}

	@Override
	public void deletePromo(String kode) throws Exception {
		promoRepo.deletePromo(kode);
	}

	@Override
	public Promos searchPromoByCode(String kode) throws Exception {
		return promoRepo.searchPromoByCode(kode);
	}
}
