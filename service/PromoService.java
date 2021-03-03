package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.Promos;

/**
 * 
 * @author WILLIAM
 *
 */
public interface PromoService {

	void insertPromo(Promos promo) throws Exception;

	List<Promos> getListPromo() throws Exception;

	void updatePromo(Promos promo) throws Exception;

	void deletePromo(String kode) throws Exception;

	Promos searchPromoByCode(String kode) throws Exception;

}
