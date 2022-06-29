package com.shopping.Ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.Ecommerce.dao.ProductDAO;
import com.shopping.Ecommerce.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productdao;

	@Override
	public List<Product> getProducts() {
		return productdao.findAll();
	}

	@Override
	public Product findProduct(long product_id) {
		return productdao.findById(product_id).get();
	}

	@Override
	public Product addProduct(Product product) {
		return productdao.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productdao.save(product);
	}

	@Override
	public void deleteProduct(long parseLong) {
		Product entity =productdao.getOne(parseLong);
		productdao.delete(entity);
		
	}

}
