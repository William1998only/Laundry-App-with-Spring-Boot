package com.lawencon.laundry.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.LaundryTypes;
import com.lawencon.laundry.repo.LaundryTypeRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="laundrytypesjpa")
public class LaundryTypeDaoNativeJpaImpl extends BaseDao implements LaundryTypeDao {

	@Autowired
	LaundryTypeRepo laundryTypeRepo;
	
	@Override
	public void insertLaundryTypes(LaundryTypes data) throws Exception {
		laundryTypeRepo.save(data);
	}

	@Override
	public List<LaundryTypes> getListLaundryTypes() throws Exception {
		return laundryTypeRepo.getListLaundryTypes();
	}

	@Override
	public void updateLaundryType(LaundryTypes laundryType) throws Exception {
		laundryTypeRepo.updateLaundryType(laundryType.getLaundryName(), 
				laundryType.getLaundryCost(),
				laundryType.getLaundryCode());
	}

	@Override
	public void deleteLaundryType(Long id) throws Exception {
		laundryTypeRepo.deleteLaundryType(id);
	}

	@Override
	public LaundryTypes searchLaundryTypeByCode(String kode) throws Exception {
		return laundryTypeRepo.searchLaundryTypeByCode(kode);
	}

}
