package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.RoleDao;
import com.lawencon.laundry.model.Roles;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class RoleServiceImpl extends BaseService implements RoleService {

	@Autowired
	@Qualifier(value = "rolesjpa")
	private RoleDao roleDao;

	@Override
	public List<Roles> getRole() throws Exception{
		return roleDao.getRole();
	}

	@Override
	public Roles getRolesByCode(int kode) throws Exception {
		Roles data = roleDao.searchRoleByCode(kode);
		if (null == data) {
			throw new Exception("Role belum terdaftar");
		} else
			return data;
	}

	@Override
	public void insertRole(Roles role) throws Exception{
		Roles roleDb = new Roles();
		if (inputCorrect(role)) {
			roleDb = roleDao.searchRoleByCode(role.getRoleCode());
			if (null != roleDb) {
				throw new Exception("Data sudah ada");
			} else {
				roleDao.insertRole(role);
			}
		} else {
			validasiKolom(role.getId());
		}
	}

	@Override
	public void updateRoles(Roles role) throws Exception{
		Roles roleDb = new Roles();
		if (inputCorrect(role)) {
			roleDb = roleDao.searchRoleByCode(role.getRoleCode());
			if (null != roleDb) {
				roleDao.updateRole(role);
			} else {
				throw new Exception("Data tidak terdaftar");
			}
		} else {
			validasiKolom(role.getId());
		}

	}

	@Override
	public void deleteRole(int kode) throws Exception{
		Roles data = new Roles();
		data = roleDao.searchRoleByCode(kode);
		if(null != data) {
			roleDao.deleteRole(data.getId());
		}else {
			throw new Exception("Data tidak terdaftar");
		}
	}

	public boolean inputCorrect(Roles data) {
		return data.getId() == null && data.getRoleCode() != null && data.getRoleName() != null
				&& !data.getRoleName().isEmpty();
	}
}
