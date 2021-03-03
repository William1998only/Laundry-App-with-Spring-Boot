package com.lawencon.laundry.service;


import java.util.List;

import com.lawencon.laundry.model.Outlets;

/**
 * 
 * @author WILLIAM
 *
 */
public interface OutletService {

	void insertOutletsData(Outlets outlet) throws Exception;

	Long getOutletsByCode(String outletsCode) throws Exception;

	List<Outlets> getAllOutlets() throws Exception;

	Outlets searchOutletsByCode(String inputKode) throws Exception;

	void updateOutlet(Outlets outlet) throws Exception;

	void deleteOutlet(Outlets outlet) throws Exception;
	
}
