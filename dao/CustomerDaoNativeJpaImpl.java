package com.lawencon.laundry.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Customers;
import com.lawencon.laundry.repo.CustomerRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="customersjpa")
public class CustomerDaoNativeJpaImpl extends BaseDao implements CustomerDao {
	
	@Autowired
	CustomerRepo customerRepo;

	@Override
	public void insertCustomer(Customers customer) throws SQLException {
		customerRepo.save(customer);
	}

	@Override
	public List<Customers> getAllCustomer() throws Exception {
		return customerRepo.getAllCustomer();
	}

	@Override
	public Long getCustomerbyNik(String inputNoKtp) throws Exception {
		Object obj = customerRepo.getCustomerbyNik(inputNoKtp);
		return Long.valueOf(String.valueOf(obj));
	}

	@Override
	public Customers searchCustomerByNik(String nik) throws Exception {
		Customers customer = customerRepo.searchCustomerByNik(nik);
		return customer;
	}

	@Override
	public void updateCustomer(Customers customer) throws Exception {
		customerRepo.updateCustomer(customer.getAddress(), customer.getName()
				, customer.getPhoneNumber(), customer.getNIK());
	}

	@Override
	public void deleteCustomer(Long id) throws Exception {
		customerRepo.deleteById(id);
	}
}
