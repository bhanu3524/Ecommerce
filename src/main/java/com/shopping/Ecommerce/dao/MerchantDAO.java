package com.shopping.Ecommerce.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.Ecommerce.entity.Merchant;
import com.shopping.Ecommerce.entity.Product;

public interface MerchantDAO extends JpaRepository<Merchant, Long>{

	Merchant save(List<Product> product);

	

}
