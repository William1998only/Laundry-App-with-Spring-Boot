package com.lawencon.laundry.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Payments;
import com.lawencon.laundry.repo.PaymentRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="paymentsjpa")
public class PaymentDaoNativeJpaImpl extends BaseDao implements PaymentDao {

	@Autowired
	PaymentRepo paymentRepo;
	
	@Override
	public void insertPaymentType(Payments data) throws Exception {
		paymentRepo.save(data);
	}

	@Override
	public List<Payments> getPaymentList() throws Exception {
		return paymentRepo.getPaymentList();
	}

	@Override
	public int getTotalTransaksi(String paymentType) throws Exception {
		List<Object[]> listObj = paymentRepo.getTotalTransaksi(paymentType);
		return listObj.size();
	}

	@Override
	public void updatePayment(Payments payment) throws Exception {
		paymentRepo.updatePayment(payment.getPaymentType(), payment.getPaymentCode());
	}

	@Override
	public void deletePayment(Long id) throws Exception {
		paymentRepo.deletePayment(id);
	}

	@Override
	public Payments searchPaymentByCode(String kode) throws Exception {
		return paymentRepo.searchPaymentByCode(kode);
	}
}
