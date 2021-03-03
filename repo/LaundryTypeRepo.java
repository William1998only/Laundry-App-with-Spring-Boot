package com.lawencon.laundry.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.LaundryTypes;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository
public interface LaundryTypeRepo extends JpaRepository<LaundryTypes, Long>{
	
	@Query(value = "SELECT * FROM t_m_laundry_types ", nativeQuery = true)
	List<LaundryTypes> getListLaundryTypes() throws Exception;
	
    @Modifying
    @Query(value = "UPDATE t_m_laundry_types " +
			"SET laundry_name = ?1, service_cost = ?2 " +
			"WHERE laundry_code = ?3", nativeQuery = true)
    void updateLaundryType(String laundryName, BigDecimal Cost, String laundryCode) throws Exception;

	@Modifying
	@Query(value = "DELETE FROM t_m_laundry_types WHERE id = ?1", nativeQuery = true)
	void deleteLaundryType(Long id) throws Exception;
	
	@Query(value = "SELECT * " + 
				   "FROM t_m_laundry_types WHERE laundry_code = ?1", nativeQuery = true)
	LaundryTypes searchLaundryTypeByCode(String kode) throws Exception;
	
}
