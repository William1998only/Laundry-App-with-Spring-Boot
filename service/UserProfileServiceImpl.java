package com.lawencon.laundry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.UserProfileDao;
import com.lawencon.laundry.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class UserProfileServiceImpl extends BaseService implements UserProfileService {

	@Autowired
	@Qualifier(value="usersprofilejpa")
	private UserProfileDao usersProfileDao;

	@Override
	public Long insertProfile(Users user) throws Exception {
		return usersProfileDao.insertProfile(user);
	}
}
