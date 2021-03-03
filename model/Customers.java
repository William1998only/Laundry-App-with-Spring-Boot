package com.lawencon.laundry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author WILLIAM
 *
 */

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "t_m_customers")
public class Customers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable=false)
	private String name;
	
	@Column(length = 100, nullable=false)
	private String address;
	
	@Column(name = "phone_number", length=15, nullable=false)
	private String phoneNumber;
	
	@Column(name = "nik", unique = true, nullable=false, length=50)
	private String NIK;
	
	public String getNIK() {
		return NIK;
	}
	public void setNIK(String nIK) {
		NIK = nIK;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
