package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.Payments;

/**
 * 
 * @author WILLIAM
 *
 */
public interface PaymentDao {

	void insertPaymentType(Payments payment) throws Exception;

	List<Payments> getPaymentList() throws Exception;

	int getTotalTransaksi(String paymentType) throws Exception;

	void updatePayment(Payments payment) throws Exception;

	void deletePayment(Long id) throws Exception;

	Payments searchPaymentByCode(String kode) throws Exception;

}
