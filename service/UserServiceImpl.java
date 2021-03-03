package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.UserDao;
import com.lawencon.laundry.model.Outlets;
import com.lawencon.laundry.model.ProfileUsers;
import com.lawencon.laundry.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

	@Autowired
	@Qualifier(value="usersjpa")
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserProfileService usersProfileService;
	
	@Autowired
	private OutletService outletsService;

	@Override
	public Users getUserByPassword(String inputUsername, String inputPassword) throws Exception {
		return userDao.getUserByPassword(inputUsername, inputPassword);
	}

	@Override
	public void insertCashier(Users user) throws Exception {
		Long idProfile = null;
		Long idOutlets = null;
		if(inputCorrect(user)){
			if(inputNotEmpty(user)) {
				Users userDb = userDao.getUserByName(user.getUserName());
				if(null != userDb) {
					throw new Exception("Username sudah ada");
				}else {
					idProfile = usersProfileService.insertProfile(user);
					outletsService.searchOutletsByCode(user.getIdOutlet().getOutletsCode());
					idOutlets = outletsService.getOutletsByCode(user.getIdOutlet().getOutletsCode());
					ProfileUsers profile = new ProfileUsers();
					profile.setId(idProfile);
					user.setIdProfile(profile);
					Outlets outlet = new Outlets();
					outlet.setId(idOutlets);
					user.setIdOutlet(outlet);
					user.setPassword(passwordEncoder.encode(user.getPassword()));
					userDao.insertCashier(user, idProfile, idOutlets);
				}
			}else {
				throw new Exception("Input data tidak boleh kosong");
			}
		}else {
			throw new Exception("Input kolom salah");
		}
	}

	@Override
	public List<Users> getAllCashier() throws Exception {
		return userDao.getAllCashier();
	}

	@Override
	public Users getCashierByUsername(String inputUsername) throws Exception {
		Users data = userDao.getCashierByUsername(inputUsername);
		if(null == data) {
			throw new Exception("Kasir tidak terdaftar");
		}else 
			return data;
	}

	@Override
	public Users getUserByName(String username) throws Exception{
		Users data = userDao.getUserByName(username);
		if(null == data) {
			throw new Exception("User tidak terdaftar");
		}else 
			return data; 
	}

	@Override
	public void updatePassword(Users user) throws Exception{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.updatePassword(user);
	}

	@Override
	public void deleteUser(Users user) throws Exception{
		userDao.deleteUser(user);
	}
	
	public boolean inputCorrect(Users data) {
		return null == data.getId() && null != data.getIdOutlet() 
				&& null != data.getIdProfile()
				&& null != data.getIdRole() && null != data.getUserName() &&
				null != data.getPassword() && null != data.getIdOutlet().getOutletsCode()
				&& null != data.getIdProfile().getAddress() && null != data.getIdProfile().getFullName()
				&& null != data.getIdProfile().getNoHp() && null == data.getIdRole().getRoleName()
				&& null == data.getIdOutlet().getId() && null == data.getIdProfile().getId();
	}
	
	public boolean inputNotEmpty(Users data) {
		return !data.getUserName().isEmpty() && !data.getPassword().isEmpty()&&
				!data.getIdOutlet().getOutletsCode().isEmpty()
				&& !data.getIdProfile().getAddress().isEmpty()
				&& !data.getIdProfile().getFullName().isEmpty()
				&& !data.getIdProfile().getNoHp().isEmpty();
	}
}
