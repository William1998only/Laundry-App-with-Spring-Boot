package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.LaundryTypes;

/**
 * 
 * @author WILLIAM
 *
 */
public interface LaundryTypeService {

	void insertLaundryTypes(LaundryTypes laundryTypes) throws Exception;

	List<LaundryTypes> getListLaundryTypes() throws Exception;

	void updateLaundryType(LaundryTypes laundryType) throws Exception;

	void deleteLaundryType(Long id) throws Exception;

	LaundryTypes searchLaundryTypeByCode(String kode) throws Exception;

}
