package com.lawencon.laundry.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Customers;
import com.lawencon.laundry.model.InvoicesHdr;
import com.lawencon.laundry.model.Outlets;
import com.lawencon.laundry.model.Payments;
import com.lawencon.laundry.model.Promos;
import com.lawencon.laundry.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="invoiceshibernate")
public class InvoiceHdrDaoNativeImpl extends BaseDao implements InvoiceHdrDao {

	@Override
	public Long insertInvoiceHdr(InvoicesHdr invoicesHdr) throws Exception {
		String sqlBuilder = bBuilder("INSERT INTO t_r_hdr_invoices ", "(id_customer, id_user, total_cost,",
				"order_date, invoice_code, id_payment, id_promo) ",
				"values((SELECT id FROM t_m_customers WHERE nik = ?), ", "(SELECT id FROM t_m_users WHERE id = ?), ",
				"0, now(), ('TR' || ((SELECT count(*) from t_r_hdr_invoices) + 1)), ",
				"(SELECT id FROM t_m_payments WHERE payment_code = ?),",
				" (SELECT id FROM t_m_promos WHERE promo_code = ?)) returning id").toString();
		Object listObj = em.createNativeQuery(sqlBuilder)
				.setParameter(1, invoicesHdr.getIdCustomer().getNIK())
				.setParameter(2, invoicesHdr.getIdUser().getId())
				.setParameter(3, invoicesHdr.getIdPayment().getPaymentCode())
				.setParameter(4, invoicesHdr.getIdPromo().getPromoCode()).getSingleResult();
		return Long.valueOf(String.valueOf(listObj));
	}

	@Override
	public void updateTotalCost(Long idInvoiceHdr, String kodePromo) throws Exception {
		String sqlBuilder = bBuilder("UPDATE t_r_hdr_invoices ", "SET total_cost = (SELECT sum(lt.service_cost) ",
				"FROM t_r_dtl_items d ", "INNER JOIN t_m_laundry_types lt ON lt.id = d.id_laundry_type ",
				"WHERE d.id_invoice = ?) - ", "(SELECT discount FROM t_m_promos ", "WHERE promo_code = ?)",
				"WHERE id = ?").toString();
		em.createNativeQuery(sqlBuilder).setParameter(1, idInvoiceHdr).setParameter(2, kodePromo)
				.setParameter(3, idInvoiceHdr).executeUpdate();
	}

	@Override
	public List<InvoicesHdr> getAllInvoiceHdr() throws Exception {
		List<InvoicesHdr> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(bBuilder("SELECT hi.order_date, hi.total_cost, u.username, o.outlet_code ", "from t_r_hdr_invoices hi ",
				"INNER JOIN t_r_dtl_items di on di.id_invoice = hi.id ",
				"INNER JOIN t_m_users u on u.id = hi.id_user ", "INNER JOIN t_m_outlets o on o.id = u.id_outlet ",
				"GROUP BY hi.id, u.username, o.outlet_code ",
				"ORDER BY hi.order_date asc").toString()).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			InvoicesHdr invoice = new InvoicesHdr();
			java.sql.Date orderDate = (java.sql.Date) objArr[0];
			invoice.setOrderDate(orderDate.toLocalDate());
			invoice.setTotalCost((BigDecimal) objArr[1]);
			Users user = new Users();
			user.setUserName((String) objArr[2]);
			Outlets outlet = new Outlets();
			outlet.setOutletsCode((String) objArr[3]);
			user.setIdOutlet(outlet);
			invoice.setIdUser(user);
			listResult.add(invoice);
		});
		return listResult;
	}

	@Override
	public List<InvoicesHdr> getAllInvoiceHdrByOutletsCode(String inputKodeOutlet) throws Exception {
		List<InvoicesHdr> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(bBuilder("SELECT hi.order_date, hi.total_cost, u.username ", "from t_r_hdr_invoices hi ",
				"INNER JOIN t_r_dtl_items di on di.id_invoice = hi.id ",
				"INNER JOIN t_m_users u on u.id = hi.id_user ", "INNER JOIN t_m_outlets o on o.id = u.id_outlet ",
				"WHERE o.outlet_code = ?",
				"GROUP BY hi.id, u.username ", 
				"order by hi.order_date asc").toString())
				.setParameter(1, inputKodeOutlet).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			InvoicesHdr invoice = new InvoicesHdr();
			java.sql.Date orderDate = (java.sql.Date) objArr[0];
			invoice.setOrderDate(orderDate.toLocalDate());
			invoice.setTotalCost((BigDecimal) objArr[1]);
			Users user = new Users();
			user.setUserName((String) objArr[2]);
			invoice.setIdUser(user);
			listResult.add(invoice);
		});
		return listResult;
	}

	@Override
	public List<InvoicesHdr> getAllInvoiceHdrByCashier(Long idUser) throws Exception {
		List<InvoicesHdr> listResult = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(bBuilder("SELECT hi.order_date,",
				" hi.total_cost, c.name, pay.payment_type, p.promo_name "
				,"from t_r_hdr_invoices hi ",
				"INNER JOIN t_r_dtl_items di on di.id_invoice = hi.id ",
				"INNER JOIN t_m_users u on u.id = hi.id_user ", "INNER JOIN t_m_customers c on c.id = hi.id_customer ",
				"INNER JOIN t_m_promos p on p.id = hi.id_promo ", "INNER JOIN t_m_outlets o on o.id = u.id_outlet ",
				"INNER JOIN t_m_payments pay on pay.id = hi.id_payment ",
				"WHERE u.id_outlet = (SELECT id_outlet from t_m_users", 
				" WHERE id = ?) ", 
				"GROUP BY hi.id, c.name, pay.payment_type, p.promo_name ",
				"order by hi.order_date asc").toString()).setParameter(1, idUser).getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			InvoicesHdr invoice = new InvoicesHdr();
			java.sql.Date orderDate = (java.sql.Date) objArr[0];
			invoice.setOrderDate(orderDate.toLocalDate());
			invoice.setTotalCost((BigDecimal) objArr[1]);
			Customers customer = new Customers();
			customer.setName((String) objArr[2]);
			invoice.setIdCustomer(customer);
			Payments payment = new Payments();
			payment.setPaymentType((String) objArr[3]);
			invoice.setIdPayment(payment);
			Promos promo = new Promos();
			promo.setPromoName((String) objArr[4]);
			invoice.setIdPromo(promo);
			listResult.add(invoice);
		});
		return listResult;
	}

}
