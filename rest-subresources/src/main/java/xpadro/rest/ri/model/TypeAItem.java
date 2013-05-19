package xpadro.rest.ri.model;

public class TypeAItem extends Item {
	private static final long serialVersionUID = 1L;
	private String code;

	public TypeAItem() {}
	
	public TypeAItem(int id, float price, String code) {
		super(id, price, "A");
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	
}
