package com.example.demo.exception;

public class ProductNotFoundException extends RuntimeException {
    /**
     * This is the example of Customized Exception
	 * If the particular Product ID is not found then the ProductNotFoundException() method will be called
	 */
	private static final long serialVersionUID = 1L;

public ProductNotFoundException(Long id) {
	 super("The Product with pId " + id + " cannot be found");
 }
}
