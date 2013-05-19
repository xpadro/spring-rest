package xpadro.rest.ri.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Root resource
 */
@Path("/warehouse")
public class WarehouseResource {
	
	@GET
	public String getWarehouseInfo() {
		return "Warehouse location: Barcelona";
	}
	
	@Path("/items/{itemId}")
	public ItemResource getItem(@PathParam("itemId") Integer itemId) {
		ItemResource itemResource = null;

		if (itemId > 10) {
			itemResource = new TypeAResource(itemId);
		}
		else {
			itemResource = new TypeBResource(itemId);
		}
		
		return itemResource;
	}
}
