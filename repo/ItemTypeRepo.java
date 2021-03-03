package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ItemTypes;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface ItemTypeRepo extends JpaRepository <ItemTypes, Long>{
	
	@Query(value = "SELECT * FROM t_m_item_types ", nativeQuery=true)
	List<ItemTypes> getListItemTypes() throws Exception;
	
	@Query(value = "select id from t_m_item_types " + "where item_code = ?1 ", nativeQuery = true)
	Object getItemTypesByCode(String inputKode) throws Exception;
	
	@Query(value = "SELECT * FROM t_m_item_types WHERE item_code = ?1", nativeQuery = true)
	ItemTypes searchItemTypeByCode(String kode) throws Exception;
	
	@Query(value = "UPDATE t_m_item_types SET item_type = ?1 " +
				"WHERE item_code = ?2 returning null", nativeQuery = true)
	void updateItemType(String itemType, String itemCode)  throws Exception;
	
	@Modifying
	@Query(value = "DELETE FROM t_m_item_types WHERE id = ?1 ", nativeQuery = true)
	void deleteItemType(Long id) throws Exception;
}
