package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.Payments;

/**
 * 
 * @author WILLIAM
 *
 */
public interface PaymentService {

	void insertPaymentType(Payments payment) throws Exception;

	List<Payments> getPaymentList() throws Exception;

	int getTotalTransaksi(String paymentType) throws Exception;

	void updatePayment(Payments payment) throws Exception;

	void deletePayment(String kode) throws Exception;

	Payments searchPaymentByCode(String kode) throws Exception;

}
