package com.lawencon.laundry.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.laundry.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */
public interface UserDao {

	Users getUserByPassword(String inputUsername, String inputPassword) throws Exception;

	void insertCashier(Users user, Long idProfile, Long idUser) throws SQLException;

	List<Users> getAllCashier() throws Exception;

	Users getCashierByUsername(String inputUsername) throws Exception;

	Users getUserByName(String username) throws Exception;

	void updatePassword(Users user) throws Exception;

	void deleteUser(Users user) throws Exception;

}
