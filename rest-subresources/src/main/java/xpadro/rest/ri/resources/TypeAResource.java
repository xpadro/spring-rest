package xpadro.rest.ri.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import xpadro.rest.ri.model.TypeAItem;
import xpadro.rest.ri.repository.TypeAItemRepository;

/**
 * Type A Subresource
 */
public class TypeAResource implements ItemResource {
	private int itemId;
	private TypeAItemRepository itemRepository = new TypeAItemRepository();

	public TypeAResource(int id) {
		this.itemId = id;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TypeAItem getTypeAItem() {
		TypeAItem item = itemRepository.retrieveItem(itemId);
		if (item == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return item;
	}

}
