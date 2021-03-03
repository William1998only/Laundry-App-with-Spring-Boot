package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.Customers;

/**
 * 
 * @author WILLIAM
 *
 */
public interface CustomerDao {

	void insertCustomer(Customers customer) throws Exception;

	List<Customers> getAllCustomer() throws Exception;

	Long getCustomerbyNik(String inputNoKtp) throws Exception;

	Customers searchCustomerByNik(String nik) throws Exception;

	void updateCustomer(Customers customer) throws Exception;

	void deleteCustomer(Long id) throws Exception;

}
