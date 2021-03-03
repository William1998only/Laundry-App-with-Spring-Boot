package com.lawencon.laundry.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.CustomerDao;
import com.lawencon.laundry.model.Customers;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class CustomerServiceImpl extends BaseService implements CustomerService {

	@Autowired
	@Qualifier(value="customersjpa")
	private CustomerDao customerDao;

	@Override
	public void insertCustomer(Customers customer) throws Exception {
		Customers customerDb = new Customers();
		if(inputCorrect(customer)) {
			customerDb = customerDao.searchCustomerByNik(customer.getNIK());
			if(null != customerDb) {
				throw new Exception("Customer sudah ada");
			}else {
				customerDao.insertCustomer(customer);
			}
		}else {
			validasiKolom(customer.getId());
		}
	}

	@Override
	public List<Customers> getAllCustomer() throws Exception {
		List<Customers> listResult = new ArrayList<>();
		listResult = customerDao.getAllCustomer();
		if(listResult.size() == 0) {
			throw new Exception("Data belum ada");
		}else {
			return listResult;
		}
	}

	@Override
	public Long getCustomerbyNik(String inputNoKtp) throws Exception{
		return customerDao.getCustomerbyNik(inputNoKtp);
	}

	@Override
	public Customers searchCustomerByNik(String nik) throws Exception{
		Customers data = customerDao.searchCustomerByNik(nik);
		if(null == data) {
			throw new Exception("NIK belum terdaftar");
		}else return data;
	}

	@Override
	public void updateCustomer(Customers customer) throws Exception{
		Customers customerDb = new Customers();
		if(inputCorrect(customer)) {
			customerDb = customerDao.searchCustomerByNik(customer.getNIK());
			if(null != customerDb) {
				customerDao.updateCustomer(customer);
			}else {
				throw new Exception("Customer tidak terdaftar");
			}
		}else {
			validasiKolom(customer.getId());
		}
	}

	@Override
	public void deleteCustomer(Long id) throws Exception{
		customerDao.deleteCustomer(id);
	}
	
	public boolean inputCorrect(Customers cstmr) {
		return cstmr.getId() == null && cstmr.getName() != null 
				&& cstmr.getAddress() != null && cstmr.getPhoneNumber() != null
				&& cstmr.getNIK() != null && !cstmr.getName().isEmpty() 
				&& !cstmr.getAddress().isEmpty() 
				&& !cstmr.getPhoneNumber().isEmpty() && !cstmr.getNIK().isEmpty();
	}
}
