package xpadro.rest.ri.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import xpadro.rest.ri.model.TypeBItem;
import xpadro.rest.ri.repository.TypeBItemRepository;

/**
 * Type B Subresource
 */
public class TypeBResource implements ItemResource {
	private int itemId;
	private TypeBItemRepository itemRepository = new TypeBItemRepository();

	public TypeBResource(int id) {
		this.itemId = id;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TypeBItem getTypeBItem() {
		TypeBItem item = itemRepository.retrieveItem(itemId);
		if (item == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return item;
	}
}
