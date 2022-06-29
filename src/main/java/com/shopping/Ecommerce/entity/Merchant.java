package com.shopping.Ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MERCHANT")
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long merchant_id;
	private String merchant_name;
	private String merchant_type;
	private long merchant_accountNumber;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "mp_f", referencedColumnName = "merchant_id")
	List<Product> product = new ArrayList<>();
	
	public Merchant(String merchant_name, String merchant_type, long merchant_accountNumber) {
		super();
		this.merchant_name = merchant_name;
		this.merchant_type = merchant_type;
		this.merchant_accountNumber = merchant_accountNumber;
	}

	public Merchant() {
		super();
	}

	
	
	public long getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(long merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getMerchant_type() {
		return merchant_type;
	}

	public void setMerchant_type(String merchant_type) {
		this.merchant_type = merchant_type;
	}

	public long getMerchant_accountNumber() {
		return merchant_accountNumber;
	}

	public void setMerchant_accountNumber(long merchant_accountNumber) {
		this.merchant_accountNumber = merchant_accountNumber;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Merchant [merchant_id=" + merchant_id + ", merchant_name=" + merchant_name + ", merchant_type="
				+ merchant_type + ", merchant_accountNumber=" + merchant_accountNumber + "]";
	}

	
}


