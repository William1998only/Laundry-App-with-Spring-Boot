package com.lawencon.laundry.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * 
 * @author WILLIAM
 *
 */

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "t_r_hdr_invoices")
public class InvoicesHdr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_customer", nullable=false)
	private Customers idCustomer;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable=false)
	private Users idUser;
	
	@Column(name = "total_cost")
	private BigDecimal totalCost;
	
	@Column(name = "order_date", nullable=false)
	private LocalDate orderDate;
	
	@Column(name = "invoice_code", unique=true, nullable=false, length=15)
	private String invoiceCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_payment", nullable=false)
	private Payments idPayment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_promo", nullable=false)
	private Promos idPromo;
	
	@Transient
	private List<ItemsDtl> items;

	public List<ItemsDtl> getItems() {
		return items;
	}
	public void setItems(List<ItemsDtl> items) {
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customers getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Customers idCustomer) {
		this.idCustomer = idCustomer;
	}
	public Users getIdUser() {
		return idUser;
	}
	public void setIdUser(Users idUser) {
		this.idUser = idUser;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public Payments getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(Payments idPayment) {
		this.idPayment = idPayment;
	}
	public Promos getIdPromo() {
		return idPromo;
	}
	public void setIdPromo(Promos idPromo) {
		this.idPromo = idPromo;
	}
}
