package com.lawencon.laundry.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_r_dtl_items")
public class ItemsDtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_invoice", nullable=false)
	private InvoicesHdr idInvoice;
	
	@Column(name = "item_name", length=50, nullable=false)
	private String itemName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_item_type", nullable=false)
	private ItemTypes idItemType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_laundry_type", nullable=false)
	private LaundryTypes idLaundryType;
	
	@Column(name = "expected_done_date", nullable=false)
	private LocalDate expectedDoneDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public InvoicesHdr getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(InvoicesHdr idInvoice) {
		this.idInvoice = idInvoice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public ItemTypes getIdItemType() {
		return idItemType;
	}
	public void setIdItemType(ItemTypes idItemType) {
		this.idItemType = idItemType;
	}
	public LaundryTypes getIdLaundryType() {
		return idLaundryType;
	}
	public void setIdLaundryType(LaundryTypes idLaundryType) {
		this.idLaundryType = idLaundryType;
	}
	public LocalDate getExpectedDoneDate() {
		return expectedDoneDate;
	}
	public void setExpectedDoneDate(LocalDate expectedDoneDate) {
		this.expectedDoneDate = expectedDoneDate;
	}
}
