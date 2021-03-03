package com.lawencon.laundry.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.InvoicesHdr;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface InvoiceHdrRepo extends JpaRepository<InvoicesHdr, Long>{
	
	@Query(value = "INSERT INTO t_r_hdr_invoices " + 
					"(id_customer, id_user, total_cost," +
				"order_date, invoice_code, id_payment, id_promo) " +
				"VALUES((SELECT id FROM t_m_customers WHERE nik = ?1), " +
				"(SELECT id FROM t_m_users WHERE id = ?2), " +
				"0, now(), ('TR' || ((SELECT count(*) FROM t_r_hdr_invoices) + 1)), " +
				"(SELECT id FROM t_m_payments WHERE payment_code = ?3)," +
				" (SELECT id FROM t_m_promos WHERE promo_code = ?4)) returning id", nativeQuery = true)
	Object insertInvoiceHdr(String nik, Long idUser, String paymentType, String promoCode) throws Exception;
	
	@Query(value = "UPDATE t_r_hdr_invoices " + 
				"SET total_cost = (SELECT sum(lt.service_cost) " +
				"FROM t_r_dtl_items d " +
				"INNER JOIN t_m_laundry_types lt ON lt.id = d.id_laundry_type " +
				"WHERE d.id_invoice = ?1) - " +
				"(SELECT discount FROM t_m_promos " + "WHERE promo_code = ?2)" +
				"WHERE id = ?1 returning null", nativeQuery = true)
	void updateTotalCost(Long idInvoiceHdr, String kodePromo) throws SQLException;
	
	@Query(value = "SELECT hi.order_date, hi.total_cost, u.username, o.outlet_code " + 
				"from t_r_hdr_invoices hi " +
				"INNER JOIN t_r_dtl_items di on di.id_invoice = hi.id " +
				"INNER JOIN t_m_users u on u.id = hi.id_user " + 
				"INNER JOIN t_m_outlets o on o.id = u.id_outlet " +
				"GROUP BY hi.id, u.username, o.outlet_code " +
				"ORDER BY hi.order_date asc", nativeQuery = true)
	List<Object[]> getAllInvoiceHdr() throws Exception;
	
	@Query(value = "SELECT hi.order_date, hi.total_cost, u.username " + 
				"FROM t_r_hdr_invoices hi " +
				"INNER JOIN t_r_dtl_items di on di.id_invoice = hi.id " +
				"INNER JOIN t_m_users u on u.id = hi.id_user " +
				"INNER JOIN t_m_outlets o on o.id = u.id_outlet " +
				"WHERE o.outlet_code = ?1 " +
				"GROUP BY hi.id, u.username " + 
				"ORDER BY hi.order_date ASC", nativeQuery = true)
	List<Object[]> getAllInvoiceHdrByOutletsCode(String inputKodeOutlet) throws Exception;
	
	@Query(value = "SELECT hi.order_date," +
				" hi.total_cost, c.name, pay.payment_type, p.promo_name " +
				"FROM t_r_hdr_invoices hi " +
				"INNER JOIN t_r_dtl_items di on di.id_invoice = hi.id " +
				"INNER JOIN t_m_users u on u.id = hi.id_user " + 
				"INNER JOIN t_m_customers c on c.id = hi.id_customer " +
				"INNER JOIN t_m_promos p on p.id = hi.id_promo " +
				"INNER JOIN t_m_outlets o on o.id = u.id_outlet " +
				"INNER JOIN t_m_payments pay on pay.id = hi.id_payment " +
				"WHERE u.id_outlet = (SELECT id_outlet FROM t_m_users" + 
				" WHERE id = ?1) " +
				"GROUP BY hi.id, c.name, pay.payment_type, p.promo_name " +
				"ORDER BY hi.order_date ASC", nativeQuery = true)
	List<Object[]> getAllInvoiceHdrByCashier(Long idUser) throws Exception;
	
}
