package com.lawencon.laundry.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ProfileUsers;
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.repo.ProfileUserRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="usersprofilejpa")
public class UserProfileDaoNativeJpaImpl extends BaseDao implements UserProfileDao {

	@Autowired
	ProfileUserRepo profileUserRepo;
	
	@Override
	public Long insertProfile(Users user) throws SQLException {
		profileUserRepo.save(user.getIdProfile());
//		return Long.parseLong(String.valueOf(result));
		return user.getIdProfile().getId();
	}

	@Override
	public ProfileUsers getProfileByNoHp(String noHp) throws Exception {
		return profileUserRepo.getProfileByNoHp(noHp);
	}
}
