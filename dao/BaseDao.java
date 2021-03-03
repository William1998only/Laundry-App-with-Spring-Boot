package com.lawencon.laundry.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * 
 * @author WILLIAM
 *
 */
public abstract class BaseDao {
	
	@PersistenceContext
	protected EntityManager em;

	protected StringBuilder bBuilder(String... datas) {
		StringBuilder b = new StringBuilder();
		for (String d : datas) {
			b.append(d);
		}
		return b;
	}
}
