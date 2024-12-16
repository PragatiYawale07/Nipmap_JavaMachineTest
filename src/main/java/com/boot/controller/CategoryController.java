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

import com.boot.entity.Category;
import com.boot.repository.CategoryRepo;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
   @Autowired
   private CategoryRepo cRepo;
   
   @GetMapping()
	public Page<Category> getAllCategories(Pageable pageable)
    {
	    return cRepo.findAll(pageable);
	}
	
   @PostMapping()
   public String addCategory(@RequestBody Category categ)
   {
	    cRepo.save(categ);
		return "Record save successfully.........";
   }
	
	@GetMapping("/{id}")
	public Optional<Category> getRecordById(@PathVariable long id)
	{
		return cRepo.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable long id)
	{
		if(cRepo.existsById(id))
		{		
		  cRepo.deleteById(id);
		  return "Record deteled by id successfully.......";
		}
		else
		{
			return "Category not found......";
		}
	 }
	
	@PutMapping("/{id}")
	public String updateCategory(@PathVariable long id,@RequestBody Category categ)
	{
		Optional<Category> existingCategory = cRepo.findById(id);
		
		if(existingCategory.isPresent())
		{
			Category category =existingCategory.get();
			categ.setName(category.getName());
			cRepo.save(categ);
		    return "Record update by id successfully...... ";
		}
		else
		{
			return "category not found.......";
		}
	}
}
