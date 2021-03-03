package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository
public interface UserRepo extends JpaRepository<Users, Long>{
	
	@Query(value = "SELECT u.id, u.username, u.password, "
			+ "u.id_outlets, u.id_role, r.role_code, u.id_profile FROM t_m_users u "
			+ "inner join t_m_outlets o on o.id = u.id_outlets "
			+ "inner join t_m_profile_users p on p.id = u.id_profile inner join t_m_roles r on r.id = u.id_role "
			+ "WHERE u.username = ?1 and u.password = ?2", nativeQuery = true)
	List<Object[]> getUserByPassword(String inputUsername, String inputPassword) throws Exception;
	
	@Query(value = "SELECT u.username, o.outlet_code FROM t_m_users u "+
			"INNER JOIN t_m_outlets o on o.id = u.id_outlet "+
			"WHERE u.id_role = 2", nativeQuery = true)
	List<Object[]> getAllCashier() throws Exception;
	
	@Query(value = "SELECT u.username, o.outlet_code FROM t_m_users u " +
			"INNER JOIN t_m_outlets o on o.id = u.id_outlet " + 
			"WHERE u.username = ?1 and u.id_role = 2", nativeQuery = true)
	List<Object[]> getCashierByUsername(String inputUsername) throws Exception;
	
	@Query(value = "SELECT * FROM t_m_users " +
			"WHERE username = ?1", nativeQuery = true)
	List<Object[]> getUserByName(String username) throws Exception;
	
	@Transactional
    @Modifying
    @Query(value = "UPDATE t_m_users " +
			"SET password = ?1 WHERE username = ?2", nativeQuery = true)
    void updatePassword(String password, String username) throws Exception;
    
	@Modifying
	@Query(value = "DELETE FROM t_m_users " +
			"WHERE username = ?1 AND password = ?2", nativeQuery = true)
	void deleteUser(String username, String password) throws Exception;
}
