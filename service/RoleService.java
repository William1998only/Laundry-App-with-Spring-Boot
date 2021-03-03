package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.Roles;

/**
 * 
 * @author WILLIAM
 *
 */
public interface RoleService {

	List<Roles> getRole() throws Exception;

	Roles getRolesByCode(int kode) throws Exception;

	void insertRole(Roles role) throws Exception;

	void updateRoles(Roles role) throws Exception;

	void deleteRole(int kode) throws Exception;

}
