package com.lawencon.laundry.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Outlets;
import com.lawencon.laundry.model.ProfileUsers;
import com.lawencon.laundry.model.Roles;
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.repo.UserRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="usersjpa")
public class UserDaoNativeJpaImpl extends BaseDao implements UserDao {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public Users getUserByPassword(String inputUsername, String inputPassword) throws Exception {
		List<Users> userList = new ArrayList<>();
		List<Object[]> listObj = userRepo.getUserByPassword(inputUsername, inputPassword);
		listObj.forEach(objArr -> {
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
		userRepo.save(user);
	}

	@Override
	public List<Users> getAllCashier() throws Exception {
		List<Users> userList = new ArrayList<>();
		List<Object[]> listObj = userRepo.getAllCashier();
		listObj.forEach(objArr -> {
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
		List<Users> listUser = new ArrayList<>();
		List<Object[]> listObj = userRepo.getCashierByUsername(inputUsername);
		listObj.forEach(objArr -> {
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
	public Users getUserByName(String username) throws Exception{
		List<Users> listUser = new ArrayList<>();
		List<Object[]> listObj = userRepo.getUserByName(username);
		listObj.forEach(objArr -> {
			Users user = new Users();
			user.setId(Long.valueOf(String.valueOf(objArr[0])));
			user.setPassword((String) objArr[1]);
			user.setUserName((String) objArr[2]);
			listUser.add(user);
		});
		return listUser.size() > 0 ? listUser.get(0) : null;
	}

	@Override
	public void updatePassword(Users user) throws Exception{
		userRepo.updatePassword(user.getPassword(), user.getUserName());
	}

	@Override
	public void deleteUser(Users user) throws Exception{
		userRepo.deleteUser(user.getUserName(), user.getPassword());
	}

}
