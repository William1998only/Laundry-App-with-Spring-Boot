package com.lawencon.laundry.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Customers;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="customershibernate")
public class CustomerDaoNativeImpl extends BaseDao implements CustomerDao {

	@Override
	public void insertCustomer(Customers data) throws Exception {
		String sqlBuilder = bBuilder("INSERT INTO t_m_customers ", "(name, address, phone_number, nik) ",
				"values(?, ?, ?, ?)").toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, data.getName()).setParameter(2, data.getAddress())
				.setParameter(3, data.getPhoneNumber()).setParameter(4, data.getNIK()).executeUpdate();
	}

	@Override
	public List<Customers> getAllCustomer() throws Exception {
		List<Customers> customerList = new ArrayList<>();
		List<?> listObj = em.createNativeQuery("SELECT * FROM t_m_customers").getResultList();
		listObj.forEach((val -> {
			Object[] objArr = (Object[]) val;
			Customers customer = new Customers();
			customer.setName((String) objArr[3]);
			customer.setAddress((String) objArr[2]);
			customer.setPhoneNumber((String) objArr[4]);
			customer.setNIK((String) objArr[1]);
            customerList.add(customer);
        }));
		return customerList;
	}

	@Override
	public Long getCustomerbyNik(String inputNoKtp) throws Exception {
		String sqlBuilder = bBuilder("select id from t_m_customers ", "where nik = ? ").toString();
		Object listObj = em.createNativeQuery(sqlBuilder).setParameter(1, inputNoKtp).getSingleResult();
		return Long.valueOf(String.valueOf(listObj));
	}

	@Override
	public Customers searchCustomerByNik(String nik) throws Exception {
		String sqlBuilder = bBuilder("SELECT * FROM t_m_customers WHERE nik = ?").toString();
		List<Customers> customerList = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder)
				.setParameter(1, nik).getResultList();
		listObj.forEach((val -> {
			Object[] objArr = (Object[]) val;
			Customers customer = new Customers();
			customer.setName((String) objArr[3]);
			customer.setAddress((String) objArr[2]);
			customer.setPhoneNumber((String) objArr[4]);
			customer.setNIK((String) objArr[1]);
            customerList.add(customer);
        }));
		return customerList.get(0);
	}

	@Override
	public void updateCustomer(Customers customer) throws Exception{
		em.createNativeQuery(bBuilder("UPDATE t_m_customers ",
				"SET address = ?, name = ?, phone_number = ? ",
				"WHERE nik = ?"
				).toString())
		.setParameter(1, customer.getAddress())
		.setParameter(2, customer.getName())
		.setParameter(3, customer.getPhoneNumber())
		.setParameter(4, customer.getNIK())
		.executeUpdate();
	}

	@Override
	public void deleteCustomer(Long id) throws Exception{
		em.createNativeQuery("DELETE FROM t_m_customers WHERE id = ?")
		.setParameter(1,  id)
		.executeUpdate();
	}
}
