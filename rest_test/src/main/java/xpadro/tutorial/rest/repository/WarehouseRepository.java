package xpadro.tutorial.rest.repository;

import java.util.Set;

import xpadro.tutorial.rest.model.Product;
import xpadro.tutorial.rest.model.Warehouse;

/**
 * Manages data from warehouses and its products
 * @author xpadro
 *
 */
public interface WarehouseRepository {
	
	/**
	 * Returns the warehouse identified by the id
	 * @param id
	 * @return the warehouse
	 */
	public Warehouse getWarehouse(Integer id);
	
	/**
	 * Creates a new warehouse
	 * @param warehouse
	 */
	public void createWarehouse(Warehouse warehouse);
	
	/**
	 * Removes an existing warehouse identified by the id
	 * @param warehouseId
	 */
	public void removeWarehouse(int warehouseId);
	
	/**
	 * Returns the list of all products contained in the warehouse identified by the id
	 * @param warehouseId
	 * @return
	 */
	public Set<Product> getProducts(int warehouseId);
	
	/**
	 * Gets a product by its id that is contained in the warehouse
	 * @param warehouseId
	 * @param productId
	 * @return the product
	 */
	public Product getProduct(int warehouseId, int productId);
	
	/**
	 * Adds a new product to an existing warehouse
	 * @param warehouseId
	 * @param product
	 */
	public void addProduct(int warehouseId, Product product);
	
	/**
	 * Removes an existing product identified by the id
	 * @param warehouseId
	 * @param productId
	 */
	public void removeProduct(int warehouseId, int productId);
}
