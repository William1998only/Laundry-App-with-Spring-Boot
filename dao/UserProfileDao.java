package com.lawencon.laundry.dao;

import java.sql.SQLException;

import com.lawencon.laundry.model.ProfileUsers;
import com.lawencon.laundry.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */
public interface UserProfileDao {

	Long insertProfile(Users user) throws SQLException;
	
	ProfileUsers getProfileByNoHp(String noHp) throws Exception;

}
