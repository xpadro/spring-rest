package xpadro.tutorial.rest.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import xpadro.tutorial.rest.model.Product;
import xpadro.tutorial.rest.model.Warehouse;

/**
 * A stub repository that uses dummy data for testing
 * @author xpadro
 *
 */
@Repository
public class WarehouseRepositoryImpl implements WarehouseRepository {
	private Map<Integer, Warehouse> warehouses;
	
	
	public WarehouseRepositoryImpl() {
		warehouses = new HashMap<Integer, Warehouse>();
		createDummyWarehouses();
	}

	@Override
	public Warehouse getWarehouse(Integer id) {
		return warehouses.get(id);
	}

	@Override
	public void createWarehouse(Warehouse warehouse) {
		warehouses.put(warehouse.getId(), warehouse);
	}

	@Override
	public void removeWarehouse(int warehouseId) {
		warehouses.remove(warehouseId);
	}

	@Override
	public Set<Product> getProducts(int warehouseId) {
		return warehouses.get(warehouseId).getProducts();
	}

	@Override
	public Product getProduct(int warehouseId, int productId) {
		return warehouses.get(warehouseId).getProduct(productId);
	}

	@Override
	public void addProduct(int warehouseId, Product product) {
		warehouses.get(warehouseId).addProduct(product);
	}

	@Override
	public void removeProduct(int warehouseId, int productId) {
		warehouses.get(warehouseId).removeProduct(productId);
	}

	private void createDummyWarehouses() {
		Set<Product> products = new HashSet<Product>();
		Product product = new Product(1, "PROD_004");
		products.add(product);
		product = new Product(2, "PROD_015");
		products.add(product);
		product = new Product(3, "PROD_125");
		products.add(product);
		Warehouse warehouse = new Warehouse(1, "WAR_BCN_004", products);
		
		warehouses.put(1, warehouse);
	}
}
