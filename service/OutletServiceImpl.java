package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.laundry.dao.OutletDao;
import com.lawencon.laundry.model.Outlets;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class OutletServiceImpl extends BaseService implements OutletService {

	@Autowired
	@Qualifier(value="outletsjpa")
	private OutletDao outletDao;

	@Override
	public void insertOutletsData(Outlets outlet) throws Exception {
		Outlets outletDb = new Outlets();
		if(inputCorrect(outlet)) {
			outletDb = outletDao.searchOutletsByCode(outlet.getOutletsCode());
			if(null != outletDb) {
				throw new Exception("Data sudah ada");
			}else {
				outletDao.insertOutletsData(outlet);
			}
		}else {
			validasiKolom(outlet.getId());
		}
	}

	@Override
	public Long getOutletsByCode(String outletsCode) throws Exception {
		return outletDao.getOutletsByCode(outletsCode);
	}

	@Override
	public List<Outlets> getAllOutlets() throws Exception {
		return outletDao.getAllOutlets();
	}

	@Override
	public Outlets searchOutletsByCode(String inputKode) throws Exception {
		Outlets data = outletDao.searchOutletsByCode(inputKode);
		if(null == data) {
			throw new Exception("Outlet belum terdaftar");
		}else return data;
	}

	@Override
	public void updateOutlet(Outlets outlet) throws Exception{
		Outlets outletDb = new Outlets();
		if(inputCorrect(outlet)) {
			outletDb = outletDao.searchOutletsByCode(outlet.getOutletsCode());
			if(null != outletDb) {
				outletDao.updateOutlet(outlet);
			}else {
				throw new Exception("Data tidak terdaftar");
			}
		}else {
			validasiKolom(outlet.getId());
		}
	}

	@Override
	public void deleteOutlet(Outlets data) throws Exception{
		outletDao.deleteOutlet(data);
	}
	
	public boolean inputCorrect(Outlets data) {
		return data.getId() == null && data.getOutletsName() != null 
				&& data.getLocation() != null
				&& data.getPhoneNumber() != null && data.getOutletsCode() != null
				&& !data.getOutletsName().isEmpty() && !data.getLocation().isEmpty()
				&& !data.getPhoneNumber().isEmpty() && !data.getOutletsCode().isEmpty();
	}
}
