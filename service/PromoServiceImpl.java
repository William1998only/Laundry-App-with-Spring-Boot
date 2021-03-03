package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.PromoDao;
import com.lawencon.laundry.model.Promos;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class PromoServiceImpl extends BaseService implements PromoService {

	@Autowired
	@Qualifier(value="promosjpa")
	private PromoDao promoDao;

	@Override
	public void insertPromo(Promos data) throws Exception {
		Promos promoDb = new Promos();
		if(inputCorrect(data)) {
			promoDb = promoDao.searchPromoByCode(data.getPromoCode());
			if(null == promoDb) {
				promoDao.insertPromo(data);
			}else
				throw new Exception("Data promo sudah ada");
		}else
			validasiKolom(data.getId());
	}

	@Override
	public List<Promos> getListPromo() throws Exception {
		return promoDao.getListPromo();
	}

	@Override
	public void updatePromo(Promos promo) throws Exception {
		Promos promoDb = new Promos();
		if(inputCorrect(promo)) {
			promoDb = promoDao.searchPromoByCode(promo.getPromoCode());
			if(null != promoDb) {
				promoDao.updatePromo(promo);
			}else
				throw new Exception("Data promo tidak ada");
		}else
			validasiKolom(promo.getId());
		
	}

	@Override
	public void deletePromo(String kode) throws Exception {
		Promos promoDb = new Promos();
		promoDb = promoDao.searchPromoByCode(kode);
		if(null != promoDb) {
			promoDao.deletePromo(kode);
		}else
			throw new Exception("Data promo tidak ada");
	}

	@Override
	public Promos searchPromoByCode(String kode) throws Exception {
		return promoDao.searchPromoByCode(kode);
	}
	
	public boolean inputCorrect(Promos data) {
		return data.getId() == null && data.getPromoCode() != null 
				&& data.getPromoName() != null && data.getDiscount() != null 
				&& !data.getPromoCode().isEmpty() && !data.getPromoName().isEmpty();
	}

}
