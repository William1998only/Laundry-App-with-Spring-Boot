package com.lawencon.laundry.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.laundry.model.Promos;

/**
 * 
 * @author WILLIAM
 *
 */
public interface PromoDao {

	void insertPromo(Promos promo) throws SQLException;

	List<Promos> getListPromo() throws Exception;

	void updatePromo(Promos promo) throws Exception;

	void deletePromo(String kode) throws Exception;

	Promos searchPromoByCode(String kode) throws Exception;

}
