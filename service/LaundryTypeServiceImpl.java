package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.LaundryTypeDao;
import com.lawencon.laundry.model.LaundryTypes;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class LaundryTypeServiceImpl extends BaseService implements LaundryTypeService {

	@Autowired
	@Qualifier(value="laundrytypesjpa")
	private LaundryTypeDao laundryTypeDao;

	@Override
	public void insertLaundryTypes(LaundryTypes laundryType) throws Exception {
		LaundryTypes laundryTypeDb = new LaundryTypes();
		if(inputCorrect(laundryType)) {
			laundryTypeDb = laundryTypeDao.searchLaundryTypeByCode(laundryType.getLaundryCode());
			if(null != laundryTypeDb) {
				throw new Exception("Data sudah ada");
			}else {
				laundryTypeDao.insertLaundryTypes(laundryType);
			}
		}else {
			validasiKolom(laundryType.getId());
		}
	}

	@Override
	public List<LaundryTypes> getListLaundryTypes() throws Exception {
		List<LaundryTypes> listResult = laundryTypeDao.getListLaundryTypes();
		if(listResult.size() == 0) {
			throw new Exception("Data belum ada");
		}else {
			return listResult;
		}
	}

	@Override
	public void updateLaundryType(LaundryTypes laundryType) throws Exception {
		LaundryTypes laundryTypeDb = new LaundryTypes();
		if(inputCorrect(laundryType)) {
			laundryTypeDb = laundryTypeDao.searchLaundryTypeByCode(laundryType.getLaundryCode());
			if(null != laundryTypeDb) {
				laundryTypeDao.updateLaundryType(laundryType);
			}else {
				throw new Exception("Servis tidak terdaftar");
			}
		}else {
			validasiKolom(laundryType.getId());
		}
	}

	@Override
	public void deleteLaundryType(Long id) throws Exception{
		laundryTypeDao.deleteLaundryType(id);
	}

	@Override
	public LaundryTypes searchLaundryTypeByCode(String kode) throws Exception {
		LaundryTypes data = laundryTypeDao.searchLaundryTypeByCode(kode);
		if(null == data) {
			throw new Exception("Servis belum terdaftar");
		}else return data;
	}
	
	public boolean inputCorrect(LaundryTypes data) {
		return data.getId() == null && data.getLaundryName() != null 
				&& data.getLaundryCode() != null && data.getLaundryCost() != null
				&& !data.getLaundryName().isEmpty() 
				&& !data.getLaundryCode().isEmpty();
	}

}
