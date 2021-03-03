package com.lawencon.laundry.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Roles;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value = "roleshibernate")
public class RoleDaoNativeImpl extends BaseDao implements RoleDao {

	@Override
	public List<Roles> getRole() throws Exception{
		String sqlBuilder = bBuilder("select * from t_m_roles ").toString();
		List<Roles> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Roles role = new Roles();
			role.setId((Long) objArr[0]);
			role.setRoleCode((Integer) objArr[1]);
			role.setRoleName((String) objArr[2]);
			listResult.add(role);
		});
		return listResult;
	}

	@Override
	public Roles searchRoleByCode(int kode) throws Exception{
		String sqlBuilder = bBuilder("SELECT * from t_m_roles "
				, "WHERE role_code = ?").toString();
		List<Roles> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).setParameter(1, kode).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Roles role = new Roles();
			role.setId((Long) objArr[0]);
			role.setRoleCode((Integer) objArr[1]);
			role.setRoleName((String) objArr[2]);
			listResult.add(role);
		});
		return listResult.get(0);
	}

	@Override
	public void insertRole(Roles data) throws Exception{
		String sqlBuilder = bBuilder("INSERT INTO t_m_roles ",
				"(role_code, role_name) ", "values(?, ?)")
				.toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, data.getRoleCode())
				.setParameter(2, data.getRoleName()).executeUpdate();
	}

	@Override
	public void updateRole(Roles role) throws Exception{
		em.createNativeQuery(bBuilder("UPDATE t_m_roles ",
				"SET role_name = ? WHERE role_code = ?").toString())
		.setParameter(1, role.getRoleName())
		.setParameter(2, role.getRoleCode())
		.executeUpdate();
	}

	@Override
	public void deleteRole(Long id) throws Exception{
		em.createNativeQuery(bBuilder("DELETE FROM t_m_roles ",
				"WHERE id = ?").toString())
		.setParameter(1, id)
		.executeUpdate();
	}

}
