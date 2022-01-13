package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	/*
     *  For READ Operation to All Products in the Collection
     */
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
	/*
	 * public List<Product> getAllProducts(){
	 * 
	 * return Arrays.asList( new Product("P101","Monitor","Electronics"), new
	 * Product("P102","Blanket","Household"), new
	 * Product("P103","Laptop","Electronics"), new
	 * Product("P104","Shirt","Fashion"), new Product("P104","Pen","School")); }
	 */
	
	/*
     *  For READ Operation using Specific Request Parameter ID
     */
	
    @GetMapping("/products/{pId}")
	public Product getProduct(@PathVariable("pId") Long id) {
		return service.getProduct(id).orElseThrow(()-> new ProductNotFoundException(id));
	}
    /*
     * @PostMapping("/products") 
     * &
     * @RequestBody for creation of new product
     * 
     *  For CREATE Operation
     *
     */
    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
    	service.addProduct(product);
    	System.out.println("printing product values:" + product);
    }
    
    /*
     *  For UPDATE Operation for product
     */
    @PutMapping("/product/{pId}")
    public void updateProduct(@RequestBody Product product,@PathVariable("pId") Long id)
    {
    	service.updateProduct(id, product);
    }
    
    
    /*
     *  For DELETE Operation for product
     */
    @DeleteMapping("/product/{pId}")
    public void deleteProduct(@PathVariable("pId")Long id) {
    	service.deletProduct(id);
    }
}
