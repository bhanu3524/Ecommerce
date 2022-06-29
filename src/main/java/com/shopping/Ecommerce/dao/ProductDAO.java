package com.shopping.Ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.Ecommerce.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Long>{

}
