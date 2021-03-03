package com.lawencon.laundry.model;

import java.math.BigDecimal;

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
@Table(name = "t_m_laundry_types")
public class LaundryTypes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="laundry_name", length=50, nullable=false, unique=true)
	private String laundryName;
	
	@Column(name="laundry_code", length=15, nullable=false, unique=true)
	private String laundryCode;
	
	@Column(name="service_cost")
	private BigDecimal laundryCost;
	
	public BigDecimal getLaundryCost() {
		return laundryCost;
	}
	public void setLaundryCost(BigDecimal laundryCost) {
		this.laundryCost = laundryCost;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLaundryName() {
		return laundryName;
	}
	public void setLaundryName(String laundryName) {
		this.laundryName = laundryName;
	}
	public String getLaundryCode() {
		return laundryCode;
	}
	public void setLaundryCode(String laundryCode) {
		this.laundryCode = laundryCode;
	}
}
