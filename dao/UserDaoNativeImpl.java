package com.lawencon.laundry.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Outlets;
import com.lawencon.laundry.model.ProfileUsers;
import com.lawencon.laundry.model.Roles;
import com.lawencon.laundry.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="usershibernate")
public class UserDaoNativeImpl extends BaseDao implements UserDao {

	@Override
	public Users getUserByPassword(String inputUsername, String inputPassword) throws Exception {
		String sqlBuilder = bBuilder("SELECT u.id, u.username, u.password, ",
				"u.id_outlets, u.id_role, r.role_code, u.id_profile ", "FROM t_m_users u ",
				"inner join t_m_outlets o on o.id = u.id_outlets ",
				"inner join t_m_profile_users p on p.id = u.id_profile ", "inner join t_m_roles r on r.id = u.id_role ",
				"WHERE u.username = ? and u.password = ?").toString();
		List<Users> userList = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).setParameter(1, inputUsername).setParameter(2, inputPassword).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Users user = new Users();
			user.setId((Long) objArr[0]);
			user.setUserName((String) objArr[1]);
			user.setPassword((String) objArr[2]);
			Outlets outlet = new Outlets();
			outlet.setId((Long) objArr[3]);
			user.setIdOutlet(outlet);
			Roles role = new Roles();
			role.setId((Long) objArr[4]);
			role.setRoleCode((Integer) objArr[5]);
			user.setIdRole(role);
			ProfileUsers profile = new ProfileUsers();
			profile.setId((Long) objArr[6]);
			user.setIdProfile(profile);
			userList.add(user);
		});
		return userList.get(0);
	}

	@Override
	public void insertCashier(Users user, Long idProfile, Long idOutlets) throws SQLException {
		String sqlBuilder = bBuilder("INSERT INTO t_m_users ", "(username, password, id_role, id_profile, id_outlet) ",
				"values(?, ?, ?, ?, ?)").toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, user.getUserName())
				.setParameter(2, user.getPassword())
				.setParameter(3, user.getIdRole().getId())
				.setParameter(4, idProfile)
				.setParameter(5, idOutlets)
				.executeUpdate();
	}

	@Override
	public List<Users> getAllCashier() throws Exception {
		List<Users> userList = new ArrayList<>();
		String sqlBuilder = bBuilder("SELECT u.username, o.outlet_code FROM t_m_users u ",
				"INNER JOIN t_m_outlets o on o.id = u.id_outlet ",
				"WHERE u.id_role = 2").toString();
		List<?> listObj = em.createNativeQuery(sqlBuilder).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Users user = new Users();
			user.setUserName((String) objArr[0]);
			Outlets outlet = new Outlets();
			outlet.setOutletsCode((String) objArr[1]);
			user.setIdOutlet(outlet);
			userList.add(user);
		});
		return userList;
	}

	@Override
	public Users getCashierByUsername(String inputUsername) throws Exception {
		String sqlBuilder = bBuilder("SELECT u.username, o.outlet_code FROM t_m_users u",
				"INNER JOIN t_m_outlets o on o.id = u.id_outlet "
				, "WHERE u.username = ? and u.id_role = 2").toString();
		List<Users> listUser = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).setParameter(1, inputUsername).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Users user = new Users();
			user.setUserName((String) objArr[0]);
			Outlets outlet = new Outlets();
			outlet.setOutletsCode((String) objArr[1]);
			user.setIdOutlet(outlet);
			listUser.add(user);
		});
		return listUser.get(0);
	}

	@Override
	public Users getUserByName(String username) {
		String sqlBuilder = bBuilder("SELECT * FROM t_m_users ",
				"WHERE username = ?").toString();
		List<Users> listUser = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder)
				.setParameter(1, username)
				.getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Users user = new Users();
			user.setId((Long) objArr[1]);
			user.setPassword((String) objArr[1]);
			user.setUserName((String) objArr[2]);
			listUser.add(user);
		});
		return listUser.get(0);
	}

	@Override
	public void updatePassword(Users user) {
		String sqlBuilder = bBuilder("UPDATE t_m_users ",
				"SET password = ? WHERE username = ?").toString();
		em.createNativeQuery(sqlBuilder)
		.setParameter(1, user.getPassword())
		.setParameter(2, user.getUserName())
		.executeUpdate();
	}

	@Override
	public void deleteUser(Users user) {
		String sqlBuilder = bBuilder("DELETE FROM t_m_users ", 
				"WHERE username = ? AND password = ?").toString();
		em.createNativeQuery(sqlBuilder)
		.setParameter(1, user.getUserName())
		.setParameter(2, user.getPassword())
		.executeUpdate();
	}

}
