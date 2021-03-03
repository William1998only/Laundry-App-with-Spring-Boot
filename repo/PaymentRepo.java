package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Payments;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface PaymentRepo extends JpaRepository<Payments, Long>{
	
	@Query(value = "SELECT * FROM t_m_payments ", nativeQuery=true)
	public List<Payments> getPaymentList() throws Exception;
	
	@Query(value = "SELECT * " + "FROM t_m_payments p " +
				"inner join t_r_hdr_invoices hi on hi.id_payment = p.id " + 
				"WHERE p.payment_type = ?1 ", nativeQuery=true)
	List<Object[]> getTotalTransaksi(String paymentType) throws Exception;
	
	@Modifying
	@Query(value = "UPDATE t_m_payments "+
				"SET payment_type = ?1 WHERE payment_code = ?2", nativeQuery=true)
	void updatePayment(String type, String code) throws Exception;
	
	@Modifying
	@Query(value = "DELETE FROM t_m_payments " +
				"WHERE id = ?1", nativeQuery=true)
	void deletePayment(Long id) throws Exception;
	
	@Query(value = "SELECT * from t_m_payments " +
				"WHERE payment_code = ?1", nativeQuery = true)
	Payments searchPaymentByCode(String kode) throws Exception;
	
}
