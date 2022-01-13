package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	/*
	 * For Read Operation private List<Product> products = Arrays.asList(new
	 * Product("P101", "Monitor", "Electronics"), new Product("P102", "Blanket",
	 * "Household"), new Product("P103", "Laptop", "Electronics"), new
	 * Product("P104", "Shirt", "Fashion"), new Product("P104", "Pen", "School"));
	 */
	
	/*
	 *  Caching configuration for increase the application performance
	 */
	
	@Cacheable("products")
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		repository.findAll().forEach(products::add);
		return products;
	}

	@Cacheable(value = "product", key = "#p0")
	public Optional<Product> getProduct(Long id) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return repository.findById(id);
	}

	/*
	 * @CacheEvict annotation used for cache eviction on cache name "products"
	 */

	@CacheEvict(value = "products", allEntries = true)
	public void addProduct(Product product) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repository.save(product);

	}

	/*
	 * for Update operation
	 * Multiple cache with eviction
	 * once cache eviction for specific id and the second cache eviction for all products
	 */

	@Caching(evict = { @CacheEvict(value = "products", key = "#p0"),
			@CacheEvict(value = "products", allEntries = true) })
	public void updateProduct(Long id, Product product) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (repository.findById(id).get() != null) {
			repository.save(product);
		}
	}
	/*
	 * for Delete operation
	 */

	@Caching(evict = { @CacheEvict(value = "products", key = "#p0"),
			@CacheEvict(value = "products", allEntries = true) })
	public void deletProduct(Long id) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repository.deleteById(id);
	}

}
