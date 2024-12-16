package com.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Long>{

//	public Page<Product> findAll(Pageable pageable);
}
