package com.lawencon.laundry.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Promos;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface PromoRepo extends JpaRepository<Promos, Long>{
	
	@Query(value= "SELECT * FROM t_m_promos WHERE promo_name != 'no_promo' ", nativeQuery = true)
	List<Promos> getListPromo() throws Exception;
	
	@Modifying
	@Query(value = "UPDATE t_m_promos SET discount = ?1, "+
				"promo_name = ?2 WHERE promo_code = ?3", nativeQuery=true)
	void updatePromo(BigDecimal discount, String name, String code) throws Exception;
	
	@Modifying
	@Query(value = "DELETE FROM t_m_promos WHERE promo_code = ?1", nativeQuery=true)
	void deletePromo(String kode) throws Exception;
	
	@Query(value = "SELECT * FROM t_m_promos WHERE promo_code = ?1", nativeQuery=true)
	Promos searchPromoByCode(String kode) throws Exception;
	
}
