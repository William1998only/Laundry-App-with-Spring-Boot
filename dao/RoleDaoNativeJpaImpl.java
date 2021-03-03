package com.lawencon.laundry.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Roles;
import com.lawencon.laundry.repo.RoleRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="rolesjpa")
public class RoleDaoNativeJpaImpl extends BaseDao implements RoleDao{
	
	@Autowired
	RoleRepo roleRepo;

	@Override
	public List<Roles> getRole() throws Exception {
		return roleRepo.getRole();
	}

	@Override
	public Roles searchRoleByCode(int kode) throws Exception {
		return roleRepo.searchRoleByCode(kode);
	}

	@Override
	public void insertRole(Roles role) throws Exception {
		roleRepo.save(role);
	}

	@Override
	public void updateRole(Roles role) throws Exception {
		roleRepo.updateRole(role.getRoleName(), role.getRoleCode());
	}

	@Override
	public void deleteRole(Long id) throws Exception {
		roleRepo.deleteById(id);
	}

}
