package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.PaymentDao;
import com.lawencon.laundry.model.Payments;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class PaymentServiceImpl extends BaseService implements PaymentService {

	@Autowired
	@Qualifier(value="paymentsjpa")
	private PaymentDao paymentDao;

	@Override
	public void insertPaymentType(Payments data) throws Exception {
		Payments paymentDb = new Payments();
		if(inputCorrect(data)) {
			paymentDb = paymentDao.searchPaymentByCode(data.getPaymentCode());
			if(null != paymentDb) {
				throw new Exception("Data sudah ada");
			}else {
				paymentDao.insertPaymentType(data);
			}
		}else {
			validasiKolom(data.getId());
		}
	}

	@Override
	public List<Payments> getPaymentList() throws Exception {
		List<Payments> listResult = paymentDao.getPaymentList();
		if(listResult.size() == 0) {
			throw new Exception("Data belum ada");
		}else {
			return listResult;
		}
	}

	@Override
	public int getTotalTransaksi(String paymentType) throws Exception {
		return paymentDao.getTotalTransaksi(paymentType);
	}

	@Override
	public void updatePayment(Payments payment) throws Exception {
		Payments paymentDb = new Payments();
		if(inputCorrect(payment)) {
			paymentDb = paymentDao.searchPaymentByCode(payment.getPaymentCode());
			if(null != paymentDb) {
				paymentDao.updatePayment(payment);
			}else {
				throw new Exception("Data tidak terdaftar");
			}
		}else {
			validasiKolom(payment.getId());
		}
	}

	@Override
	public void deletePayment(String kode) throws Exception {
		Payments payment = new Payments();
		payment = paymentDao.searchPaymentByCode(kode);
		if(null != payment) {
			paymentDao.deletePayment(payment.getId());
		}else {
			throw new Exception("Data tidak terdaftar");
		}
	}

	@Override
	public Payments searchPaymentByCode(String kode) throws Exception {
		Payments data = paymentDao.searchPaymentByCode(kode);
		if(null == data) {
			throw new Exception("Tipe pembayaran belum terdaftar");
		}else return data;
	}
	
	public boolean inputCorrect(Payments data) {
		return data.getId() == null && data.getPaymentType() != null 
				&& data.getPaymentCode() != null
				&& !data.getPaymentType().isEmpty() && !data.getPaymentCode().isEmpty();
	}

}
