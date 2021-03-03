package com.lawencon.laundry.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Payments;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="paymentshibernate")
public class PaymentDaoNativeImpl extends BaseDao implements PaymentDao {

	@Override
	public void insertPaymentType(Payments data) throws Exception {
		String sqlBuilder = bBuilder("INSERT INTO t_m_payments ", "(payment_type, payment_code) ", "values(?, ?)")
				.toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, data.getPaymentType())
				.setParameter(2, data.getPaymentCode()).executeUpdate();
	}

	@Override
	public List<Payments> getPaymentList() throws Exception {
		String sqlBuilder = bBuilder("select * from t_m_payments ").toString();
		List<Payments> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Payments payment = new Payments();
			payment.setPaymentCode((String) objArr[1]);
			payment.setPaymentType((String) objArr[2]);
			listResult.add(payment);
		});
		return listResult;
	}

	@Override
	public int getTotalTransaksi(String paymentType) throws Exception {
		String sqlBuilder = bBuilder("select * ", "from t_m_payments p ",
				"inner join t_r_hdr_invoices hi on hi.id_payment = p.id ", "where p.payment_type = ? ").toString();
		List<?> listObj = em.createNativeQuery(sqlBuilder, Payments.class).setParameter(1, paymentType).getResultList();
		return listObj.size();
	}

	@Override
	public void updatePayment(Payments payment) {
		em.createNativeQuery(bBuilder("UPDATE t_m_payments ",
				"SET payment_type = ? WHERE payment_code = ?").toString())
		.setParameter(1, payment.getPaymentType())
		.setParameter(2, payment.getPaymentCode())
		.executeUpdate();
	}

	@Override
	public void deletePayment(Long id) {
		em.createNativeQuery(bBuilder("DELETE FROM t_m_payments ",
				"WHERE id = ?").toString())
		.setParameter(1, id)
		.executeUpdate();
	}

	@Override
	public Payments searchPaymentByCode(String kode) {
		String sqlBuilder = bBuilder("SELECT * from t_m_payments ",
				"WHERE payment_code = ?"
				).toString();
		List<Payments> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sqlBuilder)
				.setParameter(1, kode)
				.getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Payments payment = new Payments();
			payment.setPaymentType((String) objArr[2]);
			listResult.add(payment);
		});
		return listResult.get(0);
	}
}
