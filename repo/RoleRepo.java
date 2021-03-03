package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Roles;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface RoleRepo extends JpaRepository<Roles, Long>{
	
	@Query(value = "SELECT * FROM t_m_roles ", nativeQuery=true)
	public List<Roles> getRole() throws Exception;
	
	@Modifying
	@Query(value = "UPDATE t_m_roles "+
				"SET role_name = ?1 WHERE role_code = ?2", nativeQuery=true)
	void updateRole(String name, int code) throws Exception;
	
	@Modifying
	@Query(value = "DELETE FROM t_m_roles " +
				"WHERE id = ?1", nativeQuery=true)
	void deleteRole(Long id) throws Exception;
	
	@Query(value = "SELECT * from t_m_roles " +
				"WHERE role_code = ?1", nativeQuery = true)
	Roles searchRoleByCode(int kode) throws Exception;

}
