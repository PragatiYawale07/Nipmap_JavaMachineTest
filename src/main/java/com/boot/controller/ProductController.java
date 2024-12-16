package com.boot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.entity.Product;
import com.boot.repository.ProductRepo;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/api/Products")
public class ProductController {
		
	   @Autowired
	   private ProductRepo pRepo;
	   
	   @GetMapping()
		public Page<Product> getAllProducts(Pageable pageable) {
		    return pRepo.findAll(pageable);
		}
		
	   @PostMapping()
	   public String addProduct(@RequestBody Product product)
	    {
		   System.out.println("Incoming Product:" + product);
		   pRepo.save(product);
			return "Record save successfully.........";
	    }
		
	   @GetMapping("/{id}")
	   public Optional<Product> getRecordById(@PathVariable long id)
		{
			
			return pRepo.findById(id);
		}
		
		@DeleteMapping("/{id}")
		public String deleteProduct(@PathVariable long id)
		{
			if(pRepo.existsById(id))
			{		
			  pRepo.deleteById(id);
			  return "Record deteled by id successfully.......";
			}
			else
			{
				return "Product not found......";
			}
		}
		
		@PutMapping("/{id}")
		public String updateProduct(@PathVariable long id,@RequestBody Product product)
		{
			Optional<Product> existingProduct = pRepo.findById(id);
			
			if(existingProduct.isPresent())
			{
				Product prod =existingProduct.get();
				prod.setName(product.getName());
				prod.setPrice(product.getPrice());
				pRepo.save(prod);
			    return "Record update by id successfully...... ";
			}
			else
			{
				return "Product not found.......";
			}
		}
	   
}
