package com.shopping.Ecommerce.services;

import java.util.List;

import com.shopping.Ecommerce.entity.Product;

public interface ProductService {
	
	public List<Product> getProducts();
	
	 public Product findProduct(long merchant_id);
	 
	 public Product addProduct(Product product);

	 public Product updateProduct(Product product);

	 public void deleteProduct(long parseLong);

}
