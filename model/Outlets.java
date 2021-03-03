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
@Table(name = "t_m_outlets")
public class Outlets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="outlets_name", length=60, nullable=false)
	private String outletsName;
	
	@Column(length=100, nullable=false)
	private String location;
	
	@Column(length=15, nullable=false)
	private String phoneNumber;
	
	@Column(name="outlet_code", length=25, nullable=false, unique=true)
	private String outletsCode;
	
	public String getOutletsCode() {
		return outletsCode;
	}
	public void setOutletsCode(String outletsCode) {
		this.outletsCode = outletsCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOutletsName() {
		return outletsName;
	}
	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
