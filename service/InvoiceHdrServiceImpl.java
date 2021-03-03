package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.InvoiceHdrDao;
import com.lawencon.laundry.model.InvoicesHdr;
import com.lawencon.laundry.model.Promos;
import com.lawencon.laundry.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class InvoiceHdrServiceImpl extends BaseService implements InvoiceHdrService {

	@Autowired
	@Qualifier(value="invoicesjpa")
	private InvoiceHdrDao invoicesHdrDao;
	
	@Autowired
	private ItemDtlService itemDtlService;
	
	@Autowired
	private PromoService promoService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentService paymentService;

	@Override
	public void insertInvoiceHdr(List<InvoicesHdr> invoiceList) throws Exception {
		Promos promo = new Promos();
		Long idInvoiceHdr = null;
		if(invoiceList.size() == 0) {
			throw new Exception("Input kosong");
		}
		for (int i = 0; i < invoiceList.size(); i++) {
			if(null != invoiceList.get(i).getId()) {
				throw new Exception("Tidak boleh memasukkan ID");
			}
			if(inputCorrect(invoiceList.get(i))) {
				if(inputNotEmpty(invoiceList.get(i))) {
					if (invoiceList.get(i).getIdPromo() == null) {
						promo.setPromoCode("no_promo");
						invoiceList.get(i).setIdPromo(promo);
					}else {
						promo = promoService.searchPromoByCode(invoiceList.get(i).getIdPromo().getPromoCode());
						if(null == promo) {
							invoiceList.get(i).getIdPromo().setPromoCode("no_promo");
						}
					}
					customerService.searchCustomerByNik(invoiceList.get(i).getIdCustomer().getNIK());
					paymentService.searchPaymentByCode(invoiceList.get(i).getIdPayment().getPaymentCode());
					Users user = userService.getUserByName(invoiceList.get(i).getIdUser().getUserName());
					invoiceList.get(i).setIdUser(user);
					idInvoiceHdr = invoicesHdrDao.insertInvoiceHdr(invoiceList.get(i));
					try {
						itemDtlService.insertItemDtl(idInvoiceHdr, invoiceList.get(i));
					}catch (Exception e) {
						throw new Exception(e.getMessage());
					}
					invoicesHdrDao.updateTotalCost(idInvoiceHdr, invoiceList.get(i).getIdPromo().getPromoCode());
				}else {
					throw new Exception("Input tidak boleh kosong");
				}
			}else {
				throw new Exception("Input kolom harus lengkap \n"
						+ "Daftar kolom yang harus dimasukkan: \n" 
						+ "Nik, username, daftar barang");
			}
		}
	}

	@Override
	public List<InvoicesHdr> getAllInvoiceHdr() throws Exception {
		List<InvoicesHdr> listResult = invoicesHdrDao.getAllInvoiceHdr();
		return listResult;
	}

	@Override
	public List<InvoicesHdr> getAllInvoiceHdrByOutletsCode(String inputKodeOutlet) throws Exception {
		List<InvoicesHdr> listResult = invoicesHdrDao.getAllInvoiceHdrByOutletsCode(inputKodeOutlet);
		return listResult;
	}

	@Override
	public List<InvoicesHdr> getAllInvoiceHdrByCashier(Long idUser) throws Exception {
		List<InvoicesHdr> listResult = invoicesHdrDao.getAllInvoiceHdrByCashier(idUser);
		return listResult;
	}
	
	public boolean inputCorrect(InvoicesHdr invoice) {
		return invoice.getId() == null && null != invoice.getIdCustomer() 
				&& null != invoice.getIdUser() && null != invoice.getIdPayment() 
				&& null != invoice.getIdCustomer().getNIK() 
				&& null != invoice.getIdUser().getUserName()
				&& null != invoice.getIdPayment().getPaymentCode();
	}
	
	public boolean inputNotEmpty(InvoicesHdr invoice) {
		return !invoice.getIdCustomer().getNIK().isEmpty() 
				&& !invoice.getIdPayment().getPaymentCode().isEmpty()
				&& !invoice.getIdUser().getUserName().isEmpty();
	}
}
