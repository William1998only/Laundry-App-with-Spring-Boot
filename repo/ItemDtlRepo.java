package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ItemsDtl;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface ItemDtlRepo extends JpaRepository<ItemsDtl, Long>{

	@Query(value = "INSERT INTO t_r_dtl_items " + 
				"(id_invoice, item_name, id_item_type, " +
				"id_laundry_type, expected_done_date) " +
				"VALUES(?1, ?2, (SELECT id FROM t_m_item_types WHERE item_code = ?3), " +
				"(SELECT id FROM t_m_laundry_types WHERE laundry_code = ?4), " +
				"(NOW() + INTERVAL '2 DAY')) returning null", nativeQuery=true)
	void insertItemDtl(Long idInvoiceHdr, String itemName, String itemTypeCode, String laundryCode) throws Exception;
	
	@Query(value = "SELECT di.item_name, " +
				"di.expected_done_date, it.item_type, lt.laundry_name, lt.service_cost " +
				"FROM t_r_dtl_items di " +
				"INNER JOIN t_r_hdr_invoices hi on hi.id = di.id_invoice " +
				"INNER JOIN t_m_item_types it on it.id = di.id_item_type " +
				"INNER JOIN t_m_laundry_types lt on lt.id = di.id_laundry_type " +
				"INNER JOIN t_m_users u on u.id = hi.id_user " +
				"INNER JOIN t_m_outlets o on o.id = u.id_outlet " +
				"WHERE u.id = ?1", nativeQuery = true)
	List<Object[]> getAllItemsDtlByCashier(Long idUser) throws Exception;
	
}
