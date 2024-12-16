package com.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.Category;


public interface CategoryRepo extends JpaRepository<Category,Long> {

//	public Page<Category> findAll(Pageable pageable);
}
