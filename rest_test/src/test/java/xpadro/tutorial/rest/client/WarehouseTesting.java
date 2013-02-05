package xpadro.tutorial.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import xpadro.tutorial.rest.model.Product;
import xpadro.tutorial.rest.model.Warehouse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath:xpadro/tutorial/rest/configuration/root-context.xml",
	"classpath:xpadro/tutorial/rest/configuration/app-context.xml"})
public class WarehouseTesting {
	private static final int WAREHOUSE_ID = 1;
	private static final int PRODUCT_ID = 4;
	
	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * Tests accessing to an existing warehouse
	 */
	@Test
	public void getWarehouse() {
		String uri = "http://localhost:8081/rest_test/spring/warehouses/{warehouseId}";
		Warehouse warehouse = restTemplate.getForObject(uri, Warehouse.class, WAREHOUSE_ID);
		assertNotNull(warehouse);
		assertEquals("WAR_BCN_004", warehouse.getName());
	}
	
	/**
	 * Tests the addition of a new product to an existing warehouse.
	 */
	@Test
	public void addProduct() {
		//Adds the new product
		String uri = "http://localhost:8081/rest_test/spring/warehouses/{warehouseId}/products";
		Product product = new Product(PRODUCT_ID, "PROD_999");
		URI newProductLocation = restTemplate.postForLocation(uri, product, WAREHOUSE_ID);
		
		//Checks we can access to the created product
		Product createdProduct = restTemplate.getForObject(newProductLocation, Product.class);
		assertEquals(product, createdProduct);
		assertNotNull(createdProduct.getId());
	}

	/**
	 * Tests the removal of an existing product
	 */
	@Test
	public void removeProduct() {
		String uri = "http://localhost:8081/rest_test/spring/warehouses/{warehouseId}/products/{productId}";
		restTemplate.delete(uri, WAREHOUSE_ID, PRODUCT_ID);
		
		try {
			restTemplate.getForObject(uri, Product.class, WAREHOUSE_ID, PRODUCT_ID);
			throw new AssertionError("Should have returned an 404 error code");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
}
