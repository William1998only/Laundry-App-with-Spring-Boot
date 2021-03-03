package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.Roles;

/**
 * 
 * @author WILLIAM
 *
 */
public interface RoleDao {

	List<Roles> getRole() throws Exception;

	Roles searchRoleByCode(int kode) throws Exception;

	void insertRole(Roles role) throws Exception;

	void updateRole(Roles role) throws Exception;

	void deleteRole(Long id) throws Exception;

}
