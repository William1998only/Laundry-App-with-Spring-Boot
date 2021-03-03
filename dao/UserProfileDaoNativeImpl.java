package com.lawencon.laundry.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ProfileUsers;
import com.lawencon.laundry.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="usersprofilehibernate")
public class UserProfileDaoNativeImpl extends BaseDao implements UserProfileDao {

	@Override
	public Long insertProfile(Users user) throws SQLException {
		String sqlBuilder = bBuilder("INSERT INTO t_m_profile_users ", "(fullname, address, no_hp) ",
				"values(?, ?, ?) returning id").toString();
		Object result = em.createNativeQuery(sqlBuilder).setParameter(1, user.getIdProfile().getFullName())
				.setParameter(2, user.getIdProfile().getAddress()).setParameter(3, user.getIdProfile().getNoHp())
				.getSingleResult();
		return Long.parseLong(String.valueOf(result));
	}

	@Override
	public ProfileUsers getProfileByNoHp(String noHp) throws Exception {
		List<?> listObj = em.createNativeQuery("SELECT * FROM t_m_profile_users WHERE no_hp = ?")
				.setParameter(1, noHp)
				.getResultList();
		List<ProfileUsers> listProfile = new ArrayList<>();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			ProfileUsers profile = new ProfileUsers();
			profile.setId((Long) objArr[1]);
			profile.setAddress((String) objArr[1]);
			profile.setFullName((String) objArr[2]);
			profile.setNoHp((String) objArr[3]);
			listProfile.add(profile);
		});
		return listProfile.get(0);
		
	}

}
