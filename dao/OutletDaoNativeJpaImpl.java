package com.lawencon.laundry.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Outlets;
import com.lawencon.laundry.repo.OutletRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="outletsjpa")
public class OutletDaoNativeJpaImpl extends BaseDao implements OutletDao {
	
	@Autowired
	OutletRepo outletRepo;

	@Override
	public void insertOutletsData(Outlets outlet) throws Exception {
		outletRepo.save(outlet);
	}

	@Override
	public Long getOutletsByCode(String outletsCode) throws Exception {
		Object obj = outletRepo.getOutletsByCode(outletsCode);
		return Long.valueOf(String.valueOf(obj));
	}

	@Override
	public List<Outlets> getAllOutlets() throws Exception {
		return outletRepo.getAllOutlets();
	}

	@Override
	public Outlets searchOutletsByCode(String inputKode) throws Exception {
		return outletRepo.searchOutletsByCode(inputKode);
	}

	@Override
	public void updateOutlet(Outlets outlet) throws Exception {
		outletRepo.updateOutlet(outlet.getLocation(), outlet.getOutletsName()
				, outlet.getPhoneNumber(), outlet.getOutletsCode());
	}

	@Override
	public void deleteOutlet(Outlets outlet) throws Exception {
		outletRepo.deleteOutlet(outlet.getOutletsCode());
	}
}
