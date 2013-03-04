package xpadro.tutorial.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import xpadro.tutorial.rest.exception.ProductNotFoundException;
import xpadro.tutorial.rest.model.Product;
import xpadro.tutorial.rest.model.Warehouse;
import xpadro.tutorial.rest.repository.WarehouseRepository;

/**
 * Contains CRUD operations on warehouses and its products.
 * @author xpadro
 *
 */
@Controller
public class WarehouseController {
	private static Logger logger = Logger.getLogger("main");

	@Autowired
	private WarehouseRepository warehouseRepository;

	
	/**
	 * Returns the warehouse requested by its id.
	 * @param id
	 * @return The requested warehouse
	 */
	@RequestMapping(value="/warehouses/{warehouseId}", method=RequestMethod.GET)
	public @ResponseBody Warehouse getWarehouse(@PathVariable("warehouseId") int id) {
		return warehouseRepository.getWarehouse(id);
	}
	
	/**
	 * Adds a new product to the specified warehouse.
	 * @param warehouseId The id of the warehouse where to add the new product
	 * @param product The new product to be created
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/warehouses/{warehouseId}/products", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addProduct(@PathVariable("warehouseId") int warehouseId, @RequestBody Product product, 
			HttpServletRequest request, HttpServletResponse response) {
		
		warehouseRepository.addProduct(warehouseId, product);
		response.setHeader("Location", request.getRequestURL().append("/").append(product.getId()).toString());
	}
	
	/**
	 * Removes a product from a specified warehouse.
	 * @param warehouseId The id of the warehouse where to remove the product
	 * @param productId The id of the product to be removed
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/warehouses/{warehouseId}/products/{productId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeProduct(@PathVariable("warehouseId") int warehouseId, @PathVariable("productId") int productId, 
			HttpServletRequest request, HttpServletResponse response) {
		
		warehouseRepository.removeProduct(warehouseId, productId);
	}
	
	/**
	 * Returns the product from a specified warehouse.
	 * @param warehouseId The id of the warehouse where to get the product
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/warehouses/{warehouseId}/products/{productId}", method=RequestMethod.GET)
	public @ResponseBody Product getProduct(@PathVariable("warehouseId") int warehouseId, @PathVariable("productId") int productId) {
		return warehouseRepository.getProduct(warehouseId, productId);
	}
	
	/**
	 * Handles ProductNotFoundException and returns a 404 response status code
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ProductNotFoundException.class})
	public void handleProductNotFound(ProductNotFoundException pe) {
		logger.warn("Product not found. Code: "+pe.getMessage());
	}
	
	
	@InitBinder
	public void testBinder(WebDataBinder binder, WebRequest req) {
		System.out.println();
	}
}
