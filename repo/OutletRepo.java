package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Outlets;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository
public interface OutletRepo extends JpaRepository<Outlets, Long>{
	
	@Query(value = "select id from t_m_outlets where outlet_code = ?1 ", nativeQuery = true)
	Object getOutletsByCode(String outletsCode) throws Exception;
	
	@Query(value = "select * from t_m_outlets", nativeQuery = true)
	List<Outlets> getAllOutlets() throws Exception;
	
	@Query(value = "select * from t_m_outlets where outlet_code = ?1 ", nativeQuery = true)
	Outlets searchOutletsByCode(String inputKode) throws Exception;
	
	@Modifying
	@Query(value = "UPDATE t_m_outlets SET " + 
				"location = ?1, outlets_name = ?2, phone_number = ?3 " +
				"WHERE outlet_code = ?4", nativeQuery = true)
	void updateOutlet(String location, String name,
			String phone, String code) throws Exception;
	
	@Modifying
	@Query(value = "DELETE FROM t_m_outlets WHERE " +
				"outlet_code = ?1 ", nativeQuery = true)
	void deleteOutlet(String kode) throws Exception;
	
}
