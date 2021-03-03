package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Customers;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customers, Long>{
	
	@Query(value = "SELECT * FROM t_m_customers ", nativeQuery = true)
	List<Customers> getAllCustomer() throws Exception;
	
	@Query(value = "SELECT id FROM t_m_customers WHERE nik = ?", nativeQuery = true)
	Object getCustomerbyNik(String inputNoKtp) throws Exception;
	
	@Query(value = "SELECT * FROM t_m_customers WHERE nik = ?", nativeQuery = true)
	Customers searchCustomerByNik(String nik) throws Exception;
	
	@Modifying
	@Query(value = "UPDATE t_m_customers " +
				"SET address = ?1, name = ?2, phone_number = ?3 " +
				"WHERE nik = ?4", nativeQuery = true)
	void updateCustomer(String address, String name
			, String phoneNumber, String nik) throws Exception;
	
}
