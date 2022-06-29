package com.shopping.Ecommerce.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long product_id;
	private String product_name;
	private String product_description;
	private int product_price;
	private long mp_f;
	
	
	
	/*
	 * @ManyToOne(cascade = CascadeType.MERGE)
	 * 
	 * @JoinColumn(name="mp_f") public Merchant getMerchant_id() { return
	 * getMerchant_id(); }
	 */
	 

	public Product() {
		super();
	}	
	
	

	public Product(String product_name, String product_description, int product_price, long merchant_id) {
		super();
		this.product_name = product_name;
		this.product_description = product_description;
		this.product_price = product_price;
		this.mp_f = merchant_id;
	}


	

	public long getProduct_id() {
		return product_id;
	}



	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}



	public String getProduct_name() {
		return product_name;
	}



	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}



	public String getProduct_description() {
		return product_description;
	}



	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}



	public int getProduct_price() {
		return product_price;
	}



	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}



	public long getMp_f() {
		return mp_f;
	}



	public void setMp_f(long mp_f) {
		this.mp_f = mp_f;
	}



	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_description="
				+ product_description + ", product_price=" + product_price + ", mp_f=" + mp_f + "]";
	}


	


}
