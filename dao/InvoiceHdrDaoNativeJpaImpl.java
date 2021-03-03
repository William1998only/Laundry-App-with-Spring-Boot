package com.lawencon.laundry.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Customers;
import com.lawencon.laundry.model.InvoicesHdr;
import com.lawencon.laundry.model.Outlets;
import com.lawencon.laundry.model.Payments;
import com.lawencon.laundry.model.Promos;
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.repo.InvoiceHdrRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="invoicesjpa")
public class InvoiceHdrDaoNativeJpaImpl extends BaseDao implements InvoiceHdrDao {

	@Autowired
	InvoiceHdrRepo invoiceHdrRepo;
	
	@Override
	public Long insertInvoiceHdr(InvoicesHdr invoicesHdr) throws Exception {
		Object obj = invoiceHdrRepo.insertInvoiceHdr(invoicesHdr.getIdCustomer().getNIK(), 
				invoicesHdr.getIdUser().getId(), 
				invoicesHdr.getIdPayment().getPaymentCode(), 
				invoicesHdr.getIdPromo().getPromoCode());
		return Long.valueOf(String.valueOf(obj));
	}

	@Override
	public void updateTotalCost(Long idInvoiceHdr, String kodePromo) throws SQLException {
		invoiceHdrRepo.updateTotalCost(idInvoiceHdr, kodePromo);
	}

	@Override
	public List<InvoicesHdr> getAllInvoiceHdr() throws Exception {
		List<InvoicesHdr> listResult = new ArrayList<>();
		List<Object[]> listObj = invoiceHdrRepo.getAllInvoiceHdr();
		listObj.forEach(objArr -> {
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
		List<Object[]> listObj = invoiceHdrRepo.getAllInvoiceHdrByOutletsCode(inputKodeOutlet);
		listObj.forEach(objArr -> {
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
		List<Object[]> listObj = invoiceHdrRepo.getAllInvoiceHdrByCashier(idUser);
		listObj.forEach(objArr -> {
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
