package xpadro.rest.ri.repository;

import java.util.HashMap;
import java.util.Map;

import xpadro.rest.ri.model.TypeAItem;

public class TypeAItemRepository {
	private Map<Integer, TypeAItem> items;
	
	public TypeAItemRepository() {
		items = new HashMap<Integer, TypeAItem>();
		items.put(11, new TypeAItem(11, 50.99f, "56FG84"));
		items.put(12, new TypeAItem(12, 35.50f, "12SS34"));
		items.put(13, new TypeAItem(13, 19.90f, "87HU75"));
	}
	
	public TypeAItem retrieveItem(int id) {
		return items.get(id);
	}
}
