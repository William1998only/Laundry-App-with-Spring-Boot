package com.lawencon.laundry.service;

/**
 * 
 * @author WILLIAM
 *
 */
public abstract class BaseService {
	
	public void validasiKolom(Long id) throws Exception{
		if(null != id) {
			throw new Exception("Tidak boleh memasukkan ID");
		}else {
			throw new Exception("Input tidak boleh kosong dan kolom tidak boleh kurang");
		}
	}
	
}
