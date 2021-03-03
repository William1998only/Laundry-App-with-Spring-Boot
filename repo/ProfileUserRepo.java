package com.lawencon.laundry.repo;
/**
 * 
 * @author WILLIAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ProfileUsers;

@Repository
public interface ProfileUserRepo extends JpaRepository<ProfileUsers, Long>{
		
	@Query(value = "SELECT * FROM t_m_profile_users WHERE no_hp = ?1", nativeQuery=true)
	ProfileUsers getProfileByNoHp(String noHp);
	
}
