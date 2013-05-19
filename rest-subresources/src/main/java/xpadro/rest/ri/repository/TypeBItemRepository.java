package xpadro.rest.ri.repository;

import java.util.HashMap;
import java.util.Map;

import xpadro.rest.ri.model.TypeBItem;

public class TypeBItemRepository {
	private Map<Integer, TypeBItem> items;
	
	public TypeBItemRepository() {
		items = new HashMap<Integer, TypeBItem>();
		items.put(1, new TypeBItem(1, 33.50f));
		items.put(2, new TypeBItem(2, 55.50f));
		items.put(3, new TypeBItem(3, 95.99f));
	}
	
	public TypeBItem retrieveItem(int id) {
		return items.get(id);
	}
}
